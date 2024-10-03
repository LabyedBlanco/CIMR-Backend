package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.ProjetRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private CollaborateurProjetService collaborateurProjetService;

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

    if (projet.getAnalyse() != null) {
      projet.setChargeestimee(projet.getAnalyse() + projet.getInfra() + projet.getControlequalite()
          + projet.getChargeAS400() + projet.getIntegrationcoordination() + projet.getChargeNTIC());
      ProjetToSave.setChargeestimee(projet.getChargeestimee());
    }

    if (ProjetToSave == null) {
      return null;
    } else {

      if (projet.getTitre() != null) {
        ProjetToSave.setTitre(projet.getTitre());
      }

      if (projet.getDepartement() != null) {
        ProjetToSave.setDepartement(projet.getDepartement());
      }

      if (projet.getChargeAS400() != 0) {
        ProjetToSave.setChargeAS400(projet.getChargeAS400());
      }

      if (projet.getChargeNTIC() != 0) {
        ProjetToSave.setChargeAS400(projet.getChargeNTIC());
      }

      if (projet.getChargeWINDEV() != 0) {
        ProjetToSave.setChargeWINDEV(projet.getChargeWINDEV());
      }

      if (projet.getControlequalite() != null) {
        ProjetToSave.setControlequalite(projet.getControlequalite());
      }

      if (projet.getDatedebut() != null) {
        ProjetToSave.setDatedebut(projet.getDatedebut());
      }

      if (projet.getDatelimie() != null) {
        ProjetToSave.setDatelimie(projet.getDatelimie());
      }

      if (projet.getTitre() != null) {
        ProjetToSave.setTitre(projet.getTitre());
      }

      if (projet.getInfra() != null) {
        ProjetToSave.setInfra(projet.getInfra());
      }

      if (projet.getRemarque() != null) {
        ProjetToSave.setRemarque(projet.getRemarque());
      }

      return projetRepository.save(ProjetToSave);
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

  @Transactional
  public void deleteProjetbyid(Long id) {
    projetRepository.deleteById(id);
  }

  public Set<Projet> findByTrimestreById(Long id) {
    return projetRepository.findByTrimestreId(id);
  }

}
