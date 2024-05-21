package mingati.luis.projectdb.service;

import java.util.List;

import mingati.luis.projectdb.model.BestSellProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.Product;
import mingati.luis.projectdb.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product findById(int id) {
    return productRepository.findById(id);
  }

  public Product save(Product product) {
    int id = productRepository.save(product);
    return productRepository.findById(id);
  }

  public Product update(Product product) {
    productRepository.update(product);
    return productRepository.findById(product.getId());
  }

  public void deleteById(int id) {
    productRepository.deleteById(id);
  }

  public List<BestSellProducts> getBestSellingProducts() {
    return productRepository.getBestSellingProducts();
  }

}
