package mingati.luis.projectdb.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import mingati.luis.projectdb.model.IngredientUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Ingredient;
import mingati.luis.projectdb.model.Supplier;

@Repository
public class IngredientRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Ingredient> findAll() {
    List<Ingredient> ingredients = jdbcTemplate.query("SELECT * FROM Ingredient", IngredientMapper);
    for (Ingredient ingredient : ingredients) {
      List<Supplier> suppliers = jdbcTemplate.query(
              "SELECT s.* FROM Supplier s INNER JOIN Ingredient_Supplier isupp ON s.id = isupp.fk_Supplier_Id WHERE isupp.fk_Ingredient_Id = ?",
              SupplierMapper,
              new Object[] { ingredient.getId() }
      );
      ingredient.setSuppliers(suppliers);
    }
    return ingredients;
  }

  public List<Ingredient> getIngredientsByStock(String orderBy) {
    String sql = "SELECT * FROM Ingredient ORDER BY quantity " + ("desc".equalsIgnoreCase(orderBy) ? "DESC" : "ASC");
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      Ingredient ingredient = new Ingredient();
      ingredient.setId(rs.getInt("id"));
      ingredient.setName(rs.getString("name"));
      ingredient.setQuantity(rs.getInt("quantity"));
      ingredient.setCost(rs.getDouble("cost"));
      ingredient.setMeasurementUnit(Ingredient.MeasurementUnit.valueOf(rs.getString("measurement_unit")));
      return ingredient;
    });
  }

  public List<IngredientUsage> getMostUsedIngredients() {
    String sql = "SELECT i.id, i.name, i.measurement_unit, COUNT(ip.fk_Ingredient_id) AS usage_count " +
            "FROM Ingredient_Product ip " +
            "JOIN Ingredient i ON ip.fk_Ingredient_id = i.id " +
            "GROUP BY i.id, i.name, i.measurement_unit " +
            "ORDER BY usage_count DESC";
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      IngredientUsage ingredientUsage = new IngredientUsage();
      ingredientUsage.setId(rs.getInt("id"));
      ingredientUsage.setName(rs.getString("name"));
      ingredientUsage.setMeasurementUnit(rs.getString("measurement_unit"));
      ingredientUsage.setTotalUsed(rs.getInt("usage_count"));
      return ingredientUsage;
    });
  }


  public Ingredient findById(int id) {
    Ingredient ingredient = jdbcTemplate.queryForObject("SELECT * FROM Ingredient WHERE id = ?", IngredientMapper, new Object[] { id });
    if (ingredient != null) {
      List<Supplier> suppliers = jdbcTemplate.query(
              "SELECT s.* FROM Supplier s INNER JOIN Ingredient_Supplier isupp ON s.id = isupp.fk_Supplier_Id WHERE isupp.fk_Ingredient_Id = ?",
              SupplierMapper,
              new Object[] { id }
      );
      ingredient.setSuppliers(suppliers);
    }
    return ingredient;
  }

  public int save(Ingredient ingredient) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
              "INSERT INTO Ingredient (name, quantity, measurement_unit, cost) VALUES (?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, ingredient.getName());
      ps.setInt(2, ingredient.getQuantity());
      ps.setString(3, ingredient.getMeasurementUnit().name());
      ps.setDouble(4, ingredient.getCost());
      return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    if (key != null) {
      ingredient.setId(key.intValue());
    }

    return ingredient.getId();
  }

  public void update(Ingredient ingredient) {
    jdbcTemplate.update(
            "UPDATE Ingredient SET name = ?, quantity = ?, measurement_unit = ?, cost = ? WHERE id = ?",
            new Object[] { ingredient.getName(), ingredient.getQuantity(), ingredient.getMeasurementUnit().name(), ingredient.getCost(),
                    ingredient.getId() });
  }

  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM Ingredient_Product WHERE fk_Ingredient_id = ?", new Object[] { id });
    jdbcTemplate.update("DELETE FROM Ingredient_Supplier WHERE fk_Ingredient_id = ?", new Object[] { id });

    jdbcTemplate.update("DELETE FROM Ingredient WHERE id = ?", new Object[] { id });
  }

  private final RowMapper<Ingredient> IngredientMapper = (rs, rowNum) -> {
    Ingredient ingredient = new Ingredient();
    ingredient.setId(rs.getInt("id"));
    ingredient.setName(rs.getString("name"));
    ingredient.setQuantity(rs.getInt("quantity"));
    ingredient.setMeasurementUnit(Ingredient.MeasurementUnit.valueOf(rs.getString("measurement_unit")));
    ingredient.setCost(rs.getDouble("cost"));
    return ingredient;
  };

  private final RowMapper<Supplier> SupplierMapper = (rs, rowNum) -> {
    Supplier supplier = new Supplier();
    supplier.setId(rs.getInt("id"));
    supplier.setName(rs.getString("name"));
    supplier.setCnpj(rs.getString("cnpj"));
    return supplier;
  };
}
