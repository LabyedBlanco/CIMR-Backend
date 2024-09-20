package com.example.CIMR_DSI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CIMR_DSI.Model.Competence;
import com.example.CIMR_DSI.Model.Departement;
import com.example.CIMR_DSI.Repo.CompetenceRepository;

import java.util.List;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public List<Competence> findAllDepartement() {
        return competenceRepository.findAll();
    }

    public Competence addCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }
}
