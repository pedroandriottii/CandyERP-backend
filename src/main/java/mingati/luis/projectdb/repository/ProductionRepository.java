package mingati.luis.projectdb.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Production;
import mingati.luis.projectdb.model.Product;

@Repository
public class ProductionRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Production> findAll() {
    List<Production> productions = jdbcTemplate.query("SELECT * FROM Production", productionMapper);
    for (Production production : productions) {
      List<Product> products = findProductsByProductionId(production.getId());
      production.setProducts(products);
    }
    return productions;
  }
  public Production findById(int id) {
    Production production = jdbcTemplate.queryForObject("SELECT * FROM Production WHERE id = ?", productionMapper, id);
    if (production != null) {
      List<Product> products = findProductsByProductionId(production.getId());
      production.setProducts(products);
    }
    return production;
  }

  public int save(Production production) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
              "INSERT INTO Production (start_date, end_date, name) VALUES (?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS);
      ps.setDate(1, new java.sql.Date(production.getStart_date().getTime()));
      ps.setDate(2, new java.sql.Date(production.getEnd_date().getTime()));
      ps.setString(3, production.getName());
      return ps;
    }, keyHolder);

    if (keyHolder.getKey() != null) {
      production.setId(keyHolder.getKey().intValue());
    }
    return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
  }

  public int update(Production production) {
    return jdbcTemplate.update("UPDATE Production SET start_date = ?, end_date = ?, name = ? WHERE id = ?",
            new Object[] { production.getStart_date(), production.getEnd_date(), production.getName(), production.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Production WHERE id = ?", new Object[] { id });
  }

  private final RowMapper<Production> productionMapper = (rs, rowNum) -> {
    Production production = new Production();
    production.setId(rs.getInt("id"));
    production.setStart_date(rs.getDate("start_date"));
    production.setEnd_date(rs.getDate("end_date"));
    production.setName(rs.getString("name"));
    return production;
  };

  private final RowMapper<Product> productMapper = (rs, rowNum) -> {
    Product product = new Product();
    product.setId(rs.getInt("id"));
    product.setName(rs.getString("name"));
    product.setPrice(rs.getDouble("price"));
    product.setQuantity(rs.getInt("quantity"));
    product.setFkDetailId(rs.getInt("fk_detail_id"));
    return product;
  };


  private List<Product> findProductsByProductionId(int productionId) {
    List<Product> products = jdbcTemplate.query(
            "SELECT p.* FROM Product p " +
                    "INNER JOIN Production_Product pp ON p.id = pp.fk_Product_id " +
                    "WHERE pp.fk_Production_id = ?",
            new Object[]{productionId},
            productMapper
    );
    return products;
  }
}
