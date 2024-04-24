package mingati.luis.projectdb.model;

public class Supplier {
  private int id;
  private String name;
  private String cnpj;

  public Supplier() {
  }

  public Supplier(int id, String name, String cnpj) {
    this.id = id;
    this.name = name;
    this.cnpj = cnpj;
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

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }
}
