package com.ejie.aa79b.model.excel.content;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.model.excel.ExcelContentMetodos;
import com.ejie.aa79b.model.excel.ExcelEstilo;
import com.ejie.x38.json.JSONArray;

/**
 * @author dlopez2
 *
 */
public class ExcelTable extends ExcelContentMetodos {

	/**
	 * Indica los campos de la tabla
	 */
	protected LinkedHashMap<String, List<String>> filas = new LinkedHashMap<String, List<String>>();

	/**
	 * Indica la alineación de cada celda de cada fila
	 */
	protected LinkedHashMap<String, List<String>> aligns = new LinkedHashMap<String, List<String>>();

	/**
	 * Indica el formato de cada celda de cada fila
	 */
	protected LinkedHashMap<String, List<Boolean>> numberFormat = new LinkedHashMap<String, List<Boolean>>();

	/**
	 * Indica si el formato de cada celda es fecha
	 */
	protected LinkedHashMap<String, List<Boolean>> dateFormat = new LinkedHashMap<String, List<Boolean>>();

	/**
	 * Indica los labels de la cabecera
	 */
	protected Map<String, String> labels = new LinkedHashMap<String, String>();

	protected List<String> columnasAdicionales = new ArrayList<String>();

	/**
	 * Posición X de la tabla posicionX String
	 */
	private int posicionX = 0;
	/**
	 * Posición Y de la tabla posicionY String
	 */
	private int posicionY = 0;
	/**
	 * Si el campo es true, los criterios se pintarán a partir de la última celda
	 * activa celdaActiva boolean
	 */
	private boolean celdaActiva = false;

	/**
	 * Indica el estilo de la cabecera estiloCabecera String
	 */
	private String estiloCabecera = "";

	/**
	 * Indica el estilo de la tabla estiloTabla String
	 */
	private String estiloTabla = "";

	/**
	 *
	 */
	public ExcelTable() {
		// Constructor vacío
	}

	/**
	 * @return celdaActiva
	 */
	@Override
	public boolean isCeldaActiva() {
		return this.celdaActiva;
	}

	/**
	 * @param celdaActiva boolean
	 */
	@Override
	public void setCeldaActiva(boolean celdaActiva) {
		this.celdaActiva = celdaActiva;
	}

	/**
	 * @return String
	 */
	public String getEstiloCabecera() {
		return this.estiloCabecera;
	}

	/**
	 * @param estiloCabecera String
	 */
	public void setEstiloCabecera(String estiloCabecera) {
		this.estiloCabecera = estiloCabecera;
	}

	/**
	 * @return String
	 */
	public String getEstiloTabla() {
		return this.estiloTabla;
	}

	/**
	 * @param estiloTabla String
	 */
	public void setEstiloTabla(String estiloTabla) {
		this.estiloTabla = estiloTabla;
	}

