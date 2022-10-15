package com.shf.dcs.dto;

public class RoleDto {
	
	private Long id;
	private String name;
	private Long idHidden;
	private String tempRole;
	private String tempPrivilege;
	private String userName;
	private Boolean hasMobile;
	private Boolean hasDcs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(Long idHidden) {
		this.idHidden = idHidden;
	}

	public String getTempRole() {
		return tempRole;
	}

	public void setTempRole(String tempRole) {
		this.tempRole = tempRole;
	}

	public String getTempPrivilege() {
		return tempPrivilege;
	}

	public void setTempPrivilege(String tempPrivilege) {
		this.tempPrivilege = tempPrivilege;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getHasMobile() {
		return hasMobile;
	}

	public void setHasMobile(Boolean hasMobile) {
		this.hasMobile = hasMobile;
	}

	public Boolean getHasDcs() {
		return hasDcs;
	}

	public void setHasDcs(Boolean hasDcs) {
		this.hasDcs = hasDcs;
	}
}