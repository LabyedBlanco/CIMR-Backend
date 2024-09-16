package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Planification;
import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Repo.PlanificationRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;

import java.util.Set;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanificationService {

    private PlanificationRepository planificationRepository;

    public PlanificationService(PlanificationRepository planificationRepository) {
        this.planificationRepository = planificationRepository;
    }

    public List<Planification> findAllPlanification() {
        return planificationRepository.findAll();
    }

    public Planification getCurrentPlanification() {
        return planificationRepository.getCurrentPlanification();
    }

    public Planification updatePlanification(Planification planification) {
        return planificationRepository.save(planification);
    }

    public Planification addPlanification(Planification planification) {
        return planificationRepository.save(planification);
    }

    public Planification findPlanificationbyid(Long id) {
        return planificationRepository.findPlanificationById(id).orElseThrow(
                () -> new UserNotFoundException("Planification avec cette id" + id + "est introuvable : "));
    }

    public void deletePlanificationbyid(Long id) {
        planificationRepository.deletePlanificationById(id);
    }
}
