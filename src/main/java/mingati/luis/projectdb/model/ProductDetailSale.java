package mingati.luis.projectdb.model;

public class ProductDetailSale {
  private int fk_product_id;
  private int fk_sale_order_id;
  private int fk_detail_id;
  private int quantity;

  public ProductDetailSale() {
  }

  public ProductDetailSale(int fk_product_id, int fk_sale_order_id, int fk_detail_id, int quantity) {
    this.fk_product_id = fk_product_id;
    this.fk_sale_order_id = fk_sale_order_id;
    this.fk_detail_id = fk_detail_id;
    this.quantity = quantity;
  }

  public int getFkProductId() {
    return fk_product_id;
  }

  public void setFkProductId(int fk_product_id) {
    this.fk_product_id = fk_product_id;
  }

  public int getFkSaleOrderId() {
    return fk_sale_order_id;
  }

  public void setFkSaleOrderId(int fk_sale_order_id) {
    this.fk_sale_order_id = fk_sale_order_id;
  }

  public int getFkDetailId() {
    return fk_detail_id;
  }

  public void setFkDetailId(int fk_detail_id) {
    this.fk_detail_id = fk_detail_id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
