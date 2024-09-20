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
  private Long Integrationcoordination;
  private Long Analyse;
  private Long ControleQualite;
  private Long Infra;

  public CollaborateurTrimestreId getId() {
    return id;
  }

  public void setId(CollaborateurTrimestreId id) {
    this.id = id;
  }

  public Collaborateur getCollaborateur() {
    return collaborateur;
  }

  public void setCollaborateur(Collaborateur collaborateur) {
    this.collaborateur = collaborateur;
  }

  public Trimestre getTrimestre() {
    return trimestre;
  }

  public void setTrimestre(Trimestre trimestre) {
    this.trimestre = trimestre;
  }

  public Long getDevNTIC() {
    return DevNTIC;
  }

  public void setDevNTIC(Long devNTIC) {
    DevNTIC = devNTIC;
  }

  public Long getDevAS400() {
    return DevAS400;
  }

  public void setDevAS400(Long devAS400) {
    DevAS400 = devAS400;
  }

  public Long getWINDEV() {
    return WINDEV;
  }

  public void setWINDEV(Long wINDEV) {
    WINDEV = wINDEV;
  }

  public Long getIntegrationcoordination() {
    return Integrationcoordination;
  }

  public void setIntegrationcoordination(Long integrationcoordination) {
    Integrationcoordination = integrationcoordination;
  }

  public Long getAnalyse() {
    return Analyse;
  }

  public void setAnalyse(Long analyse) {
    Analyse = analyse;
  }

  public Long getControleQualite() {
    return ControleQualite;
  }

  public void setControleQualite(Long controleQualite) {
    ControleQualite = controleQualite;
  }

  public Long getInfra() {
    return Infra;
  }

  public void setInfra(Long infra) {
    Infra = infra;
  }
}
