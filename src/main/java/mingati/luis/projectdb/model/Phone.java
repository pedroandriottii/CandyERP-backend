package mingati.luis.projectdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Phone {
  private int id;
  private String phone;
  private int fkClientId;

  public Phone() {
  }

  public Phone(int id, String phone, int fkClientId) {
    this.id = id;
    this.phone = phone;
    this.fkClientId = fkClientId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public int getFkClientId() {
    return fkClientId;
  }

  public void setFkClientId(int fkClientId) {
    this.fkClientId = fkClientId;
  }
}
