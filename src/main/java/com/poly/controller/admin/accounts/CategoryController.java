package com.poly.controller.admin.accounts;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.beans.Category_model;
import com.poly.beans.Product_model;
import com.poly.entities.Category;
import com.poly.entities.Product;
import com.poly.mapper.CategoryMapper;
import com.poly.mapper.ProductMapper;
import com.poly.repositories.CategoryRepository;

@Controller
@RequestMapping(value = "/admin/categories")
public class CategoryController {

	@Autowired 
	private CategoryRepository categoryRepo;
	
	@Autowired
	private CategoryMapper mapper;
	
	@GetMapping()
	public String index(
		Model model,
		@RequestParam(name = "page", defaultValue = "0") int page,
		@RequestParam(name = "limit", defaultValue = "10") int limit,
		@RequestParam(name = "sort_by", defaultValue = "id") String sort_field,
		@RequestParam(name = "sort_direction", defaultValue = "asc") String sort_direction
	) {
		
		Sort sort = ( sort_direction.equalsIgnoreCase("asc")) ? Sort.by(Direction.ASC, sort_field) : Sort.by(Direction.DESC, sort_field);
		Pageable pageable = PageRequest.of(page, limit, sort); 
		Page<Category> pageData = this.categoryRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		
		return "admin/categories/index";
	}
	

	@GetMapping("/create")
	public String create(
		@ModelAttribute("category_model") Category_model category_model
	) {
		
		return "admin/categories/create";
	}
	
	
	@PostMapping("/store")
	public String store(
		@Valid @ModelAttribute("category_model") Category_model category_model,
		BindingResult result
	) {
		if(result.hasErrors()) {
			System.out.println("form khong hơp le");
			return "redirect:/admin/categories/create";
		} else {
			Category category = this.mapper.convertToEntity(category_model);
			
			this.categoryRepo.save(category);
			
			return "redirect:/admin/categories";
		}
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(
		@PathVariable(name = "id") Category category,
		Model model
	) {
		Category_model category_model = this.mapper.convertToDTO(category);
		
		model.addAttribute("category_model", category_model);
		return "admin/categories/edit";
	}
	
	
	@PostMapping("/update/{id}")
	public String update(
		Model model,
		@Valid Category_model category_model,
		BindingResult result
	) {
		if(result.hasErrors()) {
			System.out.println("Có lỗi ");
			model.addAttribute("error", "Có lỗi xảy ra vui lòng thử lại");
			return "redirect:/admin/categories/edit/" + category_model.getId();
		} else {
			Category category = this.mapper.convertToEntity(category_model);
			
			this.categoryRepo.save(category);
			model.addAttribute("message", "Cập nhật thành công");
			return "redirect:/admin/categories";
		}
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(
		@PathVariable(name = "id") Integer id
	) {
		this.categoryRepo.deleteById(id);
		return "redirect:/admin/categories";
	}
	
}
