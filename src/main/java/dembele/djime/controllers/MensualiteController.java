package dembele.djime.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dembele.djime.dao.EtudiantsDao;
import dembele.djime.dao.MensualitesDao;
import dembele.djime.models.Etudiant;
import dembele.djime.models.Mensualite;

@Controller
public class MensualiteController {
	
	@Autowired
	MensualitesDao mensualitesDao ;
	@Autowired
	EtudiantsDao etudiantsDao ;
	
	Mensualite newMensualite = new Mensualite() ;

	@GetMapping("ajouter-mensualite")
	public String ajouterMensualite(Model model, @RequestParam("matricule") String matriculeEtudiant) {
		
		newMensualite.setMatriculeEtudiant(matriculeEtudiant);
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		newMensualite.setDate(date);
		
		model.addAttribute("newMensualite", newMensualite) ;
		return "/Mensualité/mensualite-ajouter" ;
	} 
	
	
	@PostMapping("enregistrer-mensualite")
	public String save(Model model, @ModelAttribute Mensualite mensualiteReçu) {
		
		mensualiteReçu.setDate(newMensualite.getDate());
		mensualiteReçu.setMatriculeEtudiant(newMensualite.getMatriculeEtudiant());
		mensualitesDao.save(mensualiteReçu) ;
		
		
		Etudiant etudiantDetail = etudiantsDao.findByMatricule(mensualiteReçu.getMatriculeEtudiant()) ;
		etudiantDetail.setTotalMensualite(0);
		mensualitesDao.findByMatriculeEtudiant(mensualiteReçu.getMatriculeEtudiant()).forEach(mensualite -> {
			etudiantDetail.setTotalMensualite(etudiantDetail.getTotalMensualite() + mensualite.getMontant()) ;
		});
		etudiantsDao.save(etudiantDetail) ;
		
		model.addAttribute("etudiantDetail", etudiantDetail ) ;
		model.addAttribute("allMensualites",mensualitesDao.findByMatriculeEtudiant(mensualiteReçu.getMatriculeEtudiant())) ;
		newMensualite = new Mensualite() ;
		
		return "/Etudiant/etudiant-detail" ;
	}
	
	@GetMapping("modifier-mensualite")
	public String modifier(Model model, @RequestParam Long id) {
		Mensualite mensualite = mensualitesDao.findById(id).get() ;
		newMensualite = mensualite ;
		model.addAttribute("newMensualite", mensualite) ;
		return "/Mensualité/mensualite-modifier" ;
	}
	
	@PostMapping("update-mensualite")
	public String update(Model model, @ModelAttribute Mensualite mensualiteReçu, @RequestParam Long id) {
		mensualiteReçu.setId(id);
		mensualiteReçu.setDate(newMensualite.getDate());
		mensualiteReçu.setMatriculeEtudiant(newMensualite.getMatriculeEtudiant());
		mensualitesDao.save(mensualiteReçu) ;
		Etudiant etudiantDetail = etudiantsDao.findByMatricule(mensualiteReçu.getMatriculeEtudiant()) ;
		etudiantDetail.setTotalMensualite(0);
		mensualitesDao.findByMatriculeEtudiant(mensualiteReçu.getMatriculeEtudiant()).forEach(mensualite -> {
			etudiantDetail.setTotalMensualite(etudiantDetail.getTotalMensualite() + mensualite.getMontant()) ;
		});
		etudiantsDao.save(etudiantDetail) ;
		model.addAttribute("etudiantDetail", etudiantDetail ) ;
		model.addAttribute("allMensualites",mensualitesDao.findByMatriculeEtudiant(mensualiteReçu.getMatriculeEtudiant())) ;
		newMensualite = new Mensualite() ;
		return "/Etudiant/etudiant-detail" ;
	}
	
	@GetMapping("supprimer-mensualite")
	public String delete(Model model, @RequestParam Long id) {
		Mensualite mensualiteReçu = mensualitesDao.findById(id).get() ;
		mensualitesDao.deleteById(id); 
		Etudiant etudiantDetail = etudiantsDao.findByMatricule(mensualiteReçu.getMatriculeEtudiant()) ;
		etudiantDetail.setTotalMensualite(0);
		mensualitesDao.findByMatriculeEtudiant(mensualiteReçu.getMatriculeEtudiant()).forEach(mensualite -> {
			etudiantDetail.setTotalMensualite(etudiantDetail.getTotalMensualite() + mensualite.getMontant()) ;
		});
		etudiantsDao.save(etudiantDetail) ;
		model.addAttribute("etudiantDetail", etudiantDetail ) ;
		model.addAttribute("allMensualites",mensualitesDao.findByMatriculeEtudiant(mensualiteReçu.getMatriculeEtudiant())) ;
		newMensualite = new Mensualite() ;
		return "/Etudiant/etudiant-detail" ;
	}
	
	
	
	
	
}
