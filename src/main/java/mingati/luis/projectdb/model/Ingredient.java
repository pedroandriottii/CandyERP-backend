package mingati.luis.projectdb.model;

public class Ingredient {
  public int id;
  public String name;
  public int quantity;
  public MeasurementUnit measurement_unit;
  public double cost;

  public enum MeasurementUnit {
    GRAM,
    KILOGRAM,
    LITER,
    MILLILITER,
    UNIT
  }

  public Ingredient() {

  }

  public Ingredient(int id, String name, int quantity, MeasurementUnit measurement_unit, double cost) {
    this.id = id;
    this.name = name;
    this.quantity = quantity;
    this.measurement_unit = measurement_unit;
    this.cost = cost;
  }

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

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public MeasurementUnit getMeasurementUnit() {
    return measurement_unit;
  }

  public void setMeasurementUnit(MeasurementUnit measurement_unit) {
    this.measurement_unit = measurement_unit;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }
}
