package com.shf.dcs.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.metadata.ConstraintDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.shf.dcs.dao.DebtUploadCusLdDAO;

@Service
@Transactional(rollbackOn = Exception.class)
public class ServiceGenericImpl<T extends Object> {

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected DebtUploadCusLdDAO debtUploadCusLdDAO;

	@Autowired
	protected Validator validator;

	@SuppressWarnings({ "unchecked" })
	protected List<ObjectError> getMessage(Integer rowNum, Set<ConstraintViolation<T>> violations) {
		List<ObjectError> listErrors = new ArrayList<ObjectError>();
		for (ConstraintViolation<?> violation : violations) {
			// kiểm tra lỗi thuộc anatation type nào
			// NotNull
			if (violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName()
					.equals("NotNull")) {
				String[] params = new String[] { rowNum.toString(), violation.getPropertyPath().toString() };
				String message = messageSource.getMessage("upload.notNull.message", params,
						LocaleContextHolder.getLocale());
				listErrors.add(new ObjectError("NotNull", message));
			}
			// MAX
			if (violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName().equals("Max")) {
				long maxLength = 0;
				// MAX
				if (Max.class.equals(violation.getConstraintDescriptor().getAnnotation().annotationType())) {
					ConstraintDescriptor<Max> maxConstraint = (ConstraintDescriptor<Max>) violation
							.getConstraintDescriptor();
					maxLength = maxConstraint.getAnnotation().value();
				}

				String[] params = new String[] { rowNum.toString(), violation.getPropertyPath().toString(),
						String.valueOf(maxLength) };
				String message = messageSource.getMessage("upload.max.message", params,
						LocaleContextHolder.getLocale());
				listErrors.add(new ObjectError("Max", message));
			}
			// không cho vượt quá 5 lỗi
			if (listErrors.size() == 5)
				break;
		}
		return listErrors;
	}

}
