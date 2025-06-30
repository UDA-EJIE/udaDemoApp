package com.ejie.aa79b.model.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author grozadilla
 *
 */
public final class ExcelFuentes {
	/**
	 * DEFAULT_FONT_HEIGHT_IN_POINTS: short.
	 */
	private static final short DEFAULT_FONT_HEIGHT_IN_POINTS = 10;

	/**
	 * FONTS: Map.
	 */
	private static final Map<String, ExcelFuentes> FONTS = new HashMap<String, ExcelFuentes>();

	/**
	 * BLACK_ARIAL_10: String.
	 */
	public static final String BLACK_ARIAL_10 = "BLACK_ARIAL_10";
	/**
	 * BLACK_ARIAL_10_BOLD: String.
	 */
	public static final String BLACK_ARIAL_10_BOLD = "BLACK_ARIAL_10_BOLD";

	static {
		// BLACK ARIAL 10
		final ExcelFuentes blackArial10 = new ExcelFuentes();
		blackArial10.setColor(HSSFColor.BLACK.index);
		blackArial10.setFontName(HSSFFont.FONT_ARIAL);
		blackArial10.setFontHeightInPoints(ExcelFuentes.DEFAULT_FONT_HEIGHT_IN_POINTS);
		ExcelFuentes.FONTS.put(ExcelFuentes.BLACK_ARIAL_10, blackArial10);

		// BLACK ARIAL 10 BOLD
		final ExcelFuentes blackArial10Bold = new ExcelFuentes();
		blackArial10Bold.setColor(HSSFColor.BLACK.index);
		blackArial10Bold.setFontName(HSSFFont.FONT_ARIAL);
		blackArial10Bold.setFontHeightInPoints(ExcelFuentes.DEFAULT_FONT_HEIGHT_IN_POINTS);
		blackArial10Bold.setBoldWeight(HSSFFont.BOLDWEIGHT_BOLD);
		ExcelFuentes.FONTS.put(ExcelFuentes.BLACK_ARIAL_10_BOLD, blackArial10Bold);
	}

	/**
	 * Este metodo devuelve una celda con el formato de cabecera.
	 *
	 * @param key
	 *            La clave de la fuente
	 * @return Un celda de cabecera
	 */
	public static ExcelFuentes getFuente(String key) {
		if (ExcelFuentes.FONTS.containsKey(key)) {
			return ExcelFuentes.FONTS.get(key);
		} else {
			throw new IllegalArgumentException("Aa79bExcelFuentes: No existe la fuente " + key);
		}
	}

	/**
	 * Este metodo sirve para obtener las claves de las fuentes almacenadas.
	 *
	 * @return Las fuentes almacenadas
	 */
	public static Set<String> getKeys() {
		return ExcelFuentes.FONTS.keySet();
	}

	/**
	 * color: short.
	 */
	private short color = HSSFFont.COLOR_NORMAL;
	/**
	 * italic: boolean.
	 */
	private boolean italic = false;
	/**
	 * boldWeight: short.
	 */
	private short boldWeight = HSSFFont.BOLDWEIGHT_NORMAL;
	/**
	 * fontHeightInPoints: short.
	 */
	private short fontHeightInPoints = ExcelFuentes.DEFAULT_FONT_HEIGHT_IN_POINTS;
	/**
	 * fontName: String.
	 */
	private String fontName = HSSFFont.FONT_ARIAL;
	/**
	 * underline: byte.
	 */
	private byte underline = HSSFFont.U_NONE;

	/**
	 * El metodo constructor.
	 */
	public ExcelFuentes() {
		// Empty constructor
	}

	/**
	 * @return the color
	 */
	public short getColor() {
		return this.color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(short color) {
		this.color = color;
	}

	/**
	 * @return the italic
	 */
	public boolean isItalic() {
		return this.italic;
	}

	/**
	 * @param italic
	 *            the italic to set
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	/**
	 * @return the boldWeight
	 */
	public short getBoldWeight() {
		return this.boldWeight;
	}

	/**
	 * @param boldWeight
	 *            the boldWeight to set
	 */
	public void setBoldWeight(short boldWeight) {
		this.boldWeight = boldWeight;
	}

	/**
	 * @return the fontHeightInPoints
	 */
	public short getFontHeightInPoints() {
		return this.fontHeightInPoints;
	}

	/**
	 * @param fontHeightInPoints
	 *            the fontHeightInPoints to set
	 */
	public void setFontHeightInPoints(short fontHeightInPoints) {
		this.fontHeightInPoints = fontHeightInPoints;
	}

	/**
	 * @return the fontName
	 */
	public String getFontName() {
		return this.fontName;
	}

	/**
	 * @param fontName
	 *            the fontName to set
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/**
	 * @return the underline
	 */
	public byte getUnderline() {
		return this.underline;
	}

	/**
	 * @param underline
	 *            the underline to set
	 */
	public void setUnderline(byte underline) {
		this.underline = underline;
	}
}
