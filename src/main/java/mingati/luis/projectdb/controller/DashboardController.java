package mingati.luis.projectdb.controller;

import mingati.luis.projectdb.model.*;
import mingati.luis.projectdb.service.ProductService;
import mingati.luis.projectdb.service.ProductionService;
import mingati.luis.projectdb.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductionService productionService;

    @GetMapping("/monthly-sales")
    public List<MonthlySales> getMonthlyRevenues() {
        return saleOrderService.getMonthlyRevenues();
    }

    @GetMapping("/best-selling-products")
    public List<BestSellProducts> getBestSellingProducts() {
            return productService.getBestSellingProducts();
    }

    @GetMapping("/best-selling-products-delivery")
    public List<BestSellProducts> getBestSellingProductsDelivery() {
        return productService.getBestSellingProductsByOrderType("DELIVERY");
    }

    @GetMapping("/best-selling-products-balcao")
    public List<BestSellProducts> getBestSellingProductsBalcony() {
        return productService.getBestSellingProductsByOrderType("BALCONY");
    }

    @GetMapping("/most-produced-products")
    public List<MostProducedProducts> getMostProducedProducts() {
        return productionService.getMostProducedProducts();
    }

    @GetMapping("/loyal-customers")
    public List<LoyalCustomers> getLoyalCustomers() {
        return productService.getLoyalCustomers();
    }

    @GetMapping("/products-by-payment-method")
    public Map<String, List<ProductsByPaymentMethod>> getProductsByPaymentMethod() {
        return saleOrderService.getProductsByPaymentMethods();
    }

    @GetMapping("/products-by-neighborhood")
    public Map<String, List<ProductsByNeighborhood>> getProductsByNeighborhood() {
        return saleOrderService.getProductsByNeighborhood();
    }

    @GetMapping("/products-by-stock")
    public List<Product> getProductsByStock(@RequestParam(defaultValue = "asc") String orderBy) {
        return productService.getProductsByStock(orderBy);
    }
}
