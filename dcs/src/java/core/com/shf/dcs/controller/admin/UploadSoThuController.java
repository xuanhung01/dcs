package com.shf.dcs.controller.admin;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.error.UploadException;
import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.model.DebtUploadHdr;
import com.shf.dcs.service.IUploadCustomerLdService;
import com.shf.dcs.service.IUploadService;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;
import com.shf.dcs.utils.enums.MapHdrFile;

@Controller
@RequestMapping("/admin/uploadSoThu")
public class UploadSoThuController {

	private static Logger logger = Logger.getLogger(UploadSoThuController.class);

	@Autowired
	public IUserService userService;

	@Autowired
	public IUploadService uploadService;

	@Autowired
	public IUploadCustomerLdService uploadCustomerLdService;

	@RequestMapping(value = "/load")
	public String load(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("subpage", "admin_upload_sothu");
		model.addAttribute("formModel", new AdminUploadDto());
		List<DebtUploadHdr> listDebtUploadHdr = uploadService.getListFileByType(MapHdrFile.FILE_SO_THU.name());
		//
		request.getSession().setAttribute("menuActive", "admin");
		request.getSession().setAttribute("listDebtUploadHdr", listDebtUploadHdr);
		return Constants.URL_DASH_BOARD;
	}

	// shows the empty form with get request
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String handleGetRequest(Model model, HttpServletRequest request) {
		model.addAttribute("subpage", "admin_upload_sothu");
		model.addAttribute("formModel", new AdminUploadDto());
		//
		return Constants.URL_DASH_BOARD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute AdminUploadDto dto, BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		List<ObjectError> listErrors = new ArrayList<ObjectError>();
		try {
			dto.setUserNameUpload(userService.getLoggedUserName());
			uploadCustomerLdService.save(dto);
			model.addAttribute("information", "Bạn đã Lưu thông tin thành công!");
			List<DebtUploadHdr> listDebtUploadHdr = uploadService.getListFileByType(MapHdrFile.FILE_SO_THU.name());
			request.getSession().setAttribute("listDebtUploadHdr", listDebtUploadHdr);
		} catch (UploadException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			model.addAttribute("listErrors", e.getListErrors());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			listErrors.add(new ObjectError("OtherException", e.getMessage()));
			model.addAttribute("listErrors", listErrors);
		}
		model.addAttribute("subpage", "admin_upload_sothu");
		model.addAttribute("formModel", dto);
		return Constants.URL_DASH_BOARD;
	}
	
	@RequestMapping(value = "/resultFail/{id:.+}" )
	@ResponseBody
	public Object downloadSuccess(@PathVariable("id") BigDecimal id,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<DebtUploadCusLd> listtUploadRecase =  null;
		int rowNum = 1;
		try {
			listtUploadRecase = uploadService.getListCustomerLdFailById(id);
			// xuất excel
			File file = ResourceUtils.getFile("classpath:template/Template-CustomerLdFail.xlsx");
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			// loop row
			for (DebtUploadCusLd debtUploadCusLd : listtUploadRecase) {
				 Row row = sheet.createRow(rowNum++);
				 row.createCell(0).setCellValue(debtUploadCusLd.getSoHopDong());
				 row.createCell(1).setCellValue(debtUploadCusLd.getCif());
				 row.createCell(2).setCellValue(debtUploadCusLd.getErrorMsg());
			}
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition",
	                "attachment; filename=CustomerLdFail.xlsx");
		    OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.close();
	        // workbook.close();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
		return null;
	}
}
