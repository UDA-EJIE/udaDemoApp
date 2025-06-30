package com.ejie.aa79b.common.formatter;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class Aa79BFormatMoneda extends NumberFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
		StringBuffer euro = new StringBuffer();// NOSONAR Debe ser stringbuffer
												// ya que extendemos
												// NumberFormat
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		return euro.append(currencyFormatter.format(number));
	}

	@Override
	public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
		return null;
	}

	@Override
	public Number parse(String source, ParsePosition parsePosition) {
		return null;
	}

}
