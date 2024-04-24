package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.IngredientSupplier;
import mingati.luis.projectdb.service.IngredientSupplierService;

@RestController
@RequestMapping("/ingredient-suppliers")
public class IngredientSupplierController {
  @Autowired
  private IngredientSupplierService ingredientSupplierService;

  @GetMapping
  public List<IngredientSupplier> getAllIngredientSuppliers() {
    return ingredientSupplierService.findAll();
  }

  @GetMapping("/{fk_supplier_id}/{fk_ingredient_id}")
  public IngredientSupplier getIngredientSupplierBySupplierIdAndIngredientId(
      @PathVariable("fk_supplier_id") int fk_supplier_id, @PathVariable("fk_ingredient_id") int fk_ingredient_id) {
    return ingredientSupplierService.findBySupplierIdAndIngredientId(fk_supplier_id, fk_ingredient_id);
  }

  @PostMapping
  public IngredientSupplier createIngredientSupplier(@RequestBody IngredientSupplier ingredientSupplier) {
    return ingredientSupplierService.save(ingredientSupplier);
  }

  @PutMapping("/{fk_supplier_id}/{fk_ingredient_id}")
  public IngredientSupplier updateIngredientSupplier(@PathVariable("fk_supplier_id") int fk_supplier_id,
      @PathVariable("fk_ingredient_id") int fk_ingredient_id, @RequestBody IngredientSupplier ingredientSupplier) {
    ingredientSupplier.setFkSupplierId(fk_supplier_id);
    ingredientSupplier.setFkIngredientId(fk_ingredient_id);
    return ingredientSupplierService.update(ingredientSupplier);
  }

  @DeleteMapping("/{fk_supplier_id}/{fk_ingredient_id}")
  public void deleteIngredientSupplier(@PathVariable("fk_supplier_id") int fk_supplier_id,
      @PathVariable("fk_ingredient_id") int fk_ingredient_id) {
    ingredientSupplierService.deleteBySupplierIdAndIngredientId(fk_supplier_id, fk_ingredient_id);
  }
}
