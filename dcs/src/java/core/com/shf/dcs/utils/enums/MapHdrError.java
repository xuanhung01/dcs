package com.shf.dcs.utils.enums;

import java.math.BigDecimal;

public enum MapHdrError {
	
	ERROR_CODE_00(new BigDecimal(00),"Ghi nhận dữ liệu tạm thành công"),
	ERROR_CODE_10(new BigDecimal(10),"Cập nhật dữ liệu thành công");

	BigDecimal errorCode;
	String errorMsg;
	
	MapHdrError(BigDecimal errorCode,String errorName) {
		this.errorCode = errorCode;
		this.errorMsg = errorName;
	}

	public BigDecimal getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
