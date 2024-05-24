package mingati.luis.projectdb.service;

import java.util.List;

import mingati.luis.projectdb.model.MostProducedProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.Production;
import mingati.luis.projectdb.repository.ProductionRepository;

@Service
public class ProductionService {
  @Autowired
  private ProductionRepository productionRepository;

  public List<Production> findAll() {
    return productionRepository.findAll();
  }

  public Production findById(int id) {
    return productionRepository.findById(id);
  }

  public Production save(Production production) {
    productionRepository.save(production);
    return production;
  }

  public Production update(Production production) {
    productionRepository.update(production);
    return productionRepository.findById(production.getId());
  }

  public void deleteById(int id) {
    productionRepository.deleteById(id);
  }

  public List<MostProducedProducts> getMostProducedProducts() {
    return productionRepository.getMostProducedProducts();
  }
}
