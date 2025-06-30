package com.ejie.aa79b.validator;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;

import com.bea.core.repackaged.springframework.util.Assert;

public class MyMessageSourceResourceBundleLocator implements ResourceBundleLocator {

	private final MessageSource messageSource;

	public MyMessageSourceResourceBundleLocator(MessageSource messageSource) {
		Assert.notNull(messageSource, "MessageSource must not be null");
		this.messageSource = messageSource;
	}

	@Override
	public ResourceBundle getResourceBundle(Locale locale) {
		return new MessageSourceResourceBundle(this.messageSource, locale);
	}

}