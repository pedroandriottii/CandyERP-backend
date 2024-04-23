package mingati.luis.projectdb.repository;

import mingati.luis.projectdb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM User", userMapper);
    }

    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM User WHERE id = ?", userMapper, new Object[] {id});
    }

    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO User (id, LastName, FirstName, Address, City) VALUES (?, ?, ?, ?, ?)",
                new Object[]{user.getId(), user.getLastName(), user.getFirstName(), user.getAddress(), user.getCity()});
    }

    public int update(User user) {
        return jdbcTemplate.update("UPDATE User SET LastName = ?, FirstName = ?, Address = ?, City = ? WHERE id = ?",
                new Object[]{user.getLastName(), user.getFirstName(), user.getAddress(), user.getCity(), user.getId()});
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM User WHERE id = ?", new Object[]{id});
    }

    private final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLastName(rs.getString("LastName"));
        user.setFirstName(rs.getString("FirstName"));
        user.setAddress(rs.getString("Address"));
        user.setCity(rs.getString("City"));
        return user;
    };
}