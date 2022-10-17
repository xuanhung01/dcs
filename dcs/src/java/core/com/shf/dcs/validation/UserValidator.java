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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"validate.password.required", "Trường bắt buộc.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm",
				"validate.passwordConfirm.required", "Trường bắt buộc.");
		UserAccountDto dto = (UserAccountDto) obj;
		if (!userNamePattern.matcher(dto.getUsername()).matches()) {
			errors.rejectValue("userName", "validate.username.pattern",
					"UserName is invalid");
		}

		if (!(dto.getUsername().length() >= 6 && dto.getUsername().length() <= 80)) {
			errors.rejectValue("userName", "validate.username.length",
					"Tên tài khoản không hợp lệ");
		}
	}
}
