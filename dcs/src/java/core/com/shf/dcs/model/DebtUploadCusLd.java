package com.shf.dcs.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the DEBT_UPLOAD_CUS_LD database table.
 * 
 */
@Entity
@Table(name = "DEBT_UPLOAD_CUS_LD")
public class DebtUploadCusLd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBT_UPLOAD_CUS_LD_SEQ")
	@SequenceGenerator(name = "DEBT_UPLOAD_CUS_LD_SEQ", sequenceName = "DEBT_UPLOAD_CUS_LD_SEQ", allocationSize = 1)
	private BigDecimal id;

	@Column(name = "SO_HOP_DONG")
	@NotNull
	@Size(max = 20)
	private String soHopDong;

	@Size(max = 400)
	@Column(name = "CHI_TIET_LS_TAC_DONG")
	private String chiTietLsTacDong;

	@Size(max = 70)
	@Column(name = "CHUC_VU")
	private String chucVu;

	@NotNull
	@Size(max = 50)
	private String cif;

	@NotNull
	@Size(max = 40)
	@Column(name = "CMND_CCCD")
	private String cmndCccd;

	@Size(max = 400)
	@Column(name = "DIA_CHI_CONG_TY")
	private String diaChiCongTy;

	@Size(max = 400)
	@Column(name = "DIA_CHI_TAM_TRU")
	private String diaChiTamTru;

	@Size(max = 400)
	@Column(name = "DIA_CHI_THUONG_TRU")
	private String diaChiThuongTru;

	@Size(max = 20)
	@Column(name = "DIEN_THOAI_CONG_TY")
	private String dienThoaiCongTy;

	@Size(max = 70)
	@Column(name = "DOI_TAC")
	private String doiTac;

	@Column(name = "DPD_HIEN_TAI")
	private BigDecimal dpdHienTai;

	@Column(name = "DPD_NGAY_BAN_GIAO")
	private BigDecimal dpdNgayBanGiao;

	@Column(name = "DU_NO_GOC_BAN_DAU")
	private BigDecimal duNoGocBanDau;

	@Column(name = "DU_NO_GOC_CON_LAI")
	private BigDecimal duNoGocConLai;

	private BigDecimal emi;

	@Column(name = "ERROR_MSG")
	private String errorMsg;

	@Size(max = 10)
	@Column(name = "GIOI_TINH")
	private String gioiTinh;

	@Column(name = "GOC_DA_TRA")
	private BigDecimal gocDaTra;

	@Column(name = "GOC_QUA_HAN")
	private BigDecimal gocQuaHan;

	@Size(max = 150)
	@Column(name = "HINH_THUC_NHAN_LUONG")
	private String hinhThucNhanLuong;

	@Size(max = 150)
	@Column(name = "HO_VA_TEN_1")
	private String hoVaTen1;

	@Size(max = 150)
	@Column(name = "HO_VA_TEN_2")
	private String hoVaTen2;

	@Size(max = 150)
	@Column(name = "HO_VA_TEN_3")
	private String hoVaTen3;

	@Size(max = 150)
	@Column(name = "HO_VA_TEN_4")
	private String hoVaTen4;

	@Column(name = "KY_TRA_NO_HIEN_TAI")
	private BigDecimal kyTraNoHienTai;

	@Column(name = "KY_TRA_NO_TIEP_THEO")
	private BigDecimal kyTraNoTiepTheo;

	@Column(name = "LAI_DA_TRA")
	private BigDecimal laiDaTra;

	@Column(name = "LAI_PHAT")
	private BigDecimal laiPhat;

	@Column(name = "LAI_PHAT_DA_TRA")
	private BigDecimal laiPhatDaTra;

	@Column(name = "LAI_QUA_HAN")
	private BigDecimal laiQuaHan;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "ERROR_CODE")
	private BigDecimal errorCode;

	@Size(max = 50)
	@Column(name = "LS_TAC_DONG_GAN_NHAT")
	private String lsTacDongGanNhat;

	@Size(max = 50)
	@Column(name = "MA_SAN_PHAM")
	private String maSanPham;

	@Size(max = 150)
	@Column(name = "MOI_QH_VOI_KHACH_HANG_1")
	private String moiQhVoiKhachHang1;

	@Size(max = 150)
	@Column(name = "MOI_QH_VOI_KHACH_HANG_2")
	private String moiQhVoiKhachHang2;

	@Size(max = 150)
	@Column(name = "MOI_QH_VOI_KHACH_HANG_3")
	private String moiQhVoiKhachHang3;

	@Size(max = 150)
	@Column(name = "MOI_QH_VOI_KHACH_HANG_4")
	private String moiQhVoiKhachHang4;

	@Size(max = 150)
	@Column(name = "MUC_DICH_VAY")
	private String mucDichVay;

	@Size(max = 150)
	@Column(name = "NGANH_NGHE")
	private String nganhNghe;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_CAP_CMND_CCCD")
	private Date ngayCapCmndCccd;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_DAO_HAN")
	private Date ngayDaoHan;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_DU_THU_1")
	private Date ngayDuThu1;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_DU_THU_2")
	private Date ngayDuThu2;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_GIAI_NGAN")
	private Date ngayGiaiNgan;

	@Size(max = 150)
	@Column(name = "NGAY_NHAN_LUONG")
	private String ngayNhanLuong;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_SINH")
	private Date ngaySinh;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_TRA_NO_DAU_TIEN")
	private Date ngayTraNoDauTien;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_TRA_NO_GAN_NHAT")
	private Date ngayTraNoGanNhat;

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAY_TRA_NO_KY_TIEP_THEO")
	private Date ngayTraNoKyTiepTheo;

	@Size(max = 150)
	@Column(name = "NGHE_NGHIEP")
	private String ngheNghiep;

	@Size(max = 150)
	@Column(name = "NGUON_THU_NHAP")
	private String nguonThuNhap;

	@Size(max = 150)
	@Column(name = "NHAN_VIEN_PHU_TRACH")
	private String nhanVienPhuTrach;

	@Size(max = 10)
	@Column(name = "NHOM_DPD")
	private String nhomDpd;

	@Size(max = 50)
	@Column(name = "NHOM_SAN_PHAM")
	private String nhomSanPham;

	@Size(max = 400)
	@Column(name = "NOI_CAP_CMND_CCCD")
	private String noiCapCmndCccd;

	@Column(name = "PHI_BAO_HIEM")
	private BigDecimal phiBaoHiem;

	@Size(max = 150)
	@Column(name = "PHUONG_TAM_TRU")
	private String phuongTamTru;

	@Size(max = 150)
	@Column(name = "PHUONG_THUONG_TRU")
	private String phuongThuongTru;

	@Size(max = 150)
	@Column(name = "QUAN_TAM_TRU")
	private String quanTamTru;

	@Size(max = 150)
	@Column(name = "QUAN_THUONG_TRU")
	private String quanThuongTru;

	@Size(max = 150)
	@Column(name = "SO_DIEN_THOAI_1")
	private String soDienThoai1;

	@Size(max = 150)
	@Column(name = "SO_DIEN_THOAI_2")
	private String soDienThoai2;

	@Size(max = 150)
	@Column(name = "SO_DIEN_THOAI_3")
	private String soDienThoai3;

	@Size(max = 150)
	@Column(name = "SO_DIEN_THOAI_4")
	private String soDienThoai4;

	@Size(max = 20)
	@Column(name = "SO_DIEN_THOAI_KH")
	private String soDienThoaiKh;

	@Column(name = "ST_DU_THU_SAU_15_NGAY")
	private BigDecimal stDuThuSau15Ngay;

	@Column(name = "ST_DU_THU_TRUOC_15_NGAY")
	private BigDecimal stDuThuTruoc15Ngay;

	@Column(name = "ST_DUOC_BAO_HIEM")
	private BigDecimal stDuocBaoHiem;

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_RUN_DATE")
	private Date sysRunDate;

	@Size(max = 70)
	private String team;

	@Size(max = 150)
	@Column(name = "TEN_CHI_NHAN_PHAT_VAY")
	private String tenChiNhanPhatVay;

	@Size(max = 150)
	@Column(name = "TEN_CONG_TY")
	private String tenCongTy;

	@NotNull
	@Size(max = 200)
	@Column(name = "TEN_KHACH_HANG")
	private String tenKhachHang;

	@Size(max = 15)
	@Column(name = "THOI_GIAN_CONG_TAC")
	private String thoiGianCongTac;

	@Size(max = 150)
	@Column(name = "TINH_TAM_TRU")
	private String tinhTamTru;

	@Size(max = 150)
	@Column(name = "TINH_THUONG_TRU")
	private String tinhThuongTru;

	@Size(max = 50)
	@Column(name = "TK_TIEN_ICH")
	private String tkTienIch;

	@Column(name = "TONG_ST_DA_THU")
	private BigDecimal tongStDaThu;

	@Column(name = "TONG_ST_QUA_HAN")
	private BigDecimal tongStQuaHan;

	@Column(name = "TONG_THU_NHAP")
	private BigDecimal tongThuNhap;

	@Column(name = "UPLOAD_HDR_ID")
	private BigDecimal uploadHdrId;

	@NotNull
	@Size(max = 50)
	private String user1;

	@Column(name = "USERNAME_UPLOAD")
	private String usernameUpload;

	public DebtUploadCusLd() {
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getChiTietLsTacDong() {
		return this.chiTietLsTacDong;
	}

	public void setChiTietLsTacDong(String chiTietLsTacDong) {
		this.chiTietLsTacDong = chiTietLsTacDong;
	}

	public String getChucVu() {
		return this.chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCmndCccd() {
		return this.cmndCccd;
	}

	public void setCmndCccd(String cmndCccd) {
		this.cmndCccd = cmndCccd;
	}

	public String getDiaChiCongTy() {
		return this.diaChiCongTy;
	}

	public void setDiaChiCongTy(String diaChiCongTy) {
		this.diaChiCongTy = diaChiCongTy;
	}

	public String getDiaChiTamTru() {
		return this.diaChiTamTru;
	}

	public void setDiaChiTamTru(String diaChiTamTru) {
		this.diaChiTamTru = diaChiTamTru;
	}

	public String getDiaChiThuongTru() {
		return this.diaChiThuongTru;
	}

	public void setDiaChiThuongTru(String diaChiThuongTru) {
		this.diaChiThuongTru = diaChiThuongTru;
	}

	public String getDienThoaiCongTy() {
		return this.dienThoaiCongTy;
	}

	public void setDienThoaiCongTy(String dienThoaiCongTy) {
		this.dienThoaiCongTy = dienThoaiCongTy;
	}

	public String getDoiTac() {
		return this.doiTac;
	}

	public void setDoiTac(String doiTac) {
		this.doiTac = doiTac;
	}

	public BigDecimal getDpdHienTai() {
		return this.dpdHienTai;
	}

	public void setDpdHienTai(BigDecimal dpdHienTai) {
		this.dpdHienTai = dpdHienTai;
	}

	public BigDecimal getDpdNgayBanGiao() {
		return this.dpdNgayBanGiao;
	}

	public void setDpdNgayBanGiao(BigDecimal dpdNgayBanGiao) {
		this.dpdNgayBanGiao = dpdNgayBanGiao;
	}

	public BigDecimal getDuNoGocBanDau() {
		return this.duNoGocBanDau;
	}

	public void setDuNoGocBanDau(BigDecimal duNoGocBanDau) {
		this.duNoGocBanDau = duNoGocBanDau;
	}

	public BigDecimal getDuNoGocConLai() {
		return this.duNoGocConLai;
	}

	public void setDuNoGocConLai(BigDecimal duNoGocConLai) {
		this.duNoGocConLai = duNoGocConLai;
	}

	public BigDecimal getEmi() {
		return this.emi;
	}

	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}

	public String getGioiTinh() {
		return this.gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public BigDecimal getGocDaTra() {
		return this.gocDaTra;
	}

	public void setGocDaTra(BigDecimal gocDaTra) {
		this.gocDaTra = gocDaTra;
	}

	public BigDecimal getGocQuaHan() {
		return this.gocQuaHan;
	}

	public void setGocQuaHan(BigDecimal gocQuaHan) {
		this.gocQuaHan = gocQuaHan;
	}

	public String getHinhThucNhanLuong() {
		return this.hinhThucNhanLuong;
	}

	public void setHinhThucNhanLuong(String hinhThucNhanLuong) {
		this.hinhThucNhanLuong = hinhThucNhanLuong;
	}

	public String getHoVaTen1() {
		return this.hoVaTen1;
	}

	public void setHoVaTen1(String hoVaTen1) {
		this.hoVaTen1 = hoVaTen1;
	}

	public String getHoVaTen2() {
		return this.hoVaTen2;
	}

	public void setHoVaTen2(String hoVaTen2) {
		this.hoVaTen2 = hoVaTen2;
	}

	public String getHoVaTen3() {
		return this.hoVaTen3;
	}

	public void setHoVaTen3(String hoVaTen3) {
		this.hoVaTen3 = hoVaTen3;
	}

	public BigDecimal getKyTraNoHienTai() {
		return this.kyTraNoHienTai;
	}

	public void setKyTraNoHienTai(BigDecimal kyTraNoHienTai) {
		this.kyTraNoHienTai = kyTraNoHienTai;
	}

	public BigDecimal getKyTraNoTiepTheo() {
		return this.kyTraNoTiepTheo;
	}

	public void setKyTraNoTiepTheo(BigDecimal kyTraNoTiepTheo) {
		this.kyTraNoTiepTheo = kyTraNoTiepTheo;
	}

	public BigDecimal getLaiDaTra() {
		return this.laiDaTra;
	}

	public void setLaiDaTra(BigDecimal laiDaTra) {
		this.laiDaTra = laiDaTra;
	}

	public BigDecimal getLaiPhat() {
		return this.laiPhat;
	}

	public void setLaiPhat(BigDecimal laiPhat) {
		this.laiPhat = laiPhat;
	}

	public BigDecimal getLaiPhatDaTra() {
		return this.laiPhatDaTra;
	}

	public void setLaiPhatDaTra(BigDecimal laiPhatDaTra) {
		this.laiPhatDaTra = laiPhatDaTra;
	}

	public BigDecimal getLaiQuaHan() {
		return this.laiQuaHan;
	}

	public void setLaiQuaHan(BigDecimal laiQuaHan) {
		this.laiQuaHan = laiQuaHan;
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

	public String getMaSanPham() {
		return this.maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getMoiQhVoiKhachHang1() {
		return this.moiQhVoiKhachHang1;
	}

	public void setMoiQhVoiKhachHang1(String moiQhVoiKhachHang1) {
		this.moiQhVoiKhachHang1 = moiQhVoiKhachHang1;
	}

	public String getMoiQhVoiKhachHang2() {
		return this.moiQhVoiKhachHang2;
	}

	public void setMoiQhVoiKhachHang2(String moiQhVoiKhachHang2) {
		this.moiQhVoiKhachHang2 = moiQhVoiKhachHang2;
	}

	public String getMoiQhVoiKhachHang3() {
		return this.moiQhVoiKhachHang3;
	}

	public void setMoiQhVoiKhachHang3(String moiQhVoiKhachHang3) {
		this.moiQhVoiKhachHang3 = moiQhVoiKhachHang3;
	}

	public String getMucDichVay() {
		return this.mucDichVay;
	}

	public void setMucDichVay(String mucDichVay) {
		this.mucDichVay = mucDichVay;
	}

	public String getNganhNghe() {
		return this.nganhNghe;
	}

	public void setNganhNghe(String nganhNghe) {
		this.nganhNghe = nganhNghe;
	}

	public Date getNgayCapCmndCccd() {
		return this.ngayCapCmndCccd;
	}

	public void setNgayCapCmndCccd(Date ngayCapCmndCccd) {
		this.ngayCapCmndCccd = ngayCapCmndCccd;
	}

	public Date getNgayDaoHan() {
		return this.ngayDaoHan;
	}

	public void setNgayDaoHan(Date ngayDaoHan) {
		this.ngayDaoHan = ngayDaoHan;
	}

	public Date getNgayDuThu1() {
		return this.ngayDuThu1;
	}

	public void setNgayDuThu1(Date ngayDuThu1) {
		this.ngayDuThu1 = ngayDuThu1;
	}

	public Date getNgayDuThu2() {
		return this.ngayDuThu2;
	}

	public void setNgayDuThu2(Date ngayDuThu2) {
		this.ngayDuThu2 = ngayDuThu2;
	}

	public Date getNgayGiaiNgan() {
		return this.ngayGiaiNgan;
	}

	public void setNgayGiaiNgan(Date ngayGiaiNgan) {
		this.ngayGiaiNgan = ngayGiaiNgan;
	}

	public String getNgayNhanLuong() {
		return ngayNhanLuong;
	}

	public void setNgayNhanLuong(String ngayNhanLuong) {
		this.ngayNhanLuong = ngayNhanLuong;
	}

	public Date getNgaySinh() {
		return this.ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgayTraNoDauTien() {
		return this.ngayTraNoDauTien;
	}

	public void setNgayTraNoDauTien(Date ngayTraNoDauTien) {
		this.ngayTraNoDauTien = ngayTraNoDauTien;
	}

	public Date getNgayTraNoGanNhat() {
		return this.ngayTraNoGanNhat;
	}

	public void setNgayTraNoGanNhat(Date ngayTraNoGanNhat) {
		this.ngayTraNoGanNhat = ngayTraNoGanNhat;
	}

	public Date getNgayTraNoKyTiepTheo() {
		return this.ngayTraNoKyTiepTheo;
	}

	public void setNgayTraNoKyTiepTheo(Date ngayTraNoKyTiepTheo) {
		this.ngayTraNoKyTiepTheo = ngayTraNoKyTiepTheo;
	}

	public String getNgheNghiep() {
		return this.ngheNghiep;
	}

	public void setNgheNghiep(String ngheNghiep) {
		this.ngheNghiep = ngheNghiep;
	}

	public String getNguonThuNhap() {
		return this.nguonThuNhap;
	}

	public void setNguonThuNhap(String nguonThuNhap) {
		this.nguonThuNhap = nguonThuNhap;
	}

	public String getNhanVienPhuTrach() {
		return this.nhanVienPhuTrach;
	}

	public void setNhanVienPhuTrach(String nhanVienPhuTrach) {
		this.nhanVienPhuTrach = nhanVienPhuTrach;
	}

	public String getNhomDpd() {
		return this.nhomDpd;
	}

	public void setNhomDpd(String nhomDpd) {
		this.nhomDpd = nhomDpd;
	}

	public String getNhomSanPham() {
		return this.nhomSanPham;
	}

	public void setNhomSanPham(String nhomSanPham) {
		this.nhomSanPham = nhomSanPham;
	}

	public String getNoiCapCmndCccd() {
		return this.noiCapCmndCccd;
	}

	public void setNoiCapCmndCccd(String noiCapCmndCccd) {
		this.noiCapCmndCccd = noiCapCmndCccd;
	}

	public BigDecimal getPhiBaoHiem() {
		return this.phiBaoHiem;
	}

	public void setPhiBaoHiem(BigDecimal phiBaoHiem) {
		this.phiBaoHiem = phiBaoHiem;
	}

	public String getPhuongTamTru() {
		return this.phuongTamTru;
	}

	public void setPhuongTamTru(String phuongTamTru) {
		this.phuongTamTru = phuongTamTru;
	}

	public String getPhuongThuongTru() {
		return this.phuongThuongTru;
	}

	public void setPhuongThuongTru(String phuongThuongTru) {
		this.phuongThuongTru = phuongThuongTru;
	}

	public String getQuanTamTru() {
		return this.quanTamTru;
	}

	public void setQuanTamTru(String quanTamTru) {
		this.quanTamTru = quanTamTru;
	}

	public String getQuanThuongTru() {
		return this.quanThuongTru;
	}

	public void setQuanThuongTru(String quanThuongTru) {
		this.quanThuongTru = quanThuongTru;
	}

	public String getSoDienThoai1() {
		return this.soDienThoai1;
	}

	public void setSoDienThoai1(String soDienThoai1) {
		this.soDienThoai1 = soDienThoai1;
	}

	public String getSoDienThoai2() {
		return this.soDienThoai2;
	}

	public void setSoDienThoai2(String soDienThoai2) {
		this.soDienThoai2 = soDienThoai2;
	}

	public String getSoDienThoai3() {
		return this.soDienThoai3;
	}

	public void setSoDienThoai3(String soDienThoai3) {
		this.soDienThoai3 = soDienThoai3;
	}

	public String getSoDienThoaiKh() {
		return this.soDienThoaiKh;
	}

	public void setSoDienThoaiKh(String soDienThoaiKh) {
		this.soDienThoaiKh = soDienThoaiKh;
	}

	public String getSoHopDong() {
		return this.soHopDong;
	}

	public void setSoHopDong(String soHopDong) {
		this.soHopDong = soHopDong;
	}

	public BigDecimal getStDuThuSau15Ngay() {
		return this.stDuThuSau15Ngay;
	}

	public void setStDuThuSau15Ngay(BigDecimal stDuThuSau15Ngay) {
		this.stDuThuSau15Ngay = stDuThuSau15Ngay;
	}

	public BigDecimal getStDuThuTruoc15Ngay() {
		return this.stDuThuTruoc15Ngay;
	}

	public void setStDuThuTruoc15Ngay(BigDecimal stDuThuTruoc15Ngay) {
		this.stDuThuTruoc15Ngay = stDuThuTruoc15Ngay;
	}

	public BigDecimal getStDuocBaoHiem() {
		return this.stDuocBaoHiem;
	}

	public void setStDuocBaoHiem(BigDecimal stDuocBaoHiem) {
		this.stDuocBaoHiem = stDuocBaoHiem;
	}

	public Date getSysRunDate() {
		return this.sysRunDate;
	}

	public void setSysRunDate(Date sysRunDate) {
		this.sysRunDate = sysRunDate;
	}

	public String getTeam() {
		return this.team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getTenChiNhanPhatVay() {
		return this.tenChiNhanPhatVay;
	}

	public void setTenChiNhanPhatVay(String tenChiNhanPhatVay) {
		this.tenChiNhanPhatVay = tenChiNhanPhatVay;
	}

	public String getTenCongTy() {
		return this.tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	public String getTenKhachHang() {
		return this.tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getThoiGianCongTac() {
		return this.thoiGianCongTac;
	}

	public void setThoiGianCongTac(String thoiGianCongTac) {
		this.thoiGianCongTac = thoiGianCongTac;
	}

	public String getTinhTamTru() {
		return this.tinhTamTru;
	}

	public void setTinhTamTru(String tinhTamTru) {
		this.tinhTamTru = tinhTamTru;
	}

	public String getTinhThuongTru() {
		return this.tinhThuongTru;
	}

	public void setTinhThuongTru(String tinhThuongTru) {
		this.tinhThuongTru = tinhThuongTru;
	}

	public String getTkTienIch() {
		return this.tkTienIch;
	}

	public void setTkTienIch(String tkTienIch) {
		this.tkTienIch = tkTienIch;
	}

	public BigDecimal getTongStDaThu() {
		return this.tongStDaThu;
	}

	public void setTongStDaThu(BigDecimal tongStDaThu) {
		this.tongStDaThu = tongStDaThu;
	}

	public BigDecimal getTongStQuaHan() {
		return this.tongStQuaHan;
	}

	public void setTongStQuaHan(BigDecimal tongStQuaHan) {
		this.tongStQuaHan = tongStQuaHan;
	}

	public BigDecimal getTongThuNhap() {
		return this.tongThuNhap;
	}

	public void setTongThuNhap(BigDecimal tongThuNhap) {
		this.tongThuNhap = tongThuNhap;
	}

	public BigDecimal getUploadHdrId() {
		return this.uploadHdrId;
	}

	public void setUploadHdrId(BigDecimal uploadHdrId) {
		this.uploadHdrId = uploadHdrId;
	}

	public String getUser1() {
		return this.user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUsernameUpload() {
		return this.usernameUpload;
	}

	public void setUsernameUpload(String usernameUpload) {
		this.usernameUpload = usernameUpload;
	}

	public String getHoVaTen4() {
		return hoVaTen4;
	}

	public void setHoVaTen4(String hoVaTen4) {
		this.hoVaTen4 = hoVaTen4;
	}

	public String getMoiQhVoiKhachHang4() {
		return moiQhVoiKhachHang4;
	}

	public void setMoiQhVoiKhachHang4(String moiQhVoiKhachHang4) {
		this.moiQhVoiKhachHang4 = moiQhVoiKhachHang4;
	}

	public String getSoDienThoai4() {
		return soDienThoai4;
	}

	public void setSoDienThoai4(String soDienThoai4) {
		this.soDienThoai4 = soDienThoai4;
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