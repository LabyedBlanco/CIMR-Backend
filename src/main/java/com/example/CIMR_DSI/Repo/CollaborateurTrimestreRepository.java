package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CollaborateurTrimestreRepository
                extends JpaRepository<CollaborateurTrimestre, CollaborateurTrimestreId> {

        @Query("SELECT cp FROM CollaborateurTrimestre cp WHERE cp.trimestre.id = :id")
        Set<CollaborateurTrimestre> findAllByTrimestreId(@Param("id") Long TrimestreId);

        @Query(nativeQuery = true, value = "SELECT " +
                        "    SUM(ct.analyse) as analyse, " +
                        "    SUM(ct.chargecompetence) as chargecompetence, " +
                        "    SUM(ct.chargedisponible) as chargedisponible, " +
                        "    SUM(ct.controle_qualite) as controleQualite, " +
                        "    SUM(ct.integrationcoordination as integrationcoordination, " +
                        "    SUM(ct.maintenence) as maintenence, " +
                        "    SUM(ct.total_netcongee) as totalNetcongee " +
                        "FROM collaborateur_trimestre ct " +
                        "JOIN trimestre t ON ct.trimestre_id = t.id " +
                        "WHERE t.id = :trimestreId")
        CollaborateurTrimestre getSumOfFields(@Param("trimestreId") Long trimestreId);

        @Query("SELECT ct FROM CollaborateurTrimestre ct WHERE ct.collaborateur.id = :colabId AND ct.trimestre.id = :trimestreId")
        CollaborateurTrimestre getCollabTrimestreCap(@Param("colabId") Long collaborateurId,
                        @Param("trimestreId") Long trimestreId);
}
