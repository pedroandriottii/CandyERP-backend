package mingati.luis.projectdb.controller;

import mingati.luis.projectdb.model.BestSellProducts;
import mingati.luis.projectdb.model.MonthlySales;
import mingati.luis.projectdb.service.ProductService;
import mingati.luis.projectdb.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/monthly-sales")
    public List<MonthlySales> getMonthlyRevenues() {
        return saleOrderService.getMonthlyRevenues();
    }

    @GetMapping("/best-selling-products")
    public List<BestSellProducts> getBestSellingProducts() {
            return productService.getBestSellingProducts();
    }
}
