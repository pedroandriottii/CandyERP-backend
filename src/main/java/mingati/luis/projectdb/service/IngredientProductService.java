package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.IngredientProduct;
import mingati.luis.projectdb.repository.IngredientProductRepository;

@Service
public class IngredientProductService {
  @Autowired
  private IngredientProductRepository ingredientProductRepository;

  public List<IngredientProduct> findAll() {
    return ingredientProductRepository.findAll();
  }

  public IngredientProduct findByProductIdAndIngredientId(int fk_product_id, int fk_ingredient_id) {
    return ingredientProductRepository.findByProductIdAndIngredientId(fk_product_id, fk_ingredient_id);
  }

  public IngredientProduct save(IngredientProduct ingredientProduct) {
    ingredientProductRepository.save(ingredientProduct);
    return ingredientProduct;
  }

  public IngredientProduct update(IngredientProduct ingredientProduct) {
    ingredientProductRepository.update(ingredientProduct);
    return ingredientProduct;
  }

  public void deleteByProductIdAndIngredientId(int fk_product_id, int fk_ingredient_id) {
    ingredientProductRepository.deleteByProductIdAndIngredientId(fk_product_id, fk_ingredient_id);
  }
}
