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

import mingati.luis.projectdb.model.Ingredient;

@Repository
public class IngredientRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Ingredient> findAll() {
    return jdbcTemplate.query("SELECT * FROM Ingredient", IngredientMapper);
  }

  public Ingredient findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM Ingredient WHERE id = ?", IngredientMapper, new Object[] { id });
  }

  public int save(Ingredient ingredient) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
          "INSERT INTO Ingredient (name, quantity, measurement_unit) VALUES (?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, ingredient.getName());
      ps.setInt(2, ingredient.getQuantity());
      ps.setString(3, ingredient.getMeasurementUnit().name());
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
        "UPDATE Ingredient SET name = ?, quantity = ?, measurement_unit = ? WHERE id = ?",
        new Object[] { ingredient.getName(), ingredient.getQuantity(), ingredient.getMeasurementUnit().name(),
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
    ingredient.setMeasurementUnit(Ingredient.MeasurementUnit.valueOf(rs.getString("MEASUREMENT_UNIT")));
    return ingredient;
  };

}