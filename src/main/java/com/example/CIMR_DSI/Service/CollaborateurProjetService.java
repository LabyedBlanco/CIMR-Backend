package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurProjet;
import com.example.CIMR_DSI.Model.CollaborateurProjetId;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.CollaborateurProjetRepository;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.Repo.ProjetRepository;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

@Service
public class CollaborateurProjetService {
  @Autowired
  private CollaborateurProjetRepository collaborateurProjetRepository;

  @Autowired
  private CollaborateurRepository collaborateurRepository;

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private ProjetRepository projetRepository;

  @Transactional
  public CollaborateurProjet assignCollaborateurToProjet(Long collaborateurId, Long projetId,
      CollaborateurProjet collaborateurProjet) {
    Collaborateur collaborateur = collaborateurRepository.findCollaborateurById(collaborateurId)
        .orElseThrow(() -> new EntityNotFoundException("Collaborateur not found"));

    Projet projet = projetRepository.findById(projetId)
        .orElseThrow(() -> new EntityNotFoundException("Projet not found"));

    collaborateurProjet.setId(new CollaborateurProjetId(collaborateurId, projetId));
    collaborateurProjet.setCollaborateur(collaborateur);
    collaborateurProjet.setProjet(projet);

    return collaborateurProjetRepository.save(collaborateurProjet);
  }

  @Transactional
  public CollaborateurProjet assignCollabToProjet(CollaborateurProjet collaborateurProjet) {
    Collaborateur vfCollab = collaborateurProjet.getCollaborateur();
    Collaborateur collaborateur = collaborateurRepository.findCollaborateurById(vfCollab.getId())
        .orElseThrow(() -> new EntityNotFoundException("Collaborateur not found"));

    Projet vfProjet = collaborateurProjet.getProjet();
    Projet projet = projetRepository.findById(vfProjet.getId())
        .orElseThrow(() -> new EntityNotFoundException("Projet not found"));

    collaborateurProjet.setId(new CollaborateurProjetId(vfCollab.getId(), vfProjet.getId()));

    try {
      authenticationService.sendAssigningEmail(vfCollab, vfProjet);
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return collaborateurProjetRepository.save(collaborateurProjet);
  }

  @Transactional
  public void removeCollabFromProjet(CollaborateurProjet collaborateurProjet) {

    Collaborateur vfCollab = collaborateurProjet.getCollaborateur();
    Collaborateur collaborateur = collaborateurRepository.findCollaborateurById(vfCollab.getId())
        .orElseThrow(() -> new EntityNotFoundException("Collaborateur not found"));

    Projet vfProjet = collaborateurProjet.getProjet();
    Projet projet = projetRepository.findById(vfProjet.getId())
        .orElseThrow(() -> new EntityNotFoundException("Projet not found"));

    CollaborateurProjetId id = new CollaborateurProjetId(vfCollab.getId(), vfProjet.getId());
    collaborateurProjetRepository.deleteById(id);

  }

  @Transactional
  public void removeCollaborateurFromProjet(Long collaborateurId, Long projetId) {
    CollaborateurProjetId id = new CollaborateurProjetId(collaborateurId, projetId);
    collaborateurProjetRepository.deleteById(id);
  }

  public Set<Projet> getProjetsForCollaborateur(Long collaborateurId) {
    return collaborateurProjetRepository.findAllProjetByCollaborateurId(collaborateurId);
  }

  public Long CountForCollaborateur(Long collaborateurId) {
    return collaborateurProjetRepository.CountAllProjetByCollaborateurId(collaborateurId);
  }

  public Set<Collaborateur> getCollaborateursForProject(Long projetId) {
    return collaborateurProjetRepository.findAllCollaborateurByProjetId(projetId);
  }

  public Set<Projet> getProjetfotCollbInTrimestre(Long CollabId, Long TrimestreId) {
    return collaborateurProjetRepository.findAllProjetByCollaborateurandTrimestreId(CollabId, TrimestreId);
  }

  public List<Collaborateur> getCollaborateurNotInProjet(Long projetId) {
    return collaborateurProjetRepository.findCollaborateursNotInProjet(projetId);
  }

  // Add more methods as needed
}
