package com.poly.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.beans.Login;
import com.poly.constant.SessionAtrr;
import com.poly.entities.Account;
import com.poly.repositories.AccountRepository;
import com.poly.utiliti.EncryptUtil;

@Controller
public class LoginController {

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "/auth/Login";
	}
	
	@PostMapping("/login")
	public String login(
		Login login
	) throws IOException {
		
		HttpSession session = this.request.getSession();
		
		Account acc = this.accountRepo.findByEmail(login.getEmail());
		
		if(acc == null) {
			
			return "redirect:/login";
		} else {
			if(EncryptUtil.check(login.getPassword(), acc.getPassword())) {
				
				session.setAttribute(SessionAtrr.CurrentUser, acc);
				return "redirect:/home";
			} else {
				
				return "redirect:/login";			
			}
		}
	}
	
	@GetMapping("/logout")
	public String logout() {
		this.request.getSession().removeAttribute(SessionAtrr.CurrentUser);
		return "redirect:/login";
	}
}
