package com.shf.dcs.utils.enums;

public interface IMapExcelField {

	String getName();
	MapExcelFieldDataType getDataType();
	String getValue();
	MapExcelFieldDataType getNotNull();
	Integer getMaxLength();
}
