package com.ejie.aa79b.model.excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author dlopez2
 *
 */
public abstract class ExcelContent {

	/**
	 * @param libro
	 *            XSSFWorkbook
	 * @param hoja
	 *            XSSFSheet
	 */
	public abstract void procesarContenido(XSSFWorkbook libro, XSSFSheet hoja);

	/**
	 * @param libro
	 *            XSSFWorkbook
	 * @param hoja
	 *            XSSFSheet
	 */
	public abstract void procesarContenidoTrados(XSSFWorkbook libro, XSSFSheet hoja);
}
