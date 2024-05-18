package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.ProductDetailSale;

@Repository
public class ProductDetailSaleRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<ProductDetailSale> findAll() {
    return jdbcTemplate.query("SELECT fk_sale_order_id, fk_product_id, fk_detail_id, quantity FROM Product_Detail_Sale",
        ProductDetailSaleMapper);
  }

  public ProductDetailSale findBySaleOrderIdAndProductIdAndDetailId(int fk_sale_order_id, int fk_product_id,
      int fk_detail_id) {
    return jdbcTemplate.queryForObject(
        "SELECT fk_sale_order_id, fk_product_id, fk_detail_id, quantity FROM Product_Detail_Sale WHERE fk_sale_order_id = ? AND fk_product_id = ? AND fk_detail_id = ?",
        ProductDetailSaleMapper, new Object[] { fk_sale_order_id, fk_product_id, fk_detail_id });
  }

  public int save(ProductDetailSale productDetailSale) {
    return jdbcTemplate.update(
        "INSERT INTO Product_Detail_Sale (fk_sale_order_id, fk_product_id, fk_detail_id, quantity) VALUES (?, ?, ?, ?)",
        new Object[] { productDetailSale.getFkSaleOrderId(), productDetailSale.getFkProductId(),
            productDetailSale.getFkDetailId(), productDetailSale.getQuantity() });
  }

  public int update(ProductDetailSale productDetailSale) {
    return jdbcTemplate.update(
        "UPDATE Product_Detail_Sale SET quantity = ? WHERE fk_sale_order_id = ? AND fk_product_id = ? AND fk_detail_id = ?",
        new Object[] { productDetailSale.getQuantity(), productDetailSale.getFkSaleOrderId(),
            productDetailSale.getFkProductId(), productDetailSale.getFkDetailId() });
  }

  public int deleteBySaleOrderId(int fk_sale_order_id) {
    return jdbcTemplate.update("DELETE FROM Product_Detail_Sale WHERE fk_sale_order_id = ?",
        new Object[] { fk_sale_order_id });
  }

  public int deleteBySaleProductDetail(int fk_sale_order_id, int fk_product_id, int fk_detail_id) {
    return jdbcTemplate.update("DELETE FROM Product_Detail_Sale WHERE fk_sale_order_id = ? AND fk_product_id = ? AND fk_detail_id = ?",
            new Object[] { fk_sale_order_id, fk_product_id, fk_detail_id });
  }

  public List<ProductDetailSale> findBySaleOrderId(int fk_sale_id) {
    return jdbcTemplate.query(
            "SELECT fk_sale_order_id, fk_product_id, fk_detail_id, quantity FROM Product_Detail_Sale WHERE fk_sale_order_id = ?",
            ProductDetailSaleMapper, new Object[] { fk_sale_id });
  }

  public final RowMapper<ProductDetailSale> ProductDetailSaleMapper = (rs, rowNum) -> {
    ProductDetailSale productDetailSale = new ProductDetailSale();
    productDetailSale.setFkSaleOrderId(rs.getInt("fk_sale_order_id"));
    productDetailSale.setFkProductId(rs.getInt("fk_product_id"));
    productDetailSale.setFkDetailId(rs.getInt("fk_detail_id"));
    productDetailSale.setQuantity(rs.getInt("quantity"));
    return productDetailSale;
  };

}
