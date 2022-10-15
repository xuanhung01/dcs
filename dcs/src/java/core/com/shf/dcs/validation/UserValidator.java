package com.shf.dcs.validation;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.shf.dcs.dto.UserAccountDto;

public class UserValidator implements Validator {
	static Pattern userNamePattern = Pattern
			.compile("^[a-zA-Z0-9][a-zA-Z0-9_]{5,80}$");

	@Override
	public boolean supports(final Class<?> clazz) {
		return UserAccountDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(final Object obj, final Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"validate.password.required", "Trường bắt buộc.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"validate.username.required", "Trường bắt buộc.");
		UserAccountDto dto = (UserAccountDto) obj;
		if (!userNamePattern.matcher(dto.getUserName()).matches()) {
			errors.rejectValue("userName", "validate.username.pattern",
					"UserName is invalid");
		}

		if (!(dto.getUserName().length() >= 6 && dto.getUserName().length() <= 80)) {
			errors.rejectValue("userName", "validate.username.length",
					"Tên tài khoản không hợp lệ");
		}
	}

	// public static void main(String[] args) {
	// String username1 = "_a3";
	// String username2 = "a31$324";
	// String username3 = "$31$@!";
	// String username4 = "a3__";
	// String username5 = "a3_asd__asd___asdasdasdasdasd234234";
	//
	// System.out.println(username1 + " "
	// + userNamePattern.matcher(username1).matches());
	// System.out.println(username2 + " "
	// + userNamePattern.matcher(username2).matches());
	// System.out.println(username3 + " "
	// + userNamePattern.matcher(username3).matches());
	// System.out.println(username4 + " "
	// + userNamePattern.matcher(username4).matches());
	// System.out.println(username5 + " "
	// + userNamePattern.matcher(username5).matches());
	// }
}
