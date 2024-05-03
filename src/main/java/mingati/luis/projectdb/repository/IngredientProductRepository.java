package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.IngredientProduct;

@Repository
public class IngredientProductRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<IngredientProduct> findAll() {
    return jdbcTemplate.query("SELECT fk_product_id, fk_ingredient_id, quantity FROM Ingredient_Product",
        IngredientProductMapper);
  }

  public IngredientProduct findByProductIdAndIngredientId(int fk_product_id, int fk_ingredient_id) {
    return jdbcTemplate.queryForObject(
        "SELECT fk_product_id, fk_ingredient_id, quantity FROM Ingredient_Product WHERE fk_product_id = ? AND fk_ingredient_id = ?",
        IngredientProductMapper, new Object[] { fk_product_id, fk_ingredient_id });
  }

  public int save(IngredientProduct ingredientProduct) {
    return jdbcTemplate.update(
        "INSERT INTO Ingredient_Product (fk_product_id, fk_ingredient_id, quantity) VALUES (?, ?, ?)",
        new Object[] { ingredientProduct.getFkProductId(), ingredientProduct.getFkIngredientId(),
            ingredientProduct.getQuantity() });
  }

  public int update(IngredientProduct ingredientProduct) {
    return jdbcTemplate.update(
        "UPDATE Ingredient_Product SET quantity = ? WHERE fk_product_id = ? AND fk_ingredient_id = ?",
        new Object[] { ingredientProduct.getQuantity(), ingredientProduct.getFkProductId(),
            ingredientProduct.getFkIngredientId() });
  }

  public int deleteByProductIdAndIngredientId(int fk_product_id, int fk_ingredient_id) {
    return jdbcTemplate.update("DELETE FROM Ingredient_Product WHERE fk_product_id = ? AND fk_ingredient_id = ?",
        new Object[] { fk_product_id, fk_ingredient_id });
  }

  public final RowMapper<IngredientProduct> IngredientProductMapper = (rs, rowNum) -> {
    IngredientProduct ingredientProduct = new IngredientProduct();
    ingredientProduct.setFkProductId(rs.getInt("fk_Product_id"));
    ingredientProduct.setFkIngredientId(rs.getInt("fk_Ingredient_id"));
    ingredientProduct.setQuantity(rs.getDouble("quantity"));
    return ingredientProduct;
  };

}
