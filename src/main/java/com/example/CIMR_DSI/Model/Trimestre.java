package com.example.CIMR_DSI.Model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.cglib.core.Local;

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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "trimestre", cascade = CascadeType.REMOVE)
  private Set<CollaborateurTrimestre> collaborateurs;

  private int totaldisponibledejour;
  private float coefficientmaintence;
  private int joursferiee;
  private LocalDate DateDebut;
  private LocalDate DateFin;

  public LocalDate getDateDebut() {
    return DateDebut;
  }

  public void setDateDebut(LocalDate dateDebut) {
    DateDebut = dateDebut;
  }

  public LocalDate getDateFin() {
    return DateFin;
  }

  public void setDateFin(LocalDate dateFin) {
    DateFin = dateFin;
  }

  public int getTotaldisponibledejour() {
    return totaldisponibledejour;
  }

  public void setTotaldisponibledejour(int totaldisponibledejour) {
    this.totaldisponibledejour = totaldisponibledejour;
  }

  public float getCoefficientmaintence() {
    return coefficientmaintence;
  }

  public void setCoefficientmaintence(float coefficientmaintence) {
    this.coefficientmaintence = coefficientmaintence;
  }

  public int getJoursferiee() {
    return joursferiee;
  }

  public void setJoursferiee(int joursferiee) {
    this.joursferiee = joursferiee;
  }

  private int ordre;

  public Set<Projet> getProjetList() {
    return projetList;
  }

  public void setProjetList(Set<Projet> projetList) {
    this.projetList = projetList;
  }

  private LocalDateTime creationDate;

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationdate) {
    this.creationDate = creationdate;
  }

  private String nomtrimestre;

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

  public Trimestre(Long id, String nomtrimestre, float coefficientmaintenence) {
    this.id = id;
    this.nomtrimestre = nomtrimestre;
  }

  public Trimestre() {

  }

  public int getOrdre() {
    return ordre;
  }

  public void setOrdre(int ordre) {
    this.ordre = ordre;
  }
}
