package com.shf.dcs.dto;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class DebtUploadCusLdDto {
	
	@NotNull(message = "Số hợp đồng")
	private String soHopDong;
	private String chiTietLsTacDong;
	private String chucVu;
	private String cif;
	private String cmndCccd;
	private String diaChiCongTy;
	private String diaChiTamTru;
	private String diaChiThuongTru;
	private String dienThoaiCongTy;
	private String doiTac;
	private BigDecimal dpdHienTai;
	private BigDecimal dpdNgayBanGiao;
	private BigDecimal duNoGocBanDau;
	private BigDecimal duNoGocConLai;
	private BigDecimal emi;
	private String errMsg;
	private String gioiTinh;
	private BigDecimal gocDaTra;
	private BigDecimal gocQuaHan;
	private String hinhThucNhanLuong;
	private String hoVaTen1;
	private String hoVaTen2;
	private String hoVaTen3;
	private BigDecimal kyTraNoHienTai;
	private BigDecimal kyTraNoTiepTheo;
	private BigDecimal laiDaTra;
	private BigDecimal laiPhat;
	private BigDecimal laiPhatDaTra;
	private BigDecimal laiQuaHan;
	private Date lastUpdateDate;
	private BigDecimal loadFlag;
	private String lsTacDongGanNhat;
	private String maSanPham;
	private String moiQhVoiKhachHang1;
	private String moiQhVoiKhachHang2;
	private String moiQhVoiKhachHang3;
	private String mucDichVay;
	private String nganhNghe;
	private Date ngayCapCmndCccd;
	private Date ngayDaoHan;
	private Date ngayDuThu1;
	private Date ngayDuThu2;
	private Date ngayGiaiNgan;
	private Date ngayNhanLuong;
	private Date ngaySinh;
	private Date ngayTraNoDauTien;
	private Date ngayTraNoGanNhat;
	private Date ngayTraNoKyTiepTheo;
	private String ngheNghiep;
	private String nguonThuNhap;
	private String nhanVienPhuTrach;
	private String nhomDpd;
	private String nhomSanPham;
	private String noiCapCmndCccd;
	private BigDecimal phiBaoHiem;
	private String phuongTamTru;
	private String phuongThuongTru;
	private String quanTamTru;
	private String quanThuongTru;
	private String soDienThoai1;
	private String soDienThoai2;
	private String soDienThoai3;
	private String soDienThoaiKh;
	private BigDecimal stDuThuSau15Ngay;
	private BigDecimal stDuThuTruoc15Ngay;
	private BigDecimal stDuocBaoHiem;
	private Date sysRunDate;
	private String team;
	private String tenChiNhanPhatVay;
	private String tenCongTy;
	private String tenKhachHang;
	private String thoiGianCongTac;
	private String tinhTamTru;
	private String tinhThuongTru;
	private String tkTienIch;
	private BigDecimal tongStDaThu;
	private BigDecimal tongStQuaHan;
	private BigDecimal tongThuNhap;
	private BigDecimal uploadHdrId;
	private String user1;
	private String usernameUpload;
	public String getSoHopDong() {
		return soHopDong;
	}
	public void setSoHopDong(String soHopDong) {
		this.soHopDong = soHopDong;
	}
	public String getChiTietLsTacDong() {
		return chiTietLsTacDong;
	}
	public void setChiTietLsTacDong(String chiTietLsTacDong) {
		this.chiTietLsTacDong = chiTietLsTacDong;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getCmndCccd() {
		return cmndCccd;
	}
	public void setCmndCccd(String cmndCccd) {
		this.cmndCccd = cmndCccd;
	}
	public String getDiaChiCongTy() {
		return diaChiCongTy;
	}
	public void setDiaChiCongTy(String diaChiCongTy) {
		this.diaChiCongTy = diaChiCongTy;
	}
	public String getDiaChiTamTru() {
		return diaChiTamTru;
	}
	public void setDiaChiTamTru(String diaChiTamTru) {
		this.diaChiTamTru = diaChiTamTru;
	}
	public String getDiaChiThuongTru() {
		return diaChiThuongTru;
	}
	public void setDiaChiThuongTru(String diaChiThuongTru) {
		this.diaChiThuongTru = diaChiThuongTru;
	}
	public String getDienThoaiCongTy() {
		return dienThoaiCongTy;
	}
	public void setDienThoaiCongTy(String dienThoaiCongTy) {
		this.dienThoaiCongTy = dienThoaiCongTy;
	}
	public String getDoiTac() {
		return doiTac;
	}
	public void setDoiTac(String doiTac) {
		this.doiTac = doiTac;
	}
	public BigDecimal getDpdHienTai() {
		return dpdHienTai;
	}
	public void setDpdHienTai(BigDecimal dpdHienTai) {
		this.dpdHienTai = dpdHienTai;
	}
	public BigDecimal getDpdNgayBanGiao() {
		return dpdNgayBanGiao;
	}
	public void setDpdNgayBanGiao(BigDecimal dpdNgayBanGiao) {
		this.dpdNgayBanGiao = dpdNgayBanGiao;
	}
	public BigDecimal getDuNoGocBanDau() {
		return duNoGocBanDau;
	}
	public void setDuNoGocBanDau(BigDecimal duNoGocBanDau) {
		this.duNoGocBanDau = duNoGocBanDau;
	}
	public BigDecimal getDuNoGocConLai() {
		return duNoGocConLai;
	}
	public void setDuNoGocConLai(BigDecimal duNoGocConLai) {
		this.duNoGocConLai = duNoGocConLai;
	}
	public BigDecimal getEmi() {
		return emi;
	}
	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public BigDecimal getGocDaTra() {
		return gocDaTra;
	}
	public void setGocDaTra(BigDecimal gocDaTra) {
		this.gocDaTra = gocDaTra;
	}
	public BigDecimal getGocQuaHan() {
		return gocQuaHan;
	}
	public void setGocQuaHan(BigDecimal gocQuaHan) {
		this.gocQuaHan = gocQuaHan;
	}
	public String getHinhThucNhanLuong() {
		return hinhThucNhanLuong;
	}
	public void setHinhThucNhanLuong(String hinhThucNhanLuong) {
		this.hinhThucNhanLuong = hinhThucNhanLuong;
	}
	public String getHoVaTen1() {
		return hoVaTen1;
	}
	public void setHoVaTen1(String hoVaTen1) {
		this.hoVaTen1 = hoVaTen1;
	}
	public String getHoVaTen2() {
		return hoVaTen2;
	}
	public void setHoVaTen2(String hoVaTen2) {
		this.hoVaTen2 = hoVaTen2;
	}
	public String getHoVaTen3() {
		return hoVaTen3;
	}
	public void setHoVaTen3(String hoVaTen3) {
		this.hoVaTen3 = hoVaTen3;
	}
	public BigDecimal getKyTraNoHienTai() {
		return kyTraNoHienTai;
	}
	public void setKyTraNoHienTai(BigDecimal kyTraNoHienTai) {
		this.kyTraNoHienTai = kyTraNoHienTai;
	}
	public BigDecimal getKyTraNoTiepTheo() {
		return kyTraNoTiepTheo;
	}
	public void setKyTraNoTiepTheo(BigDecimal kyTraNoTiepTheo) {
		this.kyTraNoTiepTheo = kyTraNoTiepTheo;
	}
	public BigDecimal getLaiDaTra() {
		return laiDaTra;
	}
	public void setLaiDaTra(BigDecimal laiDaTra) {
		this.laiDaTra = laiDaTra;
	}
	public BigDecimal getLaiPhat() {
		return laiPhat;
	}
	public void setLaiPhat(BigDecimal laiPhat) {
		this.laiPhat = laiPhat;
	}
	public BigDecimal getLaiPhatDaTra() {
		return laiPhatDaTra;
	}
	public void setLaiPhatDaTra(BigDecimal laiPhatDaTra) {
		this.laiPhatDaTra = laiPhatDaTra;
	}
	public BigDecimal getLaiQuaHan() {
		return laiQuaHan;
	}
	public void setLaiQuaHan(BigDecimal laiQuaHan) {
		this.laiQuaHan = laiQuaHan;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public BigDecimal getLoadFlag() {
		return loadFlag;
	}
	public void setLoadFlag(BigDecimal loadFlag) {
		this.loadFlag = loadFlag;
	}
	public String getLsTacDongGanNhat() {
		return lsTacDongGanNhat;
	}
	public void setLsTacDongGanNhat(String lsTacDongGanNhat) {
		this.lsTacDongGanNhat = lsTacDongGanNhat;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getMoiQhVoiKhachHang1() {
		return moiQhVoiKhachHang1;
	}
	public void setMoiQhVoiKhachHang1(String moiQhVoiKhachHang1) {
		this.moiQhVoiKhachHang1 = moiQhVoiKhachHang1;
	}
	public String getMoiQhVoiKhachHang2() {
		return moiQhVoiKhachHang2;
	}
	public void setMoiQhVoiKhachHang2(String moiQhVoiKhachHang2) {
		this.moiQhVoiKhachHang2 = moiQhVoiKhachHang2;
	}
	public String getMoiQhVoiKhachHang3() {
		return moiQhVoiKhachHang3;
	}
	public void setMoiQhVoiKhachHang3(String moiQhVoiKhachHang3) {
		this.moiQhVoiKhachHang3 = moiQhVoiKhachHang3;
	}
	public String getMucDichVay() {
		return mucDichVay;
	}
	public void setMucDichVay(String mucDichVay) {
		this.mucDichVay = mucDichVay;
	}
	public String getNganhNghe() {
		return nganhNghe;
	}
	public void setNganhNghe(String nganhNghe) {
		this.nganhNghe = nganhNghe;
	}
	public Date getNgayCapCmndCccd() {
		return ngayCapCmndCccd;
	}
	public void setNgayCapCmndCccd(Date ngayCapCmndCccd) {
		this.ngayCapCmndCccd = ngayCapCmndCccd;
	}
	public Date getNgayDaoHan() {
		return ngayDaoHan;
	}
	public void setNgayDaoHan(Date ngayDaoHan) {
		this.ngayDaoHan = ngayDaoHan;
	}
	public Date getNgayDuThu1() {
		return ngayDuThu1;
	}
	public void setNgayDuThu1(Date ngayDuThu1) {
		this.ngayDuThu1 = ngayDuThu1;
	}
	public Date getNgayDuThu2() {
		return ngayDuThu2;
	}
	public void setNgayDuThu2(Date ngayDuThu2) {
		this.ngayDuThu2 = ngayDuThu2;
	}
	public Date getNgayGiaiNgan() {
		return ngayGiaiNgan;
	}
	public void setNgayGiaiNgan(Date ngayGiaiNgan) {
		this.ngayGiaiNgan = ngayGiaiNgan;
	}
	public Date getNgayNhanLuong() {
		return ngayNhanLuong;
	}
	public void setNgayNhanLuong(Date ngayNhanLuong) {
		this.ngayNhanLuong = ngayNhanLuong;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public Date getNgayTraNoDauTien() {
		return ngayTraNoDauTien;
	}
	public void setNgayTraNoDauTien(Date ngayTraNoDauTien) {
		this.ngayTraNoDauTien = ngayTraNoDauTien;
	}
	public Date getNgayTraNoGanNhat() {
		return ngayTraNoGanNhat;
	}
	public void setNgayTraNoGanNhat(Date ngayTraNoGanNhat) {
		this.ngayTraNoGanNhat = ngayTraNoGanNhat;
	}
	public Date getNgayTraNoKyTiepTheo() {
		return ngayTraNoKyTiepTheo;
	}
	public void setNgayTraNoKyTiepTheo(Date ngayTraNoKyTiepTheo) {
		this.ngayTraNoKyTiepTheo = ngayTraNoKyTiepTheo;
	}
	public String getNgheNghiep() {
		return ngheNghiep;
	}
	public void setNgheNghiep(String ngheNghiep) {
		this.ngheNghiep = ngheNghiep;
	}
	public String getNguonThuNhap() {
		return nguonThuNhap;
	}
	public void setNguonThuNhap(String nguonThuNhap) {
		this.nguonThuNhap = nguonThuNhap;
	}
	public String getNhanVienPhuTrach() {
		return nhanVienPhuTrach;
	}
	public void setNhanVienPhuTrach(String nhanVienPhuTrach) {
		this.nhanVienPhuTrach = nhanVienPhuTrach;
	}
	public String getNhomDpd() {
		return nhomDpd;
	}
	public void setNhomDpd(String nhomDpd) {
		this.nhomDpd = nhomDpd;
	}
	public String getNhomSanPham() {
		return nhomSanPham;
	}
	public void setNhomSanPham(String nhomSanPham) {
		this.nhomSanPham = nhomSanPham;
	}
	public String getNoiCapCmndCccd() {
		return noiCapCmndCccd;
	}
	public void setNoiCapCmndCccd(String noiCapCmndCccd) {
		this.noiCapCmndCccd = noiCapCmndCccd;
	}
	public BigDecimal getPhiBaoHiem() {
		return phiBaoHiem;
	}
	public void setPhiBaoHiem(BigDecimal phiBaoHiem) {
		this.phiBaoHiem = phiBaoHiem;
	}
	public String getPhuongTamTru() {
		return phuongTamTru;
	}
	public void setPhuongTamTru(String phuongTamTru) {
		this.phuongTamTru = phuongTamTru;
	}
	public String getPhuongThuongTru() {
		return phuongThuongTru;
	}
	public void setPhuongThuongTru(String phuongThuongTru) {
		this.phuongThuongTru = phuongThuongTru;
	}
	public String getQuanTamTru() {
		return quanTamTru;
	}
	public void setQuanTamTru(String quanTamTru) {
		this.quanTamTru = quanTamTru;
	}
	public String getQuanThuongTru() {
		return quanThuongTru;
	}
	public void setQuanThuongTru(String quanThuongTru) {
		this.quanThuongTru = quanThuongTru;
	}
	public String getSoDienThoai1() {
		return soDienThoai1;
	}
	public void setSoDienThoai1(String soDienThoai1) {
		this.soDienThoai1 = soDienThoai1;
	}
	public String getSoDienThoai2() {
		return soDienThoai2;
	}
	public void setSoDienThoai2(String soDienThoai2) {
		this.soDienThoai2 = soDienThoai2;
	}
	public String getSoDienThoai3() {
		return soDienThoai3;
	}
	public void setSoDienThoai3(String soDienThoai3) {
		this.soDienThoai3 = soDienThoai3;
	}
	public String getSoDienThoaiKh() {
		return soDienThoaiKh;
	}
	public void setSoDienThoaiKh(String soDienThoaiKh) {
		this.soDienThoaiKh = soDienThoaiKh;
	}
	public BigDecimal getStDuThuSau15Ngay() {
		return stDuThuSau15Ngay;
	}
	public void setStDuThuSau15Ngay(BigDecimal stDuThuSau15Ngay) {
		this.stDuThuSau15Ngay = stDuThuSau15Ngay;
	}
	public BigDecimal getStDuThuTruoc15Ngay() {
		return stDuThuTruoc15Ngay;
	}
	public void setStDuThuTruoc15Ngay(BigDecimal stDuThuTruoc15Ngay) {
		this.stDuThuTruoc15Ngay = stDuThuTruoc15Ngay;
	}
	public BigDecimal getStDuocBaoHiem() {
		return stDuocBaoHiem;
	}
	public void setStDuocBaoHiem(BigDecimal stDuocBaoHiem) {
		this.stDuocBaoHiem = stDuocBaoHiem;
	}
	public Date getSysRunDate() {
		return sysRunDate;
	}
	public void setSysRunDate(Date sysRunDate) {
		this.sysRunDate = sysRunDate;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getTenChiNhanPhatVay() {
		return tenChiNhanPhatVay;
	}
	public void setTenChiNhanPhatVay(String tenChiNhanPhatVay) {
		this.tenChiNhanPhatVay = tenChiNhanPhatVay;
	}
	public String getTenCongTy() {
		return tenCongTy;
	}
	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getThoiGianCongTac() {
		return thoiGianCongTac;
	}
	public void setThoiGianCongTac(String thoiGianCongTac) {
		this.thoiGianCongTac = thoiGianCongTac;
	}
	public String getTinhTamTru() {
		return tinhTamTru;
	}
	public void setTinhTamTru(String tinhTamTru) {
		this.tinhTamTru = tinhTamTru;
	}
	public String getTinhThuongTru() {
		return tinhThuongTru;
	}
	public void setTinhThuongTru(String tinhThuongTru) {
		this.tinhThuongTru = tinhThuongTru;
	}
	public String getTkTienIch() {
		return tkTienIch;
	}
	public void setTkTienIch(String tkTienIch) {
		this.tkTienIch = tkTienIch;
	}
	public BigDecimal getTongStDaThu() {
		return tongStDaThu;
	}
	public void setTongStDaThu(BigDecimal tongStDaThu) {
		this.tongStDaThu = tongStDaThu;
	}
	public BigDecimal getTongStQuaHan() {
		return tongStQuaHan;
	}
	public void setTongStQuaHan(BigDecimal tongStQuaHan) {
		this.tongStQuaHan = tongStQuaHan;
	}
	public BigDecimal getTongThuNhap() {
		return tongThuNhap;
	}
	public void setTongThuNhap(BigDecimal tongThuNhap) {
		this.tongThuNhap = tongThuNhap;
	}
	public BigDecimal getUploadHdrId() {
		return uploadHdrId;
	}
	public void setUploadHdrId(BigDecimal uploadHdrId) {
		this.uploadHdrId = uploadHdrId;
	}
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUsernameUpload() {
		return usernameUpload;
	}
	public void setUsernameUpload(String usernameUpload) {
		this.usernameUpload = usernameUpload;
	}
}
