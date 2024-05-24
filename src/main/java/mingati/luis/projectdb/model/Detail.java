package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Detail {
  private int id;
  private String description;
  @JsonProperty("additional_value")
  private double additionalValue;

  public Detail() {
  }

  public Detail(int id, String description, double additional_value) {
    this.id = id;
    this.description = description;
    this.additionalValue = additional_value;
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
    return additionalValue;
  }

  public void setAdditionalValue(double additional_value) {
    this.additionalValue = additional_value;
  }
}
