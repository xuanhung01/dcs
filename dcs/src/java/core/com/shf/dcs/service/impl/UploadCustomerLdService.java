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
import javax.validation.constraints.Size;
import javax.validation.metadata.ConstraintDescriptor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.dto.DebtUploadCusLdDto;
import com.shf.dcs.error.UploadException;
import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.model.DebtUploadHdr;
import com.shf.dcs.service.IUploadCustomerLdService;
import com.shf.dcs.utils.Constants;
import com.shf.dcs.utils.DateUtilDcs;
import com.shf.dcs.utils.enums.MapExcelFieldCusLd;
import com.shf.dcs.utils.enums.MapExcelFieldDataType;
import com.shf.dcs.utils.enums.MapHdrFile;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

@Service
@Transactional(rollbackOn = Exception.class)
public class UploadCustomerLdService extends ServiceGenericImpl<DebtUploadCusLd> implements IUploadCustomerLdService {
	private static Logger logger = Logger.getLogger(UploadCustomerLdService.class);

	@Override
	public void save(AdminUploadDto dto) throws UploadException, Exception {
		List<DebtUploadCusLd> listDebtUploadCusLd = new ArrayList<DebtUploadCusLd>();
		Integer rowNum = 0;
		// valid file
		try {
			Workbook workbook = WorkbookFactory.create(dto.getFile1().getInputStream());
			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet.getLastRowNum() > Constants.MAX_NUMBER_UPLOAD) {
				throw new Exception("File upload không quá " + Constants.MAX_NUMBER_UPLOAD + " dòng");
			}
			// save header
			dto.setFileType(MapHdrFile.FILE_CUS_LD.name());
			dto.setDescription(MapHdrFile.FILE_CUS_LD.getFileName());
			dto.setFileRowSuccess(BigDecimal.valueOf(sheet.getLastRowNum()));
			DebtUploadHdr debtUploadHdr = saveDebtUploadFile(dto);
			//
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				rowNum++;
				Row row = rowIterator.next();
				// break header
				if (rowNum == 1) {
					if (row.getLastCellNum() != 78) {
						throw new Exception("File upload không đúng 78 cột");
					}
					continue;
				}
				DebtUploadCusLd debtUploadCusLd = new DebtUploadCusLd();
				Iterator<Cell> cellIterator = row.cellIterator();
				// map từ cell sang object + valid
				mapCellToObj(debtUploadCusLd,cellIterator, rowNum,Constants.MAP_EXCEL_FIELD_CUS_LD);
				// save
				listDebtUploadCusLd.add(debtUploadCusLd);
				logger.info("SOHOPDONG========" + debtUploadCusLd.getSoHopDong());
				// set 1 số giá trị
				debtUploadCusLd.setSysRunDate(new Date());
				debtUploadCusLd.setUsernameUpload(dto.getUserNameUpload());
				debtUploadCusLd.setUploadHdrId(debtUploadHdr.getId());
				// save
				debtUploadCusLdDAO.saveAndFlush(debtUploadCusLd);
			}
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
			throw e;
		}
		// Kiểm tra có dữ liệu upload không
		if (listDebtUploadCusLd == null || listDebtUploadCusLd.isEmpty()) {
			throw new Exception("File upload đang đang không có dữ liệu");
		}

		// debtUploadCusLdDAO.saveAll(listDebtUploadCusLd);
	}
}
