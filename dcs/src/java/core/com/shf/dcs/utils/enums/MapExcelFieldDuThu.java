package com.shf.dcs.utils.enums;

public enum MapExcelFieldDuThu implements IMapExcelField{
	
	// Du Thu
	cif(0,"Cif",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	soHopDong(1,"Số hợp đồng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	tenKhachHang(2,"Tên khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	nhanvien(3,"Nhân viên",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,70),
	phongBan(4,"Phòng ban",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,70),
	duThu(5,"Dự thu",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	ngayDuThu(6,"Ngày dự thu",MapExcelFieldDataType.DATESTR,MapExcelFieldDataType.NOTNULL,null),
	dpd(7,"Dpd",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,30),
	tuan(8,"Tuần",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,30),
	tyLeDuThu(9,"Tỷ lệ dự thu",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,30),
	lsTacDongGanNhat(10,"Lịch sử tác động gần nhất",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,50),
	ngheNghiep(11,"Nghề nghiệp",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,400),
	tongDnKhoanVay(12,"Tổng dư nợ khoản vay",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	tinhThuongTru(13,"Tỉnh thường trú",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,70),
	giaiPhapBackup(14,"Giải pháp backup",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,400),
	dsGiuCase(15,"DS giữ case",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NULL,70),
	soTienThu(16,"Số tiền thu",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NULL,null),
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

	MapExcelFieldDuThu(int index, String value,MapExcelFieldDataType dataType,
			MapExcelFieldDataType notNull,Integer maxLeng) {
        this.index = index;
        this.value = value;
        this.dataType = dataType;
        this.notNull = notNull;
        this.maxLeng = maxLeng;
	}

	public static MapExcelFieldDuThu valueOfIndex(int index) {
		for (MapExcelFieldDuThu e : values()) {
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
