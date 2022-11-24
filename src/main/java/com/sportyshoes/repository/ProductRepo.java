package com.sportyshoes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.entities.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product,Integer>  {
		public Iterable<Product> findByBrandAndSizeAndType(String brand,String size,String type);
}
