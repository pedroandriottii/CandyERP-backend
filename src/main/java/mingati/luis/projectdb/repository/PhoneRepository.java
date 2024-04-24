package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
    return jdbcTemplate.queryForObject("SELECT * FROM Phone WHERE id = ?", useMapper, new Object[] { id });
  }

  public int save(Phone phone) {
    return jdbcTemplate.update("INSERT INTO Phone (phone, fk_Client_id) VALUES (?, ?)",
        new Object[] { phone.getPhone(), phone.getFk_Client_id() });
  }

  public int update(Phone phone) {
    return jdbcTemplate.update("UPDATE Phone SET phone = ?, fk_Client_id = ? WHERE id = ?",
        new Object[] { phone.getPhone(), phone.getFk_Client_id(), phone.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Phone WHERE id = ?", new Object[] { id });
  }

  public final RowMapper<Phone> useMapper = (rs, rowNum) -> {
    Phone phone = new Phone();
    phone.setId(rs.getInt("id"));
    phone.setPhone(rs.getString("phone"));
    phone.setFk_Client_id(rs.getInt("fk_Client_id"));
    return phone;
  };

}
