package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.ProductionProduct;
import mingati.luis.projectdb.service.ProductionProductService;

@RestController
@RequestMapping("/production-products")
public class ProductionProductController {
  @Autowired
  private ProductionProductService productionProductService;

  @GetMapping
  public List<ProductionProduct> getAllProductionProducts() {
    return productionProductService.findAll();
  }

  @GetMapping("/{fkProductId}/{fkProductionId}")
  public ProductionProduct getProductionProductById(@PathVariable("fkProductId") int fkProductId,
      @PathVariable("fkProductionId") int fkProductionId) {
    return productionProductService.findById(fkProductId, fkProductionId);
  }

  @PostMapping
  public ProductionProduct createProductionProduct(@RequestBody ProductionProduct productionProduct) {
    System.out.println(productionProduct.getFkProductId() + " " + productionProduct.getFkProductionId() + " " + productionProduct.getQuantity());
    return productionProductService.save(productionProduct);
  }

  @PutMapping("/{fkProductId}/{fkProductionId}")
  public ProductionProduct updateProductionProduct(@PathVariable("fkProductId") int fkProductId,
      @PathVariable("fkProductionId") int fkProductionId,
      @RequestBody ProductionProduct productionProduct) {
    productionProduct.setFkProductId(fkProductId);
    productionProduct.setFkProductionId(fkProductionId);
    return productionProductService.update(productionProduct);
  }

  @DeleteMapping("/{fkProductId}/{fkProductionId}")
  public void deleteProductionProduct(@PathVariable("fkProductId") int fkProductId,
      @PathVariable("fkProductionId") int fkProductionId) {
    productionProductService.deleteById(fkProductId, fkProductionId);
  }
}
