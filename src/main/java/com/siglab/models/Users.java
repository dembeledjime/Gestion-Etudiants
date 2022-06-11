package com.siglab.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Users {
	@Id
	private String username ;
	private String password ;
	@Transient  // Signifie que l'attribut n'est pas persisté dans la DB 
	private String authority ;
}
