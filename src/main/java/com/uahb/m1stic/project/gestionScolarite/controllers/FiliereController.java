package com.uahb.m1stic.project.gestionScolarite.controllers;

import com.uahb.m1stic.project.gestionScolarite.models.Filiere;
import com.uahb.m1stic.project.gestionScolarite.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiere")
public class FiliereController {

    @Autowired
    private FiliereRepository filiereRepository;

    @PostMapping("/saveFiliere")
    public Filiere saveFiliere(@RequestBody Filiere filiere){
        return filiereRepository.save(filiere);
    }
    @GetMapping("/getAllFiliere")
    public List<Filiere> getAllFiliere(){
        return filiereRepository.findAll();
    }
}
