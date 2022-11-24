package com.sportyshoes.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.entities.AdminLogin;

@Repository
public interface AdminRepo extends CrudRepository<AdminLogin, Integer> {
	public AdminLogin findByUsernameAndPassword(String username,String password);
}
