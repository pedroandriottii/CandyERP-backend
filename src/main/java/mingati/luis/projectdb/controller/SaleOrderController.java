package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.SaleOrder;
import mingati.luis.projectdb.service.SaleOrderService;

@RestController
@RequestMapping("/sale-orders")
public class SaleOrderController {
  @Autowired
  private SaleOrderService saleOrderService;

  @GetMapping
  public List<SaleOrder> getAllSaleOrders() {
    return saleOrderService.findAll();
  }

  @GetMapping("/{id}")
  public SaleOrder getSaleOrderById(@PathVariable("id") int id) {
    return saleOrderService.findById(id);
  }

  @PostMapping
  public SaleOrder createSaleOrder(@RequestBody SaleOrder saleOrder) {
    return saleOrderService.save(saleOrder);
  }

  @PutMapping("/{id}")
  public SaleOrder updateSaleOrder(@PathVariable("id") int id, @RequestBody SaleOrder saleOrder) {
    saleOrder.setId(id);
    return saleOrderService.update(saleOrder);
  }

  @DeleteMapping("/{id}")
  public void deleteSaleOrder(@PathVariable("id") int id) {
    saleOrderService.deleteById(id);
  }
}
