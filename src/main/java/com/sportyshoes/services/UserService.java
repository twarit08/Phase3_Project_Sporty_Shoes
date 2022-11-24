package com.sportyshoes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.sportyshoes.entities.Order;
import com.sportyshoes.entities.UserRegistration;
import com.sportyshoes.repository.OrderRepo;
import com.sportyshoes.repository.RegistrationRepo;


@Service
public class UserService {
	
	@Autowired
	RegistrationRepo regRepo;
	
	@Autowired
	OrderRepo orepo;
	
	public void registerUser(UserRegistration user) {
		this.regRepo.save(user);
	}
	
	public List<UserRegistration> getUsers(){
		Iterable<UserRegistration> it = this.regRepo.findAll();
		List<UserRegistration> allUsers = Streamable.of(it).toList();
		return allUsers;
	}
	
	public void deleteUser(int userid) {
		this.regRepo.deleteById(userid);
	}
	
	public List<UserRegistration> getUser(String name){
		Iterable<UserRegistration> it = this.regRepo.findByNameStartingWith(name);
		List<UserRegistration> users = Streamable.of(it).toList();
		return users;
	}
	
	public void addOrder(Order order) {
		this.orepo.save(order);
	}
	
	public List<Order> showOrders(){
		Iterable<Order> findAll = this.orepo.findAll();
		List<Order> showAll = Streamable.of(findAll).toList();
		return showAll;
	}
	
	public List<Order> showOrderByCategoryAndDate(String category,String date){
		Iterable<Order> findOrders = this.orepo.findByCategoryAndDate(category, date);
		List<Order> allOrders = Streamable.of(findOrders).toList();
		return allOrders;
	}
	
	public void deleteOrder(int id) {
		this.orepo.deleteById(id);
	}

}
