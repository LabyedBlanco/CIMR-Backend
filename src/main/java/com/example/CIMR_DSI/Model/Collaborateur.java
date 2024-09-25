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
import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Collaborateur implements Serializable, UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @ManyToOne
  @JoinColumn(name = "competence_id")
  private Competence competence;

  private int droitdecongee;

  public Competence getCompetence() {
    return competence;
  }

  public void setCompetence(Competence competence) {
    this.competence = competence;
  }

  public int getDroitdecongee() {
    return droitdecongee;
  }

  public void setDroitdecongee(int droitdecongee) {
    this.droitdecongee = droitdecongee;
  }

  private String imageurl;
  private String Role;

  @OneToMany(mappedBy = "collaborateur", cascade = CascadeType.ALL, orphanRemoval = true)
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

  private LocalDate creele;

  public LocalDate getCreele() {
    return creele;
  }

  @Column(name = "verification_code", length = 64)
  private String verificationCode;

  private boolean enabled;

  public String getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Column(unique = true, length = 100, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new java.util.ArrayList<>();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  public void setCreele(LocalDate creele) {
    this.creele = creele;
  }

  public Set<CollaborateurProjet> getCollabprojet() {
    return collabprojet;
  }

  public void setCollabprojet(Set<CollaborateurProjet> collaborateurprojets) {
    collabprojet = collaborateurprojets;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "collaborateur", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("collaborateur")
  private Set<CollaborateurTrimestre> trimestre;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Collaborateur() {

  }

  public Set<CollaborateurTrimestre> getTrimestre() {
    return trimestre;
  }

  public void setTrimestre(Set<CollaborateurTrimestre> trimestre) {
    this.trimestre = trimestre;
  }
}
