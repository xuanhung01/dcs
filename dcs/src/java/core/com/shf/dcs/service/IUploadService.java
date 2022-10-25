package com.shf.dcs.service;

import java.math.BigDecimal;
import java.util.List;
import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.model.DebtUploadDuThu;
import com.shf.dcs.model.DebtUploadHdr;
import com.shf.dcs.model.DebtUploadSoThu;

public interface IUploadService {
	public List<DebtUploadHdr> getListFileByType(String fileType) throws Exception;
	
	public List<DebtUploadCusLd> getListCustomerLdFailById(BigDecimal uploadHdrId) throws Exception;
	
	public List<DebtUploadDuThu> getListDuThuFailById(BigDecimal uploadHdrId) throws Exception;
	
	public List<DebtUploadSoThu> getListSoThuFailById(BigDecimal uploadHdrId) throws Exception;
}
