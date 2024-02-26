package com.siglab.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Classe {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String nom ;
	private String description ;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbreEtudiant() {
		return nbreEtudiant;
	}
	public void setNbreEtudiant(int nbreEtudiant) {
		this.nbreEtudiant = nbreEtudiant;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getNomFiliere() {
		return nomFiliere;
	}
	public void setNomFiliere(String nomFiliere) {
		this.nomFiliere = nomFiliere;
	}
	public String getNomAvecNiveau() {
		return nomAvecNiveau;
	}
	public void setNomAvecNiveau(String nomAvecNiveau) {
		this.nomAvecNiveau = nomAvecNiveau;
	}

	private int nbreEtudiant ;
	private String niveau ;
	private String nomFiliere ;
	private String nomAvecNiveau ;
}
