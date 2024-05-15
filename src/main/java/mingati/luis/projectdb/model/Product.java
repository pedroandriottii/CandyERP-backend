package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {
  private int id;
  private String name;
  private double price;
  private int quantity;
  @JsonProperty("fk_Detail_id")
  private int fkDetailId;
  private List<Ingredient> ingredients;

  // Getters e setters

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

  public int getFkDetailId() {
    return fkDetailId;
  }

  public void setFkDetailId(int fkDetailId) {
    this.fkDetailId = fkDetailId;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
