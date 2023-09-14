package com.uahb.m1stic.project.gestionScolarite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Classe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClasse;
    @Column(length = 10)
    private String libelle;
    private int montantInscription;
    private int mensualite;
    private int autreFrais;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFiliere")
    private Filiere filiere;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "classe_etudiant",joinColumns = @JoinColumn(name = "idEtudiant"),inverseJoinColumns = @JoinColumn(name = "idClasse"))
    private List<Etudiant> etudiants;
    @JsonIgnore
    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY)
    List<Inscription> inscriptions;

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMontantInscription() {
        return montantInscription;
    }

    public void setMontantInscription(int montantInscription) {
        this.montantInscription = montantInscription;
    }

    public int getMensualite() {
        return mensualite;
    }

    public void setMensualite(int mensualite) {
        this.mensualite = mensualite;
    }

    public int getAutreFrais() {
        return autreFrais;
    }

    public void setAutreFrais(int autreFrais) {
        this.autreFrais = autreFrais;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}
