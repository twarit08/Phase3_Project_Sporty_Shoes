package com.sportyshoes.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="adminlogin")
public class AdminLogin {
	@Id
	private int id;
	
	@NotBlank(message = "Username cannot be empty!")
	private String username;
	
	@NotBlank(message = "Password cannot be empty!")
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "AdminLogin [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	

}
