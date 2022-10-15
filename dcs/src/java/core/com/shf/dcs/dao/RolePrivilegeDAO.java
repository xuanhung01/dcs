package com.shf.dcs.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.shf.dcs.model.DebtUserRolesHasPrivilege;
import com.shf.dcs.model.DebtUserRolesHasPrivilegePK;

public interface RolePrivilegeDAO extends CrudRepository<DebtUserRolesHasPrivilege, DebtUserRolesHasPrivilegePK> {

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from DEBT_USER_ROLES_HAS_PRIVILEGES where role_id =:role_id and "
			+ "privilege_id in (select id from DEBT_USER_PRIVILEGE where id_parent =:privilege_Id or id =:privilege_Id)")
	void deletePrivilegeByRoleId(@Param("role_id") long roleId, @Param("privilege_Id") long privilegeId);

}
