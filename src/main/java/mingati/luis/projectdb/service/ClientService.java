package mingati.luis.projectdb.service;

import mingati.luis.projectdb.model.Client;
import mingati.luis.projectdb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository userRepository;

    public List<Client> findAll() {
        return userRepository.findAll();
    }

    public Client findById(int id) {
        return userRepository.findById(id);
    }

    public Client save(Client user) {
        userRepository.save(user);
        return user;
    }

    public Client update(Client user) {
        userRepository.update(user);
        return user;
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}