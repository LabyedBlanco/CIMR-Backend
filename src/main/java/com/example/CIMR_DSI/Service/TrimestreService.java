package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurTrimestre;
import com.example.CIMR_DSI.Model.Planification;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.Repo.CollaborateurTrimestreRepository;
import com.example.CIMR_DSI.Repo.PlanificationRepository;
import com.example.CIMR_DSI.Repo.TrimestreRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class TrimestreService {

  private TrimestreRepository trimestreRepository;
  private PlanificationRepository planificationRepository;
  private CollaborateurRepository collaborateurRepository;
  private CollaborateurTrimestreService collaborateurTrimestreService;

  public TrimestreService(TrimestreRepository trimestreRepository, PlanificationRepository planificationRepository,
      CollaborateurRepository collaborateurRepository, CollaborateurTrimestreService collaborateurTrimestreService) {
    this.trimestreRepository = trimestreRepository;
    this.planificationRepository = planificationRepository;
    this.collaborateurRepository = collaborateurRepository;
    this.collaborateurTrimestreService = collaborateurTrimestreService;
  }

  public Trimestre addTrimestre(Trimestre trimestre) {
    Planification planification = trimestre.getPlanification();
    Planification planificationToSaveIn = planificationRepository.findByIdNotOptional(planification.getId());

    if (planificationToSaveIn == null) {
      return null;
    }
    int joursferiee = trimestre.getJoursferiee();
    int count = planificationToSaveIn.getTrimestreCount();
    if (count + 1 <= 4) {
      trimestre.setOrdre(count + 1);
      trimestre.setCreationDate(LocalDateTime.now());

      int year = LocalDate.now().getYear();
      LocalDate startDate;
      LocalDate endDate;

      switch (count) {
        case 0: // First trimester (January - March)
          startDate = LocalDate.of(year, 1, 1);
          endDate = LocalDate.of(year, 3, 31);
          break;
        case 1: // Second trimester (April - June)
          startDate = LocalDate.of(year, 4, 1);
          endDate = LocalDate.of(year, 6, 30);
          break;
        case 2: // Third trimester (July - September)
          startDate = LocalDate.of(year, 7, 1);
          endDate = LocalDate.of(year, 9, 30);
          break;
        case 3: // Fourth trimester (October - December)
          startDate = LocalDate.of(year, 10, 1);
          endDate = LocalDate.of(year, 12, 31);
          break;
        default:
          return null;
      }

      int totalDays = 0;
      int workingDays = 0;
      LocalDate currentDate = startDate;

      while (!currentDate.isAfter(endDate)) {
        totalDays++;
        if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
          workingDays++;
        }
        currentDate = currentDate.plusDays(1);
      }

      trimestre.setTotaldisponibledejour(workingDays - joursferiee);
      trimestre.setDateDebut(startDate);
      trimestre.setDateFin(endDate);

      // Assign Collaborateur Capacities to this Trimestre ;
      trimestreRepository.save(trimestre);

      List<Collaborateur> collaborateurs = collaborateurRepository.findAll();

      for (Collaborateur collaborateur : collaborateurs) {

        if ("user".equals(collaborateur.getRole())) {

          int congee = collaborateur.getDroitdecongee();
          CollaborateurTrimestre collaborateurTrimestre = new CollaborateurTrimestre();

          collaborateurTrimestre.setTotalNetcongee(trimestre.getTotaldisponibledejour() - congee);

          int x = (int) (collaborateurTrimestre.getTotalNetcongee() * (trimestre.getCoefficientmaintence() / 100));
          collaborateurTrimestre.setMaintenence(x);

          collaborateurTrimestre.setChargedisponible(collaborateurTrimestre.getTotalNetcongee() - x);
          collaborateurTrimestre.setAnalyse((int) (collaborateurTrimestre.getTotalNetcongee() * 0.2));
          collaborateurTrimestre.setControleQualite((int) (collaborateurTrimestre.getTotalNetcongee() * 0));
          collaborateurTrimestre.setIntegrationcoordination((int) (collaborateurTrimestre.getTotalNetcongee() * 0.3));
          collaborateurTrimestre
              .setChargecompetence(collaborateurTrimestre.getTotalNetcongee() - collaborateurTrimestre.getSum());

          collaborateurTrimestreService.assignCollaborateurToTrimestre(collaborateur.getId(), trimestre.getId(),
              collaborateurTrimestre);
        }
      }

    }
    return trimestre;
  }

  public Trimestre FindCurrentTrimestre() {
    return trimestreRepository.findFirstByOrderByCreationDateDesc();
  }

  public List<Trimestre> findAllTrimestres() {
    return trimestreRepository.findAll();
  }

  public Trimestre updateTrimestre(Trimestre trimestre) {
    return trimestreRepository.save(trimestre);
  }

  public Trimestre findTrimestrebyId(Long id) {
    return trimestreRepository.findTrimestreById(id)
        .orElseThrow(() -> new UserNotFoundException("Projets avec id = " + id + " est introuvable ! "));

  }

  @Transactional
  public void deleteTrimestreById(Long id) {
    trimestreRepository.deleteById(id);
  }

}
