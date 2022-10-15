package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;


/**
 * The persistent class for the DEBT_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="DEBT_USER_ROLE")
@Getter
@Setter
public class DebtUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBT_USER_ROLE_SEQ")
	@SequenceGenerator(name = "DEBT_USER_ROLE_SEQ", sequenceName = "DEBT_USER_ROLE_SEQ",allocationSize = 1)
	private Long id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="HAS_DCS")
	private BigDecimal hasDcs;

	@Column(name="HAS_MOBILE")
	private BigDecimal hasMobile;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	private String name;

	private String status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "DEBT_USER_ROLES_HAS_PRIVILEGES", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Collection<DebtUserPrivilege> privileges;
}