package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DEBT_USER_ROLES_HAS_PRIVILEGES database table.
 * 
 */
@Entity
@Table(name="DEBT_USER_ROLES_HAS_PRIVILEGES")
public class DebtUserRolesHasPrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DebtUserRolesHasPrivilegePK id;

	public DebtUserRolesHasPrivilege() {
	}

	public DebtUserRolesHasPrivilegePK getId() {
		return this.id;
	}

	public void setId(DebtUserRolesHasPrivilegePK id) {
		this.id = id;
	}

}