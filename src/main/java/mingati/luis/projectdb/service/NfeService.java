package mingati.luis.projectdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mingati.luis.projectdb.model.Nfe;
import mingati.luis.projectdb.repository.NfeRepository;

@Service
public class NfeService {
  @Autowired
  private NfeRepository nfeRepository;

  public List<Nfe> findAll() {
    return nfeRepository.findAll();
  }

  public Nfe findById(int id) {
    return nfeRepository.findById(id);
  }

  public Nfe save(Nfe nfe) {
    nfeRepository.save(nfe);
    return nfe;
  }

  public Nfe update(Nfe nfe) {
    nfeRepository.update(nfe);
    return nfe;
  }

  public void deleteById(int id) {
    nfeRepository.deleteById(id);
  }
}
