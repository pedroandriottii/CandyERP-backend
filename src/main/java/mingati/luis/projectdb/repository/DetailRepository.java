package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Detail;

@Repository
public class DetailRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Detail> findAll() {
    return jdbcTemplate.query("SELECT * FROM Detail", detailMapper);
  }

  public Detail findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM Detail WHERE id = ?", detailMapper, new Object[] { id });
  }

  public int save(Detail detail) {
    return jdbcTemplate.update("INSERT INTO Detail (description, additional_value) VALUES (?, ?)",
        new Object[] { detail.getDescription(), detail.getAdditionalValue() });
  }

  public int update(Detail detail) {
    return jdbcTemplate.update("UPDATE Detail SET description = ?, additional_value = ? WHERE id = ?",
        new Object[] { detail.getDescription(), detail.getAdditionalValue(), detail.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Detail WHERE id = ?", new Object[] { id });
  }

  public final RowMapper<Detail> detailMapper = (rs, rowNum) -> {
    Detail detail = new Detail();
    detail.setId(rs.getInt("id"));
    detail.setDescription(rs.getString("description"));
    detail.setAdditionalValue(rs.getDouble("additional_value"));
    return detail;
  };
}
