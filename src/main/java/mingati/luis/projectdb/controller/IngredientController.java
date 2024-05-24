package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.Ingredient;
import mingati.luis.projectdb.service.IngredientService;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
  @Autowired
  private IngredientService ingredientService;

  @GetMapping
  public List<Ingredient> getAllIngredients() {
    return ingredientService.findAll();
  }


  @GetMapping("/{id}")
  public Ingredient getIngredientById(@PathVariable("id") int id) {
    return ingredientService.findById(id);
  }

  @PostMapping
  public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
    return ingredientService.save(ingredient);
  }

  @PutMapping("/{id}")
  public Ingredient updateIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
    ingredient.setId(id);
    return ingredientService.update(ingredient);
  }

  @DeleteMapping("/{id}")
  public void deleteIngredient(@PathVariable("id") int id) {
    ingredientService.deleteById(id);
  }
}
