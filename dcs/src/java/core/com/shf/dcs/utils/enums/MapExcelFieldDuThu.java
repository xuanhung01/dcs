package com.shf.dcs.utils.enums;

public enum MapExcelFieldDuThu {
	
	// Du Thu
	cif(0,"Cif",MapExcelFieldDataType.STRING),
	soHopDong(1,"Số hợp đồng",MapExcelFieldDataType.STRING),
	tenKhachHang(2,"Tên khách hàng",MapExcelFieldDataType.STRING),
	nhanvVien(3,"Nhân viên",MapExcelFieldDataType.STRING),
	phongBan(4,"Phòng ban",MapExcelFieldDataType.STRING),
	duThu(5,"Dự thu",MapExcelFieldDataType.INTEGER),
	ngayDuThu(6,"Ngày dự thu",MapExcelFieldDataType.DATESTR),
	dpd(7,"Dpd",MapExcelFieldDataType.STRING),
	tuan(8,"Tuần",MapExcelFieldDataType.STRING),
	tyLeDuThu(9,"Tỷ lệ dự thu",MapExcelFieldDataType.STRING),
	lsTacDongGanNhat(10,"Lịch sử tác động gần nhất",MapExcelFieldDataType.STRING),
	ngheNghiep(11,"Nghề nghiệp",MapExcelFieldDataType.STRING),
	tongDNKhoanvay(12,"Tổng dư nợ khoản vay",MapExcelFieldDataType.INTEGER),
	tinhThuongTru(13,"Tỉnh thường trú",MapExcelFieldDataType.STRING),
	giaiPhapBackup(14,"Giải pháp backup",MapExcelFieldDataType.STRING),
	dsGiuCase(15,"DS giữ case",MapExcelFieldDataType.STRING),
	soTienThu(16,"Số tiền thu",MapExcelFieldDataType.INTEGER)
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

	MapExcelFieldDuThu(int index, String value,MapExcelFieldDataType dataType) {
        this.index = index;
        this.value = value;
        this.dataType = dataType;
	}

	public static MapExcelFieldDuThu valueOfIndex(int index) {
		for (MapExcelFieldDuThu e : values()) {
	        if (e.index == index) {
	            return e;
	        }
	    }
	    return null;
	}
}
