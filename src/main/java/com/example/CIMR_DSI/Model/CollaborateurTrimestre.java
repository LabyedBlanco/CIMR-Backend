package com.example.CIMR_DSI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
public class CollaborateurTrimestre {

  @EmbeddedId
  private CollaborateurTrimestreId id;

  @ManyToOne
  @MapsId("collaborateurId")
  @JoinColumn(name = "collaborateur_id")
  @JsonIgnoreProperties("trimestre")
  private Collaborateur collaborateur;

  @ManyToOne
  @MapsId("trimestreId")
  @JoinColumn(name = "trimestre_id")
  @JsonIgnoreProperties("projetList")
  private Trimestre trimestre;

  private int Integrationcoordination;
  private int Analyse;

  public int getAnalyse() {
    return Analyse;
  }

  public void setAnalyse(int analyse) {
    Analyse = analyse;
  }

  private int ControleQualite;
  private int chargedisponible;
  private int totalNetcongee;
  private int maintenence;
  private int chargecompetence;

  public int getSum() {
    return (int) (this.ControleQualite + this.Analyse + this.maintenence + this.Integrationcoordination);
  }

  public int getChargecompetence() {
    return chargecompetence;
  }

  public void setChargecompetence(int chargecompetence) {
    this.chargecompetence = chargecompetence;
  }

  public int getMaintenence() {
    return maintenence;
  }

  public void setMaintenence(int maintenence) {
    this.maintenence = maintenence;
  }

  public int getChargedisponible() {
    return chargedisponible;
  }

  public void setChargedisponible(int chargedisponible) {
    this.chargedisponible = chargedisponible;
  }

  public int getTotalNetcongee() {
    return totalNetcongee;
  }

  public void setTotalNetcongee(int totalNetcongee) {
    this.totalNetcongee = totalNetcongee;
  }

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

  public int getIntegrationcoordination() {
    return Integrationcoordination;
  }

  public void setIntegrationcoordination(int integrationcoordination) {
    Integrationcoordination = integrationcoordination;
  }

  public int getControleQualite() {
    return ControleQualite;
  }

  public void setControleQualite(int controleQualite) {
    ControleQualite = controleQualite;
  }

}
