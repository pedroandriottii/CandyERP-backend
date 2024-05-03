package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientProduct {
  @JsonProperty("fk_Product_id")
  private int fk_product_id;

  @JsonProperty("fk_Ingredient_id")
  private int fk_ingredient_id;
  private double quantity;

  public IngredientProduct() {
  }

  public IngredientProduct(int fk_product_id, int fk_ingredient_id, double quantity) {
    this.fk_product_id = fk_product_id;
    this.fk_ingredient_id = fk_ingredient_id;
    this.quantity = quantity;
  }

  public int getFkProductId() {
    return fk_product_id;
  }

  public void setFkProductId(int fk_product_id) {
    this.fk_product_id = fk_product_id;
  }

  public int getFkIngredientId() {
    return fk_ingredient_id;
  }

  public void setFkIngredientId(int fk_ingredient_id) {
    this.fk_ingredient_id = fk_ingredient_id;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }
}
