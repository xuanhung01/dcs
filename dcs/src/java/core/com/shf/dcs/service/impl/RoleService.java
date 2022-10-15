package com.shf.dcs.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shf.dcs.dao.PrivilegeRepository;
import com.shf.dcs.dao.RoleDAO;
import com.shf.dcs.dao.RolePrivilegeDAO;
import com.shf.dcs.dao.DebtUserHasRoleDAO;
import com.shf.dcs.dto.RoleDto;
import com.shf.dcs.model.DebtUserHasRole;
import com.shf.dcs.model.DebtUserPrivilege;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUserRolesHasPrivilege;
import com.shf.dcs.model.DebtUserRolesHasPrivilegePK;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.service.IRoleService;

@Service
@Transactional(rollbackOn = Exception.class)
//@Transactional(rollbackFor=Exception.class,transactionManager="transactionManager")
public class RoleService implements IRoleService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	DebtUserHasRoleDAO userRoleDAO;
	
	@Autowired
	PrivilegeRepository privilegeDAO;
	
	@Autowired
	RolePrivilegeDAO rolePrivilegeDAO;

	@Override
	public DebtUserRole createNewRole(RoleDto dto) throws Exception {
		// Check user info
		DebtUserRole role = new DebtUserRole(); 
		role.setId(dto.getId());
		role.setName(dto.getName());
		role.setCreatedBy(dto.getUserName());
		role.setCreatedDate(new Date());
		DebtUserRole savedRole = roleDao.save(role);
		return savedRole;
	}
	
	public boolean isIdExist(long id) {
		DebtUserRole role = roleDao.findById(id);
		if (role != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<DebtUserRole> findAll() {
		return roleDao.findAll();
	}

	@Override
	public DebtUserRole findRoleById(long id) {
		return roleDao.findById(id);
	}

	@Override
	public DebtUserRole editRole(RoleDto roleDto) throws Exception {
		DebtUserRole role = roleDao.findById(roleDto.getIdHidden()).get();
		if (role != null) {
			role.setName(roleDto.getName());
			roleDao.save(role);
		}
		return role;
	}

	@Override
	public void deleteRoleInfo(DebtUserRole role) throws Exception{
		roleDao.delete(role);
	}
	
	@Override
	public void saveRoleUser(Long roleId,List<String> listUser) throws Exception{
		DebtUserHasRole userRole = null;
		
		// xóa toàn bộ user có roleId
		userRoleDAO.deleteByRoleId(roleId);
		
		// phân lại toàn bộ danh sach user có roleId tương ứng
		for (String userName : listUser) {
			userRole = new DebtUserHasRole();
			userRole.setUserId(userName);
			userRole.setRoleId(roleId);
			userRole.setStatus(new BigDecimal(0));
			userRoleDAO.save(userRole);
		}
	}

	@Override
	public List<DebtUserPrivilege> findAllParent() {
		return privilegeDAO.findAllParent();
	}

	@Override
	public List<DebtUserPrivilege> searchPrivilegeLeft(long roleId,long privilegeId) {
		return privilegeDAO.searchPrivilegeLeft(roleId, privilegeId);
	}

	@Override
	public List<DebtUserPrivilege> searchPrivilegeRight(long roleId,long privilegeId) {
		return privilegeDAO.searchPrivilegeRight(roleId, privilegeId);
	}
	
	@Override
	public void saveRolePrivileger(long roleId,long privilegeId,List<String> listPrivilege) throws Exception{
		DebtUserRolesHasPrivilegePK rolePrivilegePk = null;
		DebtUserRolesHasPrivilege rolePrivilege = null;
		// xóa toàn bộ chức năng có roleId
		rolePrivilegeDAO.deletePrivilegeByRoleId(roleId,privilegeId);
		
		// phân lại toàn bộ danh sach chức năng có roleId tương ứng
		if(listPrivilege != null && listPrivilege.size() > 0) {
			listPrivilege.add(String.valueOf(privilegeId));
			for (String privilege : listPrivilege) {
				rolePrivilegePk = new DebtUserRolesHasPrivilegePK();
				rolePrivilege = new DebtUserRolesHasPrivilege();
				
				rolePrivilegePk.setPrivilegeId(Long.parseLong(privilege));
				rolePrivilegePk.setRoleId(roleId);
				rolePrivilege.setId(rolePrivilegePk);
				rolePrivilegeDAO.save(rolePrivilege);
			}
		}
	}
	
	@Override
	public void createNewRoleUser(long roleId, String userName) throws Exception {
		DebtUserHasRole userRole = new DebtUserHasRole();
		userRole.setUserId(userName);
		userRole.setRoleId(roleId);
		userRole.setStatus(new BigDecimal(0));
		userRoleDAO.save(userRole);
	}

	@Override
	public List<DebtUserPrivilege> findAllParentNotNull() {
		return privilegeDAO.findAllParentNotNull();
	}
}
