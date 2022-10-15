package com.shf.dcs.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shf.dcs.model.DebtUser;

public interface UserRepository extends JpaRepository<DebtUser, String> {
	
	// User findByEmail(String email);

	DebtUser findByUsername(String username);
	
	@Query( "select o from DebtUser o where username in :usernames AND ENABLED = 1" )
	List<DebtUser> findByUserNames(@Param("usernames") List<String> listUsername);
	
	@Query( "select o from DebtUser o where username in :usernames AND ENABLED = 1 AND groupId is not null" )
	List<DebtUser> findByUserNames1(@Param("usernames") List<String> listUsername);
	
	@Query( "select distinct o.groupId from DebtUser o where username in :usernames AND ENABLED = 1 AND groupId is not null" )
	List<BigDecimal> findGroupIdByListUser(@Param("usernames") List<String> listUsername);
	
	@Query( "select o from DebtUser o where ENABLED = 1" )
	List<DebtUser> findAllByActive();
	
	@Query(nativeQuery=true,value="select username from DEBT_USERS where ENABLED = 1")
	List<String> findAllUsernameByActive();

	// List<User> findByMaTinh(String maTinh);

	List<DebtUser> findByEnabled(Boolean trangThai);

	// Long countByMaTinh(String ma);

	// List<User> findByMaCQTC(String maCQTC);
	
	@Query(nativeQuery=true,value="select * from DEBT_USERS where username not in"
			+ " (select user_id from DEBT_user_has_role) and ENABLED = 1")
	public List<DebtUser> searchUserLeft();
	
	@Query(nativeQuery=true,value="select * from DEBT_USERS where username in"
			+ " (select user_id from DEBT_user_has_role where role_id =:role_id) and ENABLED = 1")
	public List<DebtUser> searchUserRight(@Param("role_id")long roleId);
	
	List<DebtUser> findByParentUsernameAndEnabled(String userName,Boolean trangThai);
	
	@Query(nativeQuery=true,value="select * from DEBT_USERS where ENABLED = 1 AND GROUP_ID in "
			+ " (select id from debt_sys_group where group_code =:group_code) ")
	public List<DebtUser> searchUserByGroupCode(@Param("group_code")String roleId);
	
	@Query(nativeQuery=true,value="select (Select max(level) From debt_users Connect By Prior username = parent_username Start  With parent_username Is Null) - "
			+ " (select level From   debt_users where username =:username Connect By Prior username = parent_username Start  With parent_username Is Null)  from dual ")
	public Integer getLevelByUsername(@Param("username")String username);
	
	/*@Query(nativeQuery=true,value="select level From debt_users where username =:username Connect By Prior username = parent_username Start  With parent_username Is Null")
	public Integer getLevelByUsername(@Param("username")String username);*/
	
	@Query(nativeQuery=true,value="Select username from debt_users where enabled = 1 Connect By Nocycle Prior username = parent_username start with username =:username")
	public List<String> getUserUnderByUsername(@Param("username")String username);
	
	@Query(nativeQuery=true,value="Select username from debt_users where PARENT_USERNAME =:username and ENABLED = 1")
	List<String> findByParentUserNameAndEnabled1(@Param("username")String userName);
}
