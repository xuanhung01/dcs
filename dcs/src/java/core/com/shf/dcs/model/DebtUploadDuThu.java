package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the DEBT_UPLOAD_DU_THU database table.
 * 
 */
@Entity
@Table(name = "DEBT_UPLOAD_DU_THU")
public class DebtUploadDuThu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBT_UPLOAD_DU_THU_SEQ")
	@SequenceGenerator(name = "DEBT_UPLOAD_DU_THU_SEQ", sequenceName = "DEBT_UPLOAD_DU_THU_SEQ", allocationSize = 1)
	private BigDecimal id;

	@NotNull
	@Size(max = 50)
	private String cif;

	private BigDecimal dpd;

	@Size(max = 70)
	@Column(name = "DS_GIU_CASE")
	private String dsGiuCase;

	@Column(name = "DU_THU")
	private BigDecimal duThu;

	@Column(name = "ERROR_MSG")
	private String errorMsg;

	@Size(max = 400)
	@Column(name = "GIAI_PHAP_BACKUP")
	private String giaiPhapBackup;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "ERROR_CODE")
	private BigDecimal errorCode;

	@Size(max = 50)
	@Column(name = "LS_TAC_DONG_GAN_NHAT")
	private String lsTacDongGanNhat;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_DU_THU")
	private Date ngayDuThu;

	@Size(max = 400)
	@Column(name = "NGHE_NGHIEP")
	private String ngheNghiep;

	@Size(max = 70)
	private String nhanvien;

	@Size(max = 70)
	@Column(name = "PHONG_BAN")
	private String phongBan;

	@NotNull
	@Size(max = 50)
	@Column(name = "SO_HOP_DONG")
	private String soHopDong;

	@Column(name = "SO_TIEN_THU")
	private BigDecimal soTienThu;

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_RUN_DATE")
	private Date sysRunDate;

	@NotNull
	@Size(max = 150)
	@Column(name = "TEN_KHACH_HANG")
	private String tenKhachHang;

	@Column(name = "TINH_THUONG_TRU")
	private String tinhThuongTru;

	@Column(name = "TONG_DN_KHOAN_VAY")
	private BigDecimal tongDnKhoanVay;

	@Size(max = 30)
	private String tuan;

	@Size(max = 30)
	@Column(name = "TY_LE_DU_THU")
	private String tyLeDuThu;

	@Column(name = "UPLOAD_HDR_ID")
	private BigDecimal uploadHdrId;

	@Column(name = "USERNAME_UPLOAD")
	private String usernameUpload;

	public DebtUploadDuThu() {
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

	public BigDecimal getDpd() {
		return this.dpd;
	}

	public void setDpd(BigDecimal dpd) {
		this.dpd = dpd;
	}

	public String getDsGiuCase() {
		return this.dsGiuCase;
	}

	public void setDsGiuCase(String dsGiuCase) {
		this.dsGiuCase = dsGiuCase;
	}

	public BigDecimal getDuThu() {
		return this.duThu;
	}

	public void setDuThu(BigDecimal duThu) {
		this.duThu = duThu;
	}

	public String getGiaiPhapBackup() {
		return this.giaiPhapBackup;
	}

	public void setGiaiPhapBackup(String giaiPhapBackup) {
		this.giaiPhapBackup = giaiPhapBackup;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLsTacDongGanNhat() {
		return this.lsTacDongGanNhat;
	}

	public void setLsTacDongGanNhat(String lsTacDongGanNhat) {
		this.lsTacDongGanNhat = lsTacDongGanNhat;
	}

	public Date getNgayDuThu() {
		return this.ngayDuThu;
	}

	public void setNgayDuThu(Date ngayDuThu) {
		this.ngayDuThu = ngayDuThu;
	}

	public String getNgheNghiep() {
		return this.ngheNghiep;
	}

	public void setNgheNghiep(String ngheNghiep) {
		this.ngheNghiep = ngheNghiep;
	}

	public String getNhanvien() {
		return this.nhanvien;
	}

	public void setNhanvien(String nhanvien) {
		this.nhanvien = nhanvien;
	}

	public String getPhongBan() {
		return this.phongBan;
	}

	public void setPhongBan(String phongBan) {
		this.phongBan = phongBan;
	}

	public String getSoHopDong() {
		return this.soHopDong;
	}

	public void setSoHopDong(String soHopDong) {
		this.soHopDong = soHopDong;
	}

	public BigDecimal getSoTienThu() {
		return this.soTienThu;
	}

	public void setSoTienThu(BigDecimal soTienThu) {
		this.soTienThu = soTienThu;
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

	public String getTinhThuongTru() {
		return this.tinhThuongTru;
	}

	public void setTinhThuongTru(String tinhThuongTru) {
		this.tinhThuongTru = tinhThuongTru;
	}

	public BigDecimal getTongDnKhoanVay() {
		return this.tongDnKhoanVay;
	}

	public void setTongDnKhoanVay(BigDecimal tongDnKhoanVay) {
		this.tongDnKhoanVay = tongDnKhoanVay;
	}

	public String getTuan() {
		return this.tuan;
	}

	public void setTuan(String tuan) {
		this.tuan = tuan;
	}

	public String getTyLeDuThu() {
		return this.tyLeDuThu;
	}

	public void setTyLeDuThu(String tyLeDuThu) {
		this.tyLeDuThu = tyLeDuThu;
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
}