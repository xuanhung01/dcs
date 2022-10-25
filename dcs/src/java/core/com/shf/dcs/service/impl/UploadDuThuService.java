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
import com.shf.dcs.model.DebtUploadDuThu;
import com.shf.dcs.model.DebtUploadHdr;
import com.shf.dcs.service.IUploadCustomerLdService;
import com.shf.dcs.service.IUploadDuThuService;
import com.shf.dcs.utils.Constants;
import com.shf.dcs.utils.DateUtilDcs;
import com.shf.dcs.utils.enums.MapExcelFieldCusLd;
import com.shf.dcs.utils.enums.MapExcelFieldDataType;
import com.shf.dcs.utils.enums.MapExcelFieldDuThu;
import com.shf.dcs.utils.enums.MapHdrFile;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

@Service
@Transactional(rollbackOn = Exception.class)
public class UploadDuThuService extends ServiceGenericImpl<DebtUploadDuThu> implements IUploadDuThuService {
	private static Logger logger = Logger.getLogger(UploadDuThuService.class);

	@Override
	public void save(AdminUploadDto dto) throws UploadException, Exception {
		List<DebtUploadDuThu> listDebtUploadDuThu = new ArrayList<DebtUploadDuThu>();
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
					continue;
				}
				DebtUploadDuThu debtUploadDuThu = new DebtUploadDuThu();
				Iterator<Cell> cellIterator = row.cellIterator();
				// map từ cell sang object
				debtUploadDuThu = mapCellToObj(cellIterator, rowNum);
				// valid NOTNULL, SIZE
				Set<ConstraintViolation<DebtUploadDuThu>> violations = validator.validate(debtUploadDuThu);
				// Nếu xảy ra lỗi ghi ra màn hình
				if (!violations.isEmpty()) {
					throw new UploadException(validAnotationEntity(rowNum, violations));
				}
				// save
				listDebtUploadDuThu.add(debtUploadDuThu);
				logger.info("SOHOPDONG========" + debtUploadDuThu.getSoHopDong());
				// set 1 số giá trị
				debtUploadDuThu.setSysRunDate(new Date());
				debtUploadDuThu.setUsernameUpload(dto.getUserNameUpload());
				debtUploadDuThu.setUploadHdrId(debtUploadHdr.getId());
				// save
				debtUploadDuThuDAO.saveAndFlush(debtUploadDuThu);
			}
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
			throw e;
		}
		// Kiểm tra có dữ liệu upload không
		if (listDebtUploadDuThu == null || listDebtUploadDuThu.isEmpty()) {
			throw new Exception("File upload đang đang không có dữ liệu");
		}
		// debtUploadCusLdDAO.saveAll(listDebtUploadCusLd);
	}
	
	@SuppressWarnings({ "unchecked" })
	private List<ObjectError> validAnotationEntity(Integer rowNum, Set<ConstraintViolation<DebtUploadDuThu>> violations) {
		List<ObjectError> listErrors = new ArrayList<ObjectError>();
		String columnName = "";
		for (ConstraintViolation<?> violation : violations) {
			// kiểm tra lỗi thuộc anatation type nào
			columnName = MapExcelFieldDuThu.valueOf(violation.getPropertyPath().toString()).getValue();
			// NotNull
			if (violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName()
					.equals("NotNull")) {
				String[] params = new String[] { rowNum.toString(), columnName };
				String message = messageSource.getMessage("upload.notNull.message", params,
						LocaleContextHolder.getLocale());
				listErrors.add(new ObjectError("NotNull", message));
			}
			// SIZE
			if (violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName().equals("Size")) {
				long maxLength = 0;
				// Size
				if (Size.class.equals(violation.getConstraintDescriptor().getAnnotation().annotationType())) {
					ConstraintDescriptor<Size> sizeConstraint = (ConstraintDescriptor<Size>) violation
							.getConstraintDescriptor();
					maxLength = sizeConstraint.getAnnotation().max();
				}

				String[] params = new String[] { rowNum.toString(), columnName, String.valueOf(maxLength) };
				String message = messageSource.getMessage("upload.max.message", params,
						LocaleContextHolder.getLocale());
				listErrors.add(new ObjectError("Max", message));
			}
			// không cho vượt quá 2 lỗi
			if (listErrors.size() == 2)
				break;
		}
		return listErrors;
	}
	
	@SuppressWarnings("static-access")
	protected DebtUploadDuThu mapCellToObj(Iterator<Cell> cellIterator, Integer rowNum) throws Exception{
		DataFormatter dataFormatter = new DataFormatter();
		DebtUploadDuThu debtUploadDuThu = new DebtUploadDuThu();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String tempStrCell = dataFormatter.formatCellValue(cell);
			// 
			if(StringUtils.isNotEmpty(tempStrCell)) {
				tempStrCell = tempStrCell.trim();
			} else {
				continue;
			}
			// set value column
			MapExcelFieldDuThu mapExcelFieldDuThu = MapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex());
			if(mapExcelFieldDuThu == null) {
				continue;
			}
			Field field = mapExcelFieldDuThu.getClass().getDeclaredField(mapExcelFieldDuThu.name());
			field.setAccessible(true);
			// Nếu INTEGER
			if(MapExcelFieldDataType.INTEGER.compareTo(mapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng NUMBER
				if (!NumberUtils.isNumber(tempStrCell)) {
					String[] params = new String[] { rowNum.toString(), mapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex()).getValue() };
					String message = messageSource.getMessage("upload.notNumber.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(debtUploadDuThu, new BigDecimal(tempStrCell));
			}
			// Nếu DATE
			if(MapExcelFieldDataType.DATE.compareTo(mapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng NUMBER
				Date dateCell = DateUtilDcs.getDateCellValue(cell);
				if (dateCell == null) {
					String[] params = new String[] { rowNum.toString(), mapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex()).getValue() };
					String message = messageSource.getMessage("upload.notDate.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(debtUploadDuThu, dateCell);
			}
			// Nếu DATESTR
			if(MapExcelFieldDataType.DATESTR.compareTo(mapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng String Date dd/MM/yyyy
				Date dateCell = DateUtilDcs.convertStringToDate(tempStrCell);
				if (dateCell == null) {
					String[] params = new String[] { rowNum.toString(), mapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex()).getValue() };
					String message = messageSource.getMessage("upload.notDate.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(debtUploadDuThu, dateCell);
			}
			// Nếu STRING
			if(MapExcelFieldDataType.STRING.compareTo(mapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng STRING
				field.set(debtUploadDuThu, tempStrCell);
			}
		}		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(debtUploadDuThu);
		logger.info(jsonString);
		return debtUploadDuThu;
	}

}
