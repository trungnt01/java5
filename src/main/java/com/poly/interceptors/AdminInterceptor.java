package com.poly.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.constant.SessionAtrr;
import com.poly.entities.Account;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handle
	) throws IOException {
		HttpSession session = request.getSession();
		Account acc = (Account) session.getAttribute(SessionAtrr.CurrentUser); 
		if(acc.getAdmin() == 0) {
			response.sendRedirect(request.getContextPath() + "/home");
			System.out.println(2);
			return false;
		}
		
		return true;
	}
}
