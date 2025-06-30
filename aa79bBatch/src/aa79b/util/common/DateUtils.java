package aa79b.util.common;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

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
	 * @param fecha
	 *            Date
	 * @param hora
	 *            String
	 * @return Date
	 */
	public static Date obtFechaHoraFormateada(Long fecha, String hora) {
		if (fecha != null && fecha != Long.valueOf(Constants.MAGIC_NUMBER0)) {
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
	 * @param fecha
	 *            Date
	 * @param hora
	 *            String
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

}
