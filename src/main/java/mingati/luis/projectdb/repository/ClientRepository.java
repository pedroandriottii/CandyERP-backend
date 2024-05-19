package mingati.luis.projectdb.repository;

import mingati.luis.projectdb.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Client> findAll() {
        return jdbcTemplate.query("SELECT * FROM Client", clientMapper);
    }

    public Client findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Client WHERE id = ?", clientMapper, id);
    }

    public Client save(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Client (name, street, number, neighborhood, complement) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getName());
            ps.setString(2, client.getStreet());
            ps.setString(3, client.getNumber());
            ps.setString(4, client.getNeighborhood());
            ps.setString(5, client.getComplement());
            return ps;
        }, keyHolder);

        client.setId(keyHolder.getKey().intValue());
        return client;
    }

    public void update(Client client) {
        jdbcTemplate.update(
                "UPDATE Client SET name = ?, street = ?, number = ?, neighborhood = ?, complement = ? WHERE id = ?",
                client.getName(), client.getStreet(), client.getNumber(), client.getNeighborhood(),
                client.getComplement(), client.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM Client WHERE id = ?", id);
    }

    private final RowMapper<Client> clientMapper = (rs, rowNum) -> {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setName(rs.getString("name"));
        client.setStreet(rs.getString("street"));
        client.setNumber(rs.getString("number"));
        client.setNeighborhood(rs.getString("neighborhood"));
        client.setComplement(rs.getString("complement"));
        return client;
    };
}
