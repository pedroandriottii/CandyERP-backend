package mingati.luis.projectdb.model;

import java.util.Date;

public class Production {
  private int id;
  private Date start_date;
  private Date end_date;

  public Production() {
  }

  public Production(int id, Date start_date, Date end_date) {
    this.id = id;
    this.start_date = start_date;
    this.end_date = end_date;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

}
