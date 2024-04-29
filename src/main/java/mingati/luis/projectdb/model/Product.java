package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
  private int id;
  private double price;
  private String name;
  private int quantity;
  @JsonProperty("fk_detail_id")
  private int fk_detail_id;

  public Product() {
  }

  public Product(int id, double price, String name, int quantity, int fk_detail_id) {
    this.id = id;
    this.price = price;
    this.name = name;
    this.quantity = quantity;
    this.fk_detail_id = fk_detail_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getFkDetailId() {
    return fk_detail_id;
  }

  public void setFkDetailId(int fk_detail_id) {
    this.fk_detail_id = fk_detail_id;
  }
}
