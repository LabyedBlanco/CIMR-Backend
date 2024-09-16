package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Model.Departement;
import com.example.CIMR_DSI.Repo.DepartementRepository;
import com.example.CIMR_DSI.Service.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Departement")
public class DepartementResouce {

  private DepartementRepository departementRepository ;
  private  DepartementService departementService ;

  public DepartementResouce(DepartementRepository departementRepository, DepartementService departementService) {
    this.departementRepository = departementRepository;
    this.departementService = departementService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<Departement>> getAllDepartement(){
    List<Departement> departement = departementService.findAllDepartement();
    return new ResponseEntity<>(departement,HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Departement> addDepartement(@RequestBody Departement departement){
    Departement departement1 = departementService.addDepartement(departement);
    return new ResponseEntity<>(departement1,HttpStatus.CREATED);
  }


}
