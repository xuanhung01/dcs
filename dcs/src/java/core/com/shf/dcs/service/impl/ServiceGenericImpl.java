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
import javax.validation.Validator;
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
import com.shf.dcs.utils.Constants;
import com.shf.dcs.utils.DateUtilDcs;
import com.shf.dcs.utils.enums.IMapExcelField;
import com.shf.dcs.utils.enums.MapExcelFieldCusLd;
import com.shf.dcs.utils.enums.MapExcelFieldDataType;
import com.shf.dcs.utils.enums.MapExcelFieldDuThu;
import com.shf.dcs.utils.enums.MapExcelFieldSoThu;
import com.shf.dcs.utils.enums.MapHdrError;

@Service
@Transactional(rollbackOn = Exception.class)
public class ServiceGenericImpl<Entity extends Object> {
	
	private static Logger logger = Logger.getLogger(ServiceGenericImpl.class);

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected DebtUploadCusLdDAO debtUploadCusLdDAO;
	
	@Autowired
	DebtUploadHdrDAO debtUploadHdrDAO;
	
	@Autowired
	DebtUploadSoThuDAO debtUploadSoThuDAO;
	
	@Autowired
	DebtUploadDuThuDAO debtUploadDuThuDAO;
	
	protected void mapCellToObj(Entity entity,Iterator<Cell> cellIterator, Integer rowNum,String typeEnum) throws Exception{
		DataFormatter dataFormatter = new DataFormatter();
		
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			// Lấy giá trị của CELL
			String tempStrCell = dataFormatter.formatCellValue(cell);
			// Xác định loại ENUM
			IMapExcelField mapExcelField = null;
			if(typeEnum.equals(Constants.MAP_EXCEL_FIELD_CUS_LD)) {
				mapExcelField = MapExcelFieldCusLd.valueOfIndex(cell.getColumnIndex());
			} else if(typeEnum.equals(Constants.MAP_EXCEL_FIELD_DU_THU)) {
				mapExcelField = MapExcelFieldDuThu.valueOfIndex(cell.getColumnIndex());
			} else if(typeEnum.equals(Constants.MAP_EXCEL_FIELD_SO_THU)) {
				mapExcelField = MapExcelFieldSoThu.valueOfIndex(cell.getColumnIndex());
			}
			if(mapExcelField == null) {
				continue;
			}
			Field field = entity.getClass().getDeclaredField(mapExcelField.getName());
			field.setAccessible(true);
			MapExcelFieldDataType mapExcelFieldDataType = mapExcelField.getDataType();
			String mapExcelValue = mapExcelField.getValue();
			MapExcelFieldDataType isNotNull = mapExcelField.getNotNull();
			Integer maxLength = mapExcelField.getMaxLength();
			
			// 1. Check NOTNULL
			if(MapExcelFieldDataType.NOTNULL.compareTo(isNotNull) == 0 ) {
				if(StringUtils.isEmpty(tempStrCell)) {
					String[] params = new String[] { rowNum.toString() ,mapExcelValue };
					String message = messageSource.getMessage("upload.notNull", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
			} else { // Nếu cho NULL thì kiểm tra NULL continue
				if(StringUtils.isEmpty(tempStrCell)) {
					continue;
				}
			}
			
			// 2. Nếu INTEGER
			if(MapExcelFieldDataType.INTEGER.compareTo(mapExcelFieldDataType) == 0 ) {
				// check định dạng NUMBER
				BigDecimal bigDecimalCell = DateUtilDcs.getNumericCellValue(cell);
				if (bigDecimalCell == null) {
					String[] params = new String[] { rowNum.toString(),mapExcelValue };
					String message = messageSource.getMessage("upload.notNumber.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(entity, bigDecimalCell);
			}
			// 3. Nếu DATE
			if(MapExcelFieldDataType.DATE.compareTo(mapExcelFieldDataType) == 0 ) {
				// check định dạng NUMBER
				Date dateCell = DateUtilDcs.getDateCellValue(cell);
				if (dateCell == null) {
					String[] params = new String[] { rowNum.toString(), mapExcelValue };
					String message = messageSource.getMessage("upload.notDate.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(entity, dateCell);
			}
			// 4. Nếu DATESTR
			if(MapExcelFieldDataType.DATESTR.compareTo(mapExcelFieldDataType) == 0 ) {
				// check định dạng String Date dd/MM/yyyy
				Date dateCell = DateUtilDcs.convertStringToDate(tempStrCell);
				if (dateCell == null) {
					String[] params = new String[] { rowNum.toString(), mapExcelValue };
					String message = messageSource.getMessage("upload.notDate.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				field.set(entity, dateCell);
			}
			// 5. Nếu STRING
			if(MapExcelFieldDataType.STRING.compareTo(mapExcelFieldDataType) == 0 ) {
				// 5.1 Bổ sung valid độ dài STRING
				if(maxLength != null && tempStrCell.trim().length() > maxLength) {
					String[] params = new String[] { rowNum.toString(), mapExcelValue, maxLength.toString() };
					String message = messageSource.getMessage("upload.max.message", params,LocaleContextHolder.getLocale());
					throw new Exception(message);
				}
				// check định dạng STRING
				field.set(entity, tempStrCell.trim());
			}
		}		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(entity);
		logger.info(jsonString);
	}
	
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
