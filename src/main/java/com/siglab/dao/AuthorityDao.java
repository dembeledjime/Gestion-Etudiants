package com.siglab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siglab.models.Authorities;

public interface AuthorityDao extends JpaRepository<Authorities, String> {
	public Authorities findByUsername(String username);
}
