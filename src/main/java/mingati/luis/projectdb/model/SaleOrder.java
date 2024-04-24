package mingati.luis.projectdb.model;

import java.util.Date;

public class SaleOrder {
  private int id;
  private Date date;
  private double total_price;
  private Status status;
  private OrderType order_type;
  private PaymentMethod payment_method;
  private int fk_client_id;
  private int fk_nfe_id;

  public enum Status {
    COMPLETED,
    PENDING,
  }

  public enum OrderType {
    BALCONY,
    DELIVERY,
  }

  public enum PaymentMethod {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    PIX,
  }

  public SaleOrder() {
  }

  public SaleOrder(int id, Date date, double total_price, Status status, OrderType order_type,
      PaymentMethod payment_method, int fk_client_id, int fk_nfe_id) {
    this.id = id;
    this.date = date;
    this.total_price = total_price;
    this.status = status;
    this.order_type = order_type;
    this.payment_method = payment_method;
    this.fk_client_id = fk_client_id;
    this.fk_nfe_id = fk_nfe_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getTotal_price() {
    return total_price;
  }

  public void setTotal_price(double total_price) {
    this.total_price = total_price;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public OrderType getOrder_type() {
    return order_type;
  }

  public void setOrder_type(OrderType order_type) {
    this.order_type = order_type;
  }

  public PaymentMethod getPayment_method() {
    return payment_method;
  }

  public void setPayment_method(PaymentMethod payment_method) {
    this.payment_method = payment_method;
  }

  public int getFk_client_id() {
    return fk_client_id;
  }

  public void setFk_client_id(int fk_client_id) {
    this.fk_client_id = fk_client_id;
  }

  public int getFk_nfe_id() {
    return fk_nfe_id;
  }

  public void setFk_nfe_id(int fk_nfe_id) {
    this.fk_nfe_id = fk_nfe_id;
  }

}
