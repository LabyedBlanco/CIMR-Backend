package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Repo.ProjetRepository;
import com.example.CIMR_DSI.Service.ProjetService;
import com.example.CIMR_DSI.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Projet")
public class ProjetResouce {

  ProjetService projetService;
  ProjetRepository projetRepository;

  public ProjetResouce(ProjetService projetService, ProjetRepository projetRepository) {
    this.projetRepository = projetRepository;
    this.projetService = projetService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Projet>> getAllprojet() {
    List<Projet> projet = projetService.findAllProjet();
    return new ResponseEntity<>(projet, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Projet> getProjetbyid(@PathVariable("id") Long id) {
    Projet projet = projetService.findProjetbyid(id);
    return new ResponseEntity<>(projet, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Projet> addProjet(@RequestBody Projet projet) {
    Projet newprojet = projetService.addProjet(projet);
    return new ResponseEntity<>(newprojet, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Projet> updateProjet(@RequestBody Projet projet) {

    Projet updateprojet = projetService.updateProjet(projet);
    return new ResponseEntity<>(updateprojet, HttpStatus.OK);
  }

  @PutMapping("/remarque")
  public ResponseEntity<Projet> Onremarque(@RequestBody Projet projet) {

    Projet updateprojet = projetService.ajouterRemarque(projet);
    return new ResponseEntity<>(updateprojet, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    projetService.deleteProjetbyid(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/Projet/{id}")
  public ResponseEntity<Trimestre> getTrimestreProjetsbyId(@PathVariable("id") Long id) {
    projetService.findByTrimestreById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
