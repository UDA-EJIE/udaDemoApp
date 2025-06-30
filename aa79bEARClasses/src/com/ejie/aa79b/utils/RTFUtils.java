package com.ejie.aa79b.utils;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.ejie.aa79b.common.exceptions.AppRuntimeException;

public final class RTFUtils {

	/**
	 * INIT_MARK: String.
	 */
	private static final String INIT_MARK = "<<";

	/**
	 * END_MARK: String.
	 */
	private static final String END_MARK = ">>";

	/**
	 * ENCODING: String.
	 */
	public static final String ENCODING = "UTF-8";
	/**
	 * ENCODING: String.
	 */
	public static final String ENCODING_ISO = "ISO-8859-1";

	/**
	 * HEXADECIMAL_LINE_WIDTH: int.
	 */
	private static final int HEXADECIMAL_LINE_WIDTH = 64;

	/**
	 * RTF_TROWD: String.
	 */
	private static final String RTF_TROWD = "\\trowd";

	/**
	 * RTF_BRACKET: String.
	 */
	private static final String RTF_BRACKET = "{";

	/**
	 * FONT_END_MARK: String.
	 */
	private static final String FONT_END_MARK = "}}";

	/**
	 * TAG_INICIO: String.
	 */
	private static final String TAG_INICIO = "<<INICIO>>";

	/**
	 * TAG_FIN: String.
	 */
	private static final String TAG_FIN = "<<FIN>>";

	/**
	 * INSTANCE: RTFUtils. Este campo sirve para la instancia de la clase
	 */
	private static final RTFUtils INSTANCE = new RTFUtils();

	/**
	 * Este metodo sirve para obtener la instancia de la clase.
	 * 
	 * @author ilarburu
	 * @return La instancia de la clase
	 */
	public static RTFUtils getInstance() {
		return RTFUtils.INSTANCE;
	}

	/**
	 * Un constructor para RTFUtils.
	 * 
	 * @author ilarburu
	 */
	private RTFUtils() {
		// Empty constructor
	}

