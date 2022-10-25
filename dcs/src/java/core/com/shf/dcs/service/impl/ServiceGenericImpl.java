package com.shf.dcs.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.metadata.ConstraintDescriptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shf.dcs.dao.DebtUploadCusLdDAO;
import com.shf.dcs.dao.DebtUploadDuThuDAO;
import com.shf.dcs.dao.DebtUploadHdrDAO;
import com.shf.dcs.dao.DebtUploadSoThuDAO;
import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.model.DebtUploadHdr;
import com.shf.dcs.utils.DateUtilDcs;
import com.shf.dcs.utils.enums.MapExcelFieldCusLd;
import com.shf.dcs.utils.enums.MapExcelFieldDataType;
import com.shf.dcs.utils.enums.MapHdrError;

@Service
@Transactional(rollbackOn = Exception.class)
public class ServiceGenericImpl<T extends Object> {
	
	private static Logger logger = Logger.getLogger(ServiceGenericImpl.class);

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected DebtUploadCusLdDAO debtUploadCusLdDAO;

	@Autowired
	protected Validator validator;
	
	@Autowired
	DebtUploadHdrDAO debtUploadHdrDAO;
	
	@Autowired
	DebtUploadSoThuDAO debtUploadSoThuDAO;
	
	@Autowired
	DebtUploadDuThuDAO debtUploadDuThuDAO;
	
	public DebtUploadHdr saveDebtUploadFile(AdminUploadDto adminUploadDto) {
		DebtUploadHdr debtUploadHdr = new DebtUploadHdr();
		debtUploadHdr.setCreateBy(adminUploadDto.getUserNameUpload());
		debtUploadHdr.setCreateDate(new Date());
		debtUploadHdr.setFileName(adminUploadDto.getFile1().getOriginalFilename());
		debtUploadHdr.setFileSize(BigDecimal.valueOf(adminUploadDto.getFile1().getSize()));
		debtUploadHdr.setErrorCode(MapHdrError.ERROR_CODE_00.getErrorCode());
		debtUploadHdr.setErrorMsg(MapHdrError.ERROR_CODE_00.getErrorMsg());
		debtUploadHdr.setFileType(adminUploadDto.getFileType());
		debtUploadHdr.setDescription(adminUploadDto.getDescription());
		debtUploadHdr.setFileRowTotal(adminUploadDto.getFileRowSuccess());
		debtUploadHdr.setSysRunDate(new Date());
		debtUploadHdr = debtUploadHdrDAO.save(debtUploadHdr);
		return debtUploadHdr;
	}
}
