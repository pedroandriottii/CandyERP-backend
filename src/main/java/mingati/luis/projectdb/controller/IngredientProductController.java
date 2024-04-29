package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.IngredientProduct;
import mingati.luis.projectdb.service.IngredientProductService;

@RestController
@RequestMapping("/ingredient-products")
public class IngredientProductController {
  @Autowired
  private IngredientProductService ingredientProductService;

  @GetMapping
  public List<IngredientProduct> getAllIngredientProducts() {
    return ingredientProductService.findAll();
  }

  @GetMapping("/{fk_product_id}/{fk_ingredient_id}")
  public IngredientProduct getIngredientProductByProductIdAndIngredientId(
      @PathVariable("fk_product_id") int fk_product_id, @PathVariable("fk_ingredient_id") int fk_ingredient_id) {
    return ingredientProductService.findByProductIdAndIngredientId(fk_product_id, fk_ingredient_id);
  }

  @PostMapping
  public IngredientProduct createIngredientProduct(@RequestBody IngredientProduct ingredientProduct) {
    System.out.println(ingredientProduct.getFkIngredientId() + " " + ingredientProduct.getFkProductId() + " " + ingredientProduct.getQuantity());
    return ingredientProductService.save(ingredientProduct);
  }

  @PutMapping("/{fk_product_id}/{fk_ingredient_id}")
  public IngredientProduct updateIngredientProduct(@PathVariable("fk_product_id") int fk_product_id,
      @PathVariable("fk_ingredient_id") int fk_ingredient_id, @RequestBody IngredientProduct ingredientProduct) {
    ingredientProduct.setFkProductId(fk_product_id);
    ingredientProduct.setFkIngredientId(fk_ingredient_id);
    return ingredientProductService.update(ingredientProduct);
  }

  @DeleteMapping("/{fk_product_id}/{fk_ingredient_id}")
  public void deleteIngredientProduct(@PathVariable("fk_product_id") int fk_product_id,
      @PathVariable("fk_ingredient_id") int fk_ingredient_id) {
    ingredientProductService.deleteByProductIdAndIngredientId(fk_product_id, fk_ingredient_id);
  }

}
