package com.siglab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.siglab.dao.AuthorityDao;
import com.siglab.dao.UserDao;

@SpringBootApplication
public class GestionDesEtudiantsApplication implements CommandLineRunner {
	@Autowired
	private UserDao userDao ;
	@Autowired
	private AuthorityDao authorityDao ;
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionDesEtudiantsApplication.class, args) ;
	}

	@Override
	public void run(String... args) throws Exception {
	
//		if (userDao.findByUsername("admin") == null) {
//			userDao.save(new Users("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
//			authorityDao.save(new Authorities("admin", "ROLE_ADMIN"));
//			// TODO Auto-generated method stub
//			// restConfiguration.exposeIdsFor(Classe.class) ;
//		}
	}


}