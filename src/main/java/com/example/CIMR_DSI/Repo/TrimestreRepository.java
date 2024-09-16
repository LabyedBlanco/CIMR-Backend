package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.Trimestre;
import com.example.CIMR_DSI.Model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;
import java.util.Optional;

public interface TrimestreRepository extends JpaRepository<Trimestre, Long> {

        void deleteTrimestreById(Long id);

        Optional<Trimestre> findTrimestreById(Long id);

        Trimestre findFirstByOrderByCreationDateDesc();

}
