package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.Production;
import mingati.luis.projectdb.service.ProductionService;

@RestController
@RequestMapping("/productions")
public class ProductionController {
  @Autowired
  private ProductionService productionService;

  @GetMapping
  public List<Production> getAllProductions() {
    return productionService.findAll();
  }

  @GetMapping("/{id}")
  public Production getProductionById(@PathVariable("id") int id) {
    return productionService.findById(id);
  }

  @PostMapping
  public Production createProduction(@RequestBody Production production) {
    return productionService.save(production);
  }

  @PutMapping("/{id}")
  public Production updateProduction(@PathVariable("id") int id, @RequestBody Production production) {
    production.setId(id);
    return productionService.update(production);
  }

  @DeleteMapping("/{id}")
  public void deleteProduction(@PathVariable("id") int id) {
    productionService.deleteById(id);
  }

  // @GetMapping("/product/{id}/products")
  // public List<Production> getProductionsByProductId(@PathVariable("id") int id)
  // {
  // return productionService.getProductsByProductionId(id);
  // }

}
