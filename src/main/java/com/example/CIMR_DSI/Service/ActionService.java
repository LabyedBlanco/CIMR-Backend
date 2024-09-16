package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.ActionRepository;
import com.example.CIMR_DSI.Repo.ProjetRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ActionService {
  private ActionRepository actionRepository;
  private ProjetRepository projetRepository;

  public ActionService(ActionRepository actionRepository, ProjetRepository projetRepository) {
    this.actionRepository = actionRepository;
    this.projetRepository = projetRepository;
  }

  public Action addAction(Action action) {
    Projet projet = action.getProjet();
    Projet ProjetToSave = projetRepository.findByIdNotOptional(projet.getId());

    if (ProjetToSave == null) {
      return null;
    }
    action.setDateaction(LocalDate.now());
    System.out.println(LocalDate.now());
    return actionRepository.save(action);
  }

  public Set<Action> findActionsByProjetId(Long id) {
    return actionRepository.findAllByProjetIdOrderByDatelimite(id);
  }

  public Action updateAction(Action action) {
    return actionRepository.save(action);
  }

  public Action AssignAction(Action action) {
    Action actionToSave = actionRepository.findByIdNotOptional(action.getId());
    if (actionToSave == null)
      return null;
    actionToSave.setDateaction(LocalDate.now());
    actionToSave.setEtat("Hello");
    return actionRepository.save(actionToSave);
  }

  public Action CompletedAction(Action action) {
    // action.setEtat("Action Terminé ");
    Action actionToSave = actionRepository.findByIdNotOptional(action.getId());
    if (actionToSave == null)
      return null;
    actionToSave.setEtat("Action Terminé");
    return actionRepository.save(actionToSave);
  }

  public Action UpdateAction(Action action) {
    return actionRepository.save(action);
  }

  public Action UncompletedAction(Action action) {

    Action actionToSave = actionRepository.findByIdNotOptional(action.getId());
    if (actionToSave == null)
      return null;
    actionToSave.setEtat("Action En cours");
    return actionRepository.save(actionToSave);
  }

  public Action AnnulerAction(Action action) {
    // action.setEtat("Action Terminé ");
    Action actionToSave = actionRepository.findByIdNotOptional(action.getId());
    if (actionToSave == null)
      return null;
    actionToSave.setEtat("Actions Annuler");
    return actionRepository.save(actionToSave);
  }

  @Transactional
  public void deleteActionbyid(Long id) {
    actionRepository.deleteActionById(id);
  }

  public List<Action> findAllActions() {
    return actionRepository.findAll();
  }

  public Action finActionbyid(Long id) {
    return actionRepository.findActionById(id)
        .orElseThrow(() -> new UserNotFoundException("Action with this id was not Found :" + id));
  }

}
