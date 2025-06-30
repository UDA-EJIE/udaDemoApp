package com.ejie.aa79b.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.Reports;

public class ValidationUtils {

	private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKET";

	private ValidationUtils() {
		//Constructor vacío
	}

	public static Boolean validarObligatorio(List<String> errores, Object bean, String campo) {
		Object value = Reports.retrieveObjectValue(bean, campo);
		if (value == null || StringUtils.isBlank(value.toString())) {
			errores.add(campo);
			return false;
		}
		return true;
	}
	public static Boolean validarObligatorioIf(List<String> errores, Object bean, String campo, String campo2, String... valores) {
		Object value = Reports.retrieveObjectValue(bean, campo2);
		if (value != null) {
			Boolean encontrado = false;
			for (String valor: valores) {
				if (valor.equals(value.toString())) {
					encontrado = true;
				}
			}
			if (encontrado) {
				return validarObligatorio(errores, bean, campo);
			}
		}
		return true;
	}

	public static Boolean comprobarVacios(Object bean, String... campos) {
		Boolean vacios = true;
		for (String campo : campos) {
			Object value = Reports.retrieveObjectValue(bean, campo);
			if (value != null && StringUtils.isNotBlank(value.toString())) {
				vacios = false;
				break;
			}
		}
		return vacios;
	}

	public static Boolean validarString(List<String> errores, Object bean, String campo, Integer tamanio) {
		Object value = Reports.retrieveObjectValue(bean, campo);
		if (value != null && value.toString().length() > tamanio) {
			errores.add(campo);
			return false;
		}
		return true;
	}

	public static Boolean validarRutaPIF(List<String> errores, Object bean, String campo, Integer tamanio) {
		Object value = Reports.retrieveObjectValue(bean, campo);
		if (value != null && value.toString().length() > tamanio) {
			errores.add(campo);
			return false;
		}
		int ultimaAparicionPunto = value.toString().lastIndexOf('.');
		if (ultimaAparicionPunto != -1 && ultimaAparicionPunto < value.toString().length() - 1) {
			String extension = value.toString().substring(ultimaAparicionPunto + 1);
			if (extension.equalsIgnoreCase(Constants.ZIP)) {
				errores.add(campo);
				return false;
			}
		}
		return true;
	}

	public static Boolean validarInteger(List<String> errores, Object bean, String campo, Integer tamanio) {
		Object value = Reports.retrieveObjectValue(bean, campo);
		if (value != null && StringUtils.isNotBlank(value.toString())
				&& (value.toString().length() > tamanio || !value.toString().matches("\\d+"))) {
			errores.add(campo);
			return false;
		}
		return true;
	}
	public static Boolean validarEmail(List<String> errores, Object bean, String campo, Integer tamanio) {
		Object value = Reports.retrieveObjectValue(bean, campo);
		if (value != null && StringUtils.isNotBlank(value.toString())) {
			Pattern pat=Pattern.compile("^[a-zA-Z0-9]+[\\-\\._a-zA-Z0-9]*@[a-zA-Z0-9]+[\\w-\\.]*[a-zA-Z0-9]+(\\.[A-Za-z]{2,})$"); //NUEVA
			Matcher matcher=pat.matcher(value.toString());
			if (value.toString().length() > tamanio || !matcher.find()) {
				errores.add(campo);
				return false;
			}
		}
		return true;
	}
	public static Boolean validarFechaHora(List<String> errores, Object bean, String campo) {
		Object value = Reports.retrieveObjectValue(bean, campo);
		if (value != null && DateUtils.formatearFechaWS(value.toString()) == null) {
			errores.add(campo);
			return false;
		}
		return true;
	}
	public static Boolean validarDni(List<String> errores, Object bean, String campoTipo, String campoPrefijo, String campoDni, String campoSufijo) {
		Boolean correcto = true;
		String tipo = (String)Reports.retrieveObjectValue(bean, campoTipo);
		String prefijo = (String)Reports.retrieveObjectValue(bean, campoPrefijo);
		String dni = (String)Reports.retrieveObjectValue(bean, campoDni);
		String sufijo = (String)Reports.retrieveObjectValue(bean, campoSufijo);
		if (StringUtils.isNotBlank(tipo) && StringUtils.isNotBlank(dni) && ("1".equals(tipo) || "2".equals(tipo))) {
			StringBuilder dniCompleto = new StringBuilder();
			if ("2".equals(tipo)) {
				if ("Y".equalsIgnoreCase(prefijo)) {
					dniCompleto.append("1");
				} else if ("Z".equalsIgnoreCase(prefijo)) {
					dniCompleto.append("2");
				}
			}
			dniCompleto.append(StringUtils.leftPad(dni, 7, "0"));
			Integer dniNum = Integer.parseInt(dniCompleto.toString());
			if(!String.valueOf(LETRAS_DNI.charAt(dniNum % 23)).equals(sufijo)) {
				errores.add(campoDni);
				correcto = false;
			}
		}
		return correcto;
	}
}
