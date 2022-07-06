package com.poly.controller.admin.accounts;

import java.io.File;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.poly.beans.Product_model;
import com.poly.entities.Category;
import com.poly.entities.Product;
import com.poly.mapper.ProductMapper;
import com.poly.repositories.CategoryRepository;
import com.poly.repositories.ProductRepository;
import com.poly.utiliti.UploadFileUtil;


@Controller
@RequestMapping(value = "/admin/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired 
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ProductMapper mapper;
	
	@Autowired UploadFileUtil upload;
	
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
		Page<Product> pageData = this.productRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		
		return "admin/products/index";
	}
	

	@GetMapping("/create")
	public String create(
		@ModelAttribute("product_model") Product_model product_model,
		Model model
	) {
		List<Category> lstCategory = this.categoryRepo.findAll();
		
		model.addAttribute("lstCategory" , lstCategory);
		return "admin/products/create";
	}
	
	
	@PostMapping("/store")
	public String store(
		@Valid @ModelAttribute("product_model") Product_model product_model,
		BindingResult result
	) {
		if(result.hasErrors()) {
			System.out.println("form khong hơp le");
			return "redirect:/admin/products/create";
		} else {
			Product product = this.mapper.convertToEntity(product_model);
			
			product.setCategory(this.categoryRepo.getById(product_model.getCategoryId()));
			product.setCreatedDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
			
			this.productRepo.save(product);
			
			return "redirect:/admin/products";
		}
	}
	
	@PostMapping("/storeExcel")
	public String storeExcel(
		@RequestParam("product_file") MultipartFile file 
		 
	) {
		
		 File newFile = this.upload.handleUploadFile(file);
		 String fileName = newFile.getName();
		 System.out.println("--------------"+fileName);
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(
		@PathVariable(name = "id") Product product,
		Model model
	) {
		
		Product_model product_model = this.mapper.convertToDTO(product);
		product_model.setCategoryId(product.getCategory().getId());
		List<Category> lstCategory = this.categoryRepo.findAll();
		
		model.addAttribute("lstCategory" , lstCategory);
		model.addAttribute("product_model", product_model);
		return "admin/products/edit";
	}
	
	
	@PostMapping("/update/{id}")
	public String update(
		Model model,
		@Valid Product_model product_model,
		BindingResult result,
		@PathVariable(name="id") Product product_old
	) {
		if(result.hasErrors()) {
			System.out.println("Có lỗi ");
			model.addAttribute("error", "Có lỗi xảy ra vui lòng thử lại");
			return "redirect:/admin/products/edit/" + product_model.getId();
		} else {
			
			Product product = this.mapper.convertToEntity(product_model);
			
			product.setCategory(this.categoryRepo.getById(product_model.getCategoryId()));
			product.setCreatedDate(product_old.getCreatedDate());
			
			this.productRepo.save(product);
			model.addAttribute("message", "Cập nhật thành công");
			return "redirect:/admin/products";
		}
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(
		@PathVariable(name = "id") Integer id
	) {
		this.productRepo.deleteById(id);
		return "redirect:/admin/products";
	}
}
