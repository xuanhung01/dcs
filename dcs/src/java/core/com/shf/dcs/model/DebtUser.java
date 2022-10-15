package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;


/**
 * The persistent class for the DEBT_USERS database table.
 * 
 */
@Entity
@Table(name="DEBT_USERS")
@Getter
@Setter
public class DebtUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String description;

	@Column(name="DEVICE_ID")
	private String deviceId;

	private Boolean enabled;

	@Temporal(TemporalType.DATE)
	@Column(name="END_ALLOCATED_DATE")
	private Date endAllocatedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="END_CHALLENGE_DATE")
	private Date endChallengeDate;

	private String ext;

	@Column(name="GROUP_ID")
	private BigDecimal groupId;

	@Column(name="HAS_CHALLENGE")
	private BigDecimal hasChallenge;

	@Column(name="HAS_PARTNER")
	private BigDecimal hasPartner;

	@Column(name="JSESSION_ID")
	private String jsessionId;

	@Column(name="LAST_LATITUDE")
	private String lastLatitude;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN_DATE")
	private Date lastLoginDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN_MO_DATE")
	private Date lastLoginMoDate;

	@Column(name="LAST_LONGITUDE")
	private String lastLongitude;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATED_JW_DATE")
	private Date lastUpdatedJwDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATED_WEB_JW_DATE")
	private Date lastUpdatedWebJwDate;

	@Column(name="MODEL_MOBILE")
	private String modelMobile;

	@Column(name="NUMBER_RETRY_LOGIN")
	private Integer numberRetryLogin;

	@Column(name="OS_MOBILE")
	private String osMobile;

	@Column(name="PARENT_USERNAME")
	private String parentUsername;

	@Column(name="PARTNER_NAME")
	private String partnerName;

	private String password;

	private String realname;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Temporal(TemporalType.DATE)
	@Column(name="START_ALLOCATED_DATE")
	private Date startAllocatedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="START_CHALLENGE_DATE")
	private Date startChallengeDate;

	@Column(name="TEAM_CODE")
	private String teamCode;

	@Column(name="VER_APP")
	private String verApp;

	@Column(name="WEB_JSESSION_ID")
	private String webJsessionId;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DEBT_USER_HAS_ROLE", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "username"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<DebtUserRole> roles;
}