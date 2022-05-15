package dembele.djime.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dembele.djime.models.Mensualite;

public interface MensualitesDao extends JpaRepository<Mensualite, Long> {
	
	public List<Mensualite> findByMatriculeEtudiant(String matricule) ;

}
