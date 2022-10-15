package com.shf.dcs.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shf.dcs.dto.UserAccountDto;

public class PasswordMatchesValidator implements
		ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(final PasswordMatches constraintAnnotation) {
		//
	}

	@Override
	public boolean isValid(final Object obj,
			final ConstraintValidatorContext context) {
		final UserAccountDto user = (UserAccountDto) obj;
		if (user.getPassword() != null && user.getMatchingPassword() != null) {
			return user.getPassword().equals(user.getMatchingPassword());
		} else {
			return true;
		}
	}

}
