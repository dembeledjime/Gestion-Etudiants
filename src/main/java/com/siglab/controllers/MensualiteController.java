package com.siglab.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siglab.dao.EtudiantsDao;
import com.siglab.dao.MensualitesDao;
import com.siglab.models.Etudiant;
import com.siglab.models.Mensualite;

@Controller
public class MensualiteController {
	
	@Autowired
	MensualitesDao mensualitesDao ;
	@Autowired
	EtudiantsDao etudiantsDao ;
	
	Mensualite newMensualite = new Mensualite() ;

	//lorsqu'on envoi ajouter-mensualité dès qu'on clique sur l'étudiantDetaillé 
	//on cree une nouvelle mentualité on lui met le mat de l'etudiant
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
		
		//52,53,54permettent de recuperer la new mensual et la date ensuite le sauvegarder
		mensualiteReçu.setDate(newMensualite.getDate());
		mensualiteReçu.setMatriculeEtudiant(newMensualite.getMatriculeEtudiant());
		mensualitesDao.save(mensualiteReçu) ;
		
		//on veux calculer le nbre total de la mentual que l'etudiant a payé
		//on prend le nombre total de la mensual et le remettre a 0 
		//et recalcule en fonction de tout ce qui est dans la bdd
		Etudiant etudiantDetail = etudiantsDao.findByMatricule(mensualiteReçu.getMatriculeEtudiant()) ;
		etudiantDetail.setTotalMensualite(0);
		
		//on recupere tous les mensuals qui ont la matricule de l'etudiant en question.
		//un foreach qui va me parcours la collection mensualité
		mensualitesDao.findByMatriculeEtudiant(mensualiteReçu.getMatriculeEtudiant()).forEach(mensualite -> {
			//calcule le totalMensual
			etudiantDetail.setTotalMensualite(etudiantDetail.getTotalMensualite() + mensualite.getMontant()) ;
		});
		etudiantsDao.save(etudiantDetail) ;
		
		//on envoi l'etudiant et toutes ces mensualités et je renvoi dans la vue 
		// avant de renvoyé on cree un new objet de la Mensualité pour remetre a 0 sinon la prochaine fois que je vais ajouter une new mensual ça va me remener les anciens valeurs
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
