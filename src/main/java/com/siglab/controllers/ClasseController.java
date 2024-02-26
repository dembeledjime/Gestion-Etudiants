	package com.siglab.controllers;
	
	import java.util.ArrayList;
	import java.util.List;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;

import com.siglab.dao.ClassesDao;
import com.siglab.dao.EtudiantsDao;
import com.siglab.dao.FilieresDao;
import com.siglab.models.Classe;
import com.siglab.models.Filiere;
	
	@Controller
	public class ClasseController {
	
		@Autowired
		ClassesDao classesDao;
		Classe classeDetail;
	
		Classe newClasse = new Classe();
		List<Classe> allClasses = new ArrayList<Classe>();
	
		@Autowired
		FilieresDao filieresDao;
		List<Filiere> allFiliere = new ArrayList<Filiere>();
	
		@Autowired
		EtudiantsDao etudiantsDao;
	
		@GetMapping({ "", "/home", "index" })
		public String home(Model model) {
			allClasses = classesDao.findAll();
			allClasses.forEach(classe -> {
				classe.setNbreEtudiant(etudiantsDao.findByClasse(classe.getNomAvecNiveau()).size());
				classesDao.save(classe);
			});
			model.addAttribute("allClasses", allClasses);
			return "index";
		}
	
		@GetMapping("/ajouter-classe")
		public String AjouterClasse(Model model) {
			newClasse = new Classe();
			model.addAttribute("newClasse", newClasse);
			model.addAttribute("allFilieres", filieresDao.findAll());
			return "/Classe/classe-ajouter";
		}
	
		@PostMapping("enregistrer-classe")
		public String SaveClasse(Model model, @ModelAttribute Classe classe) {
			Boolean classe_already_exist = false;
			if (classesDao.findByNomAvecNiveau(classe.getNom() + "(" + classe.getNiveau() + ")") == null) {
				classe.setNomAvecNiveau(classe.getNom() + "(" + classe.getNiveau() + ")");
				classesDao.save(classe);
				return ListeClasse(model);
			} else {
				classe_already_exist = true;
				model.addAttribute("classe_already_exist", classe_already_exist);
				model.addAttribute("newClasse", classe);
				model.addAttribute("allFilieres", filieresDao.findAll());
				return "/Classe/classe-ajouter";
			}
	
		}
	
		@GetMapping("/liste-classe")
		public String ListeClasse(Model model) {
			allClasses = classesDao.findAll();
			allClasses.forEach(classe -> {
				classe.setNbreEtudiant(etudiantsDao.findByClasse(classe.getNomAvecNiveau()).size());
				classesDao.save(classe);
			});
			model.addAttribute("allClasses", allClasses);
			return "/Classe/classe-liste";
		}
	
		@GetMapping("classe-detail")
		public String classeDetail(Model model, @RequestParam Long id) {
			classeDetail = classesDao.findById(id).get();
			model.addAttribute("classeDetail", classeDetail);
			model.addAttribute("allEtudiants", etudiantsDao.findByClasse(classeDetail.getNomAvecNiveau()));
			return "/Classe/classe-detail";
		}
	
		@GetMapping("/modifier-classe")
		public String ModifierClasse(Model model) {
			newClasse = classeDetail;
			model.addAttribute("newClasse", newClasse);
			model.addAttribute("allFilieres", filieresDao.findAll());
			return "/Classe/classe-modifier";
		}
	
		@PostMapping("update-classe")
		public String UpdateClasse(Model model, @ModelAttribute Classe classe) {
			classe.setId(classeDetail.getId());
			classe.setNomAvecNiveau(classe.getNom() + "(" + classe.getNiveau() + ")");
			classesDao.save(classe);
			return ListeClasse(model);
		}
	
		@GetMapping("/supprimer-classe")
		public String SupprClasse(Model model) {
			classesDao.deleteById(classeDetail.getId());
			return ListeClasse(model);
		}
	
	}
