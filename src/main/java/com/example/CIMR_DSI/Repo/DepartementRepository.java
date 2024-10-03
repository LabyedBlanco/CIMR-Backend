package com.example.CIMR_DSI.Repo;

import com.example.CIMR_DSI.Model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.net.DatagramPacket;
import java.util.List;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

  void deleteDepartementById(Long id);

  @Query(value = "SELECT d.*, COUNT(p.id) AS project_count, SUM(p.chargeas400) , SUM(p.chargentic) , p_last.titre ,  p_last.creele  AS project_last\r\n"
      + //
      "FROM departement d\r\n" + //
      "LEFT JOIN projet p ON p.departement_id = d.id\r\n" + //
      "LEFT JOIN (\r\n" + //
      "    SELECT p1.departement_id, p1.titre  , p1.creele\r\n" + //
      "    FROM projet p1\r\n" + //
      "    WHERE p1.id = (\r\n" + //
      "        SELECT p2.id\r\n" + //
      "        FROM projet p2\r\n" + //
      "        WHERE p2.departement_id = p1.departement_id and p2.trimestre_id = :Trimestreid \r\n" + //
      "        ORDER BY p2.id DESC \r\n" + //
      "        LIMIT 1\r\n" + //
      "    )\r\n" + //
      ") p_last ON p_last.departement_id = d.id\r\n" + //
      "where p.trimestre_id = :Trimestreid \r\n" + //
      "GROUP BY d.id;", nativeQuery = true)
  List<Object[]> findAllData(@Param("Trimestreid") Long id);

  Optional<Departement> findDepatementById(Long id);
}
