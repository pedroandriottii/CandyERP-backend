package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.IngredientSupplier;
import mingati.luis.projectdb.repository.IngredientSupplierRepository;

@Service
public class IngredientSupplierService {
  @Autowired
  private IngredientSupplierRepository ingredientSupplierRepository;

  public List<IngredientSupplier> findAll() {
    return ingredientSupplierRepository.findAll();
  }

  public IngredientSupplier findBySupplierIdAndIngredientId(int fk_supplier_id, int fk_ingredient_id) {
    return ingredientSupplierRepository.findBySupplierIdAndIngredientId(fk_supplier_id, fk_ingredient_id);
  }

  public IngredientSupplier save(IngredientSupplier ingredientSupplier) {
    ingredientSupplierRepository.save(ingredientSupplier);
    return ingredientSupplier;
  }

  public IngredientSupplier update(IngredientSupplier ingredientSupplier) {
    ingredientSupplierRepository.update(ingredientSupplier);
    return ingredientSupplier;
  }

  public void deleteBySupplierIdAndIngredientId(int fk_supplier_id, int fk_ingredient_id) {
    ingredientSupplierRepository.deleteBySupplierIdAndIngredientId(fk_supplier_id, fk_ingredient_id);
  }
}
