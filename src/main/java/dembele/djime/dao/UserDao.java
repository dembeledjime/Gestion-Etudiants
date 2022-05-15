package dembele.djime.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import dembele.djime.models.Users;

public interface UserDao extends JpaRepository<Users, Long> {
	public Users findByUsername(String username) ;

	

	

	
	

}
