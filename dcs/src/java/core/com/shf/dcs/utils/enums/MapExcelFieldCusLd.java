package com.shf.dcs.utils.enums;

public enum MapExcelFieldCusLd{
	
	// Customer-LD
	soHopDong(0,"Số hợp đồng",MapExcelFieldDataType.STRING),
	user1(1,"User",MapExcelFieldDataType.STRING),
	cif(2,"Cif",MapExcelFieldDataType.STRING),
	diaChiThuongTru(3,"Địa chỉ thường trú",MapExcelFieldDataType.STRING),
	diaChiTamTru(4,"Địa chỉ tạm trú",MapExcelFieldDataType.STRING),
	cmndCccd(5,"CMND/CCCD",MapExcelFieldDataType.STRING),
	soDienThoaiKh(6,"Số điện thoại khách hàng",MapExcelFieldDataType.STRING),
	ngayCapCmndCccd(7,"Ngày cấp CMND/CCCD",MapExcelFieldDataType.DATE),
	noiCapCmndCccd(8,"Nơi cấp CMND/CCCD",MapExcelFieldDataType.STRING),
	tenKhachHang(9,"Tên khách hàng",MapExcelFieldDataType.STRING),
	ngaySinh(10,"Ngày sinh",MapExcelFieldDataType.DATE),
	gioiTinh(11,"Giới tính",MapExcelFieldDataType.STRING),
	tenCongTy(12,"Tên công ty",MapExcelFieldDataType.STRING),
	diaChiCongTy(13,"Địa chỉ công ty",MapExcelFieldDataType.STRING),
	dienThoaiCongTy(14,"Điện thoại công ty",MapExcelFieldDataType.STRING),
	ngheNghiep(15,"Nghề nghiệp",MapExcelFieldDataType.STRING),
	nganhNghe(16,"Ngành nghề",MapExcelFieldDataType.STRING),
	thoiGianCongTac(17,"Thời gian công tác",MapExcelFieldDataType.STRING),
	chucVu(18,"Chức vụ",MapExcelFieldDataType.STRING),
	tongThuNhap(19,"Tổng thu nhập",MapExcelFieldDataType.INTEGER),
	hinhThucNhanLuong(20,"Hình thức nhận lương",MapExcelFieldDataType.STRING),
	ngayNhanLuong(21,"Ngày nhận lương",MapExcelFieldDataType.STRING),
	mucDichVay(22,"Mục đích vay",MapExcelFieldDataType.STRING),
	nguonThuNhap(23,"Nguồn thu nhập",MapExcelFieldDataType.STRING),
	tongStQuaHan(24,"Tổng số tiền quá hạn",MapExcelFieldDataType.INTEGER),
	duNoGocBanDau(25,"Dư nợ gốc ban đầu",MapExcelFieldDataType.INTEGER),
	phiBaoHiem(26,"Phí bảo hiểm",MapExcelFieldDataType.INTEGER),
	stDuocBaoHiem(27,"Số tiền được bảo hiểm",MapExcelFieldDataType.INTEGER),
	ngayGiaiNgan(28,"Ngày giải ngân",MapExcelFieldDataType.DATE),
	ngayDaoHan(29,"Ngày đáo hạn",MapExcelFieldDataType.DATE),
	ngayTraNoDauTien(30,"Ngày trả nợ đầu tiên",MapExcelFieldDataType.DATE),
	duNoGocConLai(31,"Dư nợ gốc còn lại",MapExcelFieldDataType.INTEGER),
	gocQuaHan(32,"Gốc Quá hạn",MapExcelFieldDataType.INTEGER),
	laiQuaHan(33,"Lãi quá hạn",MapExcelFieldDataType.INTEGER),
	laiPhat(34,"Lãi phạt",MapExcelFieldDataType.INTEGER),
	dpdHienTai(35,"DPD hiện tại",MapExcelFieldDataType.INTEGER),
	dpdNgayBanGiao(36,"DPD theo ngày bàn giao",MapExcelFieldDataType.INTEGER),
	kyTraNoHienTai(37,"Kỳ trả nợ hiện tại",MapExcelFieldDataType.INTEGER),
	gocDaTra(38,"Gốc đã trả",MapExcelFieldDataType.INTEGER),
	laiDaTra(39,"Lãi đã trả",MapExcelFieldDataType.INTEGER),
	laiPhatDaTra(40,"Lãi phạt đã trả",MapExcelFieldDataType.INTEGER),
	ngayTraNoKyTiepTheo(41,"Ngày trả nợ kỳ tiếp theo",MapExcelFieldDataType.DATE),
	kyTraNoTiepTheo(42,"Kỳ trả nợ tiếp theo",MapExcelFieldDataType.INTEGER),
	emi(43,"EMI",MapExcelFieldDataType.INTEGER),
	tongStDaThu(44,"Tổng số tiền đã thu",MapExcelFieldDataType.INTEGER),
	ngayTraNoGanNhat(45,"Ngày trả nợ gần nhất",MapExcelFieldDataType.DATE),
	maSanPham(46,"Mã sản phẩm",MapExcelFieldDataType.STRING),
	nhomSanPham(47,"Nhóm sản phẩm",MapExcelFieldDataType.STRING),
	tenChiNhanPhatVay(48,"Tên chi nhánh phát vay",MapExcelFieldDataType.STRING),
	tkTienIch(49,"TK tiện ích",MapExcelFieldDataType.STRING),
	lsTacDongGanNhat(50,"Lịch sử tác động gần nhất",MapExcelFieldDataType.STRING),
	chiTietLsTacDong(51,"Chi tiết lịch sửa tác động",MapExcelFieldDataType.STRING),
	phuongTamTru(52,"Phường tạm trú",MapExcelFieldDataType.STRING),
	quanTamTru(53,"Quận tạm trú",MapExcelFieldDataType.STRING),
	tinhTamTru(54,"Tỉnh tạm trú",MapExcelFieldDataType.STRING),
	phuongThuongTru(55,"Phường thường trú",MapExcelFieldDataType.STRING),
	quanThuongTru(56,"Quận thuong trú",MapExcelFieldDataType.STRING),
	tinhThuongTru(57,"Tỉnh thường trú",MapExcelFieldDataType.STRING),
	moiQhVoiKhachHang1(58,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING),
	hoVaTen1(59,"Họ và tên",MapExcelFieldDataType.STRING),
	soDienThoai1(60,"Số điện thoại",MapExcelFieldDataType.STRING),
	moiQhVoiKhachHang2(61,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING),
	hoVaTen2(62,"Họ và tên",MapExcelFieldDataType.STRING),
	soDienThoai2(63,"Số điện thoại",MapExcelFieldDataType.STRING),
	moiQhVoiKhachHang3(64,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING),
	hoVaTen3(65,"Họ và tên",MapExcelFieldDataType.STRING),
	soDienThoai3(66,"Số điện thoại",MapExcelFieldDataType.STRING),
	moiQhVoiKhachHang4(67,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING),
	hoVaTen4(68,"Họ và tên",MapExcelFieldDataType.STRING),
	soDienThoai4(69,"Số điện thoại",MapExcelFieldDataType.STRING),
	nhomDpd(70,"Nhóm DPD",MapExcelFieldDataType.STRING),
	nhanVienPhuTrach(71,"Nhân viên phụ trách",MapExcelFieldDataType.STRING),
	team(72,"Team",MapExcelFieldDataType.STRING),
	doiTac(73,"Đối tác",MapExcelFieldDataType.STRING),
	stDuThuTruoc15Ngay(74,"Số tiền dự thu trước ngày 15",MapExcelFieldDataType.INTEGER),
	ngayDuThu1(75,"Ngày dự thu",MapExcelFieldDataType.DATESTR),
	stDuThuSau15Ngay(76,"Số tuền dự thu sau ngày 15",MapExcelFieldDataType.INTEGER),
	ngayDuThu2(77,"Ngày dự thu 2",MapExcelFieldDataType.DATESTR)
	;
	
	// 

	private int index;
    private String value;
    private MapExcelFieldDataType dataType;


    public int getIndex() {
        return index;
    }
    public String getValue() {
        return value;
    }
    public MapExcelFieldDataType getDataType() {
		return dataType;
	}

	MapExcelFieldCusLd(int index, String value,MapExcelFieldDataType dataType) {
        this.index = index;
        this.value = value;
        this.dataType = dataType;
	}

	public static MapExcelFieldCusLd valueOfIndex(int index) {
		for (MapExcelFieldCusLd e : values()) {
	        if (e.index == index) {
	            return e;
	        }
	    }
	    return null;
	}
}
