package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.Nfe;
import mingati.luis.projectdb.service.NfeService;

@RestController
@RequestMapping("/nfes")
public class NfeController {
  @Autowired
  private NfeService nfeService;

  @GetMapping
  public List<Nfe> getAllNfes() {
    return nfeService.findAll();
  }

  @GetMapping("/{id}")
  public Nfe getNfeById(@PathVariable("id") int id) {
    return nfeService.findById(id);
  }

  @PostMapping
  public Nfe createNfe(@RequestBody Nfe nfe) {
    return nfeService.save(nfe);
  }

  @PutMapping("/{id}")
  public Nfe updateNfe(@PathVariable("id") int id, @RequestBody Nfe nfe) {
    nfe.setId(id);
    return nfeService.update(nfe);
  }

  @DeleteMapping("/{id}")
  public void deleteNfe(@PathVariable("id") int id) {
    nfeService.deleteById(id);
  }
}
