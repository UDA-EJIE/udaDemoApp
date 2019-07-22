package com.ejie.x21a.control.pruebasValidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { EsElDobleValidator.class })
@Documented
public @interface CheckEsElDoble {

   String message() default "otro no es el doble de uno";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};

}