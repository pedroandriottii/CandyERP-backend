package mingati.luis.projectdb.service;

import mingati.luis.projectdb.model.User;
import mingati.luis.projectdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public User update(User user) {
        userRepository.update(user);
        return user;
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}