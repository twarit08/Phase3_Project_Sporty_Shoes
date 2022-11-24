package com.sportyshoes.repository;

import org.springframework.data.repository.CrudRepository;

import com.sportyshoes.entities.Order;

public interface OrderRepo extends CrudRepository<Order,Integer> {
	
	public Iterable<Order> findByCategoryAndDate(String category,String date);

}
