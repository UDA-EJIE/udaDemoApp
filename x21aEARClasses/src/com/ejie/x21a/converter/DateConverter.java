package com.ejie.x21a.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;

import com.ejie.x38.util.DateTimeManager;

/**
 * Conversor de fechas dependiendo del idioma
 * 
 * @author rmontero
 * 
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String strFecha) {
		try {
			Locale locale = LocaleContextHolder.getLocale();
			SimpleDateFormat format = DateTimeManager.getDateTimeFormat(locale);
			return format.parse(strFecha);
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException();
		}

	}
}

