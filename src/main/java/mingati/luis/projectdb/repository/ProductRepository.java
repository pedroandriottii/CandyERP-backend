package mingati.luis.projectdb.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

  public Product save(Product product) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    PreparedStatementCreator preparedStatementCreator = connection -> {
      PreparedStatement ps = connection.prepareStatement(
              "INSERT INTO Product (price, name, quantity, fk_detail_id) VALUES (?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS);
      ps.setDouble(1, product.getPrice());
      ps.setString(2, product.getName());
      ps.setInt(3, product.getQuantity());
      ps.setInt(4, product.getFkDetailId());
      return ps;
    };

    jdbcTemplate.update(preparedStatementCreator, keyHolder);

    if (keyHolder.getKey() != null) {
      product.setId(keyHolder.getKey().intValue());
    }

    return product;
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
