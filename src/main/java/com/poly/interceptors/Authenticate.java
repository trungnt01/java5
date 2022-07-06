package com.poly.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.constant.SessionAtrr;

@Component
public class Authenticate implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handle
	) throws IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute(SessionAtrr.CurrentUser) == null) {
			session.setAttribute("error", "Vui lòng đăng nhập");
			response.sendRedirect(request.getContextPath() + "/login");
			System.out.println(1);
			return false;
		}
		return true;
	}
}
