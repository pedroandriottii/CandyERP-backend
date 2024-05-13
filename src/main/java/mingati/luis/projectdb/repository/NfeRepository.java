package mingati.luis.projectdb.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Nfe;

@Repository
public class NfeRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Nfe> findAll() {
    return jdbcTemplate.query("SELECT * FROM NFE", useMapper);
  }

  public Nfe findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM NFE WHERE id = ?", useMapper, new Object[] { id });
  }

  public int save(Nfe nfe) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    PreparedStatementCreator preparedStatementCreator = connection -> {
      PreparedStatement ps = connection.prepareStatement(
              "INSERT INTO NFE (serial_number) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, nfe.getSerial_number());
      return ps;
    };

    jdbcTemplate.update(preparedStatementCreator, keyHolder);
    int generatedId = Objects.requireNonNull(keyHolder.getKey()).intValue();
    System.out.println("Generated ID: " + generatedId);
    nfe.setId(generatedId);
    return generatedId;
  }

  public int update(Nfe nfe) {
    return jdbcTemplate.update("UPDATE NFE SET serial_number = ? WHERE id = ?",
        new Object[] { nfe.getSerial_number(), nfe.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM NFE WHERE id = ?", new Object[] { id });
  }

  private final RowMapper<Nfe> useMapper = (rs, rowNum) -> {
    Nfe nfe = new Nfe();
    nfe.setId(rs.getInt("id"));
    nfe.setSerial_number(rs.getString("serial_number"));
    return nfe;
  };
}
