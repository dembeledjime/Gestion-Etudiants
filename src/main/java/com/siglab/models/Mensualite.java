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
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private double montant ;
	private String description ;
	private String date ;
	private String anneeScolaire ;
	private String matriculeEtudiant ;
}
