package com.ejie.aa79b.service;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import com.ejie.aa79b.model.pdf.PdfModel;
import com.ejie.aa79b.model.pdf.content.PdfCriterios;
import com.ejie.aa79b.model.pdf.content.PdfTable;

/**
 * @author dlopez2
 * 
 */
public interface PdfReportService {
	/**
	 * Este metodo sirve para generar el PDF
	 * 
	 * @param template
	 *            La plantilla
	 * @param pdfModel
	 *            El modelo del PDF
	 * @param outputStream
	 *            El stream de salida del pdf
	 */
	public void generarPdf(String template, PdfModel pdfModel, OutputStream outputStream, Locale locale);

	/**
	 * Este metodo sirve para generar el PDF
	 * 
	 * @param template
	 *            La plantilla
	 * @param pdfModel
	 *            El modelo del PDF
	 * @return byte[] los bytes del fichero
	 */
	public byte[] generarPdf(String template, PdfModel pdfModel, Locale locale);

	/**
	 * 
	 * The method getCabeceras.
	 * 
	 * @param columns
	 *            las columnas
	 * @return lista con las cabeceras
	 */
	public PdfTable obtenerNombreColumnas(String columns, Locale locale);

	/**
	 * 
	 * The method getCabeceras.
	 * 
	 * @param criterios
	 *            los cirterios
	 * @return lista con las cabeceras
	 */
	public PdfCriterios obtenerNombreCriterios(String criterios, Locale locale);

	/**
	 * 
	 * The method getPdfModel.
	 * 
	 * @param criterios
	 *            los criterios de buisqueda a imprimir
	 * @param columnas
	 *            que se pasan en la request
	 * @param resultadoBusqueda
	 *            el JQGridResponseDto
	 * @param titulo
	 *            String
	 * @return PdfModel
	 * @throws SecurityException
	 *             e
	 * @throws IllegalArgumentException
	 *             e
	 * @throws NoSuchMethodException
	 *             e
	 * @throws IllegalAccessException
	 *             e
	 * @throws InvocationTargetException
	 *             e
	 */
	public PdfModel getPdfModel(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo);

	public PdfModel getPdfModelJson(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo);

	/**
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
	 * @param titulo
	 *            String
	 * @param fichero
	 *            String
	 * @throws Exception
	 *             Exception
	 */
	public void generarPdf(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, String titulo, Locale locale, String fichero);

	void generarPdfJson(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, String titulo, Locale locale, String fichero);

}
