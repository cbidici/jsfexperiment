package com.cbstd.jsfexperiment.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cbstd.jsfexperiment.commons.entity.Base;

/**
 * Entitiy to hold application user data
 * 
 * @author cbidici
 */

@Entity
@Table(name = "jsfexp_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User extends Base {

	private static final long serialVersionUID = -3733306569896341627L;

	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}