package com.example.CIMR_DSI.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Collaborateur implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  private Long droitdecongee;
  private String compentence;
  private String imageurl;
  private String Role;
  private String password;

  @OneToMany(mappedBy = "collaborateur", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<CollaborateurProjet> collabprojet;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  private String nom;
  private String prenom;
  private String about;
  private String email;

  public Long getChargedisponible() {
    return chargedisponible;
  }

  public void setChargedisponible(Long chargedisponible) {
    this.chargedisponible = chargedisponible;
  }

  private Long chargedisponible;
  private LocalDate creele;

  public LocalDate getCreele() {
    return creele;
  }

  public Collaborateur(Long id, Long droitdecongee, String compentence, String imageurl, String role, String password,
      String nom, String prenom, String about, String email, Long chargedisponible, LocalDate creele) {
    this.id = id;

    this.droitdecongee = droitdecongee;
    this.compentence = compentence;
    this.imageurl = imageurl;
    Role = role;
    this.password = password;
    this.nom = nom;
    this.prenom = prenom;
    this.about = about;
    this.email = email;
    this.chargedisponible = chargedisponible;
    this.creele = creele;
  }

  public void setCreele(LocalDate creele) {
    this.creele = creele;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<CollaborateurProjet> getCollabprojet() {
    return collabprojet;
  }

  public void setCollabprojet(Set<CollaborateurProjet> collaborateurprojets) {
    collabprojet = collaborateurprojets;
  }

  public Set<CollaborateurTrimestre> getTrimsestres() {
    return trimsestres;
  }

  public void setTrimsestres(Set<CollaborateurTrimestre> trimsestres) {
    this.trimsestres = trimsestres;
  }

  @OneToMany(mappedBy = "collaborateur")
  private Set<CollaborateurTrimestre> trimsestres;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getDroitdecongee() {
    return droitdecongee;
  }

  public void setDroitdecongee(Long droitdecongee) {
    this.droitdecongee = droitdecongee;
  }

  public String getCompentence() {
    return compentence;
  }

  public void setCompentence(String compentence) {
    this.compentence = compentence;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }

  public String getRole() {
    return Role;
  }

  public void setRole(String role) {
    Role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Collaborateur() {

  }
}
