package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Planification;
import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Repo.PlanificationRepository;
import com.example.CIMR_DSI.Service.PlanificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Planification")
public class PlanificationResouce {

  private PlanificationRepository planificationRepository;
  private PlanificationService planificationService;

  public PlanificationResouce(PlanificationRepository planificationRepository,
      PlanificationService planificationService) {
    this.planificationRepository = planificationRepository;
    this.planificationService = planificationService;
  }

  @GetMapping("/all")
  ResponseEntity<List<Planification>> getAllPlanification() {
    List<Planification> planification1 = planificationService.findAllPlanification();
    return new ResponseEntity<>(planification1, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  ResponseEntity<Planification> findPlanificationbyid(@PathVariable("id") Long id) {
    Planification planification1 = planificationService.findPlanificationbyid(id);
    return new ResponseEntity<>(planification1, HttpStatus.OK);
  }

  @GetMapping("/Current")
  ResponseEntity<Planification> getCurrentPlanification() {
    Planification planification = planificationService.getCurrentPlanification();
    return new ResponseEntity<>(planification, HttpStatus.OK);
  }

  @PostMapping("/add")
  ResponseEntity<Planification> addPlanifictaion(@RequestBody Planification planification) {
    Planification planification1 = planificationService.addPlanification(planification);
    return new ResponseEntity<>(planification1, HttpStatus.CREATED);
  }

  @PostMapping("/update")
  ResponseEntity<Planification> updatePlanification(@RequestBody Planification planification) {
    Planification planification1 = planificationService.updatePlanification(planification);
    return new ResponseEntity<>(planification1, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  ResponseEntity<Planification> deletePlanificationbyid(@PathVariable("id") Long id) {
    planificationService.deletePlanificationbyid(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
