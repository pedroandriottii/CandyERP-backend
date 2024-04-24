package mingati.luis.projectdb.controller;

import mingati.luis.projectdb.model.Client;
import mingati.luis.projectdb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService userService;

    @GetMapping
    public List<Client> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Client getUserById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @PostMapping
    public Client createUser(@RequestBody Client user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public Client updateUser(@PathVariable("id") int id, @RequestBody Client user) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
    }
}