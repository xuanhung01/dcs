package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the DEBT_UPLOAD_HDR database table.
 * 
 */
@Entity
@Table(name = "DEBT_UPLOAD_HDR")
public class DebtUploadHdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DCS_UPLOAD_HDR_SEQ")
	@SequenceGenerator(name = "DCS_UPLOAD_HDR_SEQ", sequenceName = "DCS_UPLOAD_HDR_SEQ", allocationSize = 1)
	private BigDecimal id;

	@Column(name = "CREATE_BY")
	private String createBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	private String description;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_ROW_FAIL")
	private BigDecimal fileRowFail;

	@Column(name = "FILE_ROW_SUCCESS")
	private BigDecimal fileRowSuccess;

	@Column(name = "FILE_ROW_TOTAL")
	private BigDecimal fileRowTotal;

	@Column(name = "FILE_SIZE")
	private BigDecimal fileSize;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	private String status;

	@Column(name = "STATUS_NAME")
	private String statusName;

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_RUN_DATE")
	private Date sysRunDate;

	public DebtUploadHdr() {
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getFileRowFail() {
		return this.fileRowFail;
	}

	public void setFileRowFail(BigDecimal fileRowFail) {
		this.fileRowFail = fileRowFail;
	}

	public BigDecimal getFileRowSuccess() {
		return this.fileRowSuccess;
	}

	public void setFileRowSuccess(BigDecimal fileRowSuccess) {
		this.fileRowSuccess = fileRowSuccess;
	}

	public BigDecimal getFileRowTotal() {
		return this.fileRowTotal;
	}

	public void setFileRowTotal(BigDecimal fileRowTotal) {
		this.fileRowTotal = fileRowTotal;
	}

	public BigDecimal getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}

	public Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getSysRunDate() {
		return this.sysRunDate;
	}

	public void setSysRunDate(Date sysRunDate) {
		this.sysRunDate = sysRunDate;
	}

}