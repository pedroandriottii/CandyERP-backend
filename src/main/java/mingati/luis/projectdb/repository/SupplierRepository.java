package mingati.luis.projectdb.repository;

import mingati.luis.projectdb.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SupplierRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Supplier> findAll() {
    return jdbcTemplate.query("SELECT * FROM Supplier", useMapper);
  }

  public Supplier findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM Supplier WHERE id = ?", useMapper, new Object[] { id });
  }

  public int save(Supplier supplier) {
    return jdbcTemplate.update("INSERT INTO Supplier (name, cnpj) VALUES (?, ?)",
        new Object[] { supplier.getName(), supplier.getCnpj() });
  }

  public int update(Supplier supplier) {
    return jdbcTemplate.update("UPDATE Supplier SET name = ?, cnpj = ? WHERE id = ?",
        new Object[] { supplier.getName(), supplier.getCnpj(), supplier.getId() });
  }

  @Transactional
  public void deleteById(int supplierId) {
    jdbcTemplate.update("DELETE FROM Ingredient_Supplier WHERE fk_Supplier_Id = ?", supplierId);

    jdbcTemplate.update("DELETE FROM Supplier WHERE id = ?", supplierId);
  }

  private final RowMapper<Supplier> useMapper = (rs, rowNum) -> {
    Supplier supplier = new Supplier();
    supplier.setId(rs.getInt("id"));
    supplier.setName(rs.getString("name"));
    supplier.setCnpj(rs.getString("cnpj"));
    return supplier;
  };
}
