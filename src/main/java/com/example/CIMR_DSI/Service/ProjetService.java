package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.ProjetRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProjetService {

  private ProjetRepository projetRepository;

  public ProjetService(ProjetRepository projetRepository) {
    this.projetRepository = projetRepository;
  }

  public Projet addProjet(Projet projet) {

    projet.setCreele(LocalDate.now());
    projet.setChargeestimee(projet.getAnalyse() + projet.getInfra() + projet.getControlequalite()
        + projet.getChargeAS400() + projet.getIntegrationcoordination() + projet.getChargeNTIC());
    return projetRepository.save(projet);
  }

  public List<Projet> findAllProjet() {
    return projetRepository.findAll();
  }

  public Projet updateProjet(Projet projet) {
    Projet ProjetToSave = projetRepository.findByIdNotOptional(projet.getId());
    projet.setChargeestimee(projet.getAnalyse() + projet.getInfra() + projet.getControlequalite()
        + projet.getChargeAS400() + projet.getIntegrationcoordination() + projet.getChargeNTIC());

    if (ProjetToSave == null) {
      return null;
    } else {

      return projetRepository.save(projet);
    }
  }

  public Projet chiffrerProjet(Long projet) {
    Projet ProjetToSave = projetRepository.findByIdNotOptional(projet);

    if (ProjetToSave == null) {
      return null;
    } else {
      ProjetToSave.setChiffrer(true);
      return projetRepository.save(ProjetToSave);
    }
  }

  public Projet ajouterRemarque(Projet projet) {
    Projet ProjetToSave = projetRepository.findByIdNotOptional(projet.getId());
    if (ProjetToSave == null) {
      return null;
    } else {
      ProjetToSave.setRemarque(projet.getRemarque());
      return projetRepository.save(ProjetToSave);
    }

  }

  public Projet findProjetbyid(Long id) {
    return projetRepository.findProjetById(id)
        .orElseThrow(() -> new UserNotFoundException("le Projet avec cette id " + id + "est introuvable : "));
  }

  public void deleteProjetbyid(Long id) {
    projetRepository.deleteById(id);
  }

  public Set<Projet> findByTrimestreById(Long id) {
    return projetRepository.findByTrimestreId(id);
  }

}
