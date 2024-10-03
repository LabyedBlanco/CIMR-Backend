package com.example.CIMR_DSI;

import com.example.CIMR_DSI.Model.Collaborateur;
import com.example.CIMR_DSI.Repo.CollaborateurRepository;
import com.example.CIMR_DSI.Service.CollaborateurService;
import com.example.CIMR_DSI.Service.ImageService;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.*;
import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/Collaborateurs")
public class CollaborateursResouce {

  final private CollaborateurService collaborateurService;
  final private CollaborateurRepository collaborateurRepository;

  @Autowired
  private ImageService imageService;

  public CollaborateursResouce(CollaborateurService collaborateurService,
      CollaborateurRepository collaborateurRepository) {
    this.collaborateurRepository = collaborateurRepository;
    this.collaborateurService = collaborateurService;
  }

  @PostMapping("/fileUpload/{email}")
  public ResponseEntity<Map<String, String>> uploadFile(@PathVariable("email") String email,
      @RequestParam("file") MultipartFile file)
      throws java.io.IOException {
    File directory = new File("./upload");
    if (!directory.exists())
      directory.mkdirs();

    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    File serverFile = new File(directory, fileName);

    Files.write(Paths.get(serverFile.getAbsolutePath()), file.getBytes());

    // Update the Collaborateur entity with the new image URL
    Collaborateur collaborateur = collaborateurRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Collaborateur not found"));

    String imageUrl = "http://localhost:8090/upload/" + fileName;
    collaborateur.setImageurl(imageUrl);
    collaborateurRepository.save(collaborateur);

    Map<String, String> response = new HashMap<>();
    response.put("message", "File uploaded successfully");
    response.put("imageUrl", imageUrl);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Collaborateur>> getAllCollaborateur() {
    List<Collaborateur> collaborateur = collaborateurService.findAllCollaborateur();
    return new ResponseEntity<>(collaborateur, HttpStatus.OK);
  }

  @GetMapping("/all/count")
  public ResponseEntity<List<Map<String, Object>>> getAllCollaborateurCount() {
    List<Map<String, Object>> collaborateur = collaborateurService.getAllCollaborateursWithProjetCount();
    return new ResponseEntity<>(collaborateur, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Collaborateur> addCollaborateur(@RequestBody Collaborateur collaborateur) {
    Collaborateur collaborateur1 = collaborateurService.addCollaborateur(collaborateur);
    return new ResponseEntity<>(collaborateur1, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Collaborateur> updateCollaborateur(@RequestBody Collaborateur collaborateur) {
    Collaborateur updatecollaborateur = collaborateurService.updateCollaborateur(collaborateur);
    return new ResponseEntity<>(updatecollaborateur, HttpStatus.OK);
  }

  @GetMapping("/find/{id}")
  public ResponseEntity<Collaborateur> findCollaborateurbyid(@PathVariable("id") Long id) {
    Collaborateur collaborateur1 = collaborateurService.findCollaborateurbyid(id);
    return new ResponseEntity<>(collaborateur1, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteCollaborateurbyid(@PathVariable("id") Long id) {
    collaborateurService.deleteCollaborateurbyid(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/me")
  public ResponseEntity<Collaborateur> authenticatedCollaborateur() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Collaborateur currentCollaborateur = (Collaborateur) authentication.getPrincipal();
    return ResponseEntity.ok(currentCollaborateur);
  }

}
