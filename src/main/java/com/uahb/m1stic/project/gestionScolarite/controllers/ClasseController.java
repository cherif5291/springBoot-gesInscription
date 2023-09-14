package com.uahb.m1stic.project.gestionScolarite.controllers;

import com.uahb.m1stic.project.gestionScolarite.exception.EtudiantDejaInscritException;
import com.uahb.m1stic.project.gestionScolarite.exception.ResourceNotFoundException;
import com.uahb.m1stic.project.gestionScolarite.models.Classe;
import com.uahb.m1stic.project.gestionScolarite.models.Filiere;
import com.uahb.m1stic.project.gestionScolarite.repository.ClasseRepository;
import com.uahb.m1stic.project.gestionScolarite.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/classe")
public class ClasseController {
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    FiliereRepository filiereRepository;
    @GetMapping("/getAllClasses")
    public List<Classe> classes(){return classeRepository.findAll();}

    @PostMapping("/saveClasse")
    public Classe saveClasse(@RequestBody Classe classe){
        try {
        classe.setFiliere(filiereRepository.findById(classe.getFiliere().getIdFiliere()).get());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("La filiére "+classe.getFiliere().getLibelle()+ "n'existe pas dans la base de donnée !");
        }
        return classeRepository.save(classe);
    }
    @GetMapping("/displayClassesByFiliere/{idFiliere}")
    public List<Classe> displayClassesByFiliere(@PathVariable Long idFiliere){
        List<Classe> classes = classeRepository.findAll();
        List<Classe> classeList = new ArrayList<>();
        for(Classe cl: classes){
            if (cl.getFiliere().getIdFiliere() == idFiliere){
                classeList.add(cl);
            }
        }
        Filiere fl=filiereRepository.findById(idFiliere).get();
        if(classeList.isEmpty()){
            throw new ResourceNotFoundException("Il n'existe pas de classe qui a pour filière "+fl.getLibelle() +" dans la base de données !");
        }else{
        return classeList;
        }
    }

}
