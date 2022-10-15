package com.shf.dcs.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.shf.dcs.validation.PasswordMatches;
import com.shf.dcs.validation.ValidEmail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDto {
	@NotNull
	@Size(min = 6, max = 80)
	private String userName;

	@NotNull
	@Size(min = 1)
	private String realName;

	@NotNull
	private String maTinh;

	@NotNull
	@Size(min = 6)
	private String password;

	// @NotNull
	// @Size(min = 6)
	private String newPassword;

	@NotNull
	@Size(min = 6)
	private String matchingPassword;

	@NotNull
	@Size(min = 2)
	private String userCaptchaCode;

	@ValidEmail
	@NotNull
	@Size(min = 6, max = 80)
	private String email;

	@NotNull
	@Size(min = 7, max = 8)
	private String maDonViCapTren;

	@NotNull
	@Size(min = 1)
	private String tenDonVi;

	private String maQuanHeNganSachDaCo;

	private String boPhan;

	private String chucDanh;

	@NotNull
	@Size(min = 6)
	private String soDienThoaiDonVi;

	@NotNull
	@Size(min = 6)
	private String soDienThoaiDiDong;

	private BigDecimal groupId;
	private String groupCode;

	private Long role;

	private boolean enable;

	private String parentUserName;

	private String teamCode;

	private String provinceCode;
	private String districtCode;
	private String wardCode;
	private String startAllocatedDate;
	
	private String endAllocatedDate;
	private Integer hasChallenge;
	private String startChallengeDate;
	private String endChallengeDate;
	
	private Boolean hasPartner;
	
	private String partnerName;
	
	private String passwordConfirm;
	
	private String ext;
	private String staffCode;
}
