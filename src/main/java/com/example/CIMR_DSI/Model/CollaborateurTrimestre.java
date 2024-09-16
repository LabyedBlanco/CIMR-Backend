package com.example.CIMR_DSI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class CollaborateurTrimestre {

  @EmbeddedId
  private CollaborateurTrimestreId id;

  @ManyToOne
  @MapsId("collaborateurId")
  @JoinColumn(name = "collaborateur_id")
  @JsonBackReference
  private Collaborateur collaborateur;

  @ManyToOne
  @MapsId("trimestreId")
  @JoinColumn(name = "trimestre_id")
  private Trimestre trimestre;


  private Long DevNTIC;
  private Long DevAS400;
  private Long WINDEV;
  private Long Integrationcoordination ;
  private Long Analyse ;
}
