package com.ejie.aa79b.utils.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejie.x38.util.Constants;

/**
 * The type Aa79bDateFormaterFactory.
 *
 * @author vdorantes
 *
 */
public final class DateFormaterFactory {
	/**
	 * INSTANCE: Aa79bDateUtils.
	 */
	private static final DateFormaterFactory INSTANCE = new DateFormaterFactory();

	/**
	 * SRT_DATE_FORMAT: DateFormat.
	 */
	private static final DateFormat SRT_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	/**
	 * SRP_DATE_FORMAT: DateFormat.
	 */
	private static final DateFormat SRP_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * Aa79b_DATE_FORMAT: DateFormat.
	 */
	private static final DateFormat Aa79b_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * FECHA_CORTA_ES_DATE_FORMAT: DateFormat.
	 */
	private static final DateFormat FECHA_CORTA_ES_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * FECHA_CORTA_EU_DATE_FORMAT: DateFormat.
	 */
	private static final DateFormat FECHA_CORTA_EU_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * FECHA_CORTA_EU_DATE_FORMAT: DateFormat.
	 */
	private static final DateFormat FECHA_CORTA_EU_SIN_BARRAS_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	private static final DateFormat HORA_FORMAT = new SimpleDateFormat("HH:mm");

	/**
	 * LOGGER: Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DateFormaterFactory.class);

	/**
	 * Este metodo devuelve la instancia de la clase.
	 *
	 * @return La instancia de la clase
	 */
	public static DateFormaterFactory getInstance() {
		return DateFormaterFactory.INSTANCE;
	}

	/**
	 * The method getAa79bDateFormater.
	 *
	 * @return Aa79bDateFormater
	 */
	public DateFormater getAa79bDateFormater() {
		return new Aa79bDateFormaterImpl() {
			@Override
			public DateFormat getDateFormat() {
				return DateFormaterFactory.Aa79b_DATE_FORMAT;
			}
		};
	}

	/**
	 * The method getSrtDateFormater.
	 *
	 * @return Aa79bDateFormater
	 */
	public DateFormater getSrtDateFormater() {
		return new Aa79bDateFormaterImpl() {
			@Override
			public DateFormat getDateFormat() {
				return DateFormaterFactory.SRT_DATE_FORMAT;
			}
		};
	}

	/**
	 * The method getSrtDateFormater.
	 *
	 * @return Aa79bDateFormater
	 */
	public DateFormater getSrpDateFormater() {
		return new Aa79bDateFormaterImpl() {
			@Override
			public DateFormat getDateFormat() {
				return DateFormaterFactory.SRP_DATE_FORMAT;
			}
		};
	}

	/**
	 * The method getSrtDateFormater.
	 *
	 * @return Aa79bDateFormater
	 */
	public DateFormater getFechaCortaSinBarras() {
		return new Aa79bDateFormaterImpl() {
			@Override
			public DateFormat getDateFormat() {
				return DateFormaterFactory.FECHA_CORTA_EU_SIN_BARRAS_DATE_FORMAT;
			}
		};
	}

	/**
	 * The method getFechaCortaDateFormater.
	 *
	 * @param locale
	 *            Locale
	 * @return Aa79bDateFormater
	 */
	public DateFormater getFechaCortaDateFormater(Locale locale) {
		if (Constants.CASTELLANO.equals(locale.getLanguage())) {
			return new Aa79bDateFormaterImpl() {
				@Override
				public DateFormat getDateFormat() {
					return DateFormaterFactory.FECHA_CORTA_ES_DATE_FORMAT;
				}
			};
		} else {
			return new Aa79bDateFormaterImpl() {
				@Override
				public DateFormat getDateFormat() {
					return DateFormaterFactory.FECHA_CORTA_EU_DATE_FORMAT;
				}
			};
		}
	}

	/**
	 * The method getHoraDateFormater.
	 *
	 * @return Aa79bDateFormater
	 */
	public DateFormater getHoraDateFormater() {
		return new Aa79bDateFormaterImpl() {
			@Override
			public DateFormat getDateFormat() {
				return DateFormaterFactory.HORA_FORMAT;
			}
		};
	}

	/**
	 * Constructor para Aa79bDateUtils.
	 */
	private DateFormaterFactory() {
		// Empty constructor
	}

	/**
	 * The type Aa79bDateFormaterImpl.
	 *
	 * @author vdorantes
	 *
	 */
	private static abstract class Aa79bDateFormaterImpl implements DateFormater {
		/**
		 * The method getDateFormat.
		 *
		 * @return DateFormat
		 */
		public abstract DateFormat getDateFormat();

		/**
		 * The method parse.
		 *
		 * @param date
		 *            Date
		 * @return String
		 */
		@Override
		public String parse(Date date) {
			try {
				return this.getDateFormat().format(date);
			} catch (Exception e) {
				DateFormaterFactory.LOGGER.error(e.getMessage(), e);
				return "";
			}
		}

		/**
		 * The method parse.
		 *
		 * @param string
		 *            String
		 * @return Date
		 */
		@Override
		public Date parse(String string) {
			try {
				return this.getDateFormat().parse(string);
			} catch (Exception e) {
				DateFormaterFactory.LOGGER.error(e.getMessage(), e);
				return null;
			}
		}
	}
}
