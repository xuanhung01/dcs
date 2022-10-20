package com.shf.dcs.controller.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.error.UploadException;
import com.shf.dcs.model.DebtUploadHdr;
import com.shf.dcs.service.IUploadCustomerLdService;
import com.shf.dcs.service.IUploadService;
import com.shf.dcs.service.IUserService;
import com.shf.dcs.utils.Constants;

@Controller
@RequestMapping("/admin/uploadCustomerLd")
public class UploadCustomerLdController {

	private static Logger logger = Logger.getLogger(UploadCustomerLdController.class);

	@Autowired
	public IUserService userService;

	@Autowired
	public IUploadService uploadService;

	@Autowired
	public IUploadCustomerLdService uploadCustomerLdService;

	@RequestMapping(value = "/load")
	public String load(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("subpage", "admin_upload_customerLd");
		model.addAttribute("formModel", new AdminUploadDto());
		List<DebtUploadHdr> listDebtUploadHdr = uploadService.getListFileRecase();
		//
		request.getSession().setAttribute("menuActive", "admin");
		request.getSession().setAttribute("listUploadReallocationDto", null);
		request.getSession().setAttribute("listDebtUploadHdr", listDebtUploadHdr);
		return Constants.URL_DASH_BOARD;
	}

	// shows the empty form with get request
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String handleGetRequest(Model model, HttpServletRequest request) {
		model.addAttribute("subpage", "admin_upload_customerLd");
		model.addAttribute("formModel", new AdminUploadDto());
		//
		request.getSession().setAttribute("listUploadReallocationDto", null);
		return Constants.URL_DASH_BOARD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute AdminUploadDto dto, BindingResult bindingResult, Model model,
			HttpServletRequest request, HttpSession httpSession) throws Exception {
		List<ObjectError> listErrors = new ArrayList<ObjectError>();
		try {
			dto.setUserNameUpload(userService.getLoggedUserName());
			uploadCustomerLdService.save(dto);
			model.addAttribute("information", "Bạn đã Lưu thông tin thành công.");
			List<DebtUploadHdr> listDebtUploadHdr = uploadService.getListFileRecase();
			request.getSession().setAttribute("listDebtUploadHdr", listDebtUploadHdr);
		} catch (UploadException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			model.addAttribute("listErrors", e.getListErrors());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			listErrors.add(new ObjectError("OtherException", e.getMessage()));
			model.addAttribute("listErrors", listErrors);
		}
		model.addAttribute("subpage", "admin_upload_customerLd");
		model.addAttribute("formModel", dto);
		return Constants.URL_DASH_BOARD;
	}
}
