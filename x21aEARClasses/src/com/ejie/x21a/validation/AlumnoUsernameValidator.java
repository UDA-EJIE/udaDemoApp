package com.ejie.x21a.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AlumnoUsernameValidator implements Validator {

	@Autowired
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(String.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		
		
		
		
	}
	
	

}
