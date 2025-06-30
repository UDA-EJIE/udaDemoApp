package com.ejie.aa79b.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.enums.TipoFacturacionEnum;

public class GeneralUtils {

	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private GeneralUtils() {
		// Constructor
	}

	public static boolean isValidInteger(Integer value) {
		return value != null && value > Constants.CERO;
	}

	public static boolean isValidBigdecimal(BigDecimal value) {
		return value != null && value.compareTo(BigDecimal.ZERO) == 1;
	}

	/**
	 * Devuelve regex para comprobar si un campo ha sido seleccionado para
	 * visualizarlo en el informe
	 * 
	 * @param campo String
	 * @return String
	 */
	public static String getRegexColumna(String campo) {
		StringBuilder regex = new StringBuilder(Constants.EMPTY);
		if (StringUtils.isNotBlank(campo)) {
			regex.append("\\[\"").append(campo).append(".*?\\]");
		}
		return regex.toString();
	}

	/**
	 * 
	 * @param codProvincia String
	 * @return Boolean
	 */
	public static Boolean estaEnCae(String codProvincia) {
		if (Constants.COD_PROV_BIZKAIA.equals(codProvincia) || Constants.COD_PROV_ARABA.equals(codProvincia)
				|| Constants.COD_PROV_GIPUZKOA.equals(codProvincia)) {
			return true;
		}

		return false;
	}

	public static String format2Decimals(BigDecimal valor) {
		if (valor != null) {
			Locale locale = LocaleContextHolder.getLocale();
			DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(locale);
			unusualSymbols.setDecimalSeparator(',');
			unusualSymbols.setGroupingSeparator('.');
			DecimalFormat formateador = new DecimalFormat("#,###,###,##0.00", unusualSymbols);
			return formateador.format(Utils.roundTo(valor.doubleValue(), 2));
		} else {
			return "";
		}
	}

	public static String format4Decimals(BigDecimal valor) {
		if (valor != null) {
			Locale locale = LocaleContextHolder.getLocale();
			DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols(locale);
			unusualSymbols.setDecimalSeparator(',');
			unusualSymbols.setGroupingSeparator('.');
			DecimalFormat formateador = new DecimalFormat("#,###,###,##0.0000", unusualSymbols);
			return formateador.format(Utils.roundTo(valor.doubleValue(), 4));
		} else {
			return "";
		}
	}

	public static String getFormateoMoneda(BigDecimal campo) {
		NumberFormat currencyFormatter = null;
		if (campo != null) {
			currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);
			return currencyFormatter.format(campo);
		} else {
			return "";
		}
	}

	/**
	 * devuelve true si factura por euro/hora por interprete (1), si no, devuelve
	 * false, factura por euro/interprete (2,3,4,0)
	 * 
	 * @param tipoActo Long
	 * @return Boolean
	 */
	public static Boolean facturaPorEuroHoraInterprete(String tipoFacturacion) {
		if (TipoFacturacionEnum.EURO_HORA_INTERPRETE.getValue().equals(tipoFacturacion)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param email String
	 * @return boolean
	 */
	public static boolean emailValido(String email) {
		String regex = EMAIL_REGEX;
		boolean resul = false;
		if (email != null && !"".equals(email)) {
			resul = Pattern.matches(regex, email);
		}
		return resul;
	}

}
