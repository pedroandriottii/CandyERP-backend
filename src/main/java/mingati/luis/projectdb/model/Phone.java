package mingati.luis.projectdb.model;

public class Phone {
  private int id;
  private String phone;
  private int fk_Client_id;

  public Phone() {
  }

  public Phone(int id, String phone, int fk_Client_id) {
    this.id = id;
    this.phone = phone;
    this.fk_Client_id = fk_Client_id;
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

  public int getFk_Client_id() {
    return fk_Client_id;
  }

  public void setFk_Client_id(int fk_Client_id) {
    this.fk_Client_id = fk_Client_id;
  }

}
