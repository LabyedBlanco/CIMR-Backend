package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurTrimestre;
import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;

import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollaborateurService {

  private CollaborateurRepository collaborateurRepository;
  private CollaborateurProjetService collaborateurProjetService;
  private CollaborateurTrimestreService collaborateurTrimestreService;
  private TrimestreService trimestreService;

  public CollaborateurService(CollaborateurRepository collaborateurRepository, TrimestreService trimestreService,
      CollaborateurTrimestreService collaborateurTrimestreService,
      CollaborateurProjetService collaborateurProjetService) {
    this.collaborateurRepository = collaborateurRepository;
    this.collaborateurProjetService = collaborateurProjetService;
    this.collaborateurTrimestreService = collaborateurTrimestreService;
    this.trimestreService = trimestreService;

  }

  public Collaborateur addCollaborateur(Collaborateur collaborateur) {

    Date date = new Date(0);

    collaborateurRepository.save(collaborateur);

    Trimestre trimestreActual = trimestreService.FindCurrentTrimestre();

    if (trimestreActual != null) {
      int congee = collaborateur.getDroitdecongee();
      CollaborateurTrimestre collaborateurTrimestre = new CollaborateurTrimestre();

      collaborateurTrimestre.setTotalNetcongee(trimestreActual.getTotaldisponibledejour() - congee);

      int x = (int) (collaborateurTrimestre.getTotalNetcongee() * (trimestreActual.getCoefficientmaintence() / 100));
      collaborateurTrimestre.setMaintenence(x);

      collaborateurTrimestre.setChargedisponible(collaborateurTrimestre.getTotalNetcongee() - x);
      collaborateurTrimestre.setAnalyse((int) (collaborateurTrimestre.getTotalNetcongee() * 0.2));
      collaborateurTrimestre.setControleQualite((int) (collaborateurTrimestre.getTotalNetcongee() * 0));
      collaborateurTrimestre.setIntegrationcoordination((int) (collaborateurTrimestre.getTotalNetcongee() * 0.3));
      collaborateurTrimestre
          .setChargecompetence(collaborateurTrimestre.getTotalNetcongee() - collaborateurTrimestre.getSum());

      collaborateurTrimestreService.assignCollaborateurToTrimestre(collaborateur.getId(), trimestreActual.getId(),
          collaborateurTrimestre);
    }

    return collaborateur;
  }

  public Collaborateur updateCollaborateur(Collaborateur collaborateur) {
    return collaborateurRepository.save(collaborateur);
  }

  public List<Collaborateur> findAllCollaborateur() {
    return collaborateurRepository.findAll();
  }

  @Transactional
  public void deleteCollaborateurbyid(Long id) {
    collaborateurRepository.deleteCollaborateurById(id);
  }

  public Collaborateur findCollaborateurbyid(Long id) {
    return collaborateurRepository.findCollaborateurById(id)
        .orElseThrow(() -> new UserNotFoundException("The Collaboratuer with this id : " + id + " is Not Found :  "));
  }

  public List<Map<String, Object>> getAllCollaborateursWithProjetCount() {
    List<Object[]> results = collaborateurRepository.findAllCollaborateursWithProjetCount();
    List<Map<String, Object>> response = new ArrayList<>();

    for (Object[] result : results) {
      Collaborateur collaborateur = (Collaborateur) result[0]; // First element is Collaborateur
      Long projetCount = (Long) result[1]; // Second element is the project count

      // Create a map to hold the Collaborateur and project count
      Map<String, Object> data = new HashMap<>();
      data.put("collaborateur", collaborateur);
      data.put("projetCount", projetCount);

      response.add(data);
    }

    return response;
  }

  public Collaborateur getCollaborateursNotInProjet(Long idP) {
    return null;
  }

}
