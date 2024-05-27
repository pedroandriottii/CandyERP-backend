package mingati.luis.projectdb.repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

import mingati.luis.projectdb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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

      if (saleOrder.getFk_client_id() == null) {
        ps.setNull(5, java.sql.Types.INTEGER);
      } else {
        ps.setInt(5, saleOrder.getFk_client_id());
      }

      ps.setInt(6, saleOrder.getFk_nfe_id());
      return ps;
    }, keyHolder);

    saleOrder.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    return keyHolder.getKey().intValue();
  }


  public int update(SaleOrder saleOrder) {
    return jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(
              "UPDATE Sale_Order SET date = ?, total_price = ?, order_type = ?, payment_method = ?, fk_client_id = ?, fk_nfe_id = ? WHERE id = ?"
      );
      ps.setDate(1, new java.sql.Date(saleOrder.getDate().getTime()));
      ps.setDouble(2, saleOrder.getTotal_price());
      ps.setString(3, saleOrder.getOrder_type().toString());
      ps.setString(4, saleOrder.getPayment_method().toString());

      if (saleOrder.getFk_client_id() == null) {
        ps.setNull(5, java.sql.Types.INTEGER);
      } else {
        ps.setInt(5, saleOrder.getFk_client_id());
      }

      ps.setInt(6, saleOrder.getFk_nfe_id());
      ps.setInt(7, saleOrder.getId());
      return ps;
    });
  }


  public int deleteById(int id) {
    return jdbcTemplate.update("DELETE FROM Sale_Order WHERE id = ?", new Object[] { id });
  }

  public List<MonthlySales> getMonthlySales() {
    String sql = "SELECT sub.year, sub.month, sub.total_revenue " +
            "FROM ( " +
            "    SELECT YEAR(date) AS year, MONTH(date) AS month, SUM(total_price) AS total_revenue " +
            "    FROM Sale_Order " +
            "    GROUP BY YEAR(date), MONTH(date) " +
            ") sub " +
            "ORDER BY sub.year, sub.month";

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      int year = rs.getInt("year");
      int month = rs.getInt("month");
      BigDecimal totalRevenue = rs.getBigDecimal("total_revenue");
      return new MonthlySales(year, month, totalRevenue);
    });
  }


  public Map<String, Double> getProductsByPaymentMethods() {
    YearMonth currentMonth = YearMonth.now();
    LocalDate startOfMonth = currentMonth.atDay(1);
    LocalDate endOfMonth = currentMonth.atEndOfMonth();

    String[] paymentMethods = {"CASH", "CREDIT_CARD", "DEBIT_CARD", "PIX"};
    Map<String, Double> result = new HashMap<>();

    for (String paymentMethod : paymentMethods) {
      String sql = "SELECT SUM(pds.quantity * p.price) AS total_value_sold " +
              "FROM Product p " +
              "JOIN Product_Detail_Sale pds ON p.id = pds.fk_Product_id " +
              "JOIN Sale_Order so ON pds.fk_Sale_Order_id = so.id " +
              "WHERE so.payment_method = ? AND so.date BETWEEN ? AND ?";

      Double totalValueSold = jdbcTemplate.queryForObject(sql, new Object[]{paymentMethod, startOfMonth, endOfMonth}, Double.class);

      // If no sales found for the payment method, set totalValueSold to 0
      if (totalValueSold == null) {
        totalValueSold = 0.0;
      }

      result.put(paymentMethod, totalValueSold);
    }

    return result;
  }

  public Map<String, NeighborhoodSales> getProductsByNeighborhood() {
    YearMonth currentMonth = YearMonth.now();
    LocalDate startOfMonth = currentMonth.atDay(1);
    LocalDate endOfMonth = currentMonth.atEndOfMonth();

    String sql = "SELECT c.neighborhood, COUNT(so.id) AS total_sales, SUM(pds.quantity * p.price) AS total_value_sold " +
            "FROM Product p " +
            "JOIN Product_Detail_Sale pds ON p.id = pds.fk_Product_id " +
            "JOIN Sale_Order so ON pds.fk_Sale_Order_id = so.id " +
            "JOIN Client c ON so.fk_Client_id = c.id " +
            "WHERE so.date BETWEEN ? AND ? " +
            "GROUP BY c.neighborhood " +
            "ORDER BY total_sales DESC";

    List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(sql, startOfMonth, endOfMonth);

    Map<String, NeighborhoodSales> result = new HashMap<>();

    for (Map<String, Object> row : queryResults) {
      String neighborhood = (String) row.get("neighborhood");
      int totalSales = ((Number) row.get("total_sales")).intValue();
      double totalValueSold = ((Number) row.get("total_value_sold")).doubleValue();

      NeighborhoodSales sales = new NeighborhoodSales(totalSales, totalValueSold);
      result.put(neighborhood, sales);
    }

    return result;
  }


  public final RowMapper<SaleOrder> saleOrderMapper = (rs, rowNum) -> {
    SaleOrder saleOrder = new SaleOrder();
    saleOrder.setId(rs.getInt("id"));
    saleOrder.setDate(rs.getDate("date"));
    saleOrder.setTotal_price(rs.getDouble("total_price"));
    saleOrder.setOrder_type(SaleOrder.OrderType.valueOf(rs.getString("order_type")));
    saleOrder.setPayment_method(SaleOrder.PaymentMethod.valueOf(rs.getString("payment_method")));

    int fkClientId = rs.getInt("fk_client_id");
    if (rs.wasNull()) {
      saleOrder.setFk_client_id(null);
    } else {
      saleOrder.setFk_client_id(fkClientId);
    }

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
