package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.SaleOrder;

@Repository
public class SaleOrderRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<SaleOrder> findAll() {
    return jdbcTemplate.query("SELECT * FROM Sale_Order", saleOrderMapper);
  }

  public SaleOrder findById(int id) {
    return jdbcTemplate.queryForObject("SELECT * FROM Sale_Order WHERE id = ?", saleOrderMapper, new Object[] { id });
  }

  public int save(SaleOrder saleOrder) {
    return jdbcTemplate.update(
        "INSERT INTO Sale_Order (date, total_price, status, order_type, payment_method, fk_client_id, fk_nfe_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
        new Object[] { saleOrder.getDate(), saleOrder.getTotal_price(), saleOrder.getStatus().toString(),
            saleOrder.getOrder_type().toString(), saleOrder.getPayment_method().toString(), saleOrder.getFk_client_id(),
            saleOrder.getFk_nfe_id() });
  }

  public int update(SaleOrder saleOrder) {
    return jdbcTemplate.update(
        "UPDATE Sale_Order SET date = ?, total_price = ?, status = ?, order_type = ?, payment_method = ?, fk_client_id = ? WHERE id = ?",
        new Object[] { saleOrder.getDate(), saleOrder.getTotal_price(), saleOrder.getStatus().toString(),
            saleOrder.getOrder_type().toString(), saleOrder.getPayment_method().toString(), saleOrder.getFk_client_id(),
            saleOrder.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Sale_Order WHERE id = ?", new Object[] { id });
  }

  public final RowMapper<SaleOrder> saleOrderMapper = (rs, rowNum) -> {
    SaleOrder saleOrder = new SaleOrder();
    saleOrder.setId(rs.getInt("id"));
    saleOrder.setDate(rs.getDate("date"));
    saleOrder.setTotal_price(rs.getDouble("total_price"));
    saleOrder.setStatus(SaleOrder.Status.valueOf(rs.getString("status")));
    saleOrder.setOrder_type(SaleOrder.OrderType.valueOf(rs.getString("order_type")));
    saleOrder.setPayment_method(SaleOrder.PaymentMethod.valueOf(rs.getString("payment_method")));
    saleOrder.setFk_client_id(rs.getInt("fk_client_id"));
    saleOrder.setFk_nfe_id(rs.getInt("fk_nfe_id"));
    return saleOrder;
  };
}
