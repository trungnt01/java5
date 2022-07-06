package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Category;
import com.poly.entities.Product;
import com.poly.repositories.CategoryRepository;
import com.poly.repositories.ProductRepository;

@Controller
public class HomeController {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/home")
	public String index(
		Model model,
		@RequestParam(name="page", defaultValue="0") int page,
		@RequestParam(name="size", defaultValue="20") int size
	) {
		List<Category> lstCategory = this.categoryRepo.findAll();
		
		Pageable pageable = PageRequest.of(page, size); 
		Page<Product> pageData = this.productRepo.findAll(pageable);
		
		model.addAttribute("pageData", pageData);
		model.addAttribute("lstCategory", lstCategory);
		
		return "/auth/home";
	}
}
