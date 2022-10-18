package com.shf.dcs.service;

import java.math.BigDecimal;
import java.util.List;

import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.model.DebtUploadHdr;

public interface IUploadService {
	public List<DebtUploadHdr> getListFileRecase() throws Exception;
}
