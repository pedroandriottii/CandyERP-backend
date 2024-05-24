package mingati.luis.projectdb.service;

import java.util.List;
import java.util.Map;

import mingati.luis.projectdb.model.MonthlySales;
import mingati.luis.projectdb.model.NeighborhoodSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.SaleOrder;
import mingati.luis.projectdb.repository.SaleOrderRepository;

@Service
public class SaleOrderService {
  @Autowired
  private SaleOrderRepository saleOrderRepository;

  public List<SaleOrder> findAll() {
    return saleOrderRepository.findAll();
  }

  public SaleOrder findById(int id) {
    return saleOrderRepository.findById(id);
  }

  public SaleOrder save(SaleOrder saleOrder) {
    saleOrderRepository.save(saleOrder);
    return saleOrder;
  }
  public SaleOrder update(SaleOrder saleOrder) {
    saleOrderRepository.update(saleOrder);
    return saleOrder;
  }

  public List<MonthlySales> getMonthlyRevenues() {
    return saleOrderRepository.getMonthlySales();
  }

  public Map<String, Double> getProductsByPaymentMethods() {
    return saleOrderRepository.getProductsByPaymentMethods();
  }

  public Map<String, NeighborhoodSales> getProductsByNeighborhood() {
    return saleOrderRepository.getProductsByNeighborhood();
  }

  public void deleteById(int id) {
    saleOrderRepository.deleteById(id);
  }
}
