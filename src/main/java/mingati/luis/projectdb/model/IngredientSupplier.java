package mingati.luis.projectdb.model;

public class IngredientSupplier {
  private int fk_ingredient_id;
  private int fk_supplier_id;

  public IngredientSupplier() {
  }

  public IngredientSupplier(int fk_ingredient_id, int fk_supplier_id) {
    this.fk_ingredient_id = fk_ingredient_id;
    this.fk_supplier_id = fk_supplier_id;
  }

  public int getFkIngredientId() {
    return fk_ingredient_id;
  }

  public void setFkIngredientId(int fk_ingredient_id) {
    this.fk_ingredient_id = fk_ingredient_id;
  }

  public int getFkSupplierId() {
    return fk_supplier_id;
  }

  public void setFkSupplierId(int fk_supplier_id) {
    this.fk_supplier_id = fk_supplier_id;
  }
}
