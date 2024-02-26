package com.siglab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siglab.models.Classe;

public interface ClassesDao extends JpaRepository<Classe, Long> {
	public Classe findByNomAvecNiveau(String nom) ;

}
