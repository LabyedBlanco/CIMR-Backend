package com.example.CIMR_DSI.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CollaborateurTrimestreId {

  @Column(name = "collaborateur_id")
  private Long collaborateurId;

  @Column(name = "trimestre_id")
  private Long trimestreId;
}
