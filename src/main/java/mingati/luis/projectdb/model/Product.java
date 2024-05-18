package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {
  private int id;
  private String name;
  private double price;
  private int quantity;
  @JsonProperty("fk_product_id")
  private Integer fkProductId;
  private List<Ingredient> ingredients;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Integer getFkProductId() {
    return fkProductId;
  }

  public void setFkProductId(Integer fkProductId) {
    this.fkProductId = fkProductId;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
