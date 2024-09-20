package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.Competence;
import com.example.CIMR_DSI.Model.Departement;
import com.example.CIMR_DSI.Repo.DepartementRepository;
import com.example.CIMR_DSI.Service.CompetenceService;
import com.example.CIMR_DSI.Service.DepartementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Competence")
public class CompetenceResouce {

    @Autowired
    CompetenceService competenceService;

    @GetMapping("/all")
    public ResponseEntity<List<Competence>> getAllCompetence() {
        List<Competence> competence = competenceService.findAllDepartement();
        return new ResponseEntity<>(competence, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Competence> addCompetence(@RequestBody Competence competence) {
        Competence competence1 = competenceService.addCompetence(competence);
        return new ResponseEntity<>(competence1, HttpStatus.CREATED);
    }
}
