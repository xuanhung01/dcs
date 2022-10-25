package com.shf.dcs.utils.enums;

public enum MapExcelFieldCusLd implements IMapExcelField{
	
	// Customer-LD
	soHopDong(0,"Số hợp đồng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,20),
	user1(1,"User",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	cif(2,"Cif",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	diaChiThuongTru(3,"Địa chỉ thường trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,400),
	diaChiTamTru(4,"Địa chỉ tạm trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,400),
	cmndCccd(5,"CMND/CCCD",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,40),
	soDienThoaiKh(6,"Số điện thoại khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,20),
	ngayCapCmndCccd(7,"Ngày cấp CMND/CCCD",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NOTNULL,null),
	noiCapCmndCccd(8,"Nơi cấp CMND/CCCD",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,400),
	tenKhachHang(9,"Tên khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,200),
	ngaySinh(10,"Ngày sinh",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NOTNULL,null),
	gioiTinh(11,"Giới tính",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,10),
	tenCongTy(12,"Tên công ty",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,400),
	diaChiCongTy(13,"Địa chỉ công ty",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,400),
	dienThoaiCongTy(14,"Điện thoại công ty",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,20),
	ngheNghiep(15,"Nghề nghiệp",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	nganhNghe(16,"Ngành nghề",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	thoiGianCongTac(17,"Thời gian công tác",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,15),
	chucVu(18,"Chức vụ",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,70),
	tongThuNhap(19,"Tổng thu nhập",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	hinhThucNhanLuong(20,"Hình thức nhận lương",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	ngayNhanLuong(21,"Ngày nhận lương",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	mucDichVay(22,"Mục đích vay",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	nguonThuNhap(23,"Nguồn thu nhập",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	tongStQuaHan(24,"Tổng số tiền quá hạn",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	duNoGocBanDau(25,"Dư nợ gốc ban đầu",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	phiBaoHiem(26,"Phí bảo hiểm",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	stDuocBaoHiem(27,"Số tiền được bảo hiểm",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	ngayGiaiNgan(28,"Ngày giải ngân",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NOTNULL,null),
	ngayDaoHan(29,"Ngày đáo hạn",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NOTNULL,null),
	ngayTraNoDauTien(30,"Ngày trả nợ đầu tiên",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NULL,null),
	duNoGocConLai(31,"Dư nợ gốc còn lại",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	gocQuaHan(32,"Gốc Quá hạn",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	laiQuaHan(33,"Lãi quá hạn",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	laiPhat(34,"Lãi phạt",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	dpdHienTai(35,"DPD hiện tại",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	dpdNgayBanGiao(36,"DPD theo ngày bàn giao",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	kyTraNoHienTai(37,"Kỳ trả nợ hiện tại",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	gocDaTra(38,"Gốc đã trả",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	laiDaTra(39,"Lãi đã trả",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	laiPhatDaTra(40,"Lãi phạt đã trả",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	ngayTraNoKyTiepTheo(41,"Ngày trả nợ kỳ tiếp theo",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NULL,null),
	kyTraNoTiepTheo(42,"Kỳ trả nợ tiếp theo",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	emi(43,"EMI",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	tongStDaThu(44,"Tổng số tiền đã thu",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	ngayTraNoGanNhat(45,"Ngày trả nợ gần nhất",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NULL,null),
	maSanPham(46,"Mã sản phẩm",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	nhomSanPham(47,"Nhóm sản phẩm",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	tenChiNhanPhatVay(48,"Tên chi nhánh phát vay",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	tkTienIch(49,"TK tiện ích",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,50),
	lsTacDongGanNhat(50,"Lịch sử tác động gần nhất",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,50),
	chiTietLsTacDong(51,"Chi tiết lịch sửa tác động",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,400),
	phuongTamTru(52,"Phường tạm trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	quanTamTru(53,"Quận tạm trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	tinhTamTru(54,"Tỉnh tạm trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	phuongThuongTru(55,"Phường thường trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	quanThuongTru(56,"Quận thuong trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	tinhThuongTru(57,"Tỉnh thường trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	moiQhVoiKhachHang1(58,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	hoVaTen1(59,"Họ và tên",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	soDienThoai1(60,"Số điện thoại",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	moiQhVoiKhachHang2(61,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	hoVaTen2(62,"Họ và tên",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	soDienThoai2(63,"Số điện thoại",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	moiQhVoiKhachHang3(64,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	hoVaTen3(65,"Họ và tên",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	soDienThoai3(66,"Số điện thoại",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	moiQhVoiKhachHang4(67,"Mối quan hệ với khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	hoVaTen4(68,"Họ và tên",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	soDienThoai4(69,"Số điện thoại",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	nhomDpd(70,"Nhóm DPD",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,10),
	nhanVienPhuTrach(71,"Nhân viên phụ trách",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,150),
	team(72,"Team",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,70),
	doiTac(73,"Đối tác",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,70),
	stDuThuTruoc15Ngay(74,"Số tiền dự thu trước ngày 15",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	ngayDuThu1(75,"Ngày dự thu",MapExcelFieldDataType.DATESTR,MapExcelFieldDataType.NULL,null),
	stDuThuSau15Ngay(76,"Số tuền dự thu sau ngày 15",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
	ngayDuThu2(77,"Ngày dự thu 2",MapExcelFieldDataType.DATESTR,MapExcelFieldDataType.NULL,null)
	;
	
	// 

	private int index;
    private String value;
    private MapExcelFieldDataType dataType;
    private MapExcelFieldDataType notNull;
    private Integer maxLeng;


    public int getIndex() {
        return index;
    }

	MapExcelFieldCusLd(int index, String value,MapExcelFieldDataType dataType,
			MapExcelFieldDataType notNull,Integer maxLeng) {
        this.index = index;
        this.value = value;
        this.dataType = dataType;
        this.notNull = notNull;
        this.maxLeng = maxLeng;
	}

	public static MapExcelFieldCusLd valueOfIndex(int index) {
		for (MapExcelFieldCusLd e : values()) {
	        if (e.index == index) {
	            return e;
	        }
	    }
	    return null;
	}
	
	@Override
	public String getName() {
		return super.name();
	}

	@Override
	public MapExcelFieldDataType getDataType() {
		return dataType;
	}

	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public MapExcelFieldDataType getNotNull() {
		return notNull;
	}
	
	@Override
	public Integer getMaxLength() {
		return maxLeng;
	}
}
