package mingati.luis.projectdb.controller;

import mingati.luis.projectdb.model.Product;
import mingati.luis.projectdb.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportsService reportsService;

    @GetMapping("/products")
    public List<Product> getReportsProducts() {
        return reportsService.getReportsProducts();
    }

    @GetMapping("/sales")
    public Double getReportsSales() {
        return reportsService.getReportsSale();
    }
}
