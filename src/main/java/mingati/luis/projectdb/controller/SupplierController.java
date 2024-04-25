package mingati.luis.projectdb.controller;

import mingati.luis.projectdb.model.Supplier;
import mingati.luis.projectdb.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;

  @GetMapping
  public List<Supplier> getAllSuppliers() {
    return supplierService.findAll();
  }

  @GetMapping("/{id}")
  public Supplier getSupplierById(@PathVariable("id") int id) {
    return supplierService.findById(id);
  }

  @PostMapping
  public Supplier createSupplier(@RequestBody Supplier supplier) {
    return supplierService.save(supplier);
  }

  @PutMapping("/{id}")
  public Supplier updateSupplier(@PathVariable("id") int id, @RequestBody Supplier supplier) {
    supplier.setId(id);
    return supplierService.update(supplier);
  }
  @DeleteMapping("/{id}")
  public void deleteSupplier(@PathVariable("id") int id) {
    supplierService.deleteById(id);
  }
}
