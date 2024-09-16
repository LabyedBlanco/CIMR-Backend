package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurProjet;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {

  void deleteCollaborateurById(Long id);

  Optional<Collaborateur> findCollaborateurById(Long id);

  @Query("SELECT c, COUNT(cp.projet) " +
      "FROM Collaborateur c " +
      "LEFT JOIN CollaborateurProjet cp ON cp.collaborateur.id = c.id " +
      "GROUP BY c")
  List<Object[]> findAllCollaborateursWithProjetCount();

}
