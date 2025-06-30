package com.ejie.aa79b.model.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author ilarburu
 */

/**
 * Titulo: Aa79bExcelEstilo. Empresa: Eurohelp Consulting Copyright: Copyright
 * (c) 2014
 *
 * @author dlopez
 * @version 1.0
 *
 */
public final class ExcelEstilo {
	/**
	 * STYLES: Map.
	 */
	private static final Map<String, ExcelEstilo> STYLES = new HashMap<String, ExcelEstilo>();
	/**
	 * DOBLE_LINE_BOTTOM_BORDER: short.
	 */
	private static final short DOBLE_LINE_BOTTOM_BORDER = 6;
	/**
	 * BOTTOM_BORDER: short.
	 */
	private static final short BOTTOM_BORDER = 1;
	/**
	 * BOTTOM_BORDER: short.
	 */
	private static final short RIGHT_BORDER = 1;
	/**
	 * CONTENT: String.
	 */
	public static final String CONTENT = "CONTENT";
	/**
	 * HEADER: String.
	 */
	public static final String HEADER = "HEADER";
	/**
	 * HEADER: String.
	 */
	public static final String HEADER_TRADOS = "HEADER_TRADOS";
	/**
	 * TITLE: String.
	 */
	public static final String TITLE = "TITLE";
	/**
	 * CONTENT_LEFT: String.
	 */
	public static final String CONTENT_LEFT = "CONTENT_LEFT";
	/**
	 * CONTENT_RIGHT: String.
	 */
	public static final String CONTENT_RIGHT = "CONTENT_RIGHT";
	/**
	 * CONTENT_CENTER: String.
	 */
	public static final String CONTENT_CENTER = "CONTENT_CENTER";
	/**
	 * FONDO_ROJO: String.
	 */
	public static final String FONDO_NARANJA_CLARO = "FONDO_NARANJA_CLARO";
	/**
	 * TITLE_FONDO_NARANJA_CLARO: String.
	 */
	public static final String TITLE_FONDO_NARANJA_CLARO = "TITLE_FONDO_NARANJA_CLARO";
	/**
	 * BORDER_TOP: String.
	 */
	public static final String BORDER_TOP = "BORDER_TOP";
	/**
	 * BORDER_BOTTOM: String.
	 */
	public static final String BORDER_BOTTOM = "BORDER_BOTTOM";
	/**
	 * BORDER_LEFT: String.
	 */
	public static final String BORDER_LEFT = "BORDER_LEFT";
	/**
	 * BORDER_RIGHT: String.
	 */
	public static final String BORDER_RIGHT = "BORDER_RIGHT";
	/**
	 * BORDER_TOP_LEFT: String.
	 */
	public static final String BORDER_TOP_LEFT = "BORDER_TOP_LEFT";
	/**
	 * BORDER_TOP_RIGHT: String.
	 */
	public static final String BORDER_TOP_RIGHT = "BORDER_TOP_RIGHT";
	/**
	 * BORDER_BOTTOM_LEFT: String.
	 */
	public static final String BORDER_BOTTOM_LEFT = "BORDER_BOTTOM_LEFT";
	/**
	 * BORDER_BOTTOM_RIGHT: String.
	 */
	public static final String BORDER_BOTTOM_RIGHT = "BORDER_BOTTOM_RIGHT";

	/**
	 * CURRENCY: String.
	 */
	public static final String CURRENCY = "CURRENCY";

	/**
	 * CURRENCY: String.
	 */
	public static final String DATE = "DATE";

