package com.uahb.m1stic.project.gestionScolarite.controllers;

import com.uahb.m1stic.project.gestionScolarite.exception.*;
import com.uahb.m1stic.project.gestionScolarite.models.Classe;
import com.uahb.m1stic.project.gestionScolarite.models.Etudiant;
import com.uahb.m1stic.project.gestionScolarite.models.Inscription;
import com.uahb.m1stic.project.gestionScolarite.repository.ClasseRepository;
import com.uahb.m1stic.project.gestionScolarite.repository.EtudiantRepository;
import com.uahb.m1stic.project.gestionScolarite.repository.InscriptionRepository;
import com.uahb.m1stic.project.gestionScolarite.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/inscription")
public class IncriptionController {
    @Autowired
    private InscriptionRepository inscriptionRepository;
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    InscriptionService inscriptionService;

    @GetMapping("/getInscriptions")
    public List<Inscription> getInscriptions(){
        return inscriptionRepository.findAll();
    }

    @PostMapping("/saveInscription")
    public Inscription saveInscription(@RequestBody Inscription inscription){
        try {
        inscription.setClasse(classeRepository.findById(inscription.getClasse().getIdClasse()).get());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("La classe "+inscription.getClasse().getIdClasse() +"n'existe pas dans la base de données!");
        }
        try {
        inscription.setEtudiant(etudiantRepository.findById(inscription.getEtudiant().getIdEtudiant()).get());
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("L'étudiant "+inscription.getEtudiant().getIdEtudiant()+ "n'existe pas dans la base de donnée !");
        }
        // Vérifier si l'étudiant a deja fait cette classe
        if (inscriptionService.isEtudiantAlreadyInscrit(inscription.getEtudiant(), inscription.getClasse())) {
            throw new EtudiantDejaInscritException("L'étudiant "+inscription.getEtudiant().getPrenom() +" a déjà fait cette classe "+inscription.getClasse().getLibelle()+"!");
        }
        //Vérifier si montant verse conforme
        int montant = inscription.getClasse().getMensualite()+inscription.getClasse().getAutreFrais()+inscription.getClasse().getMontantInscription();
        if(inscription.getMontantVerse()<montant){
            throw new MontantVerseNonConformeException("L'étudiant doit verser au minimum "+montant+" pour pouvoir s'inscrire");
        }
        /*if(inscriptionService.isValidAnneeAcademique(inscription.getAnneAcademique())==false){
            throw new AnneeAcademiqueNotValide("L'année académique doit respecter le format AAAA-AAAA");
        }*/
         if (inscriptionService.anneeAcademiqueValidation(inscription.getAnneAcademique())==false){
             throw new IntervalAnneeAcademiqueNotValide("L'année academique doit respecter l'intervale de 1 an par exemple 2022-2023 et la première année doit etre inférieur à la seconde !");
         }

        return inscriptionRepository.save(inscription);
    }

    /*Fonction qui affiche pour une classe donnee et une annee academique les inscrit*/
    @GetMapping("/showRegistrationForYear/{idClasse}/{anneeAca}")
    public List<Etudiant> showRegistrationForYear(@PathVariable Long idClasse, @PathVariable /*@DateTimeFormat(pattern="yyyy-MM-dd")*/ String anneeAca){
        List<Inscription> inscriptions=inscriptionRepository.findAll();
        List<Inscription> registrationForDate=new ArrayList<>();
        List<Etudiant> etudiants = new ArrayList<>();
        for (Inscription insc : inscriptions){
            if (insc.getClasse().getIdClasse()==idClasse && insc.getAnneAcademique().equals(anneeAca)){
                registrationForDate.add(insc);
            }
        }
        for (Inscription regis: registrationForDate){
            etudiants.add(regis.getEtudiant());
        }
        Classe cl=classeRepository.findById(idClasse).get();
        if(registrationForDate.isEmpty()){
            throw new ResourceNotFoundException("Il n'y a pas d'inscrit pour la classe "+cl.getLibelle() +" durant l'année academique "+anneeAca);
        }else{
            return etudiants;
        }
    }

    @GetMapping("/getFees/{idClasse}/{anneeAca}")
    public String getFees(@PathVariable Long idClasse, @PathVariable String anneeAca) {
        int mensualite = 0,autreFrais=0,montantInscription = 0;
        List<Inscription> inscriptions=inscriptionRepository.findAll();
        Classe cl =classeRepository.findById(idClasse).get();
        for (Inscription insc : inscriptions) {
            if (insc.getClasse().getIdClasse()== idClasse && insc.getAnneAcademique().equals(anneeAca)) {
                mensualite=insc.getClasse().getMensualite();
                autreFrais=insc.getClasse().getAutreFrais();
                montantInscription=insc.getClasse().getMontantInscription();
            }
        }
        return "La classe "+cl.getLibelle()+" durant l'année scloraire "+anneeAca +" a pour Frais " +
                "d'inscription : " +montantInscription+" FcFa" +", mensualite "+mensualite+" FcFa"+" et autre frais "+autreFrais+" FcFa";
    }
}
