package com.shf.dcs.controller;

import java.security.Principal;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Control exception handling.
 * 
 * @author cuongnguyen
 *
 */
@Controller
public class AppController {
	public static final String REDIRECT_NOPERMISSION = "redirect:/403";

	public ModelAndView handleNoSuchRequestHandlingMethodException() {
		return null;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		model.setViewName("common/403");
		return model;
	}
	
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ModelAndView access(Exception ex) {
		ModelAndView model = new ModelAndView();
		model.setViewName("common/500");
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView hasException(Exception ex) {

		ModelAndView model = new ModelAndView();
		model.setViewName("common/outService");
		model.addObject("exceptionMessage", ex.getMessage());
		ex.printStackTrace();
		return model;
	}
     
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noPage(HttpServletRequest request, Exception ex) {

		ModelAndView model = new ModelAndView();
		model.setViewName("common/403");
		return model;
	}

	// @RequestMapping(value = "/404", method = RequestMethod.GET)
	// public ModelAndView noPage() {
	// ModelAndView model = new ModelAndView();
	// model.setViewName("common/403");
	// return model;
	// }

	public static boolean isProductionMode(HttpServletRequest request) {
		return !request.getLocalAddr().startsWith("10.192")
				&& !request.getLocalAddr().startsWith("localhost");
	}
}
