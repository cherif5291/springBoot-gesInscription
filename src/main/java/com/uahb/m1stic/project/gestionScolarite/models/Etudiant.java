package com.uahb.m1stic.project.gestionScolarite.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idEtudiant;
    @Column(length = 10)
     private String matricule;
    @Column(length = 10)
     private String nom;
    @Column(length = 20)
     private String prenom;
     @Temporal(TemporalType.DATE)
     private Date naiss;
     @Column(length = 10)
     private String tel;
     @Column(length = 15)
     private String adresse;
     @Column(length = 25)
     private String email;
     @Lob
     private byte[] photo;
    @JsonIgnore
    @OneToMany(mappedBy = "etudiant",fetch = FetchType.LAZY)
     private List<Inscription> inscriptions;

    @ManyToMany
     private List<Classe> classes;

    public Long getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Long idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getNaiss() {
        return naiss;
    }

    public void setNaiss(Date naiss) {
        this.naiss = naiss;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }
}
