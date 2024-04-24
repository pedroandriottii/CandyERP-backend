package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Product;

@Repository
public class ProductRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Product> findAll() {
    return jdbcTemplate.query("SELECT * FROM Product", productMapper);
  }

  public Product findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM Product WHERE id = ?", productMapper, new Object[] { id });
  }

  public int save(Product product) {
    return jdbcTemplate.update("INSERT INTO Product (price, name, quantity, fk_detail_id) VALUES (?, ?, ?, ?)",
        new Object[] { product.getPrice(), product.getName(), product.getQuantity(), product.getFkDetailId() });
  }

  public int update(Product product) {
    return jdbcTemplate.update("UPDATE Product SET price = ?, name = ?, quantity = ?, fk_detail_id = ? WHERE id = ?",
        new Object[] { product.getPrice(), product.getName(), product.getQuantity(), product.getFkDetailId(),
            product.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Product WHERE id = ?", new Object[] { id });
  }

  public final RowMapper<Product> productMapper = (rs, rowNum) -> {
    Product product = new Product();
    product.setId(rs.getInt("id"));
    product.setPrice(rs.getDouble("price"));
    product.setName(rs.getString("name"));
    product.setQuantity(rs.getInt("quantity"));
    product.setFkDetailId(rs.getInt("fk_detail_id"));
    return product;
  };
}
