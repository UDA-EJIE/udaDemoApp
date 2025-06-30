package com.ejie.aa79b.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.model.excel.content.ExcelCriterios;

public class ExcelUtils {

	protected static final String REGEX_STRING_ENTRE_CORCHETES = "\\[(.*?)\\]";
	protected static final String REGEX_COMAS_ENTRE_CORCHETES = "(\\[[^]]*\\]|\\([^)]*\\)|\"[^\"]*\"),?|,";
	protected static final String STRING_VACIO = "";
	protected static final String COMILLAS = "\"";
	protected static final String SUBST = "$1 ";

	private ExcelUtils() {
		// Constructor privado
	}

	public static List<ExcelCriterios> obtenerCriterios(String columns) {

		// eliminamos primer y ultimo caracter innecesarios
		String sCriterios = columns.substring(Constants.UNO, columns.length() - Constants.UNO);
		// eliminamos las comas entre corchetes
		Pattern pattern = Pattern.compile(REGEX_COMAS_ENTRE_CORCHETES);
		Matcher matcher = pattern.matcher(sCriterios);
		String sCriteriosSinComas = matcher.replaceAll(SUBST);
		// obtenemos array con los strings de datos de columnas
		pattern = Pattern.compile(REGEX_STRING_ENTRE_CORCHETES);
		matcher = pattern.matcher(sCriteriosSinComas);
		String[] sACriterios = new String[Constants.VEINTE];
		int count = Constants.CERO;
		while (matcher.find()) {
			// si seleccionado mostrar gestor, cambiamos el name para que no
			// aparezca en formato html
			if (matcher.group(Constants.UNO) != null && matcher.group(Constants.UNO).contains("vipEntidadGestorEu")) {
				sACriterios[count] = matcher.group(Constants.UNO).replace("vipEntidadGestorEu",
						"gestorExpediente.solicitante.nombreCompleto");
			} else if (matcher.group(Constants.UNO) != null
					&& matcher.group(Constants.UNO).contains("expedienteTradRev.numTotalPalConTramosPerfectMatch")) {
				sACriterios[count] = matcher.group(Constants.UNO).replace("expedienteTradRev.numTotalPalConTramosPerfectMatch",
						"numTotalPalConTramosPerfectMatchExcelReport");
			} else {
				sACriterios[count] = matcher.group(Constants.UNO);
			}
			count++;
		}

		List<ExcelCriterios> lCriterios = new ArrayList<ExcelCriterios>();

		String[] sACriteriosColumna = new String[20];
		ExcelCriterios excelCriterios;
		for (String sCriterio : sACriterios) {
			if (sCriterio != null) {
				// obtenemos array con los valores del label, name, ancho de
				// columna
				// y disposicion
				sACriteriosColumna = sCriterio.split(Constants.COMA);
				// obtenenmos el ancho de celda de criterio valor con el ancho
				// de
				// columna pasado de la tabla
				List<Integer> anchosCelda = obtenerAnchoCeldas(sACriteriosColumna[Constants.DOS]);
				// utilizamos objeto para pasarle los valores
				excelCriterios = new ExcelCriterios(anchosCelda.get(Constants.CERO), anchosCelda.get(Constants.UNO),
						Constants.CERO, false);
				// obtenemos valores de label y name (les quitamos las
				// commillas)
				Map<String, String> mCriterios = new LinkedHashMap<String, String>();
				mCriterios.put(Constants.NAME, sACriteriosColumna[Constants.CERO].replaceAll(COMILLAS, STRING_VACIO));
				mCriterios.put(Constants.LABEL, sACriteriosColumna[Constants.UNO].replaceAll(COMILLAS, STRING_VACIO));
				excelCriterios.setCriterios(mCriterios);
				lCriterios.add(excelCriterios);
			}
		}

		return lCriterios;
	}

	public static List<Integer> obtenerAnchoCeldas(String anchoCelda) {
		List<Integer> lista = new ArrayList<Integer>();
		if (anchoCelda != null && !STRING_VACIO.equals(anchoCelda)) {
			Integer iAnchoCelda = Integer.valueOf(anchoCelda);
			obtenerAnchoCeldasValueAux(lista, iAnchoCelda);
		} else {
			lista.add(Constants.DOS);
			lista.add(Constants.DOS);
		}
		return lista;
	}

	private static void obtenerAnchoCeldasValueAux(List<Integer> lista, Integer iAnchoCelda) {
		if (Constants.CERO < iAnchoCelda && Constants.CINCUENTA >= iAnchoCelda) {
			lista.add(Constants.DOS);
			lista.add(Constants.DOS);
		} else if (Constants.CINCUENTA < iAnchoCelda && Constants.CIEN >= iAnchoCelda) {
			lista.add(Constants.TRES);
			lista.add(Constants.TRES);
		} else if (Constants.CIEN < iAnchoCelda && Constants.DOSCIENTOS >= iAnchoCelda) {
			lista.add(Constants.TRES);
			lista.add(Constants.TRES);
		} else if (Constants.DOSCIENTOS < iAnchoCelda && Constants.TRESCIENTOS >= iAnchoCelda) {
			lista.add(Constants.CUATRO);
			lista.add(Constants.CUATRO);
		} else {
			lista.add(Constants.CINCO);
			lista.add(Constants.CINCO);
		}
	}

}
