package dembele.djime.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dembele.djime.dao.AuthorityDao;
import dembele.djime.dao.UserDao;

import dembele.djime.models.Users;

@Controller
public class UserController {
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;
	@Autowired
	PasswordEncoder passwordEncoder ;
	@Autowired
	UserDao userDao ;
	@Autowired
	AuthorityDao authorityDao ;
	String oldUsername;
	
	Users userDetail;
	Users newUser = new Users();
	

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		return new ModelAndView("register", "user", new Users());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST) // Methode recupérée depuis Internet pour enregistrer un user depuis le formulaire 
	public ModelAndView processRegister(@ModelAttribute("user") Users userRegistrationObject) { //  Recupere le ModelAttribut du formulaire html avec le nom : userRegistrationObject
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); // Cree une liste d'authorité ou Role car 1 user peut avoir plusireur autority ou role 
		authorities.add(new SimpleGrantedAuthority("ROLE_" + userRegistrationObject.getAuthority())); // ajoute le role recupéré depuis le form dans la liste des authorité crée sous le prefixe ROLE_ qui correspond a la convention de nommage de spring Secrutiy

		// Cree mtn un nouvel user de la classe USer de Spring lui meme en lui donnant l'username du modelAttribut, le mot de passe encodé avc notre password encodeur injecté, et la liste de ses role créé plus haut 
		User user = new User(userRegistrationObject.getUsername(), passwordEncoder.encode( userRegistrationObject.getPassword() ), authorities); 
		// Ajoute mtn l'user dans la DB avc le bean injecté jdbcUserDetailsManager ( code recupéré depuis Internet aussi )
		jdbcUserDetailsManager.createUser(user);
		// Redirige mtn vers la View Home
		return new ModelAndView("redirect:/home");
	}
	
	@RequestMapping("error-403")
	public String error403() {
		return "/User/500.html" ;
	}
	
	@GetMapping("/users")
	public String usersList(Model model) {
		ArrayList<Users> users = new ArrayList<Users>() ;
		userDao.findAll().forEach(user -> {
			user.setAuthority(authorityDao.findByUsername(user.getUsername()).getAuthority()) ;
			users.add(user) ;
		}) ;
		model.addAttribute("allUsers", users) ;
		return "/User/user-liste" ;
	}
	
	@GetMapping("user-detail")
	public String userDetail(Model model, @RequestParam String username) {
		userDetail = userDao.findByUsername(username);
     	userDetail.setAuthority(authorityDao.findByUsername(userDetail.getUsername()).getAuthority()) ;
		model.addAttribute("userDetail",userDetail) ;
		return "/User/user-detail" ;
	}
	    
    @GetMapping("/modifier-user")
	public String Modifieruser(Model model) {
    	oldUsername=userDetail.getUsername();
		newUser = userDetail ;
		model.addAttribute("newUser", newUser) ;
		return "/User/user-modifier" ;
	}
    
    @GetMapping("/modifier-user-2")
	public String Modifieruser2(Model model, @RequestParam String username) {
    	oldUsername=username;
    	 newUser = userDao.findByUsername(username) ;
		model.addAttribute("newUser", newUser) ;
		return "/User/user-modifier" ;
	}
    
    
    @PostMapping("update-user")
	public String Updateuser(Model model, @ModelAttribute Users userRegistrationObject) {
//    	userRegistrationObject.setUsername(username);
    	authorityDao.delete(authorityDao.findByUsername(oldUsername));
    	userDao.delete(userDao.findByUsername(oldUsername));
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		authorities.add(new SimpleGrantedAuthority("ROLE_" + userRegistrationObject.getAuthority())); 
		User user = new User(userRegistrationObject.getUsername(), passwordEncoder.encode( userRegistrationObject.getPassword() ), authorities); 
		jdbcUserDetailsManager.createUser(user);
		
		return usersList(model) ;
	}
	
	@GetMapping("/supprimer-user")
	public String Supprimeruser(Model model) {
		authorityDao.delete(authorityDao.findByUsername(userDetail.getUsername()));
		userDao.delete(userDao.findByUsername(userDetail.getUsername()));
		return usersList(model) ;
	}
	
	@GetMapping("/supprimer-user-2")
	public String Supprimeruser2(Model model, @RequestParam String username) {
		authorityDao.delete(authorityDao.findByUsername(username));
		userDao.delete(userDao.findByUsername(username));
		return usersList(model) ;
	}

}
