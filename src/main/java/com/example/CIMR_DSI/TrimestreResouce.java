package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Repo.TrimestreRepository;
import com.example.CIMR_DSI.Service.TrimestreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Trimestre")
public class TrimestreResouce {

  final private TrimestreRepository trimestreRepository;
  final private TrimestreService trimestreService;

  public TrimestreResouce(TrimestreRepository trimestreRepository, TrimestreService trimestreService) {
    this.trimestreRepository = trimestreRepository;
    this.trimestreService = trimestreService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Trimestre>> getAllTrimestre() {
    List<Trimestre> trimestre = trimestreService.findAllTrimestres();
    return new ResponseEntity<>(trimestre, HttpStatus.OK);
  }

  @GetMapping("/Current")
  public ResponseEntity<Trimestre> getCurrentTrimestre() {
    Trimestre trimestre = trimestreService.FindCurrentTrimestre();
    return new ResponseEntity<>(trimestre, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Trimestre> findTrimestrebyid(@PathVariable("id") Long id) {
    Trimestre trimestre = trimestreService.findTrimestrebyId(id);
    return new ResponseEntity<>(trimestre, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Trimestre> addTrimestre(@RequestBody Trimestre trimestre) {
    Trimestre trimestre1 = trimestreService.addTrimestre(trimestre);
    return new ResponseEntity<>(trimestre1, HttpStatus.OK);
  }

  @PostMapping("/update")
  public ResponseEntity<Trimestre> updateTrimestre(Trimestre trimestre) {
    Trimestre trimestre1 = trimestreService.updateTrimestre(trimestre);
    return new ResponseEntity<>(trimestre1, HttpStatus.OK);
  }

  @GetMapping("/delete/{id}")
  public ResponseEntity<Trimestre> deleteTrimestrebyid(@PathVariable("id") Long id) {
    trimestreService.deleteTrimestreById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
