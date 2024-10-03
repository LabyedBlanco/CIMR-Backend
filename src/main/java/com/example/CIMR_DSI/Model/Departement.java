package com.example.CIMR_DSI.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Departement implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  private String Titre;
  private String description;

  public String getDesc() {
    return description;
  }

  public void setDesc(String desc) {
    this.description = desc;
  }

  private Long NbrCollaborateurs;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitre() {
    return Titre;
  }

  public void setTitre(String titre) {
    Titre = titre;
  }

  public Departement(Long id, String titre, Long nbrCollaborateurs) {
    this.id = id;
    Titre = titre;
    NbrCollaborateurs = nbrCollaborateurs;
  }

  public Long getNbrCollaborateurs() {
    return NbrCollaborateurs;
  }

  public void setNbrCollaborateurs(Long nbrCollaborateurs) {
    NbrCollaborateurs = nbrCollaborateurs;
  }

  public Departement() {
  }

  @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL)
  private List<Projet> projet;
}
