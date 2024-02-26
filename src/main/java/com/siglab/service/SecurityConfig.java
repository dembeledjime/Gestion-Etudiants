package com.siglab.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.config.Customizer.withDefaults;
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
		auth.jdbcAuthentication().dataSource(dataSource); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(withDefaults());
        http.authorizeHttpRequests(requests -> requests.antMatchers("/**classe", "/**-filiere").hasAnyRole("USER", "ADMIN"));
        http.authorizeHttpRequests(requests -> requests
                .antMatchers("/enregistrer**", "/supprimer**", "/ajouter**", "/modifier**", "/update**", "/register")
                .hasRole("ADMIN"));
        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated()).exceptionHandling(handling -> handling.accessDeniedPage("/error-403"));
		
	}


    @Bean
    PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}

    @Bean
    JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {  
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

}
