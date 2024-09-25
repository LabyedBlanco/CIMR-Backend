package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface ActionRepository extends JpaRepository<Action, Long> {

  @Transactional
  void deleteActionById(Long id);

  Optional<Action> findActionById(Long id);

  @Query("SELECT a FROM Action a WHERE a.id = :id")
  Action findByIdNotOptional(@Param("id") Long id);

  Set<Action> findAllByProjetIdOrderByDatelimite(Long projetId);

  @Modifying
  @Query("UPDATE Action a SET a.etat = 'Pas terminer' WHERE a.datelimite <= CURRENT_DATE")
  void CheckdateLimit();

}
