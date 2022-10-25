package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the DEBT_UPLOAD_SO_THU database table.
 * 
 */
@Entity
@Table(name = "DEBT_UPLOAD_SO_THU")
public class DebtUploadSoThu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBT_UPLOAD_SO_THU_SEQ")
	@SequenceGenerator(name = "DEBT_UPLOAD_SO_THU_SEQ", sequenceName = "DEBT_UPLOAD_SO_THU_SEQ", allocationSize = 1)
	private BigDecimal id;

	private String cif;

	@Column(name = "ERROR_MSG")
	private String errorMsg;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "ERROR_CODE")
	private BigDecimal errorCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_BAN_GIAO")
	private Date ngayBanGiao;

	@Column(name = "NGAY_GHI_NHAN")
	private BigDecimal ngayGhiNhan;

	@Column(name = "NHOM_DPD")
	private String nhomDpd;

	@Column(name = "SO_HOP_DONG")
	private String soHopDong;

	@Column(name = "SO_NGAY_QH_BAN_GIAO")
	private BigDecimal soNgayQhBanGiao;

	@Column(name = "SO_THU_NH_GHI_NHAN")
	private BigDecimal soThuNhGhiNhan;

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_RUN_DATE")
	private Date sysRunDate;

	@Column(name = "TEN_KHACH_HANG")
	private String tenKhachHang;

	@Column(name = "UPLOAD_HDR_ID")
	private BigDecimal uploadHdrId;

	@Column(name = "USERNAME_UPLOAD")
	private String usernameUpload;

	public DebtUploadSoThu() {
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Date getNgayBanGiao() {
		return this.ngayBanGiao;
	}

	public void setNgayBanGiao(Date ngayBanGiao) {
		this.ngayBanGiao = ngayBanGiao;
	}

	public BigDecimal getNgayGhiNhan() {
		return ngayGhiNhan;
	}

	public void setNgayGhiNhan(BigDecimal ngayGhiNhan) {
		this.ngayGhiNhan = ngayGhiNhan;
	}

	public String getNhomDpd() {
		return this.nhomDpd;
	}

	public void setNhomDpd(String nhomDpd) {
		this.nhomDpd = nhomDpd;
	}

	public String getSoHopDong() {
		return this.soHopDong;
	}

	public void setSoHopDong(String soHopDong) {
		this.soHopDong = soHopDong;
	}

	public BigDecimal getSoNgayQhBanGiao() {
		return this.soNgayQhBanGiao;
	}

	public void setSoNgayQhBanGiao(BigDecimal soNgayQhBanGiao) {
		this.soNgayQhBanGiao = soNgayQhBanGiao;
	}

	public BigDecimal getSoThuNhGhiNhan() {
		return this.soThuNhGhiNhan;
	}

	public void setSoThuNhGhiNhan(BigDecimal soThuNhGhiNhan) {
		this.soThuNhGhiNhan = soThuNhGhiNhan;
	}

	public Date getSysRunDate() {
		return this.sysRunDate;
	}

	public void setSysRunDate(Date sysRunDate) {
		this.sysRunDate = sysRunDate;
	}

	public String getTenKhachHang() {
		return this.tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public BigDecimal getUploadHdrId() {
		return this.uploadHdrId;
	}

	public void setUploadHdrId(BigDecimal uploadHdrId) {
		this.uploadHdrId = uploadHdrId;
	}

	public String getUsernameUpload() {
		return this.usernameUpload;
	}

	public void setUsernameUpload(String usernameUpload) {
		this.usernameUpload = usernameUpload;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public BigDecimal getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(BigDecimal errorCode) {
		this.errorCode = errorCode;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
}