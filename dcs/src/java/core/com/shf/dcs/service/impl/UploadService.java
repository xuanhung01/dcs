package com.shf.dcs.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.shf.dcs.dao.DebtUploadCusLdDAO;
import com.shf.dcs.dao.DebtUploadHdrDAO;
import com.shf.dcs.dao.RoleDAO;
import com.shf.dcs.dao.UserRepository;
import com.shf.dcs.dto.AdminUploadDto;
import com.shf.dcs.model.DebtUploadCusLd;
import com.shf.dcs.model.DebtUploadHdr;
import com.shf.dcs.model.DebtUser;
import com.shf.dcs.service.IUploadService;
import com.shf.dcs.utils.Constants;
import com.shf.dcs.utils.DateUtilDcs;

import vn.co.dssfw.utils.DateUtil;

@Service
@Transactional(rollbackOn = Exception.class)
public class UploadService implements IUploadService {
	
	private static Logger logger = Logger.getLogger(UploadService.class);
	
	@Autowired
	DebtUploadHdrDAO debtUploadHdrDAO;
	
	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	protected DebtUploadCusLdDAO debtUploadCusLdDAO;
	
	@Override
	public List<DebtUploadHdr> getListFileByType(String fileType) throws Exception {
		return debtUploadHdrDAO.getListFileByType(fileType);
	}
	
	/**
	 * Tìm user trong danh sách
	 * @param listUser
	 * @param userName
	 * @return
	 */
	public DebtUser hasUserInList(List<DebtUser> listUser, String userName) {
    	// tìm user
    	for (DebtUser user : listUser) {
			if(user.getUsername().equals(StringUtils.upperCase(userName))) {
				return user;
			}
		}
    	return null;
	}

	@Override
	public List<DebtUploadCusLd> getListCustomerLdFailById(BigDecimal uploadHdrId) throws Exception {
		return debtUploadCusLdDAO.getByUploadHdrIdFail(uploadHdrId);
	}
}