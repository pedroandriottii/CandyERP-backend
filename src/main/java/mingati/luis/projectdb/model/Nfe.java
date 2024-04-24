package mingati.luis.projectdb.model;

public class Nfe {
  private int id;
  private String serial_number;

  public Nfe() {
  }

  public Nfe(String serial_number) {
    this.serial_number = serial_number;
  }

  public Nfe(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSerial_number() {
    return serial_number;
  }

  public void setSerial_number(String serial_number) {
    this.serial_number = serial_number;
  }
}
