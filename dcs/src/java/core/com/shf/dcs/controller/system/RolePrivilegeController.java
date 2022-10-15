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
import com.shf.dcs.model.DebtUserPrivilege;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.service.IRoleService;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;

@Controller
@RequestMapping("/system/rolePrivilege")
public class RolePrivilegeController {
	
	private static Logger logger = Logger.getLogger(RolePrivilegeController.class);
	
	@Autowired
	public IUserService userService;
	
	@Autowired
	public IRoleService roleService;
	
	public static String SUB_PAGE = "system_users_privilege";
	
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
		List<DebtUserPrivilege> listPrivilegeRight = new ArrayList<DebtUserPrivilege>();
		List<DebtUserPrivilege> listPrivilege = new ArrayList<DebtUserPrivilege>();
		List<DebtUserPrivilege> listPrivilegeLeft = new ArrayList<DebtUserPrivilege>();
		
		try {
			listRole = roleService.findAll();
			listPrivilege = roleService.findAllParent();
			
			// set nhóm mặc định nếu không có
			if(roleDto == null || StringUtils.isEmpty(roleDto.getTempRole())) {
				roleDto = new RoleDto();
				roleDto.setTempRole(String.valueOf(listRole.get(0).getId()));
				roleDto.setTempPrivilege(String.valueOf(listPrivilege.get(0).getId()));
			}
			
			// lấy danh sách chức năng chưa được phân nhóm 
			listPrivilegeLeft = roleService.searchPrivilegeLeft(Long.valueOf(roleDto.getTempRole()), Long.valueOf(roleDto.getTempPrivilege()));
			
			// Lấy danh sách chức năng được phân nhóm theo id role
			listPrivilegeRight = roleService.searchPrivilegeRight(Long.valueOf(roleDto.getTempRole()), Long.valueOf(roleDto.getTempPrivilege()));
			if(listPrivilegeRight == null) {
				listPrivilegeRight = new ArrayList<DebtUserPrivilege>();
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
		
		modelAndView.addObject(Constants.SUB_PAGE, SUB_PAGE);
		modelAndView.addObject(Constants.FORM_MODEL, roleDto);
		modelAndView.addObject(Constants.LIST_ROLE, listRole);
		modelAndView.addObject(Constants.LIST_PRIVILEGE, listPrivilege);
		modelAndView.addObject("listPrivilegeLeft", listPrivilegeLeft);
		modelAndView.addObject("listPrivilegeRight", listPrivilegeRight);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/save/{listPrivilege:.+}/{tempRole:.+}/{tempPrivilege:..+}")
	public ModelAndView save(
			@PathVariable("listPrivilege") String listPrivilege,
			@PathVariable("tempRole") long tempRole,
			@PathVariable("tempPrivilege") long tempPrivilege,
			Model model,
			HttpServletRequest request) {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		ModelAndView modelAndView = new ModelAndView(Constants.URL_DASH_BOARD);
		List<String> listUser = new ArrayList<String>();
		RoleDto roleDto = new RoleDto();
		try {
			// phân nhóm cho danh sách user
			if(StringUtils.isNotEmpty(listPrivilege)) {
				// remove @@ ở đầu và cuối 
				if(StringUtils.startsWith(listPrivilege, STR_SPILIT))
					listPrivilege = StringUtils.removeStart(listPrivilege, STR_SPILIT);
				if(StringUtils.endsWith(listPrivilege, STR_SPILIT))
					listPrivilege = StringUtils.removeEnd(listPrivilege, STR_SPILIT);
				
				// lấy danh sách
				String[] arrUserName = StringUtils.split(listPrivilege, STR_SPILIT);
				for (String userName : arrUserName) {
					listUser.add(userName);
				}
			} 
			// phân nhóm
			roleService.saveRolePrivileger(tempRole,tempPrivilege, listUser);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			errors.add(new ObjectError("OtherException", e.getMessage()));
		}

		if (!errors.isEmpty()) {
			modelAndView.addObject(Constants.LIST_ERRORS, errors);
			return modelAndView;
		} 
		roleDto.setTempRole(String.valueOf(tempRole));
		roleDto.setTempPrivilege(String.valueOf(tempPrivilege));
		modelAndView = search(roleDto, model, request);
		return modelAndView;
	}
}
