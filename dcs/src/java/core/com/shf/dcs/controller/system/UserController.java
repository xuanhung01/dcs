package com.shf.dcs.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.shf.dcs.dto.UserAccountDto;
import com.shf.dcs.error.EmailExistsException;
import com.shf.dcs.error.EmailSendException;
import com.shf.dcs.error.UserAlreadyExistException;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;
import vn.co.dssfw.utils.DateUtil;

@Controller
@RequestMapping("/system/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(RolePrivilegeController.class);
	// -----------------------------------------------------------------------
	@Autowired
	public IUserService userService;

	@RequestMapping(value = "/load")
	public String users(Model model, HttpServletRequest request) throws Exception {
		DebtUser currentUser = userService.getLoggedUser();
		if (currentUser != null) {
			List<DebtUser> users = userService.findAll();
			model.addAttribute("subpage", "system_users");
			model.addAttribute("userList", users);
		}
		return Constants.URL_DASH_BOARD;
	}

	@RequestMapping(value = "/add")
	public ModelAndView showAddUser(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView(Constants.URL_DASH_BOARD);
		model.addObject("subpage", "system_users");
		model.addObject("act", "create");
		UserAccountDto userAccountDto = new UserAccountDto();
		List<DebtUser> users = new ArrayList<DebtUser>();
		try {
			users = userService.findAll();
			userAccountDto.setStartAllocatedDate(DateUtil.getCurrentDateWithFormat(DateUtil.yyyyMMdd3));
			userAccountDto.setStartChallengeDate(DateUtil.getCurrentDateWithFormat(DateUtil.yyyyMMdd3));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
		model.addObject("userList", users);
		model.addObject("formMode", "create");
		model.addObject("formModel", userAccountDto);
		return model;
	}

	@RequestMapping(value = "/edit/{id:.+}")
	public ModelAndView showEditUser(@PathVariable("id") String userName,
			HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView(Constants.URL_DASH_BOARD);
		model.addObject("subpage", "system_users");
		model.addObject("act", "edit");
		if (userService.getLoggedUserName().equals(userName)) {
			model.addObject("selfEdit", true);
		} else {
			model.addObject("selfEdit", false);
		}
		List<DebtUser> users = userService.findAll();
		DebtUser user = userService.findUserByUserName(userName);
		UserAccountDto userDto = userService.createDto(user);
		model.addObject("userList", users);
		model.addObject("formMode", "edit");
		model.addObject("formModel", userDto);
		return model;
	}

	@RequestMapping(value = "/view/{id:.+}")
	public ModelAndView showViewUser(@PathVariable("id") String userName,
			HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView(Constants.URL_DASH_BOARD);
		model.addObject("subpage", "system_users");
		model.addObject("act", "view");
		DebtUser user = userService.findUserByUserName(userName);
		UserAccountDto userDto = userService.createDto(user);
		model.addObject("formModel", userDto);
		return model;
	}

	@RequestMapping(value = "/remove/{id:.+}", method = RequestMethod.GET)
	public String removeUser(@PathVariable("id") String userName,
			HttpServletRequest request, Model model) throws Exception {
		DebtUser user = userService.findUserByUserName(userName);
		userService.deleteUserInfo(user);
		
		model.addAttribute("message", "RemoveSuccess");
		return "redirect:/system/user/load";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8","application/json;charset=UTF-8"})
	public String editUser(@ModelAttribute @Valid UserAccountDto dto,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		DebtUser edittedUser = null;

		if (errors.isEmpty()) {
			try {
				Date limitDate = DateUtil.getDate(1, 1, 2099);
		        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

		        if(special.matcher(dto.getRealName()).find()) {
					errors.add(new ObjectError("OtherException","Tên tài khoản hoặc Họ và tên có ký tự đặc biệt [!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]"));
				} 
		        if(StringUtils.isNotEmpty(dto.getStartAllocatedDate())) {
					if(!DateUtil.isDateFormat(dto.getStartAllocatedDate(), DateUtil.yyyyMMdd3)) {
						errors.add(new ObjectError("OtherException","Ngày hiệu lực p/b không đúng định dạng ngày tháng"));
					}
					Date startAllocatedDate = DateUtil.parseDate(dto.getStartAllocatedDate(), DateUtil.yyyyMMdd3);
					if(DateUtil.compare(startAllocatedDate ,limitDate) ==1) {
						errors.add(new ObjectError("OtherException","Ngày hiệu lực p/b không được lớn 01/01/2099"));
					}
				} 
		        if(StringUtils.isNotEmpty(dto.getEndAllocatedDate())) {
					if(!DateUtil.isDateFormat(dto.getEndAllocatedDate(), DateUtil.yyyyMMdd3)) {
						errors.add(new ObjectError("OtherException","Ngày kết thúc p/b không đúng định dạng ngày tháng"));
					}
					Date startAllocatedDate = DateUtil.parseDate(dto.getStartAllocatedDate(), DateUtil.yyyyMMdd3);
					Date endAllocatedDate = DateUtil.parseDate(dto.getEndAllocatedDate(), DateUtil.yyyyMMdd3);
					if(DateUtil.compare(startAllocatedDate, endAllocatedDate) == 1) {
						errors.add(new ObjectError("OtherException","Ngày kết thúc p/b không được lớn hơn Ngày hiệu lực p/b"));
					} else if(DateUtil.compare(endAllocatedDate,limitDate) ==1) {
						errors.add(new ObjectError("OtherException","Ngày kết thúc p/b không được lớn 01/01/2099"));
					}
				} 
		        if(dto.getGroupId() != null && StringUtils.isEmpty(dto.getTeamCode())) { 
						errors.add(new ObjectError("OtherException","Nếu Group Code có giá trị. Bạn phải chọn giá trị Team Code"));
		        }
		        if (errors.isEmpty()) {
					edittedUser = userService.editUserAccount(dto);
				}
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
				errors.add(new ObjectError("DataError", e.getMessage()));
			}
		}
		if (!errors.isEmpty()) {
			model.addAttribute("listErrors", errors);
		} else {
			if (edittedUser != null) {
				// load cache
				// userService.resetAllUnderUserCaches();
				// cacheService.refreshAllCache();
				model.addAttribute("message", "EditUserSuccess");
				return "redirect:/system/user/load";
			}
		}
		List<DebtUser> users = userService.findAll();
		model.addAttribute("formMode", "edit");
		model.addAttribute("subpage", "system_users");
		model.addAttribute("act", "edit");
		model.addAttribute("formModel", dto);
		model.addAttribute("userList", users);
		return Constants.URL_DASH_BOARD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute @Valid UserAccountDto dto,
			BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		List<ObjectError> errors = new ArrayList<ObjectError>();
		List<DebtUser> users = null;
		try {
			Date limitDate = DateUtil.getDate(1, 1, 2099);
	        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

	        if(special.matcher(dto.getUserName()).find() || special.matcher(dto.getRealName()).find()) {
				errors.add(new ObjectError("OtherException","Tên tài khoản hoặc Họ và tên có ký tự đặc biệt [!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]"));
			} 
	        if(StringUtils.isNotEmpty(dto.getStartAllocatedDate())) {
				if(!DateUtil.isDateFormat(dto.getStartAllocatedDate(), DateUtil.yyyyMMdd3)) {
					errors.add(new ObjectError("OtherException","Ngày hiệu lực p/b không đúng định dạng ngày tháng"));
				}
				Date startAllocatedDate = DateUtil.parseDate(dto.getStartAllocatedDate(), DateUtil.yyyyMMdd3);
				if(DateUtil.compare(startAllocatedDate ,limitDate) ==1) {
					errors.add(new ObjectError("OtherException","Ngày hiệu lực p/b không được lớn 01/01/2099"));
				}
			} 
	        if(StringUtils.isNotEmpty(dto.getEndAllocatedDate())) {
				if(!DateUtil.isDateFormat(dto.getEndAllocatedDate(), DateUtil.yyyyMMdd3)) {
					errors.add(new ObjectError("OtherException","Ngày kết thúc p/b không đúng định dạng ngày tháng"));
				}
				Date startAllocatedDate = DateUtil.parseDate(dto.getStartAllocatedDate(), DateUtil.yyyyMMdd3);
				Date endAllocatedDate = DateUtil.parseDate(dto.getEndAllocatedDate(), DateUtil.yyyyMMdd3);
				if(DateUtil.compare(startAllocatedDate, endAllocatedDate) == 1) {
					errors.add(new ObjectError("OtherException","Ngày kết thúc p/b không được lớn hơn Ngày hiệu lực p/b"));
				} else if(DateUtil.compare(endAllocatedDate,limitDate) ==1) {
					errors.add(new ObjectError("OtherException","Ngày kết thúc p/b không được lớn 01/01/2099"));
				}
			}
	        if(dto.getGroupId() != null && StringUtils.isEmpty(dto.getTeamCode())) { 
					errors.add(new ObjectError("OtherException","Nếu Group Code có giá trị. Bạn phải chọn giá trị Team Code"));
	        }
			if (errors.isEmpty()) {
				userService.createNewUserAccount(dto);
			}
		} catch (UserAlreadyExistException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			errors.add(new ObjectError("UserAlreadyExistException","Tên tài khoản " + dto.getUserName() + " đã tồn tại"));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			errors.add(new ObjectError("OtherException", e.getMessage()));
		}
		users = userService.findAll();
		if (!errors.isEmpty()) {
			model.addAttribute("listErrors", errors);
		} else {
			model.addAttribute("message", "AddUserSuccess");
			dto = new UserAccountDto();
			// cacheService.refreshAllCache();
		}
		model.addAttribute("userList", users);
		model.addAttribute("formMode", "create");
		model.addAttribute("subpage", "system_users");
		model.addAttribute("act", "create");
		model.addAttribute("formModel", dto);
		return Constants.URL_DASH_BOARD;
	}
	
	@RequestMapping(value = "/active/{id:.+}", method = RequestMethod.GET)
	public String activeUser(@PathVariable("id") String userName,
			HttpServletRequest request, Model model) throws Exception {
		DebtUser user = userService.findUserByUserName(userName);
		List<ObjectError> errors = new ArrayList<ObjectError>();
		if(user.getEnabled()) {
			user.setEnabled(false);
			List<String> listUserName = new ArrayList<String>();
			listUserName.add(user.getUsername());
			userService.saveUser(user);
		} else {
			user.setEnabled(true);
			userService.saveUser(user);
		}		
		List<DebtUser> users = userService.findAll();
		model.addAttribute("subpage", "system_users");
		model.addAttribute("userList", users);
		return Constants.URL_DASH_BOARD;
	}
	
	@RequestMapping(value = "/check/user")
	@ResponseBody
	public boolean checkUsernameAjax(
			@RequestParam(value = "username") String username) {
		try {
			return !userService.isUsernameExist(username);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
	}
}