	/**
	 * @param posicionX   int
	 * @param posicionY   int
	 * @param celdaActiva boolean
	 */
	public ExcelTable(int posicionX, int posicionY, boolean celdaActiva) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.celdaActiva = celdaActiva;
	}

	/**
	 * @return int
	 */
	@Override
	public int getPosicionX() {
		return this.posicionX;
	}

	/**
	 * @param posicionX int
	 */
	@Override
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * @return int
	 */
	@Override
	public int getPosicionY() {
		return this.posicionY;
	}

	/**
	 * @param posicionY int
	 */
	@Override
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * @return LinkedHashMap
	 */
	public Map<String, String> getLabels() {
		return this.labels;
	}

	/**
	 * @param map LinkedHashMap
	 */
	public void setLabels(Map<String, String> map) {
		this.labels = map;
	}

	/**
	 * @param key   String
	 * @param value String
	 */
	public void addlabel(String key, String value) {
		this.labels.put(key, (value != null) ? value : "");
	}

	/**
	 * @param posicion String con las columnas
	 */
	public void addFila(int posicion) {
		this.filas.put(String.valueOf(posicion), new ArrayList<String>());
		this.aligns.put(String.valueOf(posicion), new ArrayList<String>());
		this.numberFormat.put(String.valueOf(posicion), new ArrayList<Boolean>());
		this.dateFormat.put(String.valueOf(posicion), new ArrayList<Boolean>());
	}

	public void addFila(int posicion, List<String> valores) {
		this.filas.put(String.valueOf(posicion), valores);
	}

	public void addFilaCasera(int posicion, List<String> valores, List<Boolean> isNumeric, List<Boolean> isDate) {
		this.filas.put(String.valueOf(posicion), valores);
		this.numberFormat.put(String.valueOf(posicion), isNumeric);
		this.dateFormat.put(String.valueOf(posicion), isDate);
	}

	/**
	 * @param label     label de la columna
	 * @param texto     texto del label
	 * @param propertie propertie del bean
	 */
	public void addColumna(String propertie, String label, Locale locale) {
		this.labels.put(label, this.getMsg().getMessage(label, null, locale));
		this.columnasAdicionales.add(propertie);
	}

	/**
	 * @param columns           String con las columnas
	 * @param resultadoBusqueda el resultado de la busqueda
	 */
	public void addContenidoTabla(String columns, List<?> resultadoBusqueda) {
		JSONArray jsonArr = new JSONArray(columns);

		Boolean isNumber = false;
		Boolean isDate = false;

		String align = "";
		int i = 0;
		for (i = 0; i < jsonArr.length(); ++i) {
			JSONArray column = (JSONArray) jsonArr.get(i);
			String propertie = (String) column.get(0);
			if (column.length() != 3) {
				align = (String) column.get(3);
			} else {
				align = "center";
			}

			if (column.length() == 6) {
				isNumber = (Boolean) column.get(4);
				isDate = (Boolean) column.get(5);
			} else {
				isNumber = false;
				isDate = false;
			}

			int j = 0;
			for (Object object : resultadoBusqueda) {
				if (i == 0) {
					this.addFila(j);
				}
				String contenido = Reports.retrieveObjectValue(object, propertie) == null ? ""
						: Reports.retrieveObjectValue(object, propertie).toString();
				// introducimos el contenido en su posicion, recogiendo antes la
				// lista en cuestion
				this.filas.get(String.valueOf(j)).add(contenido);
				this.aligns.get(String.valueOf(j)).add(align);
				this.numberFormat.get(String.valueOf(j)).add(isNumber);
				this.dateFormat.get(String.valueOf(j)).add(isDate);
				j++;
			}

		}

		// Miramos ahora las columnas adionales

		for (String propertie : this.columnasAdicionales) {

			int j = 0;
			for (Object object : resultadoBusqueda) {
				if (i == 0) {
					this.addFila(j);
				}
				String contenido = Reports.retrieveObjectValue(object, propertie) == null ? ""
						: Reports.retrieveObjectValue(object, propertie).toString();
				// introducimos el contenido en su posicion, recogiendo antes la
				// lista en cuestion
				this.filas.get(String.valueOf(j)).add(contenido);
				this.aligns.get(String.valueOf(j)).add(align);
				this.numberFormat.get(String.valueOf(j)).add(isNumber);
				this.dateFormat.get(String.valueOf(j)).add(isDate);
				j++;
			}
			i++;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ejie.aa79b.model.excel.ExcelContent#procesarContenido(org.apache.poi.
	 * xssf.usermodel.XSSFWorkbook, org.apache.poi.xssf.usermodel.XSSFSheet)
	 */
	@Override()
	public void procesarContenido(XSSFWorkbook libro, XSSFSheet hoja) {

		SimpleDateFormat sdf = new SimpleDateFormat(Constants.FECHA_HORA_EU);
		SimpleDateFormat sdfEs = new SimpleDateFormat(Constants.FECHA_HORA_ES);

		int posX = 0;
		int posY = 0;

		if (this.celdaActiva) {
			posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + 1;
		}
		// cabecera de la tabla

		this.procesarContenidoCabecera(this.labels.keySet(), hoja, libro, posX, posY);

//		// Estilo
		XSSFCellStyle numericDecimal = ExcelEstilo.getEstilo(libro, ExcelEstilo.CURRENCY, true);
		XSSFCellStyle numeric = ExcelEstilo.getEstilo(libro, ExcelEstilo.CURRENCY, false);
		XSSFCellStyle date = ExcelEstilo.getEstilo(libro, ExcelEstilo.DATE, true);
		XSSFCellStyle center = ExcelEstilo.getEstiloAlineacionCelda(libro, "center");
		XSSFCellStyle right = ExcelEstilo.getEstiloAlineacionCelda(libro, "right");
		XSSFCellStyle left = ExcelEstilo.getEstiloAlineacionCelda(libro, "");

		posY++;
		// Datos tabla
		if (!this.filas.isEmpty()) {
			Set<String> dataKeys = this.filas.keySet();
			for (String k : dataKeys) {
				posX = 0;
				final List<String> valores = this.filas.get(k);
				final List<String> alignVals = this.aligns.get(k);
				final List<Boolean> numberFormatVals = this.numberFormat.get(k);
				final List<Boolean> dateFormatVals = this.dateFormat.get(k);

				for (int x = 0; x < valores.size(); x++) {
					String string = valores.get(x);
					String alignVal = alignVals != null ? alignVals.get(x) : "";
					Boolean numericData = numberFormatVals != null && numberFormatVals.size() > 0
							? numberFormatVals.get(x)
							: false;
					Boolean dateFormat = dateFormatVals != null && dateFormatVals.size() > 0 ? dateFormatVals.get(x)
							: false;
					Boolean withDecimal = false;

					// Obtenemos la fila y celda.
					XSSFRow fila = hoja.getRow(this.posicionY + posY);
					if (fila == null) {
						fila = hoja.createRow(this.posicionY + posY);
					}
					XSSFCell celda = fila.getCell(this.posicionX + posX);
					if (celda == null) {
						celda = fila.createCell(this.posicionX + posX);
					}

					// Escribimos el valor de la celda.
					String strValorCelda = "";
					if (string != null) {
						strValorCelda = string;
					}
					// Ponemos el tipo de celda.
					if (numericData) {
						if (isNumeric(strValorCelda.replace(".", "").replace(",", "."))) {
							celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
							if (strValorCelda.contains(",") || strValorCelda.contains(".")) {
								withDecimal = true;
								strValorCelda = strValorCelda.replace(".", "").replace(",", ".");
								celda.setCellValue(Double.valueOf(strValorCelda));
							} else {
								withDecimal = false;
								if (strValorCelda != "") {
									celda.setCellValue(Long.valueOf(strValorCelda));
								}
							}
						}else {
							celda.setCellType(XSSFCell.CELL_TYPE_STRING);
							celda.setCellValue(strValorCelda);
						}


					} else if (dateFormat) {
						celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						if (strValorCelda != null && !"".equals(strValorCelda)) {
							try {
								celda.setCellValue(sdf.parse(strValorCelda));
							} catch (ParseException e) {
								try {
									celda.setCellValue(sdfEs.parse(strValorCelda));
								} catch (ParseException ex) {
									celda.setCellValue(strValorCelda);
								}
							}
						} else {
							celda.setCellValue(strValorCelda);
						}
					} else {
						celda.setCellType(XSSFCell.CELL_TYPE_STRING);
						celda.setCellValue(strValorCelda);
					}

					if (StringUtils.isNotBlank(this.getEstiloTabla())) {
						celda.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloTabla(), null));
					} else {
						if (numericData && withDecimal) {
							celda.setCellStyle(numericDecimal);
						} else if (numericData) {
							celda.setCellStyle(numeric);
						} else if (dateFormat) {
							celda.setCellStyle(date);
						} else if ("center".equals(alignVal)) {
							celda.setCellStyle(center);
						} else if ("right".equals(alignVal)) {
							celda.setCellStyle(right);
						} else {
							celda.setCellStyle(left);
						}
					}
					// Activamos la celda
					celda.setAsActiveCell();
					posX++;
				}
				posY++;
			}

		} // Fin if de datos de la tabla*/
		// Autoajustar tamaÃ±o columnas
		int columnNumber = this.labels.size();
		for (int i = 0; i < columnNumber; i++) {
			hoja.autoSizeColumn(this.posicionX + i);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.ejie.aa79b.model.excel.ExcelContent#procesarContenido(org.apache.poi.
	 * xssf.usermodel.XSSFWorkbook, org.apache.poi.xssf.usermodel.XSSFSheet)
	 */
	@Override()
	public void procesarContenidoTrados(XSSFWorkbook libro, XSSFSheet hoja) {

		SimpleDateFormat sdf = new SimpleDateFormat(Constants.FECHA_HORA_EU);
		SimpleDateFormat sdfEs = new SimpleDateFormat(Constants.FECHA_HORA_ES);

		int posX = 0;
		int posY = 0;

		if (this.celdaActiva) {
			posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + 1;
		}
		// cabecera de la tabla

		this.procesarContenidoCabeceraTrados(this.labels.keySet(), hoja, libro, posX, posY);

		// Estilo
		XSSFCellStyle estiloTrados = ExcelEstilo.getEstiloTrados(libro, this.getEstiloTabla(), null);
		XSSFCellStyle numericDecimal = ExcelEstilo.getEstiloTrados(libro, ExcelEstilo.CURRENCY, true);
		XSSFCellStyle numeric = ExcelEstilo.getEstiloTrados(libro, ExcelEstilo.CURRENCY, false);
		XSSFCellStyle date = ExcelEstilo.getEstiloTrados(libro, ExcelEstilo.DATE, true);
		XSSFCellStyle center = ExcelEstilo.getEstiloAlineacionCeldaTrados(libro, "center");
		XSSFCellStyle right = ExcelEstilo.getEstiloAlineacionCeldaTrados(libro, "right");
		XSSFCellStyle left = ExcelEstilo.getEstiloAlineacionCeldaTrados(libro, "");

		posY = posY + 2;

		// Datos tabla
		if (!this.filas.isEmpty()) {
			Set<String> dataKeys = this.filas.keySet();
			for (String k : dataKeys) {
				posX = 0;
				final List<String> valores = this.filas.get(k);
				final List<String> alignVals = this.aligns.get(k);
				final List<Boolean> numberFormatVals = this.numberFormat.get(k);
				final List<Boolean> dateFormatVals = this.dateFormat.get(k);

				// Bottom border ultima linea
				if (posY - 1 == dataKeys.size()) {
					estiloTrados.setBorderBottom((short) 1);
					estiloTrados.setBottomBorderColor(HSSFColor.BLACK.index);
					numericDecimal.setBorderBottom((short) 1);
					numericDecimal.setBottomBorderColor(HSSFColor.BLACK.index);
					numeric.setBorderBottom((short) 1);
					numeric.setBottomBorderColor(HSSFColor.BLACK.index);
					date.setBorderBottom((short) 1);
					date.setBottomBorderColor(HSSFColor.BLACK.index);
					center.setBorderBottom((short) 1);
					center.setBottomBorderColor(HSSFColor.BLACK.index);
					right.setBorderBottom((short) 1);
					right.setBottomBorderColor(HSSFColor.BLACK.index);
					left.setBorderBottom((short) 1);
					left.setBottomBorderColor(HSSFColor.BLACK.index);
				}

				for (int x = 0; x < valores.size(); x++) {
					String string = valores.get(x);
					String alignVal = alignVals != null ? alignVals.get(x) : "";
					Boolean numericData = numberFormatVals != null && numberFormatVals.size() > 0
							? numberFormatVals.get(x)
							: false;
					Boolean dateFormat = dateFormatVals != null && dateFormatVals.size() > 0 ? dateFormatVals.get(x)
							: false;
					Boolean withDecimal = false;

					// Obtenemos la fila y celda.
					XSSFRow fila = hoja.getRow(this.posicionY + posY);
					if (fila == null) {
						fila = hoja.createRow(this.posicionY + posY);
					}
					XSSFCell celda = fila.getCell(this.posicionX + posX);
					if (celda == null) {
						celda = fila.createCell(this.posicionX + posX);
					}

					// Escribimos el valor de la celda.
					String strValorCelda = "";
					if (string != null) {
						strValorCelda = string;
					}
					// Ponemos el tipo de celda.
					if (numericData) {
						if (isNumeric(strValorCelda.replace(".", "").replace(",", "."))) {
							celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
							if (strValorCelda.contains(",") || strValorCelda.contains(".")) {
								withDecimal = true;
								strValorCelda = strValorCelda.replace(".", "").replace(",", ".");
								celda.setCellValue(Double.valueOf(strValorCelda));
							} else {
								withDecimal = false;
								if (strValorCelda != "") {
									celda.setCellValue(Long.valueOf(strValorCelda));
								}
							}
						}else {
							celda.setCellType(XSSFCell.CELL_TYPE_STRING);
							celda.setCellValue(strValorCelda);
						}


					} else if (dateFormat) {
						celda.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
						if (strValorCelda != null && !"".equals(strValorCelda)) {
							try {
								celda.setCellValue(sdf.parse(strValorCelda));
							} catch (ParseException e) {
								try {
									celda.setCellValue(sdfEs.parse(strValorCelda));
								} catch (ParseException ex) {
									celda.setCellValue(strValorCelda);
								}
							}
						} else {
							celda.setCellValue(strValorCelda);
						}
					} else {
						celda.setCellType(XSSFCell.CELL_TYPE_STRING);
						celda.setCellValue(strValorCelda);
					}

					if (StringUtils.isNotBlank(this.getEstiloTabla())) {
						celda.setCellStyle(estiloTrados);
					} else {
						if (numericData && withDecimal) {
							celda.setCellStyle(numericDecimal);
						} else if (numericData) {
							celda.setCellStyle(numeric);
						} else if (dateFormat) {
							celda.setCellStyle(date);
						} else if ("center".equals(alignVal)) {
							celda.setCellStyle(center);
						} else if ("right".equals(alignVal)) {
							celda.setCellStyle(right);
						} else {
							celda.setCellStyle(center);
						}
					}

					// Activamos la celda
					celda.setAsActiveCell();
					posX++;
				}
				posY++;
			}

		} // Fin if de datos de la tabla*/

	}

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}


	private void procesarContenidoCabecera(Set<String> labelKeys, XSSFSheet hoja, XSSFWorkbook libro, int posX,
			int posY) {

		XSSFCellStyle cabecera = ExcelEstilo.getEstilo(libro, ExcelEstilo.HEADER, null);

		for (String k : labelKeys) {
			final String value = this.labels.get(k).toString();
			// Obtenemos la fila y celda.
			XSSFRow fila = hoja.getRow(this.posicionY + posY);
			if (fila == null) {
				fila = hoja.createRow(this.posicionY + posY);
			}
			XSSFCell celda = fila.getCell(this.posicionX + posX);
			if (celda == null) {
				celda = fila.createCell(this.posicionX + posX);
			}

			// Ponemos el tipo de celda.
			celda.setCellType(XSSFCell.CELL_TYPE_STRING);

			// Escribimos el valor de la celda.
			celda.setCellValue(value);

			// Estilo
			if (StringUtils.isNotBlank(this.getEstiloCabecera())
					&& ExcelEstilo.HEADER.equals(this.getEstiloCabecera())) {
				celda.setCellStyle(cabecera);
			} else if (StringUtils.isNotBlank(this.getEstiloCabecera())) {
				celda.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloCabecera(), null));
			}

			// Activamos la celda
			celda.setAsActiveCell();

			posX++;
		} // Fin for parametros

	}

	private void procesarContenidoCabeceraTrados(Set<String> labelKeys, XSSFSheet hoja, XSSFWorkbook libro, int posX,
			int posY) {

		int posInicialY = posY;

		XSSFCellStyle cabecera = ExcelEstilo.getEstiloTrados(libro, ExcelEstilo.HEADER, null);
		cabecera.setAlignment(HorizontalAlignment.CENTER);
		cabecera.setVerticalAlignment(VerticalAlignment.CENTER);
		XSSFCellStyle cabeceraTrados = ExcelEstilo.getEstilo(libro, ExcelEstilo.HEADER_TRADOS, null);

		for (String k : labelKeys) {
			int incrementarX = 1;
			boolean asignarEstilo = true;

			final String value = this.labels.get(k).toString();
			// Obtenemos la fila y celda.
			XSSFRow fila = hoja.getRow(this.posicionY + posY);
			if (fila == null) {
				fila = hoja.createRow(this.posicionY + posY);
			}
			XSSFCell celda = fila.getCell(this.posicionX + posX);
			if (celda == null) {
				celda = fila.createCell(this.posicionX + posX);
			}
			XSSFRow filaSiguiente = hoja.getRow(this.posicionY + posY + 1);
			if (filaSiguiente == null) {
				filaSiguiente = hoja.createRow(this.posicionY + posY + 1);
			}
			XSSFCell celdaSiguiente = filaSiguiente.getCell(this.posicionX + posX);
			if (celdaSiguiente == null) {
				celdaSiguiente = filaSiguiente.createCell(this.posicionX + posX);
			}
			if (posX < 18 || posX > 25) {
				CellRangeAddress merge = new CellRangeAddress(posY, posY + 1, posX, posX);
				hoja.addMergedRegion(merge);
			} else if ((posX == 18 || posX == 22) && posY == 0) {
				CellRangeAddress merge = new CellRangeAddress(posY, posY, posX, posX + 3);
				hoja.addMergedRegion(merge);

				for (int i = 0; i <= 4; i++) {
					XSSFCell celdaTrados = fila.getCell(this.posicionX + posX + i);
					if (celdaTrados == null) {
						celdaTrados = fila.createCell(this.posicionX + posX + i);
					}

					celdaTrados.setCellStyle(cabeceraTrados);
					asignarEstilo = false;
		        }

				incrementarX = 0;
				posY = posY + 1;
			} else if (posX == 21 || posX == 25) {
				posY = posInicialY;
			}

			// Ponemos el tipo de celda.
			celda.setCellType(XSSFCell.CELL_TYPE_STRING);

			// Escribimos el valor de la celda.
			celda.setCellValue(value);

			// Estilo
			if (StringUtils.isNotBlank(this.getEstiloCabecera())
					&& ExcelEstilo.HEADER.equals(this.getEstiloCabecera())) {
				if ((posX != 18 && posX != 22) || (asignarEstilo && posY == 1)) {
					celda.setCellStyle(cabecera);
				}
				if (posX < 18 || posX > 25) {
					celdaSiguiente.setCellStyle(cabecera);
				}
			} else if (StringUtils.isNotBlank(this.getEstiloCabecera())) {
				if ((posX != 18 && posX != 22) || (asignarEstilo && posY == 1)) {
					celda.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloCabecera(), null));
				}
				if (posX < 18 || posX > 25) {
					celdaSiguiente.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloCabecera(), null));
				}
			}

			// Activamos la celda
			celda.setAsActiveCell();

			if (posX == 4) {
				hoja.setColumnWidth(posX, 15000);
			} else if (posX == 6 || posX == 10 || posX == 12) {
				hoja.setColumnWidth(posX, 10000);
			} else {
				hoja.setColumnWidth(posX, value.length() * 400);
			}

			posX = posX + incrementarX;

		} // Fin for parametros

	}

}
