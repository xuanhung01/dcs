package com.shf.dcs.service;

import java.util.List;

import com.shf.dcs.dto.RoleDto;
import com.shf.dcs.model.DebtUserPrivilege;
import com.shf.dcs.model.DebtUserRole;
import com.shf.dcs.model.DebtUser;

public interface IRoleService {
	
	DebtUserRole createNewRole(RoleDto dto) throws Exception;
	
	List<DebtUserRole> findAll();
	
	DebtUserRole findRoleById(long id);
	
	DebtUserRole editRole(RoleDto role) throws Exception;
	
	void deleteRoleInfo(DebtUserRole role) throws Exception;
	
	void saveRoleUser(Long roleId,List<String> listUser) throws Exception;
	
	void createNewRoleUser(long roleId,String userName) throws Exception;
	
	List<DebtUserPrivilege> findAllParent();
	
	List<DebtUserPrivilege> searchPrivilegeLeft(long roleId,long privilegeId);
	
	List<DebtUserPrivilege> searchPrivilegeRight(long roleId,long privilegeId);
	
	void saveRolePrivileger(long roleId,long privilegeId,List<String> listPrivilege) throws Exception;
	
	List<DebtUserPrivilege> findAllParentNotNull();
}
