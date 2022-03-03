package com.ejie.x21a.handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class X21aExceptionHandler {

	@Resource
	private ReloadableResourceBundleMessageSource appMessageSource;

	@ExceptionHandler(value = Exception.class)
	public @ResponseBody String handle(Exception exception, HttpServletRequest req,
			HttpServletResponse resp) {
		return exception.getMessage();
	}

}
