package mingati.luis.projectdb.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import mingati.luis.projectdb.model.MostProducedProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mingati.luis.projectdb.model.Production;
import mingati.luis.projectdb.model.Product;
import mingati.luis.projectdb.model.Production.ProductionStatus;

@Repository
public class ProductionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Production> findAll() {
        List<Production> productions = jdbcTemplate.query("SELECT * FROM Production", productionMapper);
        for (Production production : productions) {
            List<Product> products = findProductsByProductionId(production.getId());
            production.setProducts(products);
        }
        return productions;
    }

    public Production findById(int id) {
        Production production = jdbcTemplate.queryForObject("SELECT * FROM Production WHERE id = ?", productionMapper, id);
        if (production != null) {
            List<Product> products = findProductsByProductionId(production.getId());
            production.setProducts(products);
        }
        return production;
    }

    public int save(Production production) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Production (name, start_date, end_date, status) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, production.getName());
            if (production.getStart_date() != null) {
                ps.setDate(2, new java.sql.Date(production.getStart_date().getTime()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }
            if (production.getEnd_date() != null) {
                ps.setDate(3, new java.sql.Date(production.getEnd_date().getTime()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }
            ps.setString(4, production.getStatus().toString());
            return ps;
        }, keyHolder);

        production.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return keyHolder.getKey().intValue();
    }

    public int update(Production production) {
        return jdbcTemplate.update(
            "UPDATE Production SET start_date = ?, end_date = ?, name = ?, status = ? WHERE id = ?",
            ps -> {
                if (production.getStart_date() != null) {
                    ps.setDate(1, new java.sql.Date(production.getStart_date().getTime()));
                } else {
                    ps.setNull(1, java.sql.Types.DATE);
                }
                if (production.getEnd_date() != null) {
                    ps.setDate(2, new java.sql.Date(production.getEnd_date().getTime()));
                } else {
                    ps.setNull(2, java.sql.Types.DATE);
                }
                ps.setString(3, production.getName());
                ps.setString(4, production.getStatus().toString());
                ps.setInt(5, production.getId());
            }
        );
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM Production WHERE id = ?", id);
    }

    private final RowMapper<Production> productionMapper = (rs, rowNum) -> {
        Production production = new Production();
        production.setId(rs.getInt("id"));
        production.setStart_date(rs.getDate("start_date"));
        production.setEnd_date(rs.getDate("end_date"));
        production.setName(rs.getString("name"));
        production.setStatus(Production.ProductionStatus.valueOf(rs.getString("status")));
        return production;
    };

    private final RowMapper<Product> productMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setQuantity(rs.getInt("quantity"));
        return product;
    };

    private List<Product> findProductsByProductionId(int productionId) {
        return jdbcTemplate.query(
                "SELECT p.* FROM Product p " +
                        "INNER JOIN Production_Product pp ON p.id = pp.fk_Product_id " +
                        "WHERE pp.fk_Production_id = ?",
                productMapper,
                productionId
        );
    }

    public List<MostProducedProducts> getMostProducedProducts() {
        String sql = "SELECT p.id, p.name, SUM(pp.quantity) AS quantity " +
                "FROM Product p " +
                "JOIN Production_Product pp ON p.id = pp.fk_Product_id " +
                "GROUP BY p.id, p.name " +
                "ORDER BY quantity DESC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int totalQuantityProduced = rs.getInt("quantity");
            return new MostProducedProducts(id, name, totalQuantityProduced);
        });
    }
}
