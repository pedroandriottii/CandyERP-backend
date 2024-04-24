package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.Ingredient;
import mingati.luis.projectdb.repository.IngredientRepository;

@Service
public class IngredientService {
  @Autowired
  private IngredientRepository ingredientRepository;

  public List<Ingredient> findAll() {
    return ingredientRepository.findAll();
  }

  public Ingredient findById(int id) {
    return ingredientRepository.findById(id);
  }

  public Ingredient save(Ingredient ingredient) {
    ingredientRepository.save(ingredient);
    return ingredient;
  }

  public Ingredient update(Ingredient ingredient) {
    ingredientRepository.update(ingredient);
    return ingredient;
  }

  public void deleteById(int id) {
    ingredientRepository.deleteById(id);
  }
}
