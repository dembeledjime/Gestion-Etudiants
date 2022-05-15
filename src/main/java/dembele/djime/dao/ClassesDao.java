package dembele.djime.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dembele.djime.models.Classe;

public interface ClassesDao extends JpaRepository<Classe, Long> {
	public Classe findByNomAvecNiveau(String nom) ;

}
