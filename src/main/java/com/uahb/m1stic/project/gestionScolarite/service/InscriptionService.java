package com.uahb.m1stic.project.gestionScolarite.service;

import com.uahb.m1stic.project.gestionScolarite.exception.AnneeAcademiqueNotValide;
import com.uahb.m1stic.project.gestionScolarite.models.Classe;
import com.uahb.m1stic.project.gestionScolarite.models.Etudiant;
import com.uahb.m1stic.project.gestionScolarite.models.Inscription;
import com.uahb.m1stic.project.gestionScolarite.repository.ClasseRepository;
import com.uahb.m1stic.project.gestionScolarite.repository.EtudiantRepository;
import com.uahb.m1stic.project.gestionScolarite.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class InscriptionService {
        @Autowired
        private InscriptionRepository inscriptionRepository;

        /*CETTE FONCTION VERIFIER L'ETUDIANT EST DEJA INSCRIT DANS UNE CLASSE*/
        public boolean isEtudiantAlreadyInscrit(Etudiant etudiant, Classe classe) {
            List<Inscription> inscriptions = inscriptionRepository.findByEtudiantAndClasse(etudiant, classe);
            return !inscriptions.isEmpty();
        }
        /*CETTE FONCTION VERIFIER LE FORMAT DE L'ANNEE ACADEMIQUE EN RETOURNANT UN BOOL*/
        public boolean isValidAnneeAcademique(String anneeAca) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-yyyy");
                formatter.parse(anneeAca);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }

    /*CETTE FONCTION VERIFIER SI L'ANNEE ACADEMIQUE RESPECTE L'INTERVAL DE 1 AN ET SI LA PREMIERE ANNEE INFERIEUR A LA SECONDE
    EN RETOURNANT UN BOOL*/
        public boolean anneeAcademiqueValidation(String anneeAca){
            if(!anneeAca.matches("\\d{4}-\\d{4}")){
                throw new AnneeAcademiqueNotValide("L'année académique doit respecter le format AAAA-AAAA");
            }
            String[] anneeAcaSplit = anneeAca.split("-");
            try {
                int debut = Integer.parseInt(anneeAcaSplit[0]);
                int fin = Integer.parseInt(anneeAcaSplit[1]);
                return debut < fin && Math.abs(debut - fin)==1;
            }catch(NumberFormatException e){
                return false;
            }
        }
}

