package com.shf.dcs.service;

import java.util.List;

import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.model.DebtUploadHdr;

public interface IUploadCustomerLdService {
	public void save(AdminUploadDto dto) throws Exception;
}
