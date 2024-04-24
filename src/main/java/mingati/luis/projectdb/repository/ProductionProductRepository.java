package mingati.luis.projectdb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.ProductionProduct;

@Repository
public class ProductionProductRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<ProductionProduct> findAll() {
    return jdbcTemplate.query("SELECT fk_product_id, fk_production_id, quantity FROM Production_Product",
        productionProductMapper);
  }

  public ProductionProduct findByProductIdAndProductionId(int fk_product_id, int fk_production_id) {
    return jdbcTemplate.queryForObject(
        "SELECT fk_product_id, fk_production_id, quantity FROM Production_Product WHERE fk_product_id = ? AND fk_production_id = ?",
        productionProductMapper, new Object[] { fk_product_id, fk_production_id });
  }

  public int save(ProductionProduct productionProduct) {
    return jdbcTemplate.update(
        "INSERT INTO Production_Product (fk_product_id, fk_production_id, quantity) VALUES (?, ?, ?)",
        new Object[] { productionProduct.getFkProductId(), productionProduct.getFkProductionId(),
            productionProduct.getQuantity() });
  }

  public int update(ProductionProduct productionProduct) {
    return jdbcTemplate.update(
        "UPDATE Production_Product SET quantity = ? WHERE fk_product_id = ? AND fk_production_id = ?",
        new Object[] { productionProduct.getQuantity(), productionProduct.getFkProductId(),
            productionProduct.getFkProductionId() });
  }

  public int deleteByProductIdAndProductionId(int fk_product_id, int fk_production_id) {
    return jdbcTemplate.update("DELETE FROM Production_Product WHERE fk_product_id = ? AND fk_production_id = ?",
        new Object[] { fk_product_id, fk_production_id });
  }

  public final RowMapper<ProductionProduct> productionProductMapper = (rs, rowNum) -> {
    ProductionProduct productionProduct = new ProductionProduct();
    productionProduct.setFkProductId(rs.getInt("fk_product_id"));
    productionProduct.setFkProductionId(rs.getInt("fk_production_id"));
    productionProduct.setQuantity(rs.getInt("quantity"));
    return productionProduct;
  };
}
