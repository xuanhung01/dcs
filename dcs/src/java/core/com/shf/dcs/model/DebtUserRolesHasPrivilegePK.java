package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DEBT_USER_ROLES_HAS_PRIVILEGES database table.
 * 
 */
@Embeddable
public class DebtUserRolesHasPrivilegePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_ID")
	private Long roleId;

	@Column(name="PRIVILEGE_ID")
	private Long privilegeId;

	public DebtUserRolesHasPrivilegePK() {
	}
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPrivilegeId() {
		return this.privilegeId;
	}
	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DebtUserRolesHasPrivilegePK)) {
			return false;
		}
		DebtUserRolesHasPrivilegePK castOther = (DebtUserRolesHasPrivilegePK)other;
		return 
			(this.roleId == castOther.roleId)
			&& this.privilegeId.equals(castOther.privilegeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.roleId ^ (this.roleId >>> 32)));
		hash = hash * prime + this.privilegeId.hashCode();
		
		return hash;
	}
}