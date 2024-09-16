package com.example.CIMR_DSI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
public class CollaborateurProjet {
  @EmbeddedId
  private CollaborateurProjetId id;

  @ManyToOne
  @MapsId("collaborateurId")
  @JoinColumn(name = "collaborateur_id")
  private Collaborateur collaborateur;

  @JsonBackReference
  @ManyToOne
  @MapsId("projetId")
  @JoinColumn(name = "projet_id")
  private Projet projet;

  private Long DevAS400;
  private Long DevNTIC;
  private Long WINDEV;

  public CollaborateurProjet(CollaborateurProjetId id, Collaborateur collaborateur, Projet projet, Long devAS400,
      Long devNTIC, Long WINDEV, Long integrationcoordination, Long analyse) {
    this.id = id;
    this.collaborateur = collaborateur;
    this.projet = projet;
    DevAS400 = devAS400;
    DevNTIC = devNTIC;
    this.WINDEV = WINDEV;
    Integrationcoordination = integrationcoordination;
    Analyse = analyse;
  }

  public CollaborateurProjet() {

  }

  private Long ControleQualite;

  public Long getControleQualite() {
    return ControleQualite;
  }

  public void setControleQualite(Long controleQualite) {
    ControleQualite = controleQualite;
  }

  private Long Integrationcoordination;
  private Long Analyse;

  public CollaborateurProjetId getId() {
    return id;
  }

  public void setId(CollaborateurProjetId id) {
    this.id = id;
  }

  public Collaborateur getCollaborateur() {
    return collaborateur;
  }

  public void setCollaborateur(Collaborateur collaborateur) {
    this.collaborateur = collaborateur;
  }

  public Projet getProjet() {
    return projet;
  }

  public void setProjet(Projet projet) {
    this.projet = projet;
  }

  public Long getDevAS400() {
    return DevAS400;
  }

  public void setDevAS400(Long devAS400) {
    DevAS400 = devAS400;
  }

  public Long getDevNTIC() {
    return DevNTIC;
  }

  public void setDevNTIC(Long devNTIC) {
    DevNTIC = devNTIC;
  }

  public Long getWINDEV() {
    return WINDEV;
  }

  public void setWINDEV(Long WINDEV) {
    this.WINDEV = WINDEV;
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
}
