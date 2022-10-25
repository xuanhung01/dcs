package com.shf.dcs.utils.enums;

public enum MapHdrFile {

	FILE_CUS_LD("File Khách hàng hợp đồng"),
	FILE_SO_THU("File số thu"),
	FILE_DU_THU("File dự thu");

	String fileName;
	
	MapHdrFile(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
}
