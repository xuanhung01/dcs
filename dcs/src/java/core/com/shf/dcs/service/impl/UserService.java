package com.shf.dcs.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.shf.dcs.dao.UserRepository;
import com.shf.dcs.dto.UserAccountDto;
import com.shf.dcs.error.UserAlreadyExistException;
import com.shf.dcs.error.UserNotFoundException;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;
import vn.co.dssfw.utils.DateUtil;

@Service
@Transactional(rollbackOn = Exception.class)
//@Transactional(rollbackFor=Exception.class,transactionManager="transactionManager")
public class UserService implements IUserService {
	public static final Long USER_CAP_SO = 1L;
	public static final Long USER_CAP_BO = 2L;
	public static final Long USER_CAP_SADMIN = 3L;
	public static final Long USER_CAP_PUBLIC = 0L;
	public static final String USER_EMPTY_PHONE = null;

	public static final Long ACTION_REGISTER = 1L;
	public static final Long ACTION_CREATE = 2L;
	public static final Long ACTION_RESET_PASS = 2L;
	public static final Long ACTION_RESET_PASS_TOKEN = 3L;
	public static final Long ACTION_RESEND_TOKEN = 3L;

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRepository userDAO;

	@Override
	public DebtUser registerNewUserAccount(UserAccountDto accountDto) throws Exception {
		// Check user info
		final DebtUser user = new DebtUser();
		user.setUsername(StringUtils.upperCase(accountDto.getUsername()));
		user.setRealname(accountDto.getRealname());
		user.setGroupId(accountDto.getGroupId());
		user.setCreatedDate(new Date());
		user.setCreatedBy(getLoggedUserName());
		user.setExt(accountDto.getExt());
		user.setStaffCode(accountDto.getStaffCode());
		user.setEnabled(accountDto.getEnabled());
		user.setParentUsername(accountDto.getParentUsername());
		if (accountDto.getStartAllocatedDate() != null) {
			user.setStartAllocatedDate(DateUtil.parseDate(accountDto.getStartAllocatedDate(), DateUtil.yyyyMMdd3));
		}
		if (accountDto.getEndAllocatedDate() != null) {
			user.setEndAllocatedDate(DateUtil.parseDate(accountDto.getEndAllocatedDate(), DateUtil.yyyyMMdd3));
		}
		user.setTeamCode(accountDto.getTeamCode());
		DebtUser savedUser = userDAO.saveAndFlush(user);
		return savedUser;
	}

	@Override
	public DebtUser createNewUserAccount(final UserAccountDto accountDto) throws Exception {
		// Check user info
		if (isUsernameExist(accountDto.getUsername())) {
			throw new UserAlreadyExistException();
		}
		DebtUser user = new DebtUser();
		user.setUsername(StringUtils.upperCase(accountDto.getUsername()));
		user.setRealname(accountDto.getRealname());
		user.setParentUsername(accountDto.getParentUsername());
		user.setGroupId(accountDto.getGroupId());
		user.setCreatedDate(new Date());
		user.setCreatedBy(getLoggedUserName());
		user.setEnabled(true);
		user.setExt(accountDto.getExt());
		user.setStaffCode(accountDto.getStaffCode());
		if (accountDto.getStartAllocatedDate() != null) {
			user.setStartAllocatedDate(DateUtil.parseDate(accountDto.getStartAllocatedDate(), DateUtil.yyyyMMdd3));
		}
		if (accountDto.getEndAllocatedDate() != null) {
			user.setEndAllocatedDate(DateUtil.parseDate(accountDto.getEndAllocatedDate(), DateUtil.yyyyMMdd3));
		}
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
		user.setPartnerName(accountDto.getPartnerName());
		user.setDescription(accountDto.getPassword());
		 
		
		user.setTeamCode(accountDto.getTeamCode());
		DebtUser savedUser = userDAO.save(user);
		return savedUser;
	}

	@Override
	public void saveUser(final DebtUser user) {
		userDAO.save(user);
	}

	@Override
	public void deleteUser(final DebtUser user) {
		userDAO.delete(user);
	}

	@Override
	public void deleteUserInfo(DebtUser user) throws Exception {
		// xóa dữ liệu trong bảng user
		userDAO.delete(user);
	}

	@Override
	public DebtUser getUserByID(String userName) {
		return userDAO.findByUsername(StringUtils.upperCase(userName));
	}

	@Override
	public boolean isUsernameExist(String username) {
		final DebtUser user = userDAO.findByUsername(StringUtils.upperCase(username));
		if (user != null) {
			return true;
		}
		return false;
	}

	public boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	public DebtUser getLoggedUser() {
		String loggedUserName = getLoggedUserName();
		DebtUser user = userDAO.findByUsername(StringUtils.upperCase(loggedUserName));
		return user;
	}

