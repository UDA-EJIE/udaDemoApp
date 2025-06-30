package com.ejie.aa79b.validator;

import javax.validation.MessageInterpolator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;

public class MyValidatorFactoryBean extends org.springframework.validation.beanvalidation.LocalValidatorFactoryBean {

	private static class HibernateValidatorDelegate {

		public static MessageInterpolator buildMessageInterpolator(MessageSource messageSource) {
			return new ResourceBundleMessageInterpolator(new MyMessageSourceResourceBundleLocator(messageSource));
		}
	}

	@Override
	public void setValidationMessageSource(MessageSource messageSource) {
		setMessageInterpolator(HibernateValidatorDelegate.buildMessageInterpolator(messageSource));
	}

}