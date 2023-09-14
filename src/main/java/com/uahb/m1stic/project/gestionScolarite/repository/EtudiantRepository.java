package com.uahb.m1stic.project.gestionScolarite.repository;

import com.uahb.m1stic.project.gestionScolarite.models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
