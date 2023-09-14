package com.uahb.m1stic.project.gestionScolarite.repository;

import com.uahb.m1stic.project.gestionScolarite.models.Classe;
import com.uahb.m1stic.project.gestionScolarite.models.Etudiant;
import com.uahb.m1stic.project.gestionScolarite.models.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByEtudiantAndClasse(Etudiant etudiant, Classe classe);

}
