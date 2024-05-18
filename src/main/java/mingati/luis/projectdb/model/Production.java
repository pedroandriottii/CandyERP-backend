package mingati.luis.projectdb.model;

import java.util.Date;
import java.util.List;



public class Production {
  public enum ProductionStatus {
    IN_PROGRESS,
    COMPLETED
  }
  private int id;

  private String name;
  private Date start_date;
  private Date end_date;
  private ProductionStatus status;

  private List<Product> products;

  public Production() {
  }

  public Production(String name, int id, Date start_date, Date end_date, ProductionStatus status) {
    this.id = id;
    this.name = name;
    this.start_date = start_date;
    this.end_date = end_date;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public Date getStart_date() {
    return start_date;
  }

  public void setStart_date(Date start_date) {
    this.start_date = start_date;
  }

  public Date getEnd_date() {
    return end_date;
  }

  public void setEnd_date(Date end_date) {
    this.end_date = end_date;
  }

  public ProductionStatus getStatus() {
    return status;
  }

  public void setStatus(ProductionStatus status) {
    this.status = status;
  }

  public List<Product> getProducts() { return products; }

  public void setProducts(List<Product> products) { this.products = products; }
}
