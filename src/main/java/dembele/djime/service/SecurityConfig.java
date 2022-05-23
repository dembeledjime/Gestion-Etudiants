package dembele.djime.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource); // Permet de prendre les usrs depuis la Base de données dont
															// les infos de connexion sont dans datasource de
															// application.properties
	}

	@Bean
	public PasswordEncoder passwordEncoder() { // Permet d'utiliser un password encoder pour Spring (Obligatoire)
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception { // Code recupé depuis Internet pour
																				// ajouter un User
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// c'est cette methode qui me permet de configuer la securité niveau http
		// qui va utliser un page de connexion
		http.formLogin();
		// ces ligne permet de faire une autorisation sur les url par rapport aux
		// aux user et leur role les 2 etoiles permet de dire tous ce qui vient avant ou
		// apres
		http.authorizeRequests().antMatchers("/**classe", "/**-filiere").hasAnyRole("USER", "ADMIN");
		http.authorizeRequests()
				.antMatchers("/enregistrer**", "/supprimer**", "/ajouter**", "/modifier**", "/update**", "/register")
				.hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated().and().exceptionHandling().accessDeniedPage("/error-403");
		// Derniere ligne pour ajouter une page personnalisé d'error 403 (il faut mapper
		// dans le controller le /error-403)

	}

}
