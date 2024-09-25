package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Repo.ActionRepository;
import com.example.CIMR_DSI.Service.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Action")
public class ActionResouce {

  final private ActionService actionService;
  final private ActionRepository actionRepository;

  public ActionResouce(ActionRepository actionRepository, ActionService actionService) {
    this.actionRepository = actionRepository;
    this.actionService = actionService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Action>> getAllAction() {
    List<Action> action = actionService.findAllActions();
    return new ResponseEntity<>(action, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Action> getActionbyId(@PathVariable("id") Long id) {
    Action action = actionService.finActionbyid(id);
    return new ResponseEntity<>(action, HttpStatus.OK);
  }

  @GetMapping("/find/Projet/{id}")
  public ResponseEntity<Set<Action>> getActionByProjet(@PathVariable("id") Long id) {
    Set<Action> action1 = actionService.findActionsByProjetId(id);
    return new ResponseEntity<>(action1, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Action> addAction(@RequestBody Action action) {
    Action newAction = actionService.addAction(action);
    return new ResponseEntity<>(newAction, HttpStatus.CREATED);
  }

  @PutMapping("/Uncompleted")
  public ResponseEntity<Action> UncompletedAction(@RequestBody Action action) {
    Action action1 = actionService.UncompletedAction(action);
    return new ResponseEntity<>(action1, HttpStatus.OK);
  }

  @PutMapping("/Completed")
  public ResponseEntity<Action> CompletedAction(@RequestBody Action action) {
    Action action1 = actionService.CompletedAction(action);
    return new ResponseEntity<>(action1, HttpStatus.OK);
  }

  @PutMapping("/Annuler")
  public ResponseEntity<Action> AnnulerAction(@RequestBody Action action) {
    Action action1 = actionService.AnnulerAction(action);
    return new ResponseEntity<>(action1, HttpStatus.OK);
  }

  @PostMapping("/update")
  public ResponseEntity<Action> updateAction(@RequestBody Action action) {

    Action action1 = actionService.updateAction(action);
    return new ResponseEntity<>(action1, HttpStatus.OK);
  }

  @PostMapping("/assign")
  public ResponseEntity<Action> AssignAction(@RequestBody Action action) {
    actionService.AssignAction(action);
    return new ResponseEntity<>(action, HttpStatus.OK);
  }

  @PutMapping("/update")
  public ResponseEntity<Action> UpdateAction(@RequestBody Action action) {
    actionService.UpdateAction(action);
    return new ResponseEntity<>(action, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteAction(@PathVariable("id") Long id) {
    actionService.deleteActionbyid(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
