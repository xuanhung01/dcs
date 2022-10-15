package com.shf.dcs.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.shf.dcs.model.DebtUserHasRole;

public interface DebtUserHasRoleDAO extends CrudRepository<DebtUserHasRole, String>{
	
	@Transactional
	String deleteByRoleId(Long roleId);

	@Transactional
	void deleteByUserId(String userId) throws Exception;
}
