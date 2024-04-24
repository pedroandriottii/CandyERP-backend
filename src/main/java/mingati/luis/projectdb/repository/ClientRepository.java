package mingati.luis.projectdb.repository;

import mingati.luis.projectdb.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Client> findAll() {
        return jdbcTemplate.query("SELECT * FROM Client", ClientMapper);
    }

    public Client findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Client WHERE id = ?", ClientMapper, new Object[] { id });
    }

    public void save(Client Client) {
        jdbcTemplate
                .update("INSERT INTO Client (name, street, number, neighborhood, complement) VALUES (?, ?, ?, ?, ?)",
                        new Object[] { Client.getName(), Client.getStreet(), Client.getNumber(),
                                Client.getNeighborhood(),
                                Client.getComplement() });
    }

    public void update(Client Client) {
        jdbcTemplate.update(
                "UPDATE Client SET name = ?, street = ?, number = ?, neighborhood = ?, complement = ? WHERE id = ?",
                new Object[] { Client.getName(), Client.getStreet(), Client.getNumber(), Client.getNeighborhood(),
                        Client.getComplement(), Client.getId() });
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM Client WHERE id = ?", new Object[] { id });
    }

    private final RowMapper<Client> ClientMapper = (rs, rowNum) -> {
        Client Client = new Client();
        Client.setId(rs.getInt("id"));
        Client.setName(rs.getString("name"));
        Client.setStreet(rs.getString("street"));
        Client.setNumber(rs.getString("number"));
        Client.setNeighborhood(rs.getString("neighborhood"));
        Client.setComplement(rs.getString("complement"));
        return Client;
    };

}