package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurProjet;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.Service.CollaborateurProjetService;
import com.example.CIMR_DSI.Service.CollaborateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/ProjetCollaborateur")
public class CollaborateurProjetResouce {

  private CollaborateurProjetService collaborateurProjetService;

  public CollaborateurProjetResouce(CollaborateurProjetService collaborateurProjetService) {
    this.collaborateurProjetService = collaborateurProjetService;
  }

  @PostMapping("/assign/{idProjet}/{idCollaborateur}")
  public ResponseEntity<CollaborateurProjet> assignCollaborateurToProjet(

      @PathVariable("idCollaborateur") Long collaborateurId,
      @PathVariable("idProjet") Long projetId,
      @RequestBody CollaborateurProjet collaborateurProjet) {
    CollaborateurProjet result = collaborateurProjetService.assignCollaborateurToProjet(
        collaborateurId,
        projetId,
        collaborateurProjet);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/remove/{idProjet}/{idCollaborateur}")
  public ResponseEntity<Void> removeCollaborateurFromProjet(
      @PathVariable("idCollaborateur") Long collaborateurId,
      @PathVariable("idProjet") Long projetId) {
    collaborateurProjetService.removeCollaborateurFromProjet(collaborateurId, projetId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/projet/{id}/Collaborateurs")
  public ResponseEntity<Set<Collaborateur>> findAllCollaborateur(@PathVariable("id") Long id) {
    Set<Collaborateur> said = collaborateurProjetService.getCollaborateursForProject(id);
    return new ResponseEntity<>(said, HttpStatus.OK);
  }

  @GetMapping("/Collaborateur/{id}/Projet")
  public ResponseEntity<Set<Projet>> findAllProjets(@PathVariable("id") Long id) {
    Set<Projet> projets = collaborateurProjetService.getProjetsForCollaborateur(id);
    return new ResponseEntity<>(projets, HttpStatus.OK);
  }

  @GetMapping("/Collaborateur/{id}/Count/Projet")
  public ResponseEntity<Long> CountAllProjets(@PathVariable("id") Long id) {
    Long projets = collaborateurProjetService.CountForCollaborateur(id);
    return new ResponseEntity<>(projets, HttpStatus.OK);
  }

  @GetMapping("/notin/{id}")
  public ResponseEntity<List<Collaborateur>> getCollabNotInProjet(@PathVariable("id") Long id) {
    List<Collaborateur> collaborateurs = collaborateurProjetService.getCollaborateurNotInProjet(id);
    return new ResponseEntity<>(collaborateurs, HttpStatus.OK);
  }

}
