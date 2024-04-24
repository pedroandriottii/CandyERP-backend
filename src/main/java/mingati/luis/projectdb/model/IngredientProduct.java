package mingati.luis.projectdb.model;

public class IngredientProduct {
  private int fk_product_id;
  private int fk_ingredient_id;
  private int quantity;

  public IngredientProduct() {
  }

  public IngredientProduct(int fk_product_id, int fk_ingredient_id, int quantity) {
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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
