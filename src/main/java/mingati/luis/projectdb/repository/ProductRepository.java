package mingati.luis.projectdb.repository;

import mingati.luis.projectdb.model.BestSellProducts;
import mingati.luis.projectdb.model.Ingredient;
import mingati.luis.projectdb.model.LoyalCustomers;
import mingati.luis.projectdb.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
public class ProductRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Product> findAll() {
    List<Product> products = jdbcTemplate.query("SELECT * FROM Product", new ProductRowMapper());
    for (Product product : products) {
      List<Ingredient> ingredients = jdbcTemplate.query(
              "SELECT i.*, ip.quantity AS used_quantity " +
                      "FROM Ingredient i " +
                      "INNER JOIN Ingredient_Product ip ON i.id = ip.fk_Ingredient_Id " +
                      "WHERE ip.fk_Product_Id = ?",
              new Object[]{product.getId()},
              new IngredientWithQuantityRowMapper()
      );
      product.setIngredients(ingredients);
    }
    return products;
  }

  public Product findById(int id) {
    Product product = jdbcTemplate.queryForObject("SELECT * FROM Product WHERE id = ?", new ProductRowMapper(), id);
    if (product != null) {
      List<Ingredient> ingredients = jdbcTemplate.query(
              "SELECT i.*, ip.quantity AS used_quantity " +
                      "FROM Ingredient i " +
                      "INNER JOIN Ingredient_Product ip ON i.id = ip.fk_Ingredient_Id " +
                      "WHERE ip.fk_Product_Id = ?",
              new Object[]{id},
              new IngredientWithQuantityRowMapper()
      );
      product.setIngredients(ingredients);
    }
    return product;
  }

  public List<BestSellProducts> getBestSellingProducts() {
    String sql = "SELECT p.id, p.name, SUM(pds.quantity) AS total_quantity_sold " +
            "FROM Product p " +
            "JOIN Product_Detail_Sale pds ON p.id = pds.fk_Product_id " +
            "GROUP BY p.id, p.name " +
            "ORDER BY total_quantity_sold DESC";

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      int totalQuantitySold = rs.getInt("total_quantity_sold");
      return new BestSellProducts(id, name, totalQuantitySold);
    });
  }

  public List<BestSellProducts> getBestSellingProductsByOrderType(String orderType) {
    YearMonth currentMonth = YearMonth.now();
    LocalDate startOfMonth = currentMonth.atDay(1);
    LocalDate endOfMonth = currentMonth.atEndOfMonth();

    String sql = "SELECT p.id, p.name, SUM(pds.quantity) AS total_quantity_sold " +
            "FROM Product p " +
            "JOIN Product_Detail_Sale pds ON p.id = pds.fk_Product_id " +
            "JOIN Sale_Order so ON pds.fk_Sale_Order_id = so.id " +
            "WHERE so.order_type = ? AND so.date BETWEEN ? AND ? " +
            "GROUP BY p.id, p.name " +
            "ORDER BY total_quantity_sold DESC";

    return jdbcTemplate.query(sql, new Object[]{orderType, startOfMonth, endOfMonth}, (rs, rowNum) -> {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      int totalQuantitySold = rs.getInt("total_quantity_sold");
      return new BestSellProducts(id, name, totalQuantitySold);
    });
  }

  public List<LoyalCustomers> getLoyalCustomers() {
    String sql = "SELECT c.id, c.name, COUNT(so.id) AS total_orders " +
            "FROM Client c " +
            "JOIN Sale_Order so ON c.id = so.fk_Client_id " +
            "GROUP BY c.id, c.name " +
            "ORDER BY total_orders DESC";

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      int id = rs.getInt("id");
      String name = rs.getString("name");
      int totalOrders = rs.getInt("total_orders");
      return new LoyalCustomers(id, name, totalOrders);
    });
  }

  public int save(Product product) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
              "INSERT INTO Product (name, price, quantity, fk_product_id) VALUES (?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS
      );
      ps.setString(1, product.getName());
      ps.setDouble(2, product.getPrice());
      ps.setInt(3, product.getQuantity());
      if (product.getFkProductId() != null) {
        ps.setInt(4, product.getFkProductId());
      } else {
        ps.setNull(4, java.sql.Types.INTEGER);
      }
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
            "UPDATE Product SET name = ?, price = ?, quantity = ?, fk_product_id = ? WHERE id = ?",
            product.getName(), product.getPrice(), product.getQuantity(),
            product.getFkProductId(), product.getId()
    );
  }

  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM Product WHERE id = ?", id);
  }

  private static final class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException, SQLException {
      Product product = new Product();
      product.setId(rs.getInt("id"));
      product.setName(rs.getString("name"));
      product.setPrice(rs.getDouble("price"));
      product.setQuantity(rs.getInt("quantity"));
      product.setFkProductId((Integer) rs.getObject("fk_product_id"));
      return product;
    }
  }

  private static final class IngredientWithQuantityRowMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
      Ingredient ingredient = new Ingredient();
      ingredient.setId(rs.getInt("id"));
      ingredient.setName(rs.getString("name"));
      ingredient.setQuantity(rs.getInt("used_quantity")); // Quantidade usada no produto
      ingredient.setMeasurementUnit(Ingredient.MeasurementUnit.valueOf(rs.getString("measurement_unit")));
      ingredient.setCost(rs.getDouble("cost"));
      return ingredient;
    }
  }
}