	public String getLoggedUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		/*
		 * if (!(auth instanceof AnonymousAuthenticationToken)) {
		 * org.springframework.security.core.userdetails.User userDetails =
		 * (org.springframework.security.core.userdetails.User) auth .getPrincipal();
		 * return userDetails.getUsername(); }
		 */
		if (auth != null) {
			return StringUtils.upperCase(auth.getName());
		}
		return null;
	}

	@Override
	public void hasAccessItemMain(Boolean role1291, Boolean role1292, Boolean role1293) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<GrantedAuthority> granted = (Collection<GrantedAuthority>) auth.getAuthorities();
		for (GrantedAuthority authority : granted) {
			if ("ROLE_1291".equals(authority.getAuthority())) {
				role1291 = true;
			}
			if ("ROLE_1292".equals(authority.getAuthority())) {
				role1292 = true;
			}
			if ("ROLE_1293".equals(authority.getAuthority())) {
				role1293 = true;
			}
		}
	}

	@Override
	public org.springframework.security.core.userdetails.User getLoggedUserDetails() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) auth
					.getPrincipal();
		}
		return null;
	}

	@Override
	public void addUserInfo(ModelAndView model, HttpServletRequest request) {
		String loggedUserName = getLoggedUserName();
		model.addObject("userName", loggedUserName);
		DebtUser user = userDAO.findByUsername(StringUtils.upperCase(loggedUserName));
		if (user != null) {
			model.addObject("userDto", createDto(user));
		}
	}

	@Override
	public void addUserInfo(Model model, HttpServletRequest request) {
		String loggedUserName = getLoggedUserName();
		model.addAttribute("userName", loggedUserName);
		DebtUser user = userDAO.findByUsername(StringUtils.upperCase(loggedUserName));
		if (user != null) {
			model.addAttribute("userDto", createDto(user));
		}
	}

	@Override
	public DebtUser editUserAccount(UserAccountDto accountDto) {
		final DebtUser user = userDAO.findByUsername(StringUtils.upperCase(accountDto.getUsername()));
		if (user != null) {
			user.setRealname(accountDto.getRealname());
			user.setGroupId(accountDto.getGroupId());
			user.setParentUsername(accountDto.getParentUsername());
			user.setExt(accountDto.getExt());
			user.setStaffCode(accountDto.getStaffCode());
			if (accountDto.getStartAllocatedDate() != null) {
				user.setStartAllocatedDate(DateUtil.parseDate(accountDto.getStartAllocatedDate(), DateUtil.yyyyMMdd3));
			}
			if (accountDto.getEndAllocatedDate() != null) {
				user.setEndAllocatedDate(DateUtil.parseDate(accountDto.getEndAllocatedDate(), DateUtil.yyyyMMdd3));
			}
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			if (!accountDto.getPassword().equals(user.getPassword())) {
				user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
				user.setDescription(accountDto.getPassword());
			}
			user.setPartnerName(accountDto.getPartnerName());
			user.setTeamCode(accountDto.getTeamCode());
			return userDAO.save(user);
		} else {
			throw new UserNotFoundException("User " + accountDto.getUsername() + "Not found");
		}
	}

	@Override
	public UserAccountDto createDto(DebtUser user) {
		UserAccountDto dto = new UserAccountDto();
		dto.setUsername(user.getUsername());
		dto.setRealname(user.getRealname());
		dto.setGroupId(user.getGroupId());
		dto.setParentUsername(user.getParentUsername());
		dto.setPassword(user.getPassword());
		dto.setPasswordConfirm(user.getPassword());
		dto.setPartnerName(user.getPartnerName());
		dto.setExt(user.getExt());
		dto.setStaffCode(user.getStaffCode());
		//
		dto.setStartAllocatedDate(DateUtil.dateToString(user.getStartAllocatedDate(), DateUtil.yyyyMMdd3));
		dto.setEndAllocatedDate(DateUtil.dateToString(user.getEndAllocatedDate(), DateUtil.yyyyMMdd3));
		// dto.setHasChallenge(user.getHasChallenge());
		// dto.setStartChallengeDate(DateUtil.dateToString(user.getStartChallengeDate(),
		// DateUtil.yyyyMMdd3));
		// dto.setEndChallengeDate(DateUtil.dateToString(user.getEndChallengeDate(),
		// DateUtil.yyyyMMdd3));
		dto.setTeamCode(user.getTeamCode());
		return dto;
	}

	@Override
	public void updateDtoInfo(UserAccountDto dto, DebtUser user) {
		dto.setUsername(user.getUsername());
		dto.setRealname(user.getRealname());
	}

	@Override
	public Long getNumUsers() {
		return userDAO.count();
	}

	@Override
	public List<DebtUser> getAll() {
		return userDAO.findAll();
	}

	@Override
	public DebtUser findUserByUserName(String userName) {
		return userDAO.findByUsername(StringUtils.upperCase(userName));
	}

	@Override
	public List<DebtUser> findUserByUserNames(List<String> listUsername) {
		return userDAO.findByUserNames(listUsername);
	}

	/**
	 * Tìm user có group code not null
	 */
	@Override
	public List<DebtUser> findUserByUserNames1(List<String> listUsername) {
		return userDAO.findByUserNames1(listUsername);
	}

	@Override
	public List<DebtUser> findByEnabled(Boolean trangThai) {
		List<DebtUser> listUser = userDAO.findByEnabled(trangThai);
		return listUser;
	}

	@Override
	public List<DebtUser> findAll() {
		List<DebtUser> listUser = userDAO.findAll();
		return listUser;
	}

	@Override
	public UserAccountDto getUserDtoByID(String userName) {
		DebtUser user = findUserByUserName(StringUtils.upperCase(userName));
		if (user != null) {
			return createDto(user);
		}
		return null;
	}

	@Override
	public boolean isFormOwner(String formUserName) {
		if (getLoggedUserName() == null)
			return false;
		boolean isOwner = formUserName.equalsIgnoreCase(getLoggedUserName());
		return isOwner;
	}

	@Override
	public List<DebtUser> searchUserLeft() {
		return userDAO.searchUserLeft();
	}

	@Override
	public List<DebtUser> searchUserRight(long roleId) {
		return userDAO.searchUserRight(roleId);
	}

	@Override
	public List<DebtUser> searchUserGradeOne(String userName) {
		return userDAO.findByParentUsernameAndEnabled(StringUtils.upperCase(userName), true);
	}

	/*
	 * @Override public String getUserNameView(HttpServletRequest request) { String
	 * userNameTeamLeader = (String)
	 * request.getSession().getAttribute(Constants.USERVIEWDETAILCONTRACT);
	 * if(StringUtils.isEmpty(userNameTeamLeader)) { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); if(auth != null) {
	 * return StringUtils.upperCase(auth.getName()); } } return
	 * StringUtils.upperCase(userNameTeamLeader); }
	 */

	@Override
	public List<DebtUser> searchUserByGroupCode(String groupCode) {
		return userDAO.searchUserByGroupCode(groupCode);
	}

	@Override
	public List<BigDecimal> findGroupIdByListUser(List<String> listUsername) {
		return userDAO.findGroupIdByListUser(listUsername);
	}

	// 1 phút
	/*
	 * @CacheEvict(value =
	 * {"allUnderUserCaches","allLevelUserCaches"},allEntries=true) public void
	 * resetAllUnderUserCaches(){
	 * 
	 * }
	 */

	@Override
	public Integer getLevelByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.getLevelByUsername(username);
	}

	@Override
	public List<String> getUserUnderByUsername(String username) {
		// TODO Auto-generated method stub
		return userDAO.getUserUnderByUsername(username);
	}

	@Override
	public List<String> searchUserNameGradeOne(String userName) {
		// TODO Auto-generated method stub
		return userDAO.findByParentUserNameAndEnabled1(StringUtils.upperCase(userName));
	}

	@Override
	public DebtUser editGroupUserAccount(UserAccountDto accountDto) {

		final DebtUser user = userDAO.findByUsername(StringUtils.upperCase(accountDto.getUsername()));
		if (user != null) {
			user.setGroupId(accountDto.getGroupId());
			user.setLastUpdatedDate(new Date());
			user.setLastUpdatedBy(accountDto.getChucDanh());
			user.setExt(accountDto.getExt());
			user.setStaffCode(accountDto.getStaffCode());
			if (accountDto.getStartAllocatedDate() != null) {
				user.setStartAllocatedDate(DateUtil.parseDate(accountDto.getStartAllocatedDate(), DateUtil.yyyyMMdd3));
			}
			if (accountDto.getEndAllocatedDate() != null) {
				user.setEndAllocatedDate(DateUtil.parseDate(accountDto.getEndAllocatedDate(), DateUtil.yyyyMMdd3));
			}
			user.setTeamCode(accountDto.getTeamCode());
			return userDAO.save(user);
		} else {
			throw new UserNotFoundException("User " + accountDto.getUsername() + "Not found");
		}
	}

	@Override
	public Integer getLeafByUserName(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllUsernameByActive() {
		return userDAO.findAllUsernameByActive();
	}

	@Override
	public List<DebtUser> findAllByActive() {
		return userDAO.findAllByActive();
	}

	@Override
	public void updateJW(String username, String jwt) {
		DebtUser user = userDAO.findByUsername(StringUtils.upperCase(username));
		user.setWebJsessionId(jwt);
		user.setLastLoginDate(new Date());
		user.setLastUpdatedWebJwDate(new Date());
		userDAO.save(user);
	}
}