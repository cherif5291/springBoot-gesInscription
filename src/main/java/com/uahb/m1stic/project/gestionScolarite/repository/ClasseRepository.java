package com.uahb.m1stic.project.gestionScolarite.repository;

import com.uahb.m1stic.project.gestionScolarite.models.Classe;
import com.uahb.m1stic.project.gestionScolarite.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
