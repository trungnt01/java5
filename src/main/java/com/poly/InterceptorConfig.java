package com.poly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poly.interceptors.Authorization;
import com.poly.interceptors.Authenticate;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private Authenticate authenticate;
	
	@Autowired
	private Authorization admin;
	
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(authenticate).
		addPathPatterns("/admin/**", "/users/**", "/cart").
		excludePathPatterns("/login", "/register", "/home");
		
		registry.addInterceptor(admin).
		addPathPatterns("/admin/**" ).
		excludePathPatterns("/profile", "/cart" );
	}
	
}
