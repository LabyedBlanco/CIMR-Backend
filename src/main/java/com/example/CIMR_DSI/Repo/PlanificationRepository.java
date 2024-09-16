package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Model.Planification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlanificationRepository extends JpaRepository<Planification, Long> {

  @Transactional
  void deletePlanificationById(Long id);

  Optional<Planification> findPlanificationById(Long id);

  @Query("Select p from Planification p WHERE p.id = 1")
  Planification getCurrentPlanification();

  @Query("SELECT a FROM Planification a WHERE a.id = :id")
  Planification findByIdNotOptional(@Param("id") Long id);
}