	/**
	 * Este metodo sirve para generar un documento combinado sustituyendo las
	 * entradas en la plantilla.
	 * 
	 * @author ilarburu
	 * @param plantilla
	 *            La plantilla
	 * @param entradas
	 *            Las entradas
	 * @return El documento combinado
	 */
	public byte[] combinarDocumento(byte[] plantilla, Map<String, Object> entradas) {
		String rst;

		try {

			// Limpiamos los tags de inicio y fin de la plantilla
			// Si lo leemos con el encoding UTF-8, más adelante, al hacer las
			// sustituciones dinámicas del texto, no hay forma de que muestre
			// bien las tildess
			String strPlantilla = new String(plantilla, RTFUtils.ENCODING_ISO);
			strPlantilla = strPlantilla.replaceAll(RTFUtils.TAG_INICIO, "");
			strPlantilla = strPlantilla.replaceAll(RTFUtils.TAG_FIN, "");

			// La variable para el resultado
			final StringBuilder resultado = new StringBuilder(strPlantilla);

			int initPos = resultado.indexOf(RTFUtils.INIT_MARK);
			while (initPos >= 0) {
				final int endPos = resultado.indexOf(RTFUtils.END_MARK, initPos);
				final String key = resultado.substring(initPos + 2, endPos);
				final Object value = entradas.containsKey(key) ? (entradas.get(key) != null ? entradas.get(key) : "")
						: null;
				if (value == null) {
					initPos = resultado.indexOf(RTFUtils.INIT_MARK, endPos);
				} else if (value instanceof String) {
					// Para que se muestren correctamente las tildeds en
					// desarrollo, hay que leer el String en UTF-8 y convertirlo
					// a ISO.
					// En local esta conversión no es necesaria, y al hacerla se
					// muestran mal las tildes, así que si queremos hacer las
					// pruebas en local bastaría
					// con final String string5 = new String((String) value)

					final String string = new String(((String) value).getBytes(RTFUtils.ENCODING),
							RTFUtils.ENCODING_ISO);
					resultado.replace(initPos, endPos + 2, string);
					initPos = resultado.indexOf(RTFUtils.INIT_MARK, initPos);
				} else {
					initPos = procesaOtrosEltosRTF(resultado, initPos, endPos, key, value);
				}
			}

			rst = sustituirCaracteresEspecialesPlantilla(resultado.toString());

			// Devolver resultado
			return rst.getBytes();
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

	/**
	 * @param resultado
	 * @param initPos
	 * @param endPos
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings(value = "unchecked")
	private int procesaOtrosEltosRTF(final StringBuilder resultado, int posicionInicial, final int endPos,
			final String key, final Object value) {
		int initPos = posicionInicial;
		if (value instanceof List) {
			final List<Map<String, Object>> list = (List<Map<String, Object>>) value;

			if ("TABLA".equals(key)) {
				resultado.replace(initPos, endPos + 2, this.parsearTablaOld(list));

				initPos = resultado.indexOf(RTFUtils.INIT_MARK, initPos);
			} else {

				// Obtenemos inicio de fila
				final String plantillaPreKey = resultado.substring(0, initPos);
				final int indexOfTrowdFirst = plantillaPreKey.lastIndexOf(RTFUtils.RTF_TROWD);
				final String plantillaPreTrowd = resultado.substring(0, indexOfTrowdFirst);
				final int indexOfRowStart = plantillaPreTrowd.lastIndexOf(RTFUtils.RTF_BRACKET);
				// Obtenemos fin de fila
				final int indexOfTrowdLast = resultado.indexOf(RTFUtils.RTF_TROWD, initPos);
				final String plantillaPostTrowd = resultado.substring(0, indexOfTrowdLast);
				final int indexOfRowEnd = plantillaPostTrowd.lastIndexOf(RTFUtils.RTF_BRACKET);

				// Sustituimos la tabla
				final String tabla = this.parsearTabla(resultado.substring(indexOfRowStart, indexOfRowEnd), list);
				resultado.replace(indexOfRowStart, indexOfRowEnd, tabla);

				initPos = resultado.indexOf(RTFUtils.INIT_MARK, initPos);
			}
		} else if (value instanceof byte[]) {
			final byte[] buff = (byte[]) value;
			final String imagen = "{\\rtf1{\\pict\\picw200\\pich400\\jpegblip " + this.parsearImagen(buff)
					+ RTFUtils.FONT_END_MARK;
			resultado.replace(initPos, endPos + 2, imagen);
			initPos = resultado.indexOf(RTFUtils.INIT_MARK, initPos);
		} else {
			final String string = value.toString();
			resultado.replace(initPos, endPos + 2, string);

			initPos = resultado.indexOf(RTFUtils.INIT_MARK, initPos);
		}
		return initPos;
	}

	/**
	 * @param resultado
	 * @return
	 */
	private String sustituirCaracteresEspecialesPlantilla(final String resultado) {
		String rst;
		rst = resultado.replaceAll("Á", "\\\\'c1");
		rst = rst.replaceAll("É", "\\\\'c9");
		rst = rst.replaceAll("Í", "\\\\'cd");
		rst = rst.replaceAll("Ó", "\\\\'d3");
		rst = rst.replaceAll("Ú", "\\\\'da");
		rst = rst.replaceAll("À", "\\\\'c0");
		rst = rst.replaceAll("È", "\\\\'c8");
		rst = rst.replaceAll("Ì", "\\\\'cc");
		rst = rst.replaceAll("Ò", "\\\\'d2");
		rst = rst.replaceAll("Ù", "\\\\'d9");
		rst = rst.replaceAll("Ã", "\\\\'c3");
		rst = rst.replaceAll("Õ", "\\\\'d5");
		rst = rst.replaceAll("Â", "\\\\'c2");
		rst = rst.replaceAll("Ê", "\\\\'ca");
		rst = rst.replaceAll("Î", "\\\\'ce");
		rst = rst.replaceAll("Ô", "\\\\'d4");
		rst = rst.replaceAll("Û", "\\\\'db");
		rst = rst.replaceAll("Ä", "\\\\'c4");
		rst = rst.replaceAll("Ë", "\\\\'cb");
		rst = rst.replaceAll("Ï", "\\\\'cf");
		rst = rst.replaceAll("Ö", "\\\\'d6");
		rst = rst.replaceAll("Ü", "\\\\'dc");
		rst = rst.replaceAll("Ç", "\\\\'c7");
		rst = rst.replaceAll("Ñ", "\\\\'d1");
		rst = rst.replaceAll("á", "\\\\'e1");
		rst = rst.replaceAll("é", "\\\\'e9");
		rst = rst.replaceAll("í", "\\\\'ed");
		rst = rst.replaceAll("ó", "\\\\'f3");
		rst = rst.replaceAll("ú", "\\\\'fa");
		rst = rst.replaceAll("à", "\\\\'e0");
		rst = rst.replaceAll("è", "\\\\'e8");
		rst = rst.replaceAll("ì", "\\\\'ec");
		rst = rst.replaceAll("ò", "\\\\'f2");
		rst = rst.replaceAll("ù", "\\\\'f9");
		rst = rst.replaceAll("ã", "\\\\'e3");
		rst = rst.replaceAll("õ", "\\\\'f5");
		rst = rst.replaceAll("â", "\\\\'e2");
		rst = rst.replaceAll("ê", "\\\\'ea");
		rst = rst.replaceAll("î", "\\\\'ee");
		rst = rst.replaceAll("ô", "\\\\'f4");
		rst = rst.replaceAll("û", "\\\\'fb");
		rst = rst.replaceAll("ä", "\\\\'e4");
		rst = rst.replaceAll("ë", "\\\\'eb");
		rst = rst.replaceAll("ï", "\\\\'ef");
		rst = rst.replaceAll("ö", "\\\\'f6");
		rst = rst.replaceAll("ü", "\\\\'fc");
		rst = rst.replaceAll("ç", "\\\\'e7");
		rst = rst.replaceAll("ñ", "\\\\'f1");
		rst = rst.replaceAll("€", "\\\\'80");
		rst = rst.replaceAll("@", "\\\\'40");
		return rst;
	}

	/**
	 * Este metodo sirve para la entrada por una tabla dinamica.
	 * 
	 * @author ilarburu
	 * @param fila
	 *            La fila a combinar
	 * @param list
	 *            Las filas de la tabla
	 * @return La entrada sustituida
	 */
	private String parsearTabla(String fila, List<Map<String, Object>> list) {
		final StringBuilder buff = new StringBuilder();

		for (int index = 0; index < list.size(); index++) {
			final Map<String, Object> map = list.get(index);
			final StringBuilder row = new StringBuilder(fila); // NOPMD:
																// Necesitamos
																// crear un
																// nuevo
																// elemento por
																// linea de
																// tabla

			int initPos = row.indexOf(RTFUtils.INIT_MARK);
			while (initPos >= 0) {
				final int endPos = row.indexOf(RTFUtils.END_MARK, initPos);
				final String key = row.substring(initPos + 2, endPos);
				final String value = (String) map.get(key);

				if (value == null) {
					initPos = row.indexOf(RTFUtils.INIT_MARK, endPos);
				} else {
					row.replace(initPos, endPos + 2, value);
					initPos = row.indexOf(RTFUtils.INIT_MARK, initPos);
				}
			}

			buff.append(row.toString());
		}

		return buff.toString();
	}

	/**
	 * Este metodo sirve para la entrada por una tabla dinamica.
	 * 
	 * @author ilarburu
	 * @param list
	 *            Las filas de la tabla
	 * @return La entrada sustituida
	 */
	private String parsearTablaOld(List<Map<String, Object>> list) {
		final StringBuilder buff = new StringBuilder();

		for (int index = 0; index < list.size(); index++) {
			final Map<String, Object> map = list.get(index);
			final Map<String, Object> mSort = new TreeMap<String, Object>(map); // NOPMD:
																				// Necesitamos
																				// crear
																				// un
																				// nuevo
																				// elemento
																				// por
																				// linea
																				// de
																				// tabla

			buff.append("}\n\\par\n\\par{");
			for (String key : mSort.keySet()) {
				if (key.indexOf("TAG") != -1) {
					final String value = (String) mSort.get(key);
					buff.append(value).append("\t");
				}
			}
		}

		return buff.toString();
	}

	/**
	 * Este metodo sirve para sustituir una imagen.
	 * 
	 * @author ilarburu
	 * @param buff
	 *            La imagen
	 * @return La entrada sustituida
	 * @throws Exception
	 *             exception
	 */
	private String parsearImagen(byte[] buff) {
		final StringBuilder byteBuff = new StringBuilder();

		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buff);
		int count = 0;

		while (true) {
			final int i = byteArrayInputStream.read();

			if (i == -1) {
				break;
			}

			final String hexStr = StringUtils.leftPad(Integer.toHexString(i), 2, '0');

			count += 2;
			byteBuff.append(hexStr);

			if (count == RTFUtils.HEXADECIMAL_LINE_WIDTH) {
				count = 0;
				byteBuff.append("\n");
			}
		}

		final String imagen = byteBuff.toString();

		return imagen;
	}

}
