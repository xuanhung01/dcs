package com.shf.dcs.service;

import java.math.BigDecimal;
import java.util.List;
import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.model.DebtUploadHdr;

public interface IUploadService {
	public List<DebtUploadHdr> getListFileByType(String fileType) throws Exception;
	
	public List<DebtUploadCusLd> getListCustomerLdFailById(BigDecimal uploadHdrId) throws Exception;
}
