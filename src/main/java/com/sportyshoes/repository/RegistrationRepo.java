package com.sportyshoes.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportyshoes.entities.UserRegistration;

public interface RegistrationRepo extends CrudRepository<UserRegistration,Integer> {
	
	public Iterable<UserRegistration> findByNameStartingWith(String name);

}
