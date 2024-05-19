package mingati.luis.projectdb.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Phone;

@Repository
public class PhoneRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Phone> findAll() {
    return jdbcTemplate.query("SELECT * FROM Phone", useMapper);
  }

  public Phone findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM Phone WHERE id = ?", useMapper, id);
  }

  public int save(Phone phone) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO Phone (phone, fk_Client_id) VALUES (?, ?)";
    
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
      ps.setString(1, phone.getPhone());
      ps.setInt(2, phone.getFkClientId());
      return ps;
    }, keyHolder);

    phone.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    return keyHolder.getKey().intValue();
  }

  public int update(Phone phone) {
    return jdbcTemplate.update("UPDATE Phone SET phone = ?, fk_Client_id = ? WHERE id = ?",
        phone.getPhone(), phone.getFkClientId(), phone.getId());
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Phone WHERE id = ?", id);
  }

  public final RowMapper<Phone> useMapper = (rs, rowNum) -> {
    Phone phone = new Phone();
    phone.setId(rs.getInt("id"));
    phone.setPhone(rs.getString("phone"));
    phone.setFkClientId(rs.getInt("fk_Client_id"));
    return phone;
  };
}
