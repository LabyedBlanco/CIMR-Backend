package com.example.CIMR_DSI.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;;

@Entity
public class Projet implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "trimestre_id")
  @JsonIgnoreProperties("projetList")
  private Trimestre trimestre;

  public Trimestre getTrimestre() {
    return trimestre;
  }

  public void setTrimestre(Trimestre trimestre) {
    this.trimestre = trimestre;
  }

  @JsonManagedReference
  @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<CollaborateurProjet> projetcollab;

  public Set<CollaborateurProjet> getProjetcollab() {
    return projetcollab;
  }

  public void setProjetcollab(Set<CollaborateurProjet> projetCollaborateurs) {
    projetcollab = projetCollaborateurs;
  }

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "departement_id")
  private Departement departement;

  @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
  private Set<Action> actions;

  private String titre;
  private long chargeNTIC;
  private long chargeWINDEV;
  private long chargeAS400;
  private boolean deleted;
  private boolean aretenir;
  private boolean chiffrer;
  private Long chargeestimee;
  private Long integrationcoordination;
  private Long Controlequalite;
  private Long Infra;
  private Long Analyse;
  private String datelimie;
  private String datedebut;
  private String remarque;
  private LocalDate creele;

  public LocalDate getCreele() {
    return creele;
  }

  public void setCreele(LocalDate creele) {
    this.creele = creele;
  }

  public String getEtatdavancement() {
    return etatdavancement;
  }

  public void setEtatdavancement(String etatdavancement) {
    this.etatdavancement = etatdavancement;
  }

  private String etatdavancement;

  public Set<CollaborateurProjet> getCollaborateurs() {
    return projetcollab;
  }

  public void setCollaborateurs(Set<CollaborateurProjet> said) {
    this.projetcollab = said;
  }

  public void setChargeestimee(Long chargeestimee) {
    this.chargeestimee = chargeestimee;
  }

  public Departement getDepartement() {
    return departement;
  }

  public void setDepartement(Departement departement) {
    this.departement = departement;
  }

  public Set<Action> getActions() {
    return actions;
  }

  public void setActions(Set<Action> actions) {
    this.actions = actions;
  }

  public Projet(Long id, Set<CollaborateurProjet> said, Departement departement, Planification planification,
      Set<Action> actions, String titre, long chargeNTIC, long chargeWINDEV, long chargeAS400,
      boolean deleted, boolean aretenir, boolean chiffrer, Long chargeestimee, Long integrationcoordination,
      Long controlequalite, Long infra, Long analyse, String datelimie,
      String datedebut, String remarque, String etatdavancement) {
    this.id = id;
    this.projetcollab = said;
    this.departement = departement;
    this.actions = actions;
    this.titre = titre;
    this.chargeNTIC = chargeNTIC;
    this.chargeWINDEV = chargeWINDEV;
    this.chargeAS400 = chargeAS400;
    this.deleted = deleted;
    this.aretenir = aretenir;
    this.chiffrer = chiffrer;
    this.chargeestimee = chargeestimee;
    this.integrationcoordination = integrationcoordination;
    Controlequalite = controlequalite;
    Infra = infra;
    Analyse = analyse;
    this.datelimie = datelimie;
    this.datedebut = datedebut;
    this.remarque = remarque;
    this.etatdavancement = etatdavancement;
    this.chargeestimee = chargeAS400 + chargeNTIC + chargeWINDEV + Analyse + Infra + integrationcoordination;
  }

  public Projet() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitre() {
    return titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  public long getChargeNTIC() {
    return chargeNTIC;
  }

  public void setChargeNTIC(long chargeNTIC) {
    this.chargeNTIC = chargeNTIC;
  }

  public long getChargeWINDEV() {
    return chargeWINDEV;
  }

  public void setChargeWINDEV(long chargeWINDEV) {
    this.chargeWINDEV = chargeWINDEV;
  }

  public long getChargeAS400() {
    return chargeAS400;
  }

  public void setChargeAS400(long chargeAS400) {
    this.chargeAS400 = chargeAS400;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public boolean isAretenir() {
    return aretenir;
  }

  public void setAretenir(boolean aretenir) {
    this.aretenir = aretenir;
  }

  public boolean isChiffrer() {
    return chiffrer;
  }

  public void setChiffrer(boolean chiffrer) {
    this.chiffrer = chiffrer;
  }

  public Long getChargeestimee() {
    return chargeestimee;
  }

  public Long getIntegrationcoordination() {
    return integrationcoordination;
  }

  public void setIntegrationcoordination(Long integrationcoordination) {
    this.integrationcoordination = integrationcoordination;
  }

  public Long getControlequalite() {
    return Controlequalite;
  }

  public void setControlequalite(Long controlequalite) {
    Controlequalite = controlequalite;
  }

  public Long getInfra() {
    return Infra;
  }

  public void setInfra(Long infra) {
    Infra = infra;
  }

  public Long getAnalyse() {
    return Analyse;
  }

  public void setAnalyse(Long analyse) {
    Analyse = analyse;
  }

  public String getDatelimie() {
    return datelimie;
  }

  public void setDatelimie(String datelimie) {
    this.datelimie = datelimie;
  }

  public String getDatedebut() {
    return datedebut;
  }

  public void setDatedebut(String datedebut) {
    this.datedebut = datedebut;
  }

  public String getRemarque() {
    return remarque;
  }

  public void setRemarque(String remarque) {
    this.remarque = remarque;
  }

}
