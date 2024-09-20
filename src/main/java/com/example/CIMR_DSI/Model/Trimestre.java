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

  public float getCoefficientmaintenence() {
    return coefficientmaintenence;
  }

  public void setCoefficientmaintenence(float coefficientmaintenence) {
    this.coefficientmaintenence = coefficientmaintenence;
  }

  public Trimestre(Long id, String nomtrimestre, float coefficientmaintenence) {
    this.id = id;
    this.nomtrimestre = nomtrimestre;
    this.coefficientmaintenence = coefficientmaintenence;
  }

  public Trimestre() {

  }
}