	static {
		// DEFAULT
		final ExcelEstilo content = new ExcelEstilo();
		ExcelEstilo.STYLES.put(ExcelEstilo.CONTENT, content);

		// HEADER
		final ExcelEstilo header = new ExcelEstilo();
		header.setBorderBottom(ExcelEstilo.DOBLE_LINE_BOTTOM_BORDER);
		header.setBottomBorderColor(HSSFColor.BLACK.index);
		header.setFuente(ExcelFuentes.BLACK_ARIAL_10_BOLD);

		ExcelEstilo.STYLES.put(ExcelEstilo.HEADER, header);

		// HEADER_TRADOS
		final ExcelEstilo headerTrados = new ExcelEstilo();
		headerTrados.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerTrados.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerTrados.setBorderBottom(ExcelEstilo.BOTTOM_BORDER);
		headerTrados.setBottomBorderColor(HSSFColor.BLACK.index);
		headerTrados.setBorderRight(ExcelEstilo.RIGHT_BORDER);
		headerTrados.setRightBorderColor(HSSFColor.BLACK.index);
		headerTrados.setFuente(ExcelFuentes.BLACK_ARIAL_10_BOLD);

		ExcelEstilo.STYLES.put(ExcelEstilo.HEADER_TRADOS, headerTrados);

		// TITLE
		final ExcelEstilo title = new ExcelEstilo();
		title.setFuente(ExcelFuentes.BLACK_ARIAL_10_BOLD);
		ExcelEstilo.STYLES.put(ExcelEstilo.TITLE, title);

		// TITLE_FONDO_NARANJA_CLARO
		final ExcelEstilo titleFondoNaranjaClaro = new ExcelEstilo();
		titleFondoNaranjaClaro.setFuente(ExcelFuentes.BLACK_ARIAL_10_BOLD);
		titleFondoNaranjaClaro.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		titleFondoNaranjaClaro.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		ExcelEstilo.STYLES.put(ExcelEstilo.TITLE_FONDO_NARANJA_CLARO, titleFondoNaranjaClaro);

		// FONDO_NARANJA_CLARO
		final ExcelEstilo fondoRojo = new ExcelEstilo();
		fondoRojo.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		fondoRojo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		ExcelEstilo.STYLES.put(ExcelEstilo.FONDO_NARANJA_CLARO, fondoRojo);

		// CONTENT_LEFT
		final ExcelEstilo left = new ExcelEstilo();
		left.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		ExcelEstilo.STYLES.put(ExcelEstilo.CONTENT_LEFT, left);

		// CONTENT_RIGHT
		final ExcelEstilo right = new ExcelEstilo();
		right.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		ExcelEstilo.STYLES.put(ExcelEstilo.CONTENT_RIGHT, right);

		// CONTENT_CENTER
		final ExcelEstilo center = new ExcelEstilo();
		center.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ExcelEstilo.STYLES.put(ExcelEstilo.CONTENT_CENTER, center);

		// BORDER TOP DOUBLE
		final ExcelEstilo borderTopDouble = new ExcelEstilo();
		borderTopDouble.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_TOP, borderTopDouble);

