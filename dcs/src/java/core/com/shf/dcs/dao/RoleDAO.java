package com.shf.dcs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.shf.dcs.model.DebtUserRole;

public interface RoleDAO extends JpaRepository<DebtUserRole, Long> {

	DebtUserRole findByName(String name);

	@Override
	void delete(DebtUserRole role);

	@Override
	List<DebtUserRole> findAll();

	DebtUserRole findById(long id);

	@Query(nativeQuery = true, value = "select a.* from DEBT_USER_ROLE a, debt_users b, DEBT_USER_HAS_ROLE c "
			+ " where b.username =:username and b.username = c.user_id and c.role_id = a.id ")
	public DebtUserRole findRoleByUserName(@Param("username") String username);
}
