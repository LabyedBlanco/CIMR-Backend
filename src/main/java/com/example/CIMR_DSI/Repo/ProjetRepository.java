package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.Action;
import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.Projet;

import org.hibernate.mapping.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

  @Transactional
  void deleteById(Long id);

  Optional<Projet> findProjetById(Long id);

  @Query("SELECT a FROM Projet a WHERE a.id = :id")
  Projet findByIdNotOptional(@Param("id") Long id);

  Set<Projet> findByTrimestreId(Long id);

}
