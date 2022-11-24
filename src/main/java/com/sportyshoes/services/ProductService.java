package com.sportyshoes.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.sportyshoes.entities.Product;
import com.sportyshoes.repository.ProductRepo;


@Service
public class ProductService {
	@Autowired
	ProductRepo prepo;
	
	public void addProduct(Product product) {
		this.prepo.save(product);
	}
	public void deleteProduct(int id) {
		this.prepo.deleteById(id);
	}
	public List<Product> getAllProduct(){
		Iterable<Product> findAll = this.prepo.findAll();
		List<Product> findAllProducts = Streamable.of(findAll).toList();
		return findAllProducts;
	}
	
	public Product findProduct(int id){
		Product getProduct = this.prepo.findById(id).get();
		if(Objects.isNull(getProduct)) {
			return new Product();
		}else {
			return getProduct;
		}
	}

	
	public Product updateProduct(int id) {
		Product newProduct = this.prepo.findById(id).get();
		return newProduct;
	}
	
	public List<Product> findProduct(String brand,String size,String type){
		Iterable<Product> findAll = this.prepo.findByBrandAndSizeAndType(brand, size, type);
		List<Product> getProducts = Streamable.of(findAll).toList();
		return getProducts;
	}

}
