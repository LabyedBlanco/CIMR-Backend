package com.example.CIMR_DSI.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CIMR_DSI.Model.Competence;

import java.net.DatagramPacket;
import java.util.Set;
import java.util.List;
import java.util.Optional;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

    List<Competence> findAll();

}