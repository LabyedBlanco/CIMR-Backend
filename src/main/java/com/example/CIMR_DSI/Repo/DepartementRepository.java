package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.net.DatagramPacket;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository< Departement,Long> {

  void deleteDepartementById(Long id);


  Optional<Departement> findDepatementById(Long id );
}
