package mingati.luis.projectdb.service;

import mingati.luis.projectdb.model.Product;
import mingati.luis.projectdb.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsRepository reportsRepository;

    public List<Product> getReportsProducts() {
        return reportsRepository.findTopSellingProducts();
    }

    public Double getReportsSale() {
        return reportsRepository.findTotalSalesForCurrentMonth();
    }
}
