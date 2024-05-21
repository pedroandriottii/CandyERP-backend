package mingati.luis.projectdb.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mingati.luis.projectdb.model.MonthlySales;
import mingati.luis.projectdb.model.ProductDetailSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.SaleOrder;

@Repository
public class SaleOrderRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<SaleOrder> findAll() {
    List<SaleOrder> saleOrders = jdbcTemplate.query("SELECT * FROM Sale_Order", saleOrderMapper);
    for (SaleOrder saleOrder : saleOrders) {
      List<ProductDetailSale> productDetails = findProductDetailsBySaleOrderId(saleOrder.getId());
      saleOrder.setProductDetails(productDetails);
    }
    return saleOrders;
  }

  public SaleOrder findById(int id) {
    SaleOrder saleOrder = jdbcTemplate.queryForObject("SELECT * FROM Sale_Order WHERE id = ?", saleOrderMapper, id);
    if (saleOrder != null) {
      List<ProductDetailSale> productDetails = findProductDetailsBySaleOrderId(saleOrder.getId());
      saleOrder.setProductDetails(productDetails);
    }
    return saleOrder;
  }

  private List<ProductDetailSale> findProductDetailsBySaleOrderId(int saleOrderId) {
    List<ProductDetailSale> productDetails = jdbcTemplate.query(
            "SELECT * FROM Product_Detail_Sale WHERE fk_sale_order_id = ?",
            new Object[]{saleOrderId},
            productDetailSaleMapper
    );
    return productDetails;
  }

  public int save(SaleOrder saleOrder) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO Sale_Order (date, total_price, order_type, payment_method, fk_client_id, fk_nfe_id) VALUES (?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[] { "ID" });
      ps.setDate(1, new java.sql.Date(saleOrder.getDate().getTime()));
      ps.setDouble(2, saleOrder.getTotal_price());
      ps.setString(3, saleOrder.getOrder_type().toString());
      ps.setString(4, saleOrder.getPayment_method().toString());
      ps.setInt(5, saleOrder.getFk_client_id());
      ps.setInt(6, saleOrder.getFk_nfe_id());
      return ps;
    }, keyHolder);

    saleOrder.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    return keyHolder.getKey().intValue();
  }

  public int update(SaleOrder saleOrder) {
    return jdbcTemplate.update(
        "UPDATE Sale_Order SET date = ?, total_price = ?, order_type = ?, payment_method = ?, fk_client_id = ? WHERE id = ?",
        new Object[] { saleOrder.getDate(), saleOrder.getTotal_price(),
            saleOrder.getOrder_type().toString(), saleOrder.getPayment_method().toString(), saleOrder.getFk_client_id(),
            saleOrder.getId() });
  }

  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Sale_Order WHERE id = ?", new Object[] { id });
  }

  public List<MonthlySales> getMonthlySales() {
    String sql = "SELECT YEAR(date) AS year, MONTH(date) AS month, SUM(total_price) AS total_revenue " +
            "FROM Sale_Order " +
            "GROUP BY YEAR(date), MONTH(date) " +
            "ORDER BY YEAR(date), MONTH(date)";

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      int year = rs.getInt("year");
      int month = rs.getInt("month");
      BigDecimal totalRevenue = rs.getBigDecimal("total_revenue");
      return new MonthlySales(year, month, totalRevenue);
    });
  }


  public final RowMapper<SaleOrder> saleOrderMapper = (rs, rowNum) -> {
    SaleOrder saleOrder = new SaleOrder();
    saleOrder.setId(rs.getInt("id"));
    saleOrder.setDate(rs.getDate("date"));
    saleOrder.setTotal_price(rs.getDouble("total_price"));
    saleOrder.setOrder_type(SaleOrder.OrderType.valueOf(rs.getString("order_type")));
    saleOrder.setPayment_method(SaleOrder.PaymentMethod.valueOf(rs.getString("payment_method")));
    saleOrder.setFk_client_id(rs.getInt("fk_client_id"));
    saleOrder.setFk_nfe_id(rs.getInt("fk_nfe_id"));
    return saleOrder;
  };

  private final RowMapper<ProductDetailSale> productDetailSaleMapper = (rs, rowNum) -> {
    ProductDetailSale productDetailSale = new ProductDetailSale();
    productDetailSale.setFkProductId(rs.getInt("fk_product_id"));
    productDetailSale.setFkSaleOrderId(rs.getInt("fk_sale_order_id"));
    productDetailSale.setFkDetailId(rs.getInt("fk_detail_id"));
    productDetailSale.setQuantity(rs.getInt("quantity"));
    return productDetailSale;
  };
}
