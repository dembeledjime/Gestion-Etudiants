package dembele.djime.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dembele.djime.dao.FilieresDao;
import dembele.djime.models.Filiere;

@Controller
public class FiliereController {
	@Autowired
	FilieresDao filieresDao;

    Filiere filiereDetail;
    
    Filiere newFiliere= new Filiere();
    List<Filiere> allFiliere = new ArrayList<Filiere>();
    
    @GetMapping("/ajouter-filiere")
    public String ajouterFiliere(Model model) {
    	newFiliere = new Filiere();
    	model.addAttribute("newFiliere", newFiliere);
    	return"Filière/filiere-ajouter";
    }
    
    @PostMapping("enregistrer-filiere")
    public String enregistrerFiliere(Model model, @ModelAttribute Filiere filiere) {
    	filieresDao.save(filiere);
    	return ListeFiliere(model);
    }

    @GetMapping("/liste-filiere")
	public String ListeFiliere(Model model) {
		allFiliere = filieresDao.findAll() ;
		model.addAttribute("allFiliere", allFiliere) ;
		return "Filière/filiere-liste" ;
	}
    
    @GetMapping("filiere-detail")
	public String filiereDetail(Model model, @RequestParam Long id) {
		filiereDetail = filieresDao.findById(id).get() ;
		model.addAttribute("filiereDetail",filiereDetail) ;
		return "Filière/filiere-detail" ;
	}
    
    @GetMapping("/modifier-filiere")
	public String Modifierfiliere(Model model) {
		newFiliere = filiereDetail ;
		model.addAttribute("newFiliere", newFiliere) ;
		return "Filière/filiere-modifier" ;
	}
    
    @GetMapping("/modifier-filiere-2")
	public String Modifierfiliere2(Model model, @RequestParam Long id) {
    	newFiliere = filieresDao.findById(id).get() ;
		model.addAttribute("newFiliere", newFiliere) ;
		return "Filière/filiere-modifier" ;
	}
    
    @PostMapping("update-filiere")
	public String Updatefiliere(Model model, @ModelAttribute Filiere filiere, @RequestParam Long id) {
		filiere.setId(id);
		filieresDao.save(filiere) ;
		return ListeFiliere(model) ;
	}
	
	@GetMapping("/supprimer-filiere")
	public String Supprimerfiliere(Model model) {
		filieresDao.deleteById(filiereDetail.getId()) ;
		return ListeFiliere(model) ;
	}
	
	@GetMapping("/supprimer-filiere-2")
	public String Supprimerfiliere2(Model model, @RequestParam Long id) {
		filieresDao.deleteById(id) ;
		return ListeFiliere(model) ;
	}
}
