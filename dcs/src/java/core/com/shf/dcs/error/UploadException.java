package com.shf.dcs.error;

import java.util.List;

import org.springframework.validation.ObjectError;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadException extends Exception {
	List<ObjectError> listErrors;
	
	public UploadException(List<ObjectError> listErrors) {
		this.listErrors = listErrors;
	}
}
