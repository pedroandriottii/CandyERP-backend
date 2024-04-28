package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.IngredientSupplier;

@Repository
public class IngredientSupplierRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<IngredientSupplier> findAll() {
    return jdbcTemplate.query("SELECT fk_supplier_id, fk_ingredient_id FROM Ingredient_Supplier",
        IngredientSupplierMapper);
  }

  public IngredientSupplier findBySupplierIdAndIngredientId(int fk_supplier_id, int fk_ingredient_id) {
    return jdbcTemplate.queryForObject(
        "SELECT fk_supplier_id, fk_ingredient_id FROM Ingredient_Supplier WHERE fk_supplier_id = ? AND fk_ingredient_id = ?",
        IngredientSupplierMapper, new Object[] { fk_supplier_id, fk_ingredient_id });
  }

  public int save(IngredientSupplier ingredientSupplier) {
    System.out.println(ingredientSupplier.getFkIngredientId() + ingredientSupplier.getFkSupplierId());
    return jdbcTemplate.update(
        "INSERT INTO Ingredient_Supplier (fk_Ingredient_Id, fk_Supplier_Id) VALUES (?, ?)",
        new Object[] { ingredientSupplier.getFkIngredientId(), ingredientSupplier.getFkSupplierId() });
  }

  public int update(IngredientSupplier ingredientSupplier) {
    return jdbcTemplate.update(
        "UPDATE Ingredient_Supplier SET fk_ingredient_id = ? WHERE fk_supplier_id = ?",
        new Object[] { ingredientSupplier.getFkIngredientId(), ingredientSupplier.getFkSupplierId() });
  }

  public int deleteBySupplierIdAndIngredientId(int fk_supplier_id, int fk_ingredient_id) {
    return jdbcTemplate.update("DELETE FROM Ingredient_Supplier WHERE fk_supplier_id = ? AND fk_ingredient_id = ?",
        new Object[] { fk_supplier_id, fk_ingredient_id });
  }

  public final RowMapper<IngredientSupplier> IngredientSupplierMapper = (rs, rowNum) -> {
    IngredientSupplier ingredientSupplier = new IngredientSupplier();
    ingredientSupplier.setFkSupplierId(rs.getInt("fk_supplier_id"));
    ingredientSupplier.setFkIngredientId(rs.getInt("fk_ingredient_id"));
    return ingredientSupplier;
  };
}