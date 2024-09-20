package com.example.CIMR_DSI.Model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Planification implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  private int count;

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void incrementCount() {
    this.count++;
  }

  private String titreplanification;

  @Column(unique = true)
  private LocalDate Dateplanification;

  @OneToMany(mappedBy = "planification", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("planification")
  private Set<Trimestre> trimestre;

  public Set<Trimestre> getTrimestre() {
    return trimestre;
  }

  public void setTrimestre(Set<Trimestre> trimestre) {
    this.trimestre = trimestre;
  }

  public Planification(Long id, String titreplanification, LocalDate dateplanification) {
    this.id = id;
    this.titreplanification = titreplanification;
    Dateplanification = dateplanification;
  }

  public Planification() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitreplanification() {
    return titreplanification;
  }

  public void setTitreplanification(String titreplanification) {
    this.titreplanification = titreplanification;
  }

  public LocalDate getDateplanification() {
    return Dateplanification;
  }

  public void setDateplanification(LocalDate dateplanification) {
    Dateplanification = dateplanification;
  }

}