		// BORDER BOTTOM DOUBLE
		final ExcelEstilo borderBottomDouble = new ExcelEstilo();
		borderBottomDouble.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_BOTTOM, borderBottomDouble);

		// BORDER LEFT DOUBLE
		final ExcelEstilo borderLeftDouble = new ExcelEstilo();
		borderLeftDouble.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_LEFT, borderLeftDouble);

		// BORDER RIGHT DOUBLE
		final ExcelEstilo borderRightDouble = new ExcelEstilo();
		borderRightDouble.setBorderRight(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_RIGHT, borderRightDouble);

		// BORDER TOP-LEFT DOUBLE
		final ExcelEstilo borderTopLeftDouble = new ExcelEstilo();
		borderTopLeftDouble.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
		borderTopLeftDouble.setLeftBorderColor(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_TOP_LEFT, borderTopDouble);

		// BORDER TOP-RIGHT DOUBLE
		final ExcelEstilo borderTopRightDouble = new ExcelEstilo();
		borderTopRightDouble.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);
		borderTopRightDouble.setRightBorderColor(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_TOP_RIGHT, borderTopRightDouble);

		// BORDER BOTTOM-LEFT DOUBLE
		final ExcelEstilo borderBottomLeftDouble = new ExcelEstilo();
		borderBottomLeftDouble.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
		borderBottomLeftDouble.setLeftBorderColor(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_BOTTOM_LEFT, borderBottomLeftDouble);

		// BORDER BOTTOM-RIGHT DOUBLE
		final ExcelEstilo borderBottomRightDouble = new ExcelEstilo();
		borderBottomRightDouble.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
		borderBottomRightDouble.setRightBorderColor(HSSFCellStyle.BORDER_DOUBLE);
		ExcelEstilo.STYLES.put(ExcelEstilo.BORDER_BOTTOM_RIGHT, borderBottomRightDouble);

		// CURRENCY
		final ExcelEstilo currency = new ExcelEstilo();
		currency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		ExcelEstilo.STYLES.put(ExcelEstilo.CURRENCY, currency);

		// DATE
		final ExcelEstilo date = new ExcelEstilo();
		date.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ExcelEstilo.STYLES.put(ExcelEstilo.DATE, date);

	}

	/**
	 * Este metodo sirve para obtener el estilo de la clave.
	 *
	 * @param libro XSSFWorkbook
	 * @param key   String
	 * @return XSSFCellStyle
	 */
	public static XSSFCellStyle getEstilo(XSSFWorkbook libro, String key, Boolean decimal) {
		if (ExcelEstilo.STYLES.containsKey(key)) {
			final XSSFCellStyle celdaEstilo = libro.createCellStyle();
			ExcelEstilo estilo = ExcelEstilo.STYLES.get(key);
			celdaEstilo.setAlignment(estilo.getAlignment());
			celdaEstilo.setVerticalAlignment(estilo.getVerticalAlignment());
			celdaEstilo.setBorderBottom(estilo.getBorderBottom());
			estilo.setBorderTop(estilo.getBorderTop());
			celdaEstilo.setBorderRight(estilo.getBorderRight());
			celdaEstilo.setBorderLeft(estilo.getBorderLeft());
			celdaEstilo.setBottomBorderColor(estilo.getBottomBorderColor());
			celdaEstilo.setTopBorderColor(estilo.getTopBoderColor());
			celdaEstilo.setRightBorderColor(estilo.getRightBorderColor());
			celdaEstilo.setLeftBorderColor(estilo.getLeftBorderColor());
			celdaEstilo.setFillForegroundColor(estilo.getFillForegroundColor());
			celdaEstilo.setDataFormat(estilo.getDataFormat());
			celdaEstilo.setFillPattern(estilo.getFillPattern());
			celdaEstilo.setFillBackgroundColor(estilo.getBackgroundColor());
			celdaEstilo.setLocked(estilo.isLocked());
			celdaEstilo.setWrapText(true);

			final XSSFFont fuenteFont = libro.createFont();
			final ExcelFuentes fuente = ExcelFuentes.getFuente(estilo.getFuente());
			fuenteFont.setColor(fuente.getColor());
			fuenteFont.setItalic(fuente.isItalic());
			fuenteFont.setBoldweight(fuente.getBoldWeight());
			fuenteFont.setFontHeightInPoints(fuente.getFontHeightInPoints());
			fuenteFont.setFontName(fuente.getFontName());
			fuenteFont.setUnderline(fuente.getUnderline());
			celdaEstilo.setFont(fuenteFont);
			if (key.equals(ExcelEstilo.CURRENCY) && decimal) {
				XSSFDataFormat df = libro.createDataFormat();
				short dataFormat = df.getFormat("#,##0.00##");
				celdaEstilo.setDataFormat(dataFormat);
			} else if (key.equals(ExcelEstilo.CURRENCY) && !decimal) {
				XSSFDataFormat df = libro.createDataFormat();
				short dataFormat = df.getFormat("0");
				celdaEstilo.setDataFormat(dataFormat);
			} else if (key.equals(ExcelEstilo.DATE)) {
				//"[$-eu-ES]yyyy\"(e)ko\" mmmm\"ren\" d\"(a)\" hh:mm;@";
				String formatoFecha = "yyyy/MM/dd hh:mm";
				CreationHelper createHelper = libro.getCreationHelper();
				celdaEstilo.setDataFormat(createHelper.createDataFormat().getFormat(formatoFecha));
				celdaEstilo.setAlignment(HorizontalAlignment.CENTER);
			}

			return celdaEstilo;

		} else {
			return libro.createCellStyle();
		}
	}

	public static XSSFCellStyle getEstiloAlineacionCelda(XSSFWorkbook libro, String align) {
		if ("right".equals(align)) {
			return ExcelEstilo.getEstilo(libro, ExcelEstilo.CONTENT_RIGHT, null);
		} else if ("center".equals(align)) {
			return ExcelEstilo.getEstilo(libro, ExcelEstilo.CONTENT_CENTER, null);
		} else {
			return ExcelEstilo.getEstilo(libro, ExcelEstilo.CONTENT_LEFT, null);
		}
	}

	/**
	 * Este metodo sirve para obtener las claves de los estilos almacenadas.
	 *
	 * @return Los estilos almacenadas
	 */
	public static Set<String> getKeys() {
		return ExcelEstilo.STYLES.keySet();
	}

	/**
	 * alignment: short.
	 */
	private short alignment = HSSFCellStyle.ALIGN_GENERAL;
	/**
	 * verticalAlignment: short.
	 */
	private short verticalAlignment = HSSFCellStyle.ALIGN_GENERAL;
	/**
	 * borderTop: short.
	 */
	private short borderTop = HSSFCellStyle.BORDER_NONE;
	/**
	 * borderBottom: short.
	 */
	private short borderBottom = HSSFCellStyle.BORDER_NONE;
	/**
	 * borderRight: short.
	 */
	private short borderRight = HSSFCellStyle.BORDER_NONE;
	/**
	 * borderLeft: short.
	 */
	private short borderLeft = HSSFCellStyle.BORDER_NONE;
	/**
	 * topBoderColor: short.
	 */
	private short topBoderColor = HSSFColor.WHITE.index;
	/**
	 * bottomBorderColor: short.
	 */
	private short bottomBorderColor = HSSFColor.WHITE.index;
	/**
	 * rightBorderColor: short.
	 */
	private short rightBorderColor = HSSFColor.WHITE.index;
	/**
	 * leftBorderColor: short.
	 */
	private short leftBorderColor = HSSFColor.WHITE.index;
	/**
	 * fillForegroundColor: short.
	 */
	private short fillForegroundColor = HSSFColor.WHITE.index;
	/**
	 * dataFormat: short.
	 */
	private short dataFormat = HSSFDataFormat.getBuiltinFormat("@");
	/**
	 * fillPattern: short.
	 */
	private short fillPattern = HSSFCellStyle.NO_FILL;
	/**
	 * backgroundColor: short.
	 */
	private short backgroundColor = HSSFColor.WHITE.index;
	/**
	 * locked: boolean.
	 */
	private boolean locked = false;
	/**
	 * fuente: String.
	 */
	private String fuente = ExcelFuentes.BLACK_ARIAL_10;

	/**
	 * El metodo constructor.
	 */
	public ExcelEstilo() {
		// Empty constructor
	}

	/**
	 * @return the alignment
	 */
	public short getAlignment() {
		return this.alignment;
	}

	/**
	 * @param alignment the alignment to set
	 */
	public void setAlignment(short alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the verticalAlignment
	 */
	public short getVerticalAlignment() {
		return this.verticalAlignment;
	}

	/**
	 * @param verticalAlignment the verticalAlignment to set
	 */
	public void setVerticalAlignment(short verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	/**
	 * @return the borderTop
	 */
	public short getBorderTop() {
		return this.borderTop;
	}

	/**
	 * @param borderTop the borderTop to set
	 */
	public void setBorderTop(short borderTop) {
		this.borderTop = borderTop;
	}

	/**
	 * @return the borderBottom
	 */
	public short getBorderBottom() {
		return this.borderBottom;
	}

	/**
	 * @param borderBottom the borderBottom to set
	 */
	public void setBorderBottom(short borderBottom) {
		this.borderBottom = borderBottom;
	}

	/**
	 * @return the borderRight
	 */
	public short getBorderRight() {
		return this.borderRight;
	}

	/**
	 * @param borderRight the borderRight to set
	 */
	public void setBorderRight(short borderRight) {
		this.borderRight = borderRight;
	}

	/**
	 * @return the borderLeft
	 */
	public short getBorderLeft() {
		return this.borderLeft;
	}

	/**
	 * @param borderLeft the borderLeft to set
	 */
	public void setBorderLeft(short borderLeft) {
		this.borderLeft = borderLeft;
	}

	/**
	 * @return the topBoderColor
	 */
	public short getTopBoderColor() {
		return this.topBoderColor;
	}

	/**
	 * @param topBoderColor the topBoderColor to set
	 */
	public void setTopBoderColor(short topBoderColor) {
		this.topBoderColor = topBoderColor;
	}

	/**
	 * @return the bottomBorderColor
	 */
	public short getBottomBorderColor() {
		return this.bottomBorderColor;
	}

	/**
	 * @param bottomBorderColor the bottomBorderColor to set
	 */
	public void setBottomBorderColor(short bottomBorderColor) {
		this.bottomBorderColor = bottomBorderColor;
	}

	/**
	 * @return the rightBorderColor
	 */
	public short getRightBorderColor() {
		return this.rightBorderColor;
	}

	/**
	 * @param rightBorderColor the rightBorderColor to set
	 */
	public void setRightBorderColor(short rightBorderColor) {
		this.rightBorderColor = rightBorderColor;
	}

	/**
	 * @return the leftBorderColor
	 */
	public short getLeftBorderColor() {
		return this.leftBorderColor;
	}

	/**
	 * @param leftBorderColor the leftBorderColor to set
	 */
	public void setLeftBorderColor(short leftBorderColor) {
		this.leftBorderColor = leftBorderColor;
	}

	/**
	 * @return the fillForegroundColor
	 */
	public short getFillForegroundColor() {
		return this.fillForegroundColor;
	}

	/**
	 * @param fillForegroundColor the fillForegroundColor to set
	 */
	public void setFillForegroundColor(short fillForegroundColor) {
		this.fillForegroundColor = fillForegroundColor;
	}

	/**
	 * @return the dataFormat
	 */
	public short getDataFormat() {
		return this.dataFormat;
	}

	/**
	 * @param dataFormat the dataFormat to set
	 */
	public void setDataFormat(short dataFormat) {
		this.dataFormat = dataFormat;
	}

	/**
	 * @return the fillPattern
	 */
	public short getFillPattern() {
		return this.fillPattern;
	}

	/**
	 * @param fillPattern the fillPattern to set
	 */
	public void setFillPattern(short fillPattern) {
		this.fillPattern = fillPattern;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return this.locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the fuente
	 */
	public String getFuente() {
		return this.fuente;
	}

	/**
	 * @param fuente the fuente to set
	 */
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	/**
	 * Este metodo sirve para obetener el valor de la propiedad backgroundColor.
	 *
	 * @author llaparra
	 * @return El valor de backgroundColor
	 */
	public short getBackgroundColor() {
		return this.backgroundColor;
	}

	/**
	 * Este metodo sirve para fijar el valor de la propiedad backgroundColor.
	 *
	 * @author llaparra
	 * @param backgroundColor El nuevo valor de backgroundColor
	 */
	public void setBackgroundColor(short backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public static XSSFCellStyle getEstiloTrados(XSSFWorkbook libro, String key, Boolean decimal) {
		XSSFCellStyle style = ExcelEstilo.getEstilo(libro, key, decimal);
		style.setBorderRight((short) 1);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		return style;
	}

	public static XSSFCellStyle getEstiloAlineacionCeldaTrados(XSSFWorkbook libro, String align) {
		if ("right".equals(align)) {
			return ExcelEstilo.getEstiloTrados(libro, ExcelEstilo.CONTENT_RIGHT, null);
		} else if ("center".equals(align)) {
			return ExcelEstilo.getEstiloTrados(libro, ExcelEstilo.CONTENT_CENTER, null);
		} else {
			return ExcelEstilo.getEstiloTrados(libro, ExcelEstilo.CONTENT_LEFT, null);
		}
	}

}
