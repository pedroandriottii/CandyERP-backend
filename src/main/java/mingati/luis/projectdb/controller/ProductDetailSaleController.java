package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.ProductDetailSale;
import mingati.luis.projectdb.service.ProductDetailSaleService;

@RestController
@RequestMapping("/product-detail-sales")
public class ProductDetailSaleController {
  @Autowired
  private ProductDetailSaleService productDetailSaleService;

  @GetMapping
  public List<ProductDetailSale> getAllProductDetailSaleServices() {
    return productDetailSaleService.findAll();
  }

  @GetMapping("/{fk_sale_id}/{fk_product_id}/{fk_detail_id}")
  public ProductDetailSale getProductDetailSaleBySaleOrderIdAndProductIdAndDetailId(
      @PathVariable("fk_sale_id") int fk_sale_id, @PathVariable("fk_product_id") int fk_product_id,
      @PathVariable("fk_detail_id") int fk_detail_id) {
    return productDetailSaleService.findBySaleOrderIdAndProductIdAndDetailId(fk_sale_id, fk_product_id,
        fk_detail_id);
  }

  @PostMapping
  public ProductDetailSale createProductDetailSale(@RequestBody ProductDetailSale productDetailSale) {
    System.out.println(productDetailSale.getFkDetailId() + " " + productDetailSale.getFkProductId() + " " + productDetailSale.getFkSaleOrderId() + " " + productDetailSale.getQuantity());
    return productDetailSaleService.save(productDetailSale);
  }

  @PutMapping("/{fk_sale_id}/{fk_product_id}/{fk_detail_id}")
  public ProductDetailSale updateProductDetailSale(@PathVariable("fk_sale_id") int fk_sale_id,
      @PathVariable("fk_product_id") int fk_product_id, @PathVariable("fk_detail_id") int fk_detail_id,
      @RequestBody ProductDetailSale productDetailSale) {
    productDetailSale.setFkSaleOrderId(fk_sale_id);
    productDetailSale.setFkProductId(fk_product_id);
    productDetailSale.setFkDetailId(fk_detail_id);
    return productDetailSaleService.update(productDetailSale);
  }

  @DeleteMapping("/{fk_sale_id}")
  public void deleteProductDetailSale(@PathVariable("fk_sale_id") int fk_sale_id) {
    productDetailSaleService.deleteBySaleOrderId(fk_sale_id);
  }
}
