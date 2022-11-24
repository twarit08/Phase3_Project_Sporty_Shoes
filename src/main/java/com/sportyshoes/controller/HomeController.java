package com.sportyshoes.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sportyshoes.entities.AdminLogin;
import com.sportyshoes.entities.Order;
import com.sportyshoes.entities.Product;
import com.sportyshoes.entities.UserRegistration;
import com.sportyshoes.services.LoginService;
import com.sportyshoes.services.ProductService;
import com.sportyshoes.services.UserService;

@Controller
public class HomeController {

	@Autowired
	LoginService login;

	@Autowired
	ProductService pservice;

	@Autowired
	UserService uservice;

	@GetMapping("/adminlogin")
	public String login(Model model) {
		model.addAttribute("adminLogin", new AdminLogin());
		return "adminlogin";
	}

	@PostMapping("/dashboard")
	public String adminLogin(@Valid @ModelAttribute("adminLogin") AdminLogin adminLogin, BindingResult result,
			Model model) {
		String username = adminLogin.getUsername();
		String password = adminLogin.getPassword();
		try {
			AdminLogin login = this.login.validate(username, password);
			if (Objects.isNull(login) || result.hasErrors()) {
				System.out.println("Invalid Credentials");
				System.out.println(result);
				model.addAttribute("invalid", "Invalid Credentials!");
				return "adminlogin";
			} else {
				System.out.println("Login successfully");
				return "adminhome";
			}
		} catch (Exception e) {
			System.out.println("Exception Handled");
			return "adminlogin";
		}
	}
	
	@GetMapping("/updatepassword")
	public String showChangePassword(Model model) {
		model.addAttribute("admin", new AdminLogin());
		return "changepassword";
	}
	
	@PostMapping("/changepassword")
	public String changePassword(@ModelAttribute("admin") AdminLogin userLogin,@RequestParam("newpassword") String newPassword,Model model) {
		String username = userLogin.getUsername();
		String password = userLogin.getPassword();
		try {
			AdminLogin newLogin = this.login.validate(username, password);
			if(Objects.isNull(newLogin)) {
				model.addAttribute("invalid", "Invalid Username or Password!");
				return "changepassword";
			}else {
				newLogin.setPassword(newPassword);
				this.login.saveCredentials(newLogin);
				model.addAttribute("message", "Password changed successfully.");
				return "changepassword";
			}
		}catch(Exception e) {
			System.out.println("Exception Handled");
			return "changepassword";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		System.out.println("Logout successfully");
		model.addAttribute("message", "Logged out successfully!");
		return "logout";
	}

	@GetMapping("/getaddproduct")
	public String getAddProduct(Model model) {
		model.addAttribute("product", new Product());
		return "addproduct";
	}

	@PostMapping("/addnewproduct")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "addproduct";
		} else {
			this.pservice.addProduct(product);
			model.addAttribute("message", "Product added successfully");
			return "addproduct";
		}
	}

	@GetMapping("/getallproducts")
	public String getAllProducts(Model model) {
		List<Product> getProducts = this.pservice.getAllProduct();
		model.addAttribute("products", getProducts);
		if (getProducts.isEmpty()) {
			model.addAttribute("message", "No product found!");
			return "getproduct";
		} else {
			return "getproduct";
		}
	}

	@GetMapping("/deleteproduct")
	public String deleteProduct(@RequestParam int id) {
		this.pservice.deleteProduct(id);
		return "redirect:/getallproducts";
	}

	@GetMapping("/updateproduct")
	public String updateProduct(@RequestParam int id, Model model) {
		Product newProduct = this.pservice.updateProduct(id);
		model.addAttribute("product", newProduct);
		return "addproduct";
	}

	@GetMapping("/getproducts")
	public String getProducts(@RequestParam("brand") String brand, @RequestParam("size") String size,
			@RequestParam("type") String type, Model model) {
		List<Product> products = this.pservice.findProduct(brand, size, type);
		model.addAttribute("products", products);
		if (products.isEmpty()) {
			model.addAttribute("message", "No such product found!");
			return "showproduct";
		} else {
			return "showproduct";
		}
	}
	
	@GetMapping("/getproductbyid")
	public String getProductById(@RequestParam int id,Model model) {
		Product product = this.pservice.findProduct(id);
		model.addAttribute("products", product);
		return "getproduct";
	}

	@GetMapping("/selectproduct")
	public String showProducts() {
		return "selectproduct";
	}

	@GetMapping("/register")
	public String userRegistration(Model model) {
		model.addAttribute("user", new UserRegistration());
		return "registeruser";
	}

	@PostMapping("/adduser")
	public String addUser(@Valid @ModelAttribute("user") UserRegistration user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "registeruser";
		} else {
			this.uservice.registerUser(user);
			model.addAttribute("message", "User Registered Successfully");
			return "registeruser";
		}
	}

	@GetMapping("/getusers")
	public String getUser(Model model) {
		List<UserRegistration> users = this.uservice.getUsers();
		model.addAttribute("user", users);
		if (users.isEmpty()) {
			model.addAttribute("message", "No Users Registered!");
			return "getusers";
		} else {
			return "getusers";
		}
	}

	@GetMapping("/deleteuser")
	public String deleteUser(@RequestParam int userid) {
		this.uservice.deleteUser(userid);
		return "redirect:/getusers";
	}

	@GetMapping("/showusers")
	public String showUser(@RequestParam("name") String name, Model model) {
		List<UserRegistration> user = this.uservice.getUser(name);
		model.addAttribute("user", user);
		model.addAttribute("value", name);
		if (user.isEmpty()) {
			model.addAttribute("message", "No such user found!");
			return "getusers";
		} else {
			return "getusers";
		}
	}

	@GetMapping("/buynow")
	public String orderProduct(@RequestParam int pid, Model model) {
		Product findProduct = this.pservice.findProduct(pid);
		model.addAttribute("product", findProduct);
		return "orderproduct";
	}

	@PostMapping("/makeorder")
	public String makeOrder(@RequestParam("id") int id, @RequestParam("username") String username,
			@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("contact") String contact, @RequestParam("category") String category,
			@RequestParam("date") String date, Model model) {
		Product product = this.pservice.findProduct(id);
		Order newOrder = new Order();
		newOrder.setUsername(username);
		newOrder.setName(name);
		newOrder.setAddress(address);
		newOrder.setCity(city);
		newOrder.setState(state);
		newOrder.setContact(contact);
		newOrder.setCategory(category);
		newOrder.setDate(date);
		newOrder.setProduct(product);
		this.uservice.addOrder(newOrder);
		model.addAttribute("message", "Order placed successfully.");
		return "ordercreated";
	}
	
	@GetMapping("/searchorder")
	public String selectOrder() {
		return "searchorder";
	}
	
	@GetMapping("/showorders")
	public String searchOrder(@RequestParam("category") String category,@RequestParam("date") String date,Model model) {
		List<Order> orders = this.uservice.showOrderByCategoryAndDate(category, date);
		model.addAttribute("order",orders);
		if(orders.isEmpty()) {
			model.addAttribute("message", "No such order found!");
			return "showorders";
		}else {
			return "showorders";
			}
	}
	
	@GetMapping("/deleteorder")
	public String deleteOrder(@RequestParam int id) {
		this.uservice.deleteOrder(id);
		return "showorders";
	}
	
	@GetMapping("/sportyshoeshome")
	public String home() {
		return "sportyshoeshome";
	}

}
