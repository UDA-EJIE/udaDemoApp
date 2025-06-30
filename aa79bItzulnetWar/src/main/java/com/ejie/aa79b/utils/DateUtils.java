package com.ejie.aa79b.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.common.Constants;

/**
 * @author mrodriguez
 *
 */
public class DateUtils {

	/**
	 * Constructor
	 */
	private DateUtils() {

	}

	/**
	 *
	 * @param fecha Date
	 * @return String
	 */
	public static String obtFechaFormateada(Date fecha) {
		Locale locale = LocaleContextHolder.getLocale();
		SimpleDateFormat formato;
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES);
		} else {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU);
		}
		String date = "";
		if (fecha != null) {
			date = formato.format(fecha);
		}
		return date;
	}

	/**
	 * @param fecha  Date
	 * @param locale Locale
	 * @return String
	 */
	public static String obtFechaFormateada(Date fecha, Locale locale) {
		SimpleDateFormat formato;
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES);
		} else {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU);
		}
		String date = "";
		if (fecha != null) {
			date = formato.format(fecha);
		}
		return date;
	}

	/**
	 * @param hora Date
	 * @return String
	 */
	public static String obtHoraFormateada(Date hora) {
		SimpleDateFormat formato;
		formato = new SimpleDateFormat(Constants.HORA_FORMAT);
		String date = "";
		if (hora != null) {
			date = formato.format(hora);
		}
		return date;
	}

	/**
	 *
	 * @param fecha String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date formatearFecha(String fecha) throws ParseException {
		Locale locale = LocaleContextHolder.getLocale();
		SimpleDateFormat formato;
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES);
		} else {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU);
		}
		Date date = null;
		if (fecha != null) {
			date = formato.parse(fecha);
		}
		return date;
	}

	public static Date formatearFechaWS(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat(Constants.DATE_FORMAT_WS);
		Date date = null;
		if (StringUtils.isNotBlank(fecha)) {
			try {
				date = formato.parse(fecha);
			} catch (ParseException e) {
				return null;
			}
		}
		return date;
	}

	/**
	 * @param fecha  Date
	 * @param locale Locale
	 * @return String
	 */
	public static String obtFechaFormateadaWS(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat(Constants.DATE_FORMAT_WS);
		String date = "";
		if (fecha != null) {
			date = formato.format(fecha);
		}
		return date;
	}

	public static Date formatearFechaSrt(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat(Constants.DATE_FORMAT_SRT);
		Date date = null;
		if (StringUtils.isNotBlank(fecha)) {
			try {
				date = formato.parse(fecha);
			} catch (ParseException e) {
				return null;
			}
		}
		return date;
	}

	/**
	 * @param hora double
	 * @return String
	 */
	public static String formatHora(double hora) {

		int pEnt = (int) hora;

		double pDec = hora - pEnt;

		pDec = pDec * 60;

		int pDecimal = (int) pDec;

		StringBuilder horastot = new StringBuilder();
		horastot.append(pEnt + ":" + String.format("%02d", pDecimal));
		return horastot.toString();
	}

	/**
	 * @param fecha  Date
	 * @param locale Locale
	 * @return String
	 */
	public static String obtFechaHoraFormateada(Date fecha, Locale locale) {
		SimpleDateFormat formato;
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES + " " + Constants.HORA_FORMAT);
		} else {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU + " " + Constants.HORA_FORMAT);
		}
		String date = "";
		if (fecha != null) {
			date = formato.format(fecha);
		}
		return date;
	}

	/**
	 *
	 * @param fecha
	 * @return String
	 */
	public static String obtFechaHoraLargaFormateada(Date fecha) {
		return obtFechaHoraFormateada(fecha, LocaleContextHolder.getLocale());
	}

	/**
	 *
	 * @param fecha
	 * @param locale
	 * @return
	 */
	public static String obtFechaHoraLargaFormateada(Date fecha, Locale locale) {
		SimpleDateFormat formato;
		if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES + " " + Constants.HORA_LARGA_FORMAT);
		} else {
			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU + " " + Constants.HORA_LARGA_FORMAT);
		}
		String date = "";
		if (fecha != null) {
			date = formato.format(fecha);
		}
		return date;
	}

	/**
	 * @param fecha Date
	 * @param hora  String
	 * @return Date
	 */
	public static Date obtFechaHoraFormateada(Date fecha, String hora) {
		if (fecha != null) {
			Calendar cal = Calendar.getInstance();

			cal.setTime(fecha);
			if (StringUtils.isNotBlank(hora)) {
				String[] hour = hora.split(":");
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(hour[1]));
			}

			return cal.getTime();
		} else {
			return null;
		}
	}

	/**
	 * @param fecha  Date
	 * @param hora   String
	 * @param locale Locale
	 * @return String
	 */
	public static String obtFechaHoraFormateada(Date fecha, String hora, Locale locale) {
		String date = "";
		if (fecha != null) {
			Calendar cal = Calendar.getInstance();

			cal.setTimeInMillis(fecha.getTime());

			if (StringUtils.isNotBlank(hora)) {
				String[] hour = hora.split(":");
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(hour[1]));
			}

			SimpleDateFormat formato;
			if (Constants.LANG_CASTELLANO.equals(locale.getLanguage())) {
				formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES + " " + Constants.HORA_FORMAT);
			} else {
				formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_EU + " " + Constants.HORA_FORMAT);
			}
			date = formato.format(cal.getTime());
		}
		return date;
	}

	/**
	 * @param fecha  Date
	 * @param hora   String
	 * @param locale Locale
	 * @return String
	 */
	public static String obtFechaHoraFormateadaEs(Date fecha, String hora) {
		String date = "";
		if (fecha != null) {
			Calendar cal = Calendar.getInstance();

			cal.setTimeInMillis(fecha.getTime());

			if (StringUtils.isNotBlank(hora)) {
				String[] hour = hora.split(":");
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(hour[1]));
			}

			SimpleDateFormat formato;

			formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES + " " + Constants.HORA_FORMAT);
			date = formato.format(cal.getTime());
		}
		return date;
	}

	/**
	 * @param fecha Date
	 * @param hora  String
	 * @return Date
	 */
	public static Date obtFechaHoraFormateada(Long fecha, String hora) {
		if (fecha != null && fecha != Long.valueOf(Constants.CERO)) {
			Date fechaDate = new Date(fecha);
			if (hora != null) {
				fechaDate = DateUtils.obtFechaHoraFormateada(fechaDate, hora);
			}
			return fechaDate;
		} else {
			return null;
		}
	}

	/**
	 * @param fecha Date
	 * @param hora  String
	 * @return Date
	 */
	public static String obtFechaHoraFormateadaAux(Long fecha, String hora) {
		if (fecha != null && fecha != Long.valueOf(Constants.CERO)) {
			Date fechaDate = new Date(fecha);
			if (hora != null) {
				fechaDate = DateUtils.obtFechaHoraFormateada(fechaDate, hora);
			}
			if (fechaDate != null) {
				SimpleDateFormat formato;
				formato = new SimpleDateFormat(Constants.JAVA_DATE_FORMAT_ES);
				return formato.format(fecha);
			}
		}
		return null;
	}

	public static Date crearDateObject(Date fecha, String hora) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		String[] horasMinutos = hora.split(Constants.DOS_PUNTOS);
		if (horasMinutos != null && horasMinutos.length > 1) {
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horasMinutos[Constants.CERO]));
			cal.set(Calendar.MINUTE, Integer.parseInt(horasMinutos[Constants.UNO]));
		}
		return cal.getTime();
	}

}
