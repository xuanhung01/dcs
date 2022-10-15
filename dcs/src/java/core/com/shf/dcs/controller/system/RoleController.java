package com.shf.dcs.controller.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.shf.dcs.dto.RoleDto;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IRoleService;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;

@Controller
@RequestMapping("/system/role")
public class RoleController {

	private static Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	public IUserService userService;
	
	@Autowired
	public IRoleService roleService;
	
	public static String SUB_PAGE = "system_roles";
	
	// -----------------------------------------------------------------------
	
	@RequestMapping(value = "/load")
	public String users(Model model, HttpServletRequest request) throws Exception{
		userService.addUserInfo(model, request);
		DebtUser currentUser = userService.getLoggedUser();
		if (currentUser != null) {
			List<DebtUserRole> listRole = roleService.findAll();
			model.addAttribute(Constants.SUB_PAGE, SUB_PAGE);
			model.addAttribute(Constants.LIST_ROLE, listRole);
		}
		return Constants.URL_DASH_BOARD;
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView showAddUser(HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Constants.URL_DASH_BOARD);
		userService.addUserInfo(model, request);
		model.addObject(Constants.SUB_PAGE, SUB_PAGE);
		model.addObject(Constants.ACTION, Constants.ACTION_CREATE);
		RoleDto roleDto = new RoleDto();
		roleDto.setHasMobile(false);
		roleDto.setHasDcs(true);
		model.addObject(Constants.FORM_MODE, Constants.ACTION_CREATE);
		model.addObject(Constants.FORM_MODEL, roleDto);
		return model;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute @Valid RoleDto dto,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession httpSession) {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		DebtUserRole edittedRole = null;
		try {
			dto.setUserName(userService.getLoggedUserName());
			edittedRole = roleService.createNewRole(dto);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		if (!errors.isEmpty()) {
			model.addAttribute(Constants.LIST_ERRORS, errors);
		} else {
			if (edittedRole != null) {
				model.addAttribute(Constants.MESSAGE, Constants.MESSAGE_ADD_USER_SUCCESS);
				return "redirect:/system/role/load";
			}
		}
		userService.addUserInfo(model, request);
		model.addAttribute(Constants.FORM_MODE, Constants.ACTION_CREATE);
		model.addAttribute(Constants.SUB_PAGE, SUB_PAGE);
		model.addAttribute(Constants.ACTION, Constants.ACTION_CREATE);
		model.addAttribute(Constants.FORM_MODEL, dto);
		return Constants.URL_DASH_BOARD;
	}
	
	@RequestMapping(value = "/view/{id:.+}")
	public ModelAndView showViewUser(@PathVariable("id") long id,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView(Constants.URL_DASH_BOARD);
		model.addObject(Constants.SUB_PAGE, SUB_PAGE);
		model.addObject(Constants.ACTION, Constants.ACTION_VIEW);
		DebtUserRole role = roleService.findRoleById(id);
		userService.addUserInfo(model, request);
		model.addObject(Constants.FORM_MODEL, role);
		return model;
	}
	
	@RequestMapping(value = "/edit/{id:.+}")
	public ModelAndView showEditUser(@PathVariable("id") long id,
			HttpServletRequest request) {
		RoleDto dto = new RoleDto();
		ModelAndView model = new ModelAndView(Constants.URL_DASH_BOARD);
		model.addObject(Constants.SUB_PAGE, SUB_PAGE);
		model.addObject(Constants.ACTION, Constants.ACTION_EDIT);

		DebtUserRole role = roleService.findRoleById(id);
		BeanUtils.copyProperties(role, dto);
		dto.setIdHidden(role.getId());
		userService.addUserInfo(model, request);
		model.addObject(Constants.FORM_MODE, Constants.ACTION_EDIT);
		model.addObject(Constants.FORM_MODEL, dto);
		return model;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute @Valid RoleDto dto,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession httpSession) {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		DebtUserRole edittedRole = null;

		if (errors.isEmpty()) {
			try {
				edittedRole = roleService.editRole(dto);
			} catch (Exception e) {
				e.printStackTrace();
				errors.add(new ObjectError("DataError", e.getMessage()));
			}
		}
		if (!errors.isEmpty()) {
			model.addAttribute(Constants.LIST_ERRORS, errors);
		} else {
			if (edittedRole != null) {
				model.addAttribute(Constants.MESSAGE, Constants.MESSAGE_EDIT_USER_SUCCESS);
				return "redirect:/system/role/load";
			}
		}
		userService.addUserInfo(model, request);
		model.addAttribute(Constants.FORM_MODE, Constants.ACTION_EDIT);
		model.addAttribute(Constants.SUB_PAGE, SUB_PAGE);
		model.addAttribute(Constants.ACTION, Constants.ACTION_EDIT);
		model.addAttribute(Constants.FORM_MODEL, dto);
		return Constants.URL_DASH_BOARD;
	}
	
	@RequestMapping(value = "/remove/{id:.+}", method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") long id,
			HttpServletRequest request, Model model) {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		DebtUserRole role = roleService.findRoleById(id);
		try {
			roleService.deleteRoleInfo(role);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			model.addAttribute(Constants.LIST_ERRORS, errors);
		}	
		model.addAttribute(Constants.MESSAGE, Constants.MESSAGE_REMOVE_USER_SUCCESS);
		return "redirect:/system/role/load";
	}
}
