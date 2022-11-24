package com.sportyshoes.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="products")
public class Product {

	@Id
	@Range(min = 1,max = 5000,message = "Id must be greater than 0")
	private int id;
	
	@NotBlank(message = "Name cannot be empty.")
	private String name;
	
	@NotBlank(message = "Brand cannot be empty.")
	private String brand;
	
	private String size;
	
	private String type;
	
	private String gender;
	
	@NotBlank(message = "Price cannot be empty.")
	private String price;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Order> order;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", brand=" + brand + ", size=" + size + ", type=" + type
				+ ", gender=" + gender + ", price=" + price + "]";
	}

}
