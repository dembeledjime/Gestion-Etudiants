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
public class Etudiant {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String nom ;
	private String prenom ;
	private String sexe ;
	private String dateNaissance ;
	private String adresse ;
	private String ville;
	private int codePostale;
	private String email ;
	private String tel ;
	private String classe ;
	private String filiere ;
	private String matricule ;
	private String nationalite ;
	private double totalMensualite ;
}
