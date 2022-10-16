package com.shf.dcs.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shf.dcs.dto.UserAccountDto;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;

@Controller
@RequestMapping("/system/password")
public class PasswordController {
	
	private static Logger logger = Logger.getLogger(PasswordController.class);
	// -----------------------------------------------------------------------
	@Autowired
	public IUserService userService;

	@RequestMapping(value = "/load")
	public ModelAndView resetPassword(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView(Constants.URL_DASH_BOARD);
		model.addObject("subpage", "system_users");
		model.addObject("act", "reset");
		DebtUser user = userService.getLoggedUser();
		UserAccountDto userDto = userService.createDto(user);
		model.addObject("formModel", userDto);
		return model;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePassword(@ModelAttribute @Valid UserAccountDto dto,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession httpSession) {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		if (errors.isEmpty()) {
			try {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				DebtUser user = userService.getLoggedUser();
				if(!bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
					user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
					user.setDescription(dto.getPassword());
					userService.saveUser(user);
					dto.setPassword(user.getPassword());
					dto.setPasswordConfirm(user.getPassword());
				}
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
				errors.add(new ObjectError("DataError", e.getMessage()));
			}
		}
		if (!errors.isEmpty()) {
			model.addAttribute("listErrors", errors);
		} else {
			model.addAttribute("message", "EditUserSuccess");
			// return "redirect:/system/password/load";
		}
		model.addAttribute("formMode", "edit");
		model.addAttribute("subpage", "system_users");
		model.addAttribute("act", "reset");
		model.addAttribute("formModel", dto);
		return Constants.URL_DASH_BOARD;
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String password = bCryptPasswordEncoder.encode("Mb@12345");
		System.out.println(password);
	}
}
