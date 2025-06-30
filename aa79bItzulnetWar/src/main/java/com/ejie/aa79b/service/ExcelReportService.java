package com.ejie.aa79b.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ejie.aa79b.model.excel.ExcelModel;

/**
 * @author dlopez2
 *
 */
public interface ExcelReportService {
	/**
	 * Este metodo sirve para generar el Excel
	 *
	 * @param template
	 *            La plantilla
	 * @param excelModel
	 *            El modelo del Excel
	 * @param response
	 *            String
	 */
	public void generarExcel(String template, ExcelModel excelModel, HttpServletResponse response);

	/**
	 * Este metodo sirve para generar el Excel
	 *
	 * @param template
	 *            La plantilla
	 * @param excelModel
	 *            El modelo del Excel
	 * @throws Exception
	 *             Exception
	 * @return byte[]
	 */
	public byte[] generarExcel(String template, ExcelModel excelModel) throws Exception;

	/**
	 * Este metodo sirve para generar el Excel
	 *
	 * @param template
	 *            La plantilla
	 * @param excelModel
	 *            El modelo del Excel
	 * @throws Exception
	 *             Exception
	 * @return byte[]
	 */
	public byte[] generarExcelTrados(String template, ExcelModel excelModel) throws Exception;

	/**
	 * @param columnas
	 *            String
	 * @param locale
	 *            String
	 * @return Map
	 */
	public Map<String, String> obtenerNombreColumnas(String columnas, Locale locale);

	/**
	 *
	 * The method getCabeceras.
	 *
	 * @param criterios
	 *            los cirterios
	 * @param locale
	 *            Locale
	 * @return lista con las cabeceras
	 */
	public Map<String, String> obtenerNombreCriterios(String criterios, Locale locale);

	/**
	 * @param criterios
	 *            String
	 * @param columnas
	 *            String
	 * @param resultadoBusqueda
	 *            JQGridResponseDto
	 * @param locale
	 *            Locale
	 * @param titulo
	 *            String
	 * @param nombre
	 *            String
	 * @return ExcelModel
	 * @throws SecurityException
	 *             SecurityException
	 * @throws IllegalArgumentException
	 *             IllegalArgumentException
	 * @throws NoSuchMethodException
	 *             NoSuchMethodException
	 * @throws IllegalAccessException
	 *             IllegalAccessException
	 * @throws InvocationTargetException
	 *             InvocationTargetException
	 */
	public ExcelModel getExcelModel(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo, String nombre);

	public ExcelModel getExcelModelJson(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo, String nombre);

	/**
	 * The method generarExcel.
	 *
	 * @param template
	 *            String
	 * @param response
	 *            HttpServletResponse
	 * @param criterios
	 *            String
	 * @param columnas
	 *            String
	 * @param resultadoBusqueda
	 *            List<?>
	 * @param locale
	 *            Locale
	 * @param titulo
	 *            String
	 * @param fichero
	 *            String
	 * @throws Exception
	 *             e
	 */
	public void generarExcel(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, Locale locale, String titulo, String fichero);

	/**
	 * @param fichero
	 *            InputStream
	 * @return List<List<String>>
	 * @throws IOException
	 *             e
	 */
	public List<List<String>> leerFichero(InputStream fichero) throws IOException;

	void generarExcelJson(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, Locale locale, String titulo, String fichero);
}
