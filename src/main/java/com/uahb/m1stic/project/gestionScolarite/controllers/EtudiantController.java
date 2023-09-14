package com.uahb.m1stic.project.gestionScolarite.controllers;

import com.uahb.m1stic.project.gestionScolarite.exception.ResourceNotFoundException;
import com.uahb.m1stic.project.gestionScolarite.models.Etudiant;
import com.uahb.m1stic.project.gestionScolarite.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/getEtudiants")
    public List<Etudiant> getEtudiants(){
        return etudiantRepository.findAll();
    }

    @PostMapping("/saveEtudiant")
    public Etudiant saveEtudiant(@RequestBody Etudiant etudiant) {
        /*conversion date naiss etudiant en localdate pour poiuvoir faire le calcul de l'age et comparer a 16 ans*/
        Date naiss=etudiant.getNaiss();
        Instant instant = naiss.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        LocalDate localDate = zonedDateTime.toLocalDate();
        /*Date d'aujourdui*/
        LocalDate currentDate = LocalDate.now();
        /*calcul age etudiant*/
        int age = Period.between(localDate, currentDate).getYears();
        if (age < 16) {
            throw new IllegalArgumentException("La date de naissance est invalide, l'etudiant doit avoir au moins 16 ans!");
        }
        //etudiant.setPhoto(photo.getBytes());
        return etudiantRepository.save(etudiant);
    }

    /*@PostMapping("/saveEtudiant")
    public Etudiant saveEtudiant(@RequestBody Etudiant etudiant,@RequestParam("photo") MultipartFile photo) throws IOException {
        Instant instant = naiss.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        LocalDate localDate = zonedDateTime.toLocalDate();

        LocalDate currentDate = LocalDate.now();

        int age = Period.between(localDate, currentDate).getYears();
        if (age < 16) {
            throw new IllegalArgumentException("La date de naissance est invalide, l'etudiant doit avoir au moins 16 ans!");
        }

        if(photo.isEmpty()){
            throw new ResourceNotFoundException("");
        }
        byte[]  photoData = photo.getBytes();
        etudiant.setPhoto(photoData);
        return etudiantRepository.save(etudiant);
    }*/
}
