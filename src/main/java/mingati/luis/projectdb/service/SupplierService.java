package mingati.luis.projectdb.service;

import mingati.luis.projectdb.model.Supplier;
import mingati.luis.projectdb.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {
  @Autowired
  private SupplierRepository supplierRepository;

  public List<Supplier> findAll() {
    return supplierRepository.findAll();
  }

  public Supplier findById(int id) {
    return supplierRepository.findById(id);
  }

  public Supplier save(Supplier supplier) {
    supplierRepository.save(supplier);
    return supplier;
  }

  public Supplier update(Supplier supplier) {
    supplierRepository.update(supplier);
    return supplier;
  }

  public void deleteById(int id) {
    supplierRepository.deleteById(id);
  }
}
