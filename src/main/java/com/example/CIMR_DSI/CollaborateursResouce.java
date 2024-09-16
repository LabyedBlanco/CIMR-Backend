package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.Service.CollaborateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Collaborateurs")
public class CollaborateursResouce {

  final private CollaborateurService collaborateurService;
  final private CollaborateurRepository collaborateurRepository;

  public CollaborateursResouce(CollaborateurService collaborateurService,
      CollaborateurRepository collaborateurRepository) {
    this.collaborateurRepository = collaborateurRepository;
    this.collaborateurService = collaborateurService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Collaborateur>> getAllCollaborateur() {
    List<Collaborateur> collaborateur = collaborateurService.findAllCollaborateur();
    return new ResponseEntity<>(collaborateur, HttpStatus.OK);
  }

  @GetMapping("/all/count")
  public ResponseEntity<List<Map<String, Object>>> getAllCollaborateurCount() {
    List<Map<String, Object>> collaborateur = collaborateurService.getAllCollaborateursWithProjetCount();
    return new ResponseEntity<>(collaborateur, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Collaborateur> addCollaborateur(@RequestBody Collaborateur collaborateur) {
    Collaborateur collaborateur1 = collaborateurService.addCollaborateur(collaborateur);
    return new ResponseEntity<>(collaborateur1, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  public ResponseEntity<Collaborateur> updateCollaborateur(@RequestBody Collaborateur collaborateur) {
    Collaborateur updatecollaborateur = collaborateurService.updateCollaborateur(collaborateur);
    return new ResponseEntity<>(updatecollaborateur, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Collaborateur> findCollaborateurbyid(@PathVariable("id") Long id) {
    Collaborateur collaborateur1 = collaborateurService.findCollaborateurbyid(id);
    return new ResponseEntity<>(collaborateur1, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteCollaborateurbyid(@PathVariable("id") Long id) {
    collaborateurService.deleteCollaborateurbyid(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
