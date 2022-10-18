package com.shf.dcs.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtUploadCusLdDto {
	
	@NotNull(message = "Không")
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
}
