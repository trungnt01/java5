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

import com.poly.beans.Account_model;
import com.poly.entities.Account;
import com.poly.mapper.AccountMapper;
import com.poly.repositories.AccountRepository;
import com.poly.utiliti.EncryptUtil;

@Controller
@RequestMapping(value = "/admin/accounts")
public class AccountController {

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private AccountMapper mapper;
	
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
		Page<Account> pageData = this.accountRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		
		return "admin/accounts/index";
	}
	
	
	@GetMapping("/create")
	public String create(
		@ModelAttribute("account_model") Account_model account_model
	) {
		
		return "admin/accounts/create";
	}
	
	
	@PostMapping("/store")
	public String store(
		@Valid @ModelAttribute("account_model") Account_model account_model,
		BindingResult result
	) {
		if(result.hasErrors()) {
			System.out.println("form khong hơp le");
			return "redirect:/admin/accounts/create";
		} else {
			Account account = this.mapper.convertToEntity(account_model);
			String password = EncryptUtil.encrypt(account.getPassword());
			account.setPassword(password);
			this.accountRepo.save(account);
			
			return "redirect:/admin/accounts";
		}
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(
		@PathVariable(name = "id") Account account,
		Model model
	) {
		
		Account_model account_model = this.mapper.convertToDTO(account);
		
		model.addAttribute("account_model", account_model);
		return "admin/accounts/edit";
	}
	
	
	@PostMapping("/update/{id}")
	public String update(
		Model model,
		@Valid Account_model account_model,
		@PathVariable("id") Account account_old,
		BindingResult result
	) {
		if(result.hasErrors()) {
			System.out.println("Có lỗi ");
			model.addAttribute("error", "Có lỗi xảy ra vui lòng thử lại");
			return "redirect:/admin/accounts/edit/" + account_old.getId();
		} else {
			
			Account account = this.mapper.convertToEntity(account_model);
			account.setPassword(account_old.getPassword()) ;
			
			this.accountRepo.save(account);
			model.addAttribute("message", "Cập nhật thành công");
			return "redirect:/admin/accounts";
		}
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(
		@PathVariable(name = "id") Integer id
	) {
		this.accountRepo.deleteById(id);
		return "redirect:/admin/accounts";
	}
}
