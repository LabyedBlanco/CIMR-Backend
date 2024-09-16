package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Model.Planification;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.PlanificationRepository;
import com.example.CIMR_DSI.Repo.TrimestreRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class TrimestreService {

  private TrimestreRepository trimestreRepository;
  private PlanificationRepository planificationRepository;

  public TrimestreService(TrimestreRepository trimestreRepository, PlanificationRepository planificationRepository) {
    this.trimestreRepository = trimestreRepository;
    this.planificationRepository = planificationRepository;
  }

  public Trimestre addTrimestre(Trimestre trimestre) {
    Planification planification = trimestre.getPlanification();
    Planification planificationToSaveIn = planificationRepository.findByIdNotOptional(planification.getId());
    if (planificationToSaveIn == null) {
      return null;
    }
    trimestre.setCreationDate(LocalDateTime.now());
    return trimestreRepository.save(trimestre);
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

  public void deleteTrimestreById(Long id) {
    trimestreRepository.deleteById(id);
  }

}
