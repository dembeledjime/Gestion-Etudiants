package com.siglab.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.siglab.models.Users;

public interface UserDao extends JpaRepository<Users, String> {
	public Users findByUsername(String username) ;

	

	

	
	

}
