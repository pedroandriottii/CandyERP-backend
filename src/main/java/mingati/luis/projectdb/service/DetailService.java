package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.Detail;
import mingati.luis.projectdb.repository.DetailRepository;

@Service
public class DetailService {
  @Autowired
  private DetailRepository detailRepository;

  public List<Detail> findAll() {
    return detailRepository.findAll();
  }

  public Detail findById(int id) {
    return detailRepository.findById(id);
  }

  public Detail save(Detail detail) {
    detailRepository.save(detail);
    return detail;
  }

  public Detail update(Detail detail) {
    detailRepository.update(detail);
    return detail;
  }

  public void deleteById(int id) {
    detailRepository.deleteById(id);
  }

}
