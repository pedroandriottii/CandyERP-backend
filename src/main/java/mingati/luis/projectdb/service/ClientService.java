package mingati.luis.projectdb.service;

import mingati.luis.projectdb.model.Client;
import mingati.luis.projectdb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(int id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        clientRepository.update(client);
        return client;
    }

    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}
