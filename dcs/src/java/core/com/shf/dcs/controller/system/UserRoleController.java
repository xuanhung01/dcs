package com.shf.dcs.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/system/userRole")
public class UserRoleController {

	private static Logger logger = Logger.getLogger(UserRoleController.class);
	
	@Autowired
	public IUserService userService;
	
	@Autowired
	public IRoleService roleService;
	
	public static String SUB_PAGE = "system_users_roles";
	
	public static String STR_SPILIT = "@@";
	
	
	// -----------------------------------------------------------------------
	
	@RequestMapping(value = "/load")
	public ModelAndView users(Model model, HttpServletRequest request) {
		userService.addUserInfo(model, request);
		ModelAndView modelAndView = new ModelAndView(Constants.URL_DASH_BOARD);
		/*userService.addUserInfo(model, request);
		List<Role> listRole = roleService.findAll();
		modelAndView.addObject(Constants.SUB_PAGE, SUB_PAGE);
		modelAndView.addObject(Constants.LIST_ROLE, listRole);
		modelAndView.addObject(Constants.FORM_MODEL, new RoleDto());*/
		modelAndView = search(null, model, request);
		return modelAndView;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(
			@ModelAttribute RoleDto roleDto, Model model,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(Constants.URL_DASH_BOARD);
		List<DebtUserRole> listRole = new ArrayList<DebtUserRole>();
		List<DebtUser> listUserLeft = new ArrayList<DebtUser>();
		List<DebtUser> listUserRight = new ArrayList<DebtUser>();
		try {
			listRole = roleService.findAll();
			
			// lấy danh sách user chưa được phân nhóm
			listUserLeft = userService.searchUserLeft();
			
			// set nhóm mặc định nếu không có
			if(roleDto == null || StringUtils.isEmpty(roleDto.getTempRole())) {
				roleDto = new RoleDto();
				roleDto.setTempRole(String.valueOf(listRole.get(0).getId()));
			}
			
			// Lấy danh sách user được phân nhóm theo id role
			listUserRight = userService.searchUserRight(Long.valueOf(roleDto.getTempRole()));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
		
		modelAndView.addObject(Constants.SUB_PAGE, SUB_PAGE);
		modelAndView.addObject(Constants.FORM_MODEL, roleDto);
		modelAndView.addObject(Constants.LIST_ROLE, listRole);
		modelAndView.addObject("listUserLeft", listUserLeft);
		modelAndView.addObject("listUserRight", listUserRight);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/save/{listUserName:.+}/{tempRole:.+}")
	public ModelAndView save(
			@PathVariable("listUserName") String listUserName,
			@PathVariable("tempRole") long tempRole,
			Model model,
			HttpServletRequest request) {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		ModelAndView modelAndView = new ModelAndView(Constants.URL_DASH_BOARD);
		List<String> listUser = new ArrayList<String>();
		RoleDto roleDto = new RoleDto();
		try {
			// phân nhóm cho danh sách user
			if(StringUtils.isNotEmpty(listUserName)) {
				// remove @@ ở đầu và cuối 
				if(StringUtils.startsWith(listUserName, STR_SPILIT))
					listUserName = StringUtils.removeStart(listUserName, STR_SPILIT);
				if(StringUtils.endsWith(listUserName, STR_SPILIT))
					listUserName = StringUtils.removeEnd(listUserName, STR_SPILIT);
				
				// lấy danh sách
				String[] arrUserName = StringUtils.split(listUserName, STR_SPILIT);
				for (String userName : arrUserName) {
					listUser.add(userName);
				}
			} 
			
			// phân nhóm
		
			roleService.saveRoleUser(tempRole, listUser);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			errors.add(new ObjectError("OtherException", e.getMessage()));
		}

		if (!errors.isEmpty()) {
			modelAndView.addObject(Constants.LIST_ERRORS, errors);
			return modelAndView;
		} 
		roleDto.setTempRole(String.valueOf(tempRole));
		modelAndView = search(roleDto, model, request);
		return modelAndView;
	}
}
