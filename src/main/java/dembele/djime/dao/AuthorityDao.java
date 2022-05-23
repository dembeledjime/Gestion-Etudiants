package dembele.djime.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dembele.djime.models.Authorities;

public interface AuthorityDao extends JpaRepository<Authorities, Long> {
	public Authorities findByUsername(String username);
}
