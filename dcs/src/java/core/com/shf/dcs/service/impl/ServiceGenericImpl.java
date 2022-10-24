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
import com.shf.dcs.error.UploadException;
import com.shf.dcs.utils.DateUtilDcs;
import com.shf.dcs.utils.MapExcelFieldCusLd;
import com.shf.dcs.utils.MapExcelFieldDataType;

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

	@SuppressWarnings({ "unchecked" })
	protected List<ObjectError> validAnotationEntity(Integer rowNum, Set<ConstraintViolation<T>> violations) {
		List<ObjectError> listErrors = new ArrayList<ObjectError>();
		String columnName = "";
		for (ConstraintViolation<?> violation : violations) {
			// kiểm tra lỗi thuộc anatation type nào
			columnName = MapExcelFieldCusLd.valueOf(violation.getPropertyPath().toString()).getValue();
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
			// không cho vượt quá 5 lỗi
			if (listErrors.size() == 1)
				break;
		}
		return listErrors;
	}
	
	protected T mapCellToObj(T t, Iterator<Cell> cellIterator, Integer rowNum) throws Exception{
		DataFormatter dataFormatter = new DataFormatter();
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
			MapExcelFieldCusLd mapExcelFieldCusLd = MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex());
			if(mapExcelFieldCusLd == null) {
				continue;
			}
			Field field = t.getClass().getDeclaredField(mapExcelFieldCusLd.name());
			field.setAccessible(true);
			// Nếu INTEGER
			if(MapExcelFieldDataType.INTEGER.compareTo(MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng NUMBER
				if (!NumberUtils.isNumber(tempStrCell)) {
					String[] params = new String[] { rowNum.toString(), MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex()).getValue() };
					String message = messageSource.getMessage("upload.notNumber.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(t, new BigDecimal(tempStrCell));
			}
			// Nếu DATE
			if(MapExcelFieldDataType.DATE.compareTo(MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng NUMBER
				Date dateCell = DateUtilDcs.getDateCellValue(cell);
				if (dateCell == null) {
					String[] params = new String[] { rowNum.toString(), MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex()).getValue() };
					String message = messageSource.getMessage("upload.notDate.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(t, dateCell);
			}
			// Nếu DATESTR
			if(MapExcelFieldDataType.DATESTR.compareTo(MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng String Date dd/MM/yyyy
				Date dateCell = DateUtilDcs.convertStringToDate(tempStrCell);
				if (dateCell == null) {
					String[] params = new String[] { rowNum.toString(), MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex()).getValue() };
					String message = messageSource.getMessage("upload.notDate.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(t, dateCell);
			}
			// Nếu STRING
			if(MapExcelFieldDataType.STRING.compareTo(MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex()).getDataType()) == 0 ) {
				// check định dạng STRING
				field.set(t, tempStrCell);
			}
		}		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(t);
		logger.info(jsonString);
		return t;
	}
}
