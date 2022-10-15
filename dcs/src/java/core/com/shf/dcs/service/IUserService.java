package com.shf.dcs.service;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.shf.dcs.dto.UserAccountDto;
import com.shf.dcs.error.EmailExistsException;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUser;

public interface IUserService {

	DebtUser getLoggedUser();

	String getLoggedUserName();

	// String getUserNameView(HttpServletRequest request);

	DebtUser registerNewUserAccount(UserAccountDto accountDto) throws Exception;

	void saveUser(DebtUser user);

	void deleteUser(DebtUser user);

	DebtUser findUserByUserName(String userName);

	UserAccountDto getUserDtoByID(String userName);

	DebtUser getUserByID(String id);

	void addUserInfo(ModelAndView model, HttpServletRequest request);

	void addUserInfo(Model model, HttpServletRequest request);

	List<DebtUser> getAll();

	DebtUser createNewUserAccount(UserAccountDto dto) throws Exception;

	DebtUser editUserAccount(UserAccountDto dto);

	DebtUser editGroupUserAccount(UserAccountDto dto);

	UserAccountDto createDto(DebtUser user);

	boolean isUsernameExist(String username);

	void updateDtoInfo(UserAccountDto dto, DebtUser user);

	Long getNumUsers();

	List<DebtUser> findByEnabled(Boolean trangThai);

	List<DebtUser> findUserByUserNames(List<String> listUsername);

	List<DebtUser> findUserByUserNames1(List<String> listUsername);

	List<DebtUser> findAll();

	List<DebtUser> findAllByActive();

	org.springframework.security.core.userdetails.User getLoggedUserDetails();

	boolean isFormOwner(String formUserName);

	void deleteUserInfo(DebtUser user) throws Exception;

	List<DebtUser> searchUserLeft();

	List<DebtUser> searchUserRight(long roleId);

	List<DebtUser> searchUserGradeOne(String userName);

	List<String> searchUserNameGradeOne(String userName);

	List<DebtUser> searchUserByGroupCode(String groupCode);

	List<BigDecimal> findGroupIdByListUser(List<String> listUsername);

	// void resetAllUnderUserCaches();

	Integer getLevelByUsername(String username);

	List<String> getUserUnderByUsername(String username);

	Integer getLeafByUserName(String username) throws Exception;

	List<String> findAllUsernameByActive();

	public void updateJW(String username, String jwt);

	public void hasAccessItemMain(Boolean role1291, Boolean role1292, Boolean role1293);
}
