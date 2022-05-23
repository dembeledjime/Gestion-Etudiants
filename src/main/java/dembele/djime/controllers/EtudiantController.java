package dembele.djime.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dembele.djime.dao.ClassesDao;
import dembele.djime.dao.EtudiantsDao;
import dembele.djime.dao.MensualitesDao;
import dembele.djime.models.Classe;
import dembele.djime.models.Etudiant;
import dembele.djime.models.Mensualite;

@Controller
public class EtudiantController {

	@Autowired
	EtudiantsDao etudiantsDao;
	@Autowired
	ClassesDao classesDao;
	@Autowired
	MensualitesDao mensualitesDao;

	Etudiant newEtudiant;
	Etudiant etudiantDetail;
	List<Etudiant> allEtudiants = new ArrayList<Etudiant>();
	List<Classe> allClasses = new ArrayList<Classe>();

	@GetMapping("/ajouter-etudiant")
	public String AjouterEtudiant(Model model) {
		newEtudiant = new Etudiant();
		model.addAttribute("allClasses", classesDao.findAll());
		model.addAttribute("newEtudiant", newEtudiant);
		return "/Etudiant/etudiant-ajouter";
	}

	@PostMapping("enregistrer-etudiant")
	public String enregistrerEtudiant(Model model, @ModelAttribute Etudiant etudiant) {
		etudiant.setFiliere(classesDao.findByNomAvecNiveau(etudiant.getClasse()).getNomFiliere());
		;
		Mensualite newMensualite = new Mensualite();
		newMensualite.setMontant(etudiant.getTotalMensualite());
		newMensualite.setDescription("1ere Tranche");
		newMensualite.setMatriculeEtudiant(etudiant.getMatricule());
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());
		newMensualite.setDate(date);
		mensualitesDao.save(newMensualite);
		etudiantsDao.save(etudiant);
		return ListeEtudiant(model);
	}

	@GetMapping("/liste-etudiant")
	public String ListeEtudiant(Model model) {
		allEtudiants = etudiantsDao.findAll();
		model.addAttribute("allEtudiants", allEtudiants);
		return "/Etudiant/etudiant-liste";
	}

	@GetMapping("etudiant-detail")
	public String etudiantDetail(Model model, @RequestParam Long id) {
		etudiantDetail = etudiantsDao.findById(id).get();
		model.addAttribute("etudiantDetail", etudiantDetail);
		model.addAttribute("allMensualites", mensualitesDao.findByMatriculeEtudiant(etudiantDetail.getMatricule()));
		return "/Etudiant/etudiant-detail";
	}

	@GetMapping("/modifier-etudiant")
	public String Modifieretudiant(Model model) {
		newEtudiant = etudiantDetail;
		model.addAttribute("allClasses", classesDao.findAll());
		model.addAttribute("newEtudiant", newEtudiant);
		return "Etudiant/etudiant-modifier";
	}

	@PostMapping("update-etudiant")
	public String Updateetudiant(Model model, @ModelAttribute Etudiant etudiant, @RequestParam Long id) {
		etudiant.setId(id);
		etudiant.setFiliere(classesDao.findByNomAvecNiveau(etudiant.getClasse()).getNomFiliere());
		;
		etudiantsDao.save(etudiant);
		return ListeEtudiant(model);
	}

	@GetMapping("/supprimer-etudiant")
	public String Suppretudiant(Model model) {
		etudiantsDao.deleteById(etudiantDetail.getId());
		return ListeEtudiant(model);
	}

	@GetMapping("/modifier-etudiant-2")
	public String Modifieretudiant2(Model model, @RequestParam Long id) {
		newEtudiant = etudiantsDao.findById(id).get();
		model.addAttribute("allClasses", classesDao.findAll());
		model.addAttribute("newEtudiant", newEtudiant);
		return "/Etudiant/etudiant-modifier";
	}

	@GetMapping("/supprimer-etudiant-2")
	public String Suppretudiant2(Model model, @RequestParam Long id) {
		etudiantsDao.deleteById(id);
		return ListeEtudiant(model);
	}

}
