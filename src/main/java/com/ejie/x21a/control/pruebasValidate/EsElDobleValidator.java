package com.ejie.x21a.control.pruebasValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class EsElDobleValidator implements ConstraintValidator<CheckEsElDoble, BeanValidado> {

	   @Override
	   public void initialize(CheckEsElDoble constraintAnnotation) {
	   }

	   @Override
	   public boolean isValid(BeanValidado value, ConstraintValidatorContext constraintContext) {
	      if (value == null) {
	         return true;
	      }

	      return value.getOtroNumero() == value.getUnNumero() * 2;
	   }

}