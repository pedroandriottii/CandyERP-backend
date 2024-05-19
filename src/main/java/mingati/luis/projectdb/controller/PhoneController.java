package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.Phone;
import mingati.luis.projectdb.service.PhoneService;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneService.findAll();
    }

    @PostMapping
    public Phone createPhone(@RequestBody Phone phone) {
        System.out.println("Creating phone with details: " + phone.toString());
        Phone createdPhone = phoneService.save(phone);
        System.out.println("Phone created with ID: " + createdPhone.getId());
        return createdPhone;
    }

    @PutMapping("/{id}")
    public Phone updatePhone(@PathVariable("id") int id, @RequestBody Phone phone) {
        phone.setId(id);
        return phoneService.update(phone);
    }

    @DeleteMapping("/{id}")
    public void deletePhone(@PathVariable("id") int id) {
        phoneService.deleteById(id);
    }
}
