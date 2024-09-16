package com.example.CIMR_DSI.Model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Trimestre implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, nullable = false)
  private Long id;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "trimestre", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("trimestre")
  private Set<Projet> projetList;

  public Set<Projet> getProjetList() {
    return projetList;
  }

  public void setProjetList(Set<Projet> projetList) {
    this.projetList = projetList;
  }

  private Long TrimestreNumber;
  private LocalDateTime creationDate;

  public Long getTrimestreNumber() {
    return TrimestreNumber;
  }

  public void setTrimestreNumber(Long trimestreNumber) {
    TrimestreNumber = trimestreNumber;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationdate) {
    this.creationDate = creationdate;
  }

  public Long getOrder() {
    return TrimestreNumber;
  }

  public void setOrder(Long order) {
    if (order <= 3) {
      this.TrimestreNumber = order;
    }

  }

  private String nomtrimestre;
  private Long AS400disponible;
  private Long NTICdisponible;
  private Long WINDEVdisponible;
  private Long Analysedisponible;
  private Long Integrationcoordination;
  private Long WINDEVaconsomer;
  private Long Analyseaconsomer;
  private Long Integrationcoordinationdisponible;

  private Long AS400aconsomer;
  private Long NTICaconsomer;
  private float coefficientmaintenence;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "planification_id")
  @JsonIgnoreProperties("trimestre")
  private Planification planification;

  public Planification getPlanification() {
    return planification;
  }

  public void setPlanification(Planification planification) {
    this.planification = planification;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomtrimestre() {
    return nomtrimestre;
  }

  public void setNomtrimestre(String nomtrimestre) {
    this.nomtrimestre = nomtrimestre;
  }

  public Long getAS400disponible() {
    return AS400disponible;
  }

  public void setAS400disponible(Long AS400disponible) {
    this.AS400disponible = AS400disponible;
  }

  public Long getNTICdisponible() {
    return NTICdisponible;
  }

  public void setNTICdisponible(Long NTICdisponible) {
    this.NTICdisponible = NTICdisponible;
  }

  public Long getWINDEVdisponible() {
    return WINDEVdisponible;
  }

  public void setWINDEVdisponible(Long WINDEVdisponible) {
    this.WINDEVdisponible = WINDEVdisponible;
  }

  public Long getAnalysedisponible() {
    return Analysedisponible;
  }

  public void setAnalysedisponible(Long analysedisponible) {
    Analysedisponible = analysedisponible;
  }

  public Long getIntegrationcoordination() {
    return Integrationcoordination;
  }

  public void setIntegrationcoordination(Long integrationcoordination) {
    Integrationcoordination = integrationcoordination;
  }

  public Long getWINDEVaconsomer() {
    return WINDEVaconsomer;
  }

  public void setWINDEVaconsomer(Long WINDEVaconsomer) {
    this.WINDEVaconsomer = WINDEVaconsomer;
  }

  public Long getAnalyseaconsomer() {
    return Analyseaconsomer;
  }

  public void setAnalyseaconsomer(Long analyseaconsomer) {
    Analyseaconsomer = analyseaconsomer;
  }

  public Long getIntegrationcoordinationdisponible() {
    return Integrationcoordinationdisponible;
  }

  public void setIntegrationcoordinationdisponible(Long integrationcoordinationdisponible) {
    Integrationcoordinationdisponible = integrationcoordinationdisponible;
  }

  public Long getAS400aconsomer() {
    return AS400aconsomer;
  }

  public void setAS400aconsomer(Long AS400aconsomer) {
    this.AS400aconsomer = AS400aconsomer;
  }

  public Long getNTICaconsomer() {
    return NTICaconsomer;
  }

  public void setNTICaconsomer(Long NTICaconsomer) {
    this.NTICaconsomer = NTICaconsomer;
  }

  public float getCoefficientmaintenence() {
    return coefficientmaintenence;
  }

  public void setCoefficientmaintenence(float coefficientmaintenence) {
    this.coefficientmaintenence = coefficientmaintenence;
  }

  public Trimestre(Long id, String nomtrimestre, Long AS400disponible, Long NTICdisponible, Long WINDEVdisponible,
      Long analysedisponible, Long integrationcoordination, Long WINDEVaconsomer, Long analyseaconsomer,
      Long integrationcoordinationdisponible, Long AS400aconsomer, Long NTICaconsomer, float coefficientmaintenence) {
    this.id = id;
    this.nomtrimestre = nomtrimestre;
    this.AS400disponible = AS400disponible;
    this.NTICdisponible = NTICdisponible;
    this.WINDEVdisponible = WINDEVdisponible;
    Analysedisponible = analysedisponible;
    Integrationcoordination = integrationcoordination;
    this.WINDEVaconsomer = WINDEVaconsomer;
    Analyseaconsomer = analyseaconsomer;
    Integrationcoordinationdisponible = integrationcoordinationdisponible;
    this.AS400aconsomer = AS400aconsomer;
    this.NTICaconsomer = NTICaconsomer;
    this.coefficientmaintenence = coefficientmaintenence;
  }

  public Trimestre() {

  }
}
