package com.poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.constant.SessionAtrr;
import com.poly.entities.Account;
import com.poly.entities.Cart;
import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
import com.poly.entities.Product;
import com.poly.repositories.CartRepository;
import com.poly.repositories.OrderDetailRepository;
import com.poly.repositories.OrderRepository;

@Controller
@RequestMapping(value = "/orders")
public class OrderUserController {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping
	public String index(
		Model model
	) {
		HttpSession session = this.request.getSession();
		Account account = (Account) session.getAttribute(SessionAtrr.CurrentUser);
	
		List<Order> lstOrder = this.orderRepo.findByAccountId(account.getId());
		model.addAttribute("lstOrder", lstOrder);
		
		return "/auth/myOrder";
			
	}
	
	@GetMapping("/create/{id}")
	public String create(
		@PathVariable("id") Product product,
		Model model
	) {
		model.addAttribute("product", product);
		return "/auth/createOrder";
	}
	
	@PostMapping("/store/{id}")
	public String store(
		@PathVariable("id") Product product,
		@RequestParam(name = "quantity") Integer quantity,
		@RequestParam(name = "address") String addresss
	) {
		
		HttpSession session = this.request.getSession();
		Account account = (Account) session.getAttribute(SessionAtrr.CurrentUser);
		
		Order order = new Order();
		order.setAccount(account);
		order.setAddress(addresss);
		order.setCreateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
		order.setCustomerName(account.getFullname());
		
		Order new_order = this.orderRepo.saveAndFlush(order);
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(new_order);
		orderDetail.setPrice(product.getPrice());
		orderDetail.setProduct(product);
		orderDetail.setQuantity(quantity);
		
		this.orderDetailRepo.save(orderDetail);
		
		return "redirect:/orders";
	}
	
	
	@PostMapping("/store")
	public String storeAll(
		 
	) {
		
		HttpSession session = this.request.getSession();
		Account account = (Account) session.getAttribute(SessionAtrr.CurrentUser);
		
		Order order = new Order();
		order.setAccount(account);
		order.setAddress(account.getAddress());
		order.setCreateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
		order.setCustomerName(account.getFullname());
		
		Order new_order = this.orderRepo.saveAndFlush(order);
		
		List<Cart> lstCart = this.cartRepo.findByAccountId(account.getId());
		
		List<OrderDetail> lstOrderDetail = new ArrayList<OrderDetail>();
		for (Cart cart : lstCart) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(new_order);
			orderDetail.setPrice(cart.getProduct().getPrice());
			orderDetail.setProduct(cart.getProduct());
			orderDetail.setQuantity(cart.getQuantity());
			
			lstOrderDetail.add(orderDetail);
		}
		
		this.orderDetailRepo.saveAll(lstOrderDetail);
		
		this.cartRepo.deleteAll(lstCart);
		
		return "redirect:/orders";
	}
	
}
