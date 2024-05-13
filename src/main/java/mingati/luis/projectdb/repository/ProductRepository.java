package mingati.luis.projectdb.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Product;
import mingati.luis.projectdb.model.Ingredient;

@Repository
public class ProductRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Product> findAll() {
    List<Product> products = jdbcTemplate.query("SELECT * FROM Product", ProductMapper);
    for (Product product : products) {
      List<Ingredient> ingredients = jdbcTemplate.query(
              "SELECT i.*, ip.quantity AS quantity " +
                      "FROM Ingredient i " +
                      "INNER JOIN Ingredient_Product ip ON i.id = ip.fk_Ingredient_Id " +
                      "WHERE ip.fk_Product_Id = ?",
              new Object[]{product.getId()},
              IngredientWithQuantityMapper
      );
      product.setIngredients(ingredients);
    }
    return products;
  }

  public Product findById(int id) {
    Product product = jdbcTemplate.queryForObject("SELECT * FROM Product WHERE id = ?", ProductMapper, id);
    if (product != null) {
      List<Ingredient> ingredients = jdbcTemplate.query(
              "SELECT i.*, ip.quantity AS quantity " +
                      "FROM Ingredient i " +
                      "INNER JOIN Ingredient_Product ip ON i.id = ip.fk_Ingredient_Id " +
                      "WHERE ip.fk_Product_Id = ?",
              new Object[]{id},
              IngredientWithQuantityMapper
      );
      product.setIngredients(ingredients);
    }
    return product;
  }

  public int save(Product product) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
              "INSERT INTO Product (name, price, quantity, fk_detail_id) VALUES (?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS
      );
      ps.setString(1, product.getName());
      ps.setDouble(2, product.getPrice());
      ps.setInt(3, product.getQuantity());
      ps.setInt(4, product.getFkDetailId());
      return ps;
    }, keyHolder);
    Number key = keyHolder.getKey();
    if (key != null) {
      product.setId(key.intValue());
    }
    return product.getId();
  }

  public void update(Product product) {
    jdbcTemplate.update(
            "UPDATE Product SET name = ?, price = ?, quantity = ?, fk_detail_id = ? WHERE id = ?",
            product.getName(), product.getPrice(), product.getQuantity(), product.getFkDetailId(), product.getId()
    );
  }

  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM Product WHERE id = ?", id);
  }

  private final RowMapper<Product> ProductMapper = (rs, rowNum) -> {
    Product product = new Product();
    product.setId(rs.getInt("id"));
    product.setName(rs.getString("name"));
    product.setPrice(rs.getDouble("price"));
    product.setQuantity(rs.getInt("quantity"));
    product.setFkDetailId(rs.getInt("fk_detail_id"));
    return product;
  };

  private final RowMapper<Ingredient> IngredientWithQuantityMapper = (rs, rowNum) -> {
    Ingredient ingredient = new Ingredient();
    ingredient.setId(rs.getInt("id"));
    ingredient.setName(rs.getString("name"));
    ingredient.setQuantity(rs.getInt("quantity"));
    ingredient.setMeasurementUnit(Ingredient.MeasurementUnit.valueOf(rs.getString("measurement_unit")));
    ingredient.setCost(rs.getDouble("cost"));
    return ingredient;
  };
}
