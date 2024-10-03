package com.example.CIMR_DSI.Service;

import com.example.CIMR_DSI.Model.Departement;
import com.example.CIMR_DSI.Model.Projet;
import com.example.CIMR_DSI.Repo.DepartementRepository;
import com.example.CIMR_DSI.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartementService {

  private DepartementRepository departementRepository;

  public DepartementService(DepartementRepository departementRepository) {
    this.departementRepository = departementRepository;
  }

  public Departement addDepartement(Departement departement) {
    return departementRepository.save(departement);
  }

  public Departement updateDepartement(Departement departement) {
    return departementRepository.save(departement);
  }

  public List<Object[]> findAllDepartementByID(Long id) {
    return departementRepository.findAllData(id);
  }

  public List<Departement> findAllDepartement() {
    return departementRepository.findAll();
  }

  @Transactional
  public void deleteDepartementbyid(Long id) {
    departementRepository.deleteDepartementById(id);
  }

  public Departement findDepartementbyid(Long id) {
    return departementRepository.findDepatementById(id)
        .orElseThrow(() -> new UserNotFoundException("Departement with thi id is not Found  : " + id));
  }
}
