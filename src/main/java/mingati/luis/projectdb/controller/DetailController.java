package mingati.luis.projectdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mingati.luis.projectdb.model.Detail;
import mingati.luis.projectdb.service.DetailService;

@RestController
@RequestMapping("/details")
public class DetailController {
  @Autowired
  private DetailService detailService;

  @GetMapping
  public List<Detail> getAllDetails() {
    return detailService.findAll();
  }

  @GetMapping("/{id}")
  public Detail getDetailById(@PathVariable("id") int id) {
    return detailService.findById(id);
  }

  @PostMapping
  public Detail createDetail(@RequestBody Detail detail) {
    return detailService.save(detail);
  }

  @PutMapping("/{id}")
  public Detail updateDetail(@PathVariable("id") int id, @RequestBody Detail detail) {
    detail.setId(id);
    return detailService.update(detail);
  }

  @DeleteMapping("/{id}")
  public void deleteDetail(@PathVariable("id") int id) {
    detailService.deleteById(id);
  }
}
