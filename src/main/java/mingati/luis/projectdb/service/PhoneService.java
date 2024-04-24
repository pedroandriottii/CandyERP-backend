package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.Phone;
import mingati.luis.projectdb.repository.PhoneRepository;

@Service
public class PhoneService {
  @Autowired
  private PhoneRepository phoneRepository;

  public List<Phone> findAll() {
    return phoneRepository.findAll();
  }

  public Phone findById(int id) {
    return phoneRepository.findById(id);
  }

  public Phone save(Phone phone) {
    phoneRepository.save(phone);
    return phone;
  }

  public Phone update(Phone phone) {
    phoneRepository.update(phone);
    return phone;
  }

  public void deleteById(int id) {
    phoneRepository.deleteById(id);
  }

}
