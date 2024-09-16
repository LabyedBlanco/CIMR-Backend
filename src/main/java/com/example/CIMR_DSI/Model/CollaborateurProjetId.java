package com.example.CIMR_DSI.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CollaborateurProjetId implements Serializable {


  @Column(name = "collaborateur_id")
  private Long collaborateurId;

  public CollaborateurProjetId(Long collaborateurId, Long projetId) {
    this.collaborateurId = collaborateurId;
    this.projetId = projetId;
  }

  public CollaborateurProjetId(){

  }

  @Column(name = "projet_id")
  private Long projetId;
}
