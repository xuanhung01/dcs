package com.shf.dcs.service;

import java.util.List;

import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.model.DebtUploadHdr;

public interface IUploadDuThuService {
	public void save(AdminUploadDto dto) throws Exception;
}
