package com.uahb.m1stic.project.gestionScolarite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscription;

    private String anneAcademique;
    @Temporal(TemporalType.DATE)
    private Date dateInscription;
    @Column(length = 20)
    private int montantVerse;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEtudiant")
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idClasse")
    private Classe classe;

    public Long getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Long idInscription) {
        this.idInscription = idInscription;
    }

    public String getAnneAcademique() {
        return anneAcademique;
    }

    public void setAnneAcademique(String anneAcademique) {
        this.anneAcademique = anneAcademique;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getMontantVerse() {
        return montantVerse;
    }

    public void setMontantVerse(int montantVerse) {
        this.montantVerse = montantVerse;
    }
}
