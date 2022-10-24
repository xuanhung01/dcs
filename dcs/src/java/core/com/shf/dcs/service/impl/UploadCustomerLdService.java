package com.shf.dcs.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.dto.DebtUploadCusLdDto;
import com.shf.dcs.error.UploadException;
import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.service.IUploadCustomerLdService;
import com.shf.dcs.utils.Constants;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

@Service
@Transactional(rollbackOn = Exception.class)
public class UploadCustomerLdService extends ServiceGenericImpl<DebtUploadCusLd> implements IUploadCustomerLdService{
	private static Logger logger = Logger.getLogger(UploadCustomerLdService.class);

	@Override
	public void save(AdminUploadDto dto) throws UploadException,Exception {
		List<DebtUploadCusLd> listDebtUploadCusLd = new ArrayList<DebtUploadCusLd>();
		Integer rowNum = 0;
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
				rowNum++;
				Row row = rowIterator.next();
				// break header
				if (rowNum == 1) {
					continue;
				}
				DebtUploadCusLd debtUploadCusLd = new DebtUploadCusLd();
				Iterator<Cell> cellIterator = row.cellIterator();				
				// map từ cell sang object
				mapCellToObj(debtUploadCusLd, cellIterator, rowNum);
				// valid NOTNULL, SIZE
				Set<ConstraintViolation<DebtUploadCusLd>> violations = validator.validate(debtUploadCusLd);
				if(!violations.isEmpty()) {
					throw new UploadException (validAnotationEntity(rowNum, violations));
				}
				// save 
				// listDebtUploadCusLd.add(debtUploadCusLd);
				logger.info("SOHOPDONG========"+debtUploadCusLd.getSoHopDong());
				// set 1 số giá trị
				debtUploadCusLd.setSysRunDate(new Date());
				debtUploadCusLd.setUsernameUpload(dto.getUserNameUpload());
				// save
				debtUploadCusLdDAO.saveAndFlush(debtUploadCusLd);
			}
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
			throw e;
		}
		/*
		 * if (listDebtUploadCusLd == null || listDebtUploadCusLd.isEmpty()) { throw new
		 * Exception("File upload đang đang không có dữ liệu"); }
		 */
		// debtUploadCusLdDAO.saveAll(listDebtUploadCusLd);
	}

}
