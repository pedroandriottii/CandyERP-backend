package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientSupplier {
  @JsonProperty("fk_Ingredient_Id")
  private int fk_Ingredient_Id;

  @JsonProperty("fk_Supplier_Id")
  private int fk_Supplier_Id;

  public IngredientSupplier() {
  }

  public IngredientSupplier(int fk_ingredient_id, int fk_supplier_id) {
    this.fk_Ingredient_Id = fk_ingredient_id;
    this.fk_Supplier_Id = fk_supplier_id;
  }

  public int getFkIngredientId() {
    return fk_Ingredient_Id;
  }

  public void setFkIngredientId(int fk_ingredient_id) {
    this.fk_Ingredient_Id = fk_ingredient_id;
  }

  public int getFkSupplierId() {
    return fk_Supplier_Id;
  }

  public void setFkSupplierId(int fk_supplier_id) {
    this.fk_Supplier_Id = fk_supplier_id;
  }
}