package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the DEBT_UPLOAD_HDR database table.
 * 
 */
@Getter
@Setter
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

	@Column(name = "ERROR_CODE")
	private BigDecimal errorCode;

	@Column(name = "ERROR_MSG")
	private String errorMsg;
	
	@Column(name = "FILE_TYPE")
	private String fileType;

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_RUN_DATE")
	private Date sysRunDate;
}