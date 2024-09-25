package com.example.CIMR_DSI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Action implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  public Projet getProjet() {
    return projet;
  }

  public void setProjet(Projet projet) {
    this.projet = projet;
  }

  @ManyToOne
  @JoinColumn(name = "projet_id")
  @JsonIgnoreProperties("actions")
  private Projet projet;

  private String nomaction;

  private String etat;
  private Long charge;

  public String getEtat() {
    return etat;
  }

  public void setEtat(String etat) {
    this.etat = etat;
  }

  private LocalDate dateaction;

  public Long getCharge() {
    return charge;
  }

  public void setCharge(Long charge) {
    this.charge = charge;
  }

  public LocalDate getDatelimite() {
    return datelimite;
  }

  public void setDatelimite(LocalDate datelimite) {
    this.datelimite = datelimite;
  }

  private LocalDate datelimite;

  @ManyToOne

  private Competence competence;

  @Column(name = "contenue", length = 1000)
  private String Contenue;

  public Action(Long id, Projet projet, String nomaction, String etat, Long charge, LocalDate dateaction,
      LocalDate datelimite, String contenue) {
    this.id = id;
    this.projet = projet;
    this.nomaction = nomaction;
    this.etat = etat;
    this.charge = charge;
    this.dateaction = dateaction;
    this.datelimite = datelimite;

    Contenue = contenue;
  }

  public Action() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomaction() {
    return nomaction;
  }

  public void setNomaction(String nomaction) {
    this.nomaction = nomaction;
  }

  public LocalDate getDateaction() {
    return dateaction;
  }

  public void setDateaction(LocalDate dateaction1) {
    this.dateaction = dateaction1;
  }

  public String getContenue() {
    return Contenue;
  }

  public void setContenue(String contenue) {
    Contenue = contenue;
  }

}
