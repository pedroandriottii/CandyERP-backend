package mingati.luis.projectdb.model;

public class Detail {
  private int id;
  private String description;
  private double additional_value;

  public Detail() {
  }

  public Detail(int id, String description, double additional_value) {
    this.id = id;
    this.description = description;
    this.additional_value = additional_value;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getAdditionalValue() {
    return additional_value;
  }

  public void setAdditionalValue(double additional_value) {
    this.additional_value = additional_value;
  }
}
