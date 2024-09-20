package com.example.CIMR_DSI.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String titrecompetence;

    public Competence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitrecompetence() {
        return titrecompetence;
    }

    public void setTitrecompetence(String titrecompetence) {
        this.titrecompetence = titrecompetence;
    }

}
