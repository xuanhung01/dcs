package com.shf.dcs.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.shf.dcs.dao.DebtUploadCusLdDAO;
import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.dto.DebtUploadCusLdDto;
import com.shf.dcs.error.UploadException;
import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.service.IUploadCustomerLdService;
import com.shf.dcs.utils.Constants;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.apache.log4j.Logger;

@Service
@Transactional(rollbackOn = Exception.class)
public class UploadCustomerLdService extends ServiceGenericImpl<DebtUploadCusLd> implements IUploadCustomerLdService{
	private static Logger logger = Logger.getLogger(UploadCustomerLdService.class);

	@Override
	public void save(AdminUploadDto dto) throws UploadException,Exception {
		List<DebtUploadCusLdDto> listUploadResultDebtHomeDto = new ArrayList<DebtUploadCusLdDto>();
		DataFormatter dataFormatter = new DataFormatter();
		Integer rowNum = 0;
		String tempStrCell = "";
		// valid file
		try {
			Workbook workbook = WorkbookFactory.create(dto.getFile1().getInputStream());
			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getLastRowNum() > Constants.MAX_NUMBER_UPLOAD) {
				throw new Exception("File upload không quá "+Constants.MAX_NUMBER_UPLOAD+" dòng");
			}
			dto.setFileRowSuccess(BigDecimal.valueOf(sheet.getLastRowNum()));
			//
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				tempStrCell = "";
				rowNum++;
				Row row = rowIterator.next();
				// break header
				if (rowNum == 1) {
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				DebtUploadCusLdDto debtUploadCusLdDto = new DebtUploadCusLdDto();
				DebtUploadCusLd debtUploadCusLd = new DebtUploadCusLd();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					tempStrCell = dataFormatter.formatCellValue(cell);
					// 
					if(StringUtils.isNotEmpty(tempStrCell)) {
						tempStrCell = tempStrCell.trim();
					}
					// Số hợp đồng
					if (cell.getColumnIndex() == 0) {
	                	debtUploadCusLdDto.setSoHopDong("12345678901234");
					} 
					debtUploadCusLdDto.setChiTietLsTacDong(null);
					BeanUtils.copyProperties(debtUploadCusLdDto, debtUploadCusLd);
					Set<ConstraintViolation<DebtUploadCusLd>> violations = validator.validate(debtUploadCusLd);
					if(!violations.isEmpty()) {
						throw new UploadException (getMessage(rowNum, violations));
					}
					
					debtUploadCusLdDAO.save(debtUploadCusLd);
				}
			}
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
			throw e;
		}
		if (listUploadResultDebtHomeDto == null || listUploadResultDebtHomeDto.isEmpty()) {
			throw new Exception("File upload đang đang không có dữ liệu");
		}
	}

}
