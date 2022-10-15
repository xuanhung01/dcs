package com.shf.dcs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

// @Component
public class CustomLogoutHandler extends  SimpleUrlLogoutSuccessHandler  {
	
	   public CustomLogoutHandler() {
	        this.setDefaultTargetUrl("/j_spring_security_logout");
	   }

	   @Override
	   public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

	        // do whatever you want
	        super.onLogoutSuccess(request, response, authentication);
	   }
}
