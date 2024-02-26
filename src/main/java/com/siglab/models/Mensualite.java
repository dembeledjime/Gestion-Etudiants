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
public class Mensualite {

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAnneeScolaire() {
		return anneeScolaire;
	}
	public void setAnneeScolaire(String anneeScolaire) {
		this.anneeScolaire = anneeScolaire;
	}
	public String getMatriculeEtudiant() {
		return matriculeEtudiant;
	}
	public void setMatriculeEtudiant(String matriculeEtudiant) {
		this.matriculeEtudiant = matriculeEtudiant;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private double montant ;
	private String description ;
	private String date ;
	private String anneeScolaire ;
	private String matriculeEtudiant ;
}
