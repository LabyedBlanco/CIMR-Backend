package com.example.CIMR_DSI.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.CollaborateurProjetId;
import com.example.CIMR_DSI.Model.CollaborateurTrimestre;
import com.example.CIMR_DSI.Model.CollaborateurTrimestreId;
import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.Repo.CollaborateurTrimestreRepository;
import com.example.CIMR_DSI.Repo.TrimestreRepository;
import java.util.Set;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CollaborateurTrimestreService {

    @Autowired
    private CollaborateurTrimestreRepository collaborateurTrimestreRepository;

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Autowired
    private TrimestreRepository TrimestreRepository;

    @Transactional
    public CollaborateurTrimestre assignCollaborateurToTrimestre(Long collaborateurId, Long TrimestreId,
            CollaborateurTrimestre CollaborateurTrimestre) {
        Collaborateur collaborateur = collaborateurRepository.findCollaborateurById(collaborateurId)
                .orElseThrow(() -> new EntityNotFoundException("Collaborateur not found"));

        Trimestre Trimestre = TrimestreRepository.findById(TrimestreId)
                .orElseThrow(() -> new EntityNotFoundException("Trimestre not found"));

        CollaborateurTrimestre.setId(new CollaborateurTrimestreId(collaborateurId, TrimestreId));
        CollaborateurTrimestre.setCollaborateur(collaborateur);
        CollaborateurTrimestre.setTrimestre(Trimestre);

        return collaborateurTrimestreRepository.save(CollaborateurTrimestre);
    }

    @Transactional
    public void removeCollaborateurFromProjet(Long collaborateurId, Long TrimestreId) {
        CollaborateurTrimestreId id = new CollaborateurTrimestreId(collaborateurId, TrimestreId);
        collaborateurTrimestreRepository.deleteById(id);
    }

}
