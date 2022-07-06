package com.poly.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.constant.SessionAtrr;
import com.poly.entities.Account;
import com.poly.entities.Cart;
import com.poly.entities.Product;
import com.poly.repositories.CartRepository;
import com.poly.repositories.ProductRepository;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping()
	public String index(
		Model model,
		@RequestParam(name = "page", defaultValue = "0") int page,
		@RequestParam(name = "size", defaultValue = "15") int size
	) {
		Pageable pageable = PageRequest.of(page, size); 
		Page<Cart> pageData = this.cartRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		int total = 0;
		List<Cart> lstCart = this.cartRepo.findAll();
		for (Cart cart : lstCart) {
			total += cart.getQuantity()*cart.getProduct().getPrice();
		}
		
		model.addAttribute("total", total);
		return "/auth/cart";
	}
	
	
	@PostMapping("/store")
	public String store(
			@RequestParam("productId") int productId 
	) {
		
		Account account = (Account) this.request.getSession().getAttribute(SessionAtrr.CurrentUser);
		
		Product product  = this.productRepo.getById(productId);
		
		Cart cart = this.cartRepo.findByAccountIdAndProductId( account.getId(), productId);
		
		if(cart == null) {
			Cart cart_new = new Cart();
			cart_new.setProduct(product);
			cart_new.setAccount(account);
			cart_new.setQuantity(1);
			this.cartRepo.save(cart_new);
		} else {
			cart.setQuantity(cart.getQuantity()+1);
			this.cartRepo.save(cart);
		}
		
		return "redirect:/cart";
	}
	
	
	@PostMapping("/update/{id}")
	@ResponseBody
	public void update(
			@PathVariable(name = "id") Cart cart
//			@RequestParam(name = "upOrDown") String upOrDown
	) throws IOException {
//		int cartId = Integer.parseInt(request.getParameter("cartId"));
		String upOrDown = request.getParameter("upOrDown");
		if(upOrDown.equals("down")) {
			cart.setQuantity(cart.getQuantity() - 1);
		} 
		if(upOrDown.equals("up")) {
			cart.setQuantity(cart.getQuantity() + 1);
		}
		
		this.cartRepo.save(cart);
		PrintWriter out = this.response.getWriter();
		out.print(cart.getQuantity());
//		return "/auth/cart";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id) {
		this.cartRepo.deleteById(id);
		return "redirect:/cart";
	}
	
}
