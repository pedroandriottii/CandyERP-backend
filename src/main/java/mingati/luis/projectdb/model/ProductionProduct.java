package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionProduct {
  @JsonProperty("fk_Product_id")
  private int fk_product_id;
  @JsonProperty("fk_Production_id")
  private int fk_production_id;
  private int quantity;

  public ProductionProduct() {
  }

  public ProductionProduct(int fk_product_id, int fk_production_id, int quantity) {
    this.fk_product_id = fk_product_id;
    this.fk_production_id = fk_production_id;
    this.quantity = quantity;
  }

  public int getFkProductId() {
    return fk_product_id;
  }

  public void setFkProductId(int fk_product_id) {
    this.fk_product_id = fk_product_id;
  }

  public int getFkProductionId() {
    return fk_production_id;
  }

  public void setFkProductionId(int fk_production_id) {
    this.fk_production_id = fk_production_id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
