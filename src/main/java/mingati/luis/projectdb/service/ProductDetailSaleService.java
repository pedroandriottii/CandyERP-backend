package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.ProductDetailSale;
import mingati.luis.projectdb.repository.ProductDetailSaleRepository;

@Service
public class ProductDetailSaleService {
  @Autowired
  private ProductDetailSaleRepository productDetailSaleRepository;

  public List<ProductDetailSale> findAll() {
    return productDetailSaleRepository.findAll();
  }

  public ProductDetailSale findBySaleOrderIdAndProductIdAndDetailId(int fk_sale_id, int fk_product_id,
      int fk_detail_id) {
    return productDetailSaleRepository.findBySaleOrderIdAndProductIdAndDetailId(fk_sale_id, fk_product_id,
        fk_detail_id);
  }

  public ProductDetailSale save(ProductDetailSale productDetailSale) {
    productDetailSaleRepository.save(productDetailSale);
    return productDetailSale;
  }

  public ProductDetailSale update(ProductDetailSale productDetailSale) {
    productDetailSaleRepository.update(productDetailSale);
    return productDetailSale;
  }

  public void deleteBySaleOrderId(int fk_sale_id) {
    productDetailSaleRepository.deleteBySaleOrderId(fk_sale_id);
  }
}
