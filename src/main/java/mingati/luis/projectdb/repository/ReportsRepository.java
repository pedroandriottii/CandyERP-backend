package mingati.luis.projectdb.repository;

import mingati.luis.projectdb.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReportsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> findTopSellingProducts() {
        String sql = "SELECT p.id, p.name, p.price, SUM(pds.quantity) AS total_quantity_sold " +
                     "FROM Product p " +
                     "JOIN Product_Detail_Sale pds ON p.id = pds.fk_Product_id " +
                     "GROUP BY p.id, p.name, p.price " +
                     "ORDER BY total_quantity_sold DESC";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public Double findTotalSalesForCurrentMonth() {
        String sql = "SELECT SUM(so.total_price) " +
                     "FROM Sale_Order so " +
                     "WHERE YEAR(so.date) = YEAR(CURDATE()) AND MONTH(so.date) = MONTH(CURDATE())";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    private static final class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setQuantity(rs.getInt("total_quantity_sold"));
            return product;
        }
    }
}
