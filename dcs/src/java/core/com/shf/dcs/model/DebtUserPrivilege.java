package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DEBT_USER_PRIVILEGE database table.
 * 
 */
@Entity
@Table(name="DEBT_USER_PRIVILEGE")
public class DebtUserPrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="ID_ORDER")
	private String idOrder;

	@Column(name="ID_PARENT")
	private BigDecimal idParent;

	private String name;

	@Column(name="REQ_URL")
	private String reqUrl;

	private BigDecimal status;

	public DebtUserPrivilege() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public BigDecimal getIdParent() {
		return this.idParent;
	}

	public void setIdParent(BigDecimal idParent) {
		this.idParent = idParent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReqUrl() {
		return this.reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}