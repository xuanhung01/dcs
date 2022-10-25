package com.shf.dcs.utils.enums;

public enum MapExcelFieldSoThu implements IMapExcelField{
	
	// So Thu
	ngayBanGiao(0,"Ngày bàn giao",MapExcelFieldDataType.DATE,MapExcelFieldDataType.NOTNULL,null),
	cif(1,"Cif",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	tenKhachHang(2,"Tên khách hàng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,150),
	soHopDong(3,"Số hợp đồng",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,50),
	soNgayQhBanGiao(4,"Số ngày quá hạn bàn giao",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,50),
	nhomDpd(5,"Nhóm Dpd",MapExcelFieldDataType.STRING,MapExcelFieldDataType.NOTNULL,10),
	soThuNhGhiNhan(6,"Số thu ngân hàng ghi nhận",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null),
	ngayGhiNhan(7,"Ngày ghi nhận",MapExcelFieldDataType.INTEGER,MapExcelFieldDataType.NOTNULL,null)
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

	MapExcelFieldSoThu(int index, String value,MapExcelFieldDataType dataType,
			MapExcelFieldDataType notNull,Integer maxLeng) {
        this.index = index;
        this.value = value;
        this.dataType = dataType;
        this.notNull = notNull;
        this.maxLeng = maxLeng;
	}

	public static MapExcelFieldSoThu valueOfIndex(int index) {
		for (MapExcelFieldSoThu e : values()) {
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
