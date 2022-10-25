package com.shf.dcs.utils.enums;

public enum MapExcelFieldSoThu {
	
	// So Thu
	ngayBanGiao(0,"Ngày bàn giao",MapExcelFieldDataType.DATE),
	cif(1,"Cif",MapExcelFieldDataType.STRING),
	tenKhachHang(2,"Tên khách hàng",MapExcelFieldDataType.STRING),
	soHopDong(3,"Số hợp đồng",MapExcelFieldDataType.STRING),
	soNgayQHBanGiao(4,"Số ngày quá hạn bàn giao",MapExcelFieldDataType.INTEGER),
	nhomDpd(5,"Nhóm Dpd",MapExcelFieldDataType.STRING),
	soThuNHGhiNhan(6,"Số thu ngân hàng ghi nhận",MapExcelFieldDataType.INTEGER),
	ngayGhiNhan(7,"Ngày ghi nhận",MapExcelFieldDataType.INTEGER)
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

	MapExcelFieldSoThu(int index, String value,MapExcelFieldDataType dataType) {
        this.index = index;
        this.value = value;
        this.dataType = dataType;
	}

	public static MapExcelFieldSoThu valueOfIndex(int index) {
		for (MapExcelFieldSoThu e : values()) {
	        if (e.index == index) {
	            return e;
	        }
	    }
	    return null;
	}
}
