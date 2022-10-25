package com.shf.dcs.dto;

import java.math.BigDecimal;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import lombok.*;

@Getter
@Setter
public class AdminUploadDto {
	
	private String fileType;
	private String description;
	private CommonsMultipartFile file1;
	private String hidAction;
	private String userNameUpload;
	private BigDecimal fileRowSuccess;
	private Boolean hasUpTraiSysAdmin;
	private BigDecimal fileRowTotal;
	private String soHopDong;
	private Boolean hasRecaseUserNull;
	//
}
