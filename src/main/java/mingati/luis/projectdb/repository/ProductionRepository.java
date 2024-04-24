package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Production;

@Repository
public class ProductionRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Production> findAll() {
    return jdbcTemplate.query("SELECT * FROM Production", useMapper);
  }

  public Production findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM Production WHERE id = ?", useMapper, new Object[] { id });
  }

  public int save(Production production) {
    return jdbcTemplate.update("INSERT INTO Production (id, start_date, end_date) VALUES (?, ?, ?)",
        new Object[] { production.getId(), production.getStart_date(),
            production.getEnd_date() });
  }

  public int update(Production production) {
    return jdbcTemplate.update("UPDATE Production SET start_date = ?, end_date = ? WHERE id = ?",
        new Object[] { production.getStart_date(), production.getEnd_date(), production.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Production WHERE id = ?", new Object[] { id });
  }

  private final RowMapper<Production> useMapper = (rs, rowNum) -> {
    Production production = new Production();
    production.setId(rs.getInt("id"));
    production.setStart_date(rs.getDate("start_date"));
    production.setEnd_date(rs.getDate("end_date"));
    return production;
  };
}
