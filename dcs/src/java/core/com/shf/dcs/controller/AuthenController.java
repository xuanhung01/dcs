package com.shf.dcs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenController {

	@RequestMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("subpage", "login");
		return "common/authen";
	}

	@RequestMapping(value = "/invalidSession", method = RequestMethod.POST)
	public String invalidSession(HttpServletRequest request) {
		return "common/invalidSession";
	}
	
	@RequestMapping(value = "/invalidSession", method = RequestMethod.GET)
	public String invalidSessionGET(HttpServletRequest request) {
		return "common/invalidSession";
	}
	
	@RequestMapping(value = "/killSession", method = RequestMethod.GET)
	public String killSessionGet(HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "common/killSession";
	}
	
	@RequestMapping(value = "/killSession", method = RequestMethod.POST)
	public String killSessionPost(HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "common/killSession";
	}
	
	@RequestMapping(value = "/cobDcs", method = RequestMethod.GET)
	public String cobDcsGet(HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "common/cobDcs";
	}
	
	@RequestMapping(value = "/cobDcs", method = RequestMethod.POST)
	public String cobDcsPost(HttpServletRequest request) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "common/cobDcs";
	}
	
	@RequestMapping(value = "/expiredAccount")
	public String invalidAccount(HttpServletRequest request) {
		return "common/expiredAccount";
	}
	
	@RequestMapping(value = "/activate")
	public String activate(HttpServletRequest request) {
		return "common/activate";
	}
}
