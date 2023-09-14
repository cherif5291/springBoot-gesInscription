package com.uahb.m1stic.project.gestionScolarite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Filiere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFiliere;
    @Column(length = 20)
    private String libelle;
    @JsonIgnore
    @OneToMany(mappedBy = "filiere")
    private List<Classe> classes;

    public Long getIdFiliere() {
        return idFiliere;
    }

    public void setIdFiliere(Long idFiliere) {
        this.idFiliere = idFiliere;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }
}
