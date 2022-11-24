package com.sportyshoes.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.entities.AdminLogin;
import com.sportyshoes.repository.AdminRepo;

@Service
public class LoginService {
	@Autowired
	AdminRepo repo;
	
	public AdminLogin validate(String username,String password) {
		AdminLogin login = this.repo.findByUsernameAndPassword(username, password);
		return login;
	}
	public void saveCredentials(AdminLogin admin) {
		this.repo.save(admin);
	}
	

}
