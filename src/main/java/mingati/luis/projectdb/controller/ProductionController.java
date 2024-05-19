package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.Production;
import mingati.luis.projectdb.service.ProductionService;
import org.springframework.web.server.ResponseStatusException;

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

  @PutMapping("/{id}/status")
  public Production updateProductionStatus(@PathVariable("id") int id, @RequestBody String status) {
    Production production = productionService.findById(id);
    if (production != null) {
      production.setStatus(Production.ProductionStatus.valueOf(status));
      return productionService.update(production);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Production not found");
  }

  @DeleteMapping("/{id}")
  public void deleteProduction(@PathVariable("id") int id) {
    productionService.deleteById(id);
  }
}
