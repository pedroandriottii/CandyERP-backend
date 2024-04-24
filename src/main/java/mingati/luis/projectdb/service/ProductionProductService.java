package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.ProductionProduct;
import mingati.luis.projectdb.repository.ProductionProductRepository;

@Service
public class ProductionProductService {
  @Autowired
  private ProductionProductRepository productionProductRepository;

  public List<ProductionProduct> findAll() {
    return productionProductRepository.findAll();
  }

  public ProductionProduct findById(int fk_product_id, int fk_production_id) {
    return productionProductRepository.findByProductIdAndProductionId(fk_product_id, fk_production_id);
  }

  public ProductionProduct save(ProductionProduct productionProduct) {
    productionProductRepository.save(productionProduct);
    return productionProduct;
  }

  public ProductionProduct update(ProductionProduct productionProduct) {
    productionProductRepository.update(productionProduct);
    return productionProduct;
  }

  public void deleteById(int fk_product_id, int fk_production_id) {
    productionProductRepository.deleteByProductIdAndProductionId(fk_product_id, fk_production_id);
  }

  // public List<ProductionProduct> getProductsByProductionId(int
  // fk_production_id) {
  // return
  // productionProductRepository.getProductsByProductionId(fk_production_id);
  // }
}
