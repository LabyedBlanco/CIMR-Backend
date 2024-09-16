package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CollaborateurProjetRepository extends JpaRepository<CollaborateurProjet, CollaborateurProjetId> {
  Set<Projet> findByCollaborateurId(Long collaborateurId);

  @Query("SELECT cp.collaborateur FROM CollaborateurProjet cp WHERE cp.projet.id = :id")
  Set<Collaborateur> findAllCollaborateurByProjetId(@Param("id") Long projetId);

  @Query("SELECT cp.projet FROM CollaborateurProjet cp WHERE cp.collaborateur.id = :id")
  Set<Projet> findAllProjetByCollaborateurId(@Param("id") Long CollaborateurId);

  @Query("SELECT COUNT(cp.projet) FROM CollaborateurProjet cp WHERE cp.collaborateur.id = :id")
  Long CountAllProjetByCollaborateurId(@Param("id") Long CollaborateurId);

  @Query("SELECT DISTINCT c FROM Collaborateur c LEFT JOIN CollaborateurProjet cp ON c = cp.collaborateur AND cp.projet.id = :idProjet WHERE cp.collaborateur IS NULL")
  List<Collaborateur> findCollaborateursNotInProjet(@Param("idProjet") Long idProjet);
}
