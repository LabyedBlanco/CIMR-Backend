package com.example.CIMR_DSI;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurTrimestre;
import com.example.CIMR_DSI.Service.CollaborateurTrimestreService;

@RestController
@RequestMapping("/CollaborateurTrimestre")
public class CollaborateurTrimestreResouce {

    CollaborateurTrimestreService collaborateurTrimestreService;

    public CollaborateurTrimestreResouce(CollaborateurTrimestreService collaborateurTrimestreService) {
        this.collaborateurTrimestreService = collaborateurTrimestreService;
    }

    @GetMapping("/Trimestre/{id}")
    public ResponseEntity<Set<CollaborateurTrimestre>> findAllCapcities(@PathVariable("id") Long id) {
        Set<CollaborateurTrimestre> said = collaborateurTrimestreService.findAll(id);
        return new ResponseEntity<>(said, HttpStatus.OK);
    }

    @GetMapping("/Sum/{id}")
    public ResponseEntity<CollaborateurTrimestre> SumAll(@PathVariable("id") Long id) {
        CollaborateurTrimestre sum = collaborateurTrimestreService.getSUMALL(id);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }

}
