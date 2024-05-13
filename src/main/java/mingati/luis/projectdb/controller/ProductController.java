package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.Product;
import mingati.luis.projectdb.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public Product getProductById(@PathVariable("id") int id) {
    return productService.findById(id);
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    System.out.println("ID do product: " + product.getId());
    return productService.save(product);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
    product.setId(id);
    return productService.update(product);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable("id") int id) {
    productService.deleteById(id);
  }
}
