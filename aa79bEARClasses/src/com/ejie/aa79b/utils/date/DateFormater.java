package com.ejie.aa79b.utils.date;

import java.util.Date;

/**
 * @author vdorantes
 *
 */
public interface DateFormater {
	/**
	 * @param string
	 *            String
	 * @return Date
	 */
	public Date parse(String string);

	/**
	 * @param date
	 *            Date
	 * @return String
	 */
	public String parse(Date date);
}
