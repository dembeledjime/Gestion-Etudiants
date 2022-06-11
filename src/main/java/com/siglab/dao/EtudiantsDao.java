package com.siglab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siglab.models.Etudiant;

public interface EtudiantsDao extends JpaRepository<Etudiant, Long> {
	public List<Etudiant> findByClasse(String nomClasseAvecFiliere) ;
	public Etudiant findByMatricule(String matricule) ;
}
