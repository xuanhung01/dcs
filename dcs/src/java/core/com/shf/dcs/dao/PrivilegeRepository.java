package com.shf.dcs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.shf.dcs.model.DebtUserPrivilege;

public interface PrivilegeRepository extends JpaRepository<DebtUserPrivilege, Long> {

	DebtUserPrivilege findByName(String name);

	@Override
	void delete(DebtUserPrivilege privilege);

	@Query("SELECT t from DebtUserPrivilege t where idParent is not null")
	public List<DebtUserPrivilege> findAllParentNotNull();
	
	@Query("SELECT t from DebtUserPrivilege t where idParent is null")
	public List<DebtUserPrivilege> findAllParent();

	
	@Query(nativeQuery=true,value="SELECT a.privilege_id from DEBT_USER_ROLES_HAS_PRIVILEGES a"
			+ " where a.role_id =:role_id order by privilege_id asc")
	public List<String> findPrivilegeIdbyRoleId(@Param("role_id")long roleId);
	
	@Query(nativeQuery=true,value="select id from DEBT_USER_PRIVILEGE where id in "
			+ " (select distinct id_parent from DEBT_USER_PRIVILEGE a,DEBT_USER_ROLES_HAS_PRIVILEGES b"
			+ " where a.id = b.privilege_id and id_parent is not null and b.role_id =:role_id)"
			+ " order by id_order ")
	public List<String> findPrivilegeParentIdbyRoleId(@Param("role_id")long roleId);
	
	@Query(nativeQuery=true,value="select * from DEBT_USER_PRIVILEGE where id not in"
			+ " (select privilege_id from DEBT_USER_ROLES_HAS_PRIVILEGES where role_id =:role_id) "
			+ " and id_parent =:privilege_Id order by id asc")
	List<DebtUserPrivilege> searchPrivilegeLeft(@Param("role_id") long roleId, @Param("privilege_Id") long privilegeId);
	
	@Query(nativeQuery=true,value="select * from DEBT_USER_PRIVILEGE where id in"
			+ " (select privilege_id from DEBT_USER_ROLES_HAS_PRIVILEGES where role_id =:role_id) "
			+ " and id_parent =:privilege_Id order by id asc")
	List<DebtUserPrivilege> searchPrivilegeRight(@Param("role_id") long roleId, @Param("privilege_Id") long privilegeId);
	
	@Query(nativeQuery=true,value="SELECT a.privilege_id FROM DEBT_USER_ROLES_HAS_PRIVILEGES a,DEBT_USER_HAS_ROLE b where "
			+ "   a.role_id = b.role_id and b.user_id =:username and a.privilege_id = 1291")
	Integer searchPrivilegeMainByUsername(@Param("username") String username);
}
