package com.ejie.aa79b.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.common.exceptions.AppRuntimeException;
import com.ejie.aa79b.model.excel.ExcelCelda;
import com.ejie.aa79b.model.excel.ExcelContent;
import com.ejie.aa79b.model.excel.ExcelEstilo;
import com.ejie.aa79b.model.excel.ExcelHoja;
import com.ejie.aa79b.model.excel.ExcelModel;
import com.ejie.aa79b.model.excel.content.ExcelCriterios;
import com.ejie.aa79b.model.excel.content.ExcelTable;
import com.ejie.aa79b.model.excel.content.ExcelTableJson;
import com.ejie.x38.json.JSONArray;

/**
 * @author dlopez2
 *
 */
@Service(value = "ExcelReportService")
public class ExcelReportServiceImpl implements ExcelReportService {

	@Autowired()
	private ReloadableResourceBundleMessageSource appMessageSource;

	/**
	 * LOGGER: Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReportServiceImpl.class);

	/**
	 * Este metodo sirve para generar el EXCEL
	 *
	 * @param templateName La plantilla
	 * @param excelModel   El modelo del PDF
	 * @param response     HttpServletResponse
	 */
	@Override()
	public void generarExcel(String templateName, ExcelModel excelModel, HttpServletResponse response) {
		try {

			String propiedad = System.getProperty("java.awt.headless");
			ExcelReportServiceImpl.LOGGER.info("EXCEL propiedad de servidor [java.awt.headless]: " + propiedad);

			// Generar el libro y los estilos del libro.
			XSSFWorkbook libro = this.crearLibro(templateName, excelModel);
			// Escribimos el Excel en la response.
			this.writeResponse(templateName, excelModel.getNombre(), response, libro);

		} catch (Exception e) {
			ExcelReportServiceImpl.LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * @param templateName String
	 * @param excelModel   ExcelModel
	 * @return crearLibro
	 * @throws IOException IOException
	 */
	private XSSFWorkbook crearLibro(String templateName, ExcelModel excelModel) throws IOException {

		XSSFWorkbook libro = new XSSFWorkbook();
		this.procesarHojas(libro, excelModel.getListaHojas());
		// primera hoja activa
		libro.setSelectedTab(0);
		return libro;
	}

	/**
	 * @param templateName String
	 * @param excelModel   ExcelModel
	 * @return crearLibro
	 * @throws IOException IOException
	 */
	private XSSFWorkbook crearLibroTrados(String templateName, ExcelModel excelModel) throws IOException {

		XSSFWorkbook libro = new XSSFWorkbook();
		this.procesarHojasTrados(libro, excelModel.getListaHojas());
		// primera hoja activa
		libro.setSelectedTab(0);
		return libro;
	}

	/**
	 * @param libro      XSSFWorkbook
	 * @param listaHojas List
	 * @throws IOException IOException
	 */
	public void procesarHojas(XSSFWorkbook libro, List<ExcelHoja> listaHojas) throws IOException {

		if (!listaHojas.isEmpty()) {
			for (ExcelHoja excelHoja : listaHojas) {
				String nombre = WorkbookUtil.createSafeSheetName(excelHoja.getNombreHoja());
				final XSSFSheet hoja = libro.createSheet(nombre);
				this.procesarParametros(libro, hoja, excelHoja.getParametros());
				this.procesarContenido(libro, hoja, excelHoja.getContenido());
			}
		} else {
			libro.createSheet();
		}
	}

	/**
	 * @param libro      XSSFWorkbook
	 * @param listaHojas List
	 * @throws IOException IOException
	 */
	public void procesarHojasTrados(XSSFWorkbook libro, List<ExcelHoja> listaHojas) throws IOException {

		if (!listaHojas.isEmpty()) {
			for (ExcelHoja excelHoja : listaHojas) {
				String nombre = WorkbookUtil.createSafeSheetName(excelHoja.getNombreHoja());
				final XSSFSheet hoja = libro.createSheet(nombre);
				this.procesarParametros(libro, hoja, excelHoja.getParametros());
				this.procesarContenidoTrados(libro, hoja, excelHoja.getContenido());
			}
		} else {
			libro.createSheet();
		}
	}

	/**
	 * @param libro      XSSFWorkbook
	 * @param hoja       XSSFSheet
	 * @param contenidos List
	 */
	public void procesarContenido(XSSFWorkbook libro, XSSFSheet hoja, List<ExcelContent> contenidos) {

		for (ExcelContent excelContent : contenidos) {
			excelContent.procesarContenido(libro, hoja);
		}
	}

	/**
	 * @param libro      XSSFWorkbook
	 * @param hoja       XSSFSheet
	 * @param contenidos List
	 */
	public void procesarContenidoTrados(XSSFWorkbook libro, XSSFSheet hoja, List<ExcelContent> contenidos) {

		for (ExcelContent excelContent : contenidos) {
			excelContent.procesarContenidoTrados(libro, hoja);
		}
	}

	/**
	 * @param libro      XSSFWorkbook
	 * @param hoja       XSSFSheet
	 * @param parametros Map
	 * @throws IOException e
	 */
	public void procesarParametros(XSSFWorkbook libro, XSSFSheet hoja, Map<String, ExcelCelda> parametros)
			throws IOException {

		for (String key : parametros.keySet()) {
			final ExcelCelda aa79bCelda = parametros.get(key);

			if (aa79bCelda.getTipoCelda().equals(Reports.TIPO_CELDA_IMAGEN)) {
				InputStream is = ExcelReportServiceImpl.class.getResourceAsStream(aa79bCelda.getRutaImagen());
				byte[] bytes = IOUtils.toByteArray(is);
				int pictureIdx = libro.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
				is.close();
				CreationHelper helper = libro.getCreationHelper();
				Drawing drawing = hoja.createDrawingPatriarch();
				ClientAnchor anchor = helper.createClientAnchor();
				anchor.setCol1(aa79bCelda.getPosicionX());
				anchor.setRow1(aa79bCelda.getPosicionY());
				Picture pict = drawing.createPicture(anchor, pictureIdx);
				pict.resize();
			} else {
				// Obtenemos la fila y celda.
				XSSFRow fila = hoja.getRow(aa79bCelda.getPosicionY());
				if (fila == null) {
					fila = hoja.createRow(aa79bCelda.getPosicionY());
				}
				XSSFCell celda = fila.getCell(aa79bCelda.getPosicionX());
				if (celda == null) {
					celda = fila.createCell(aa79bCelda.getPosicionX());
				}

				// Ponemos el tipo de celda.
				celda.setCellType(XSSFCell.CELL_TYPE_STRING);

				// Escribimos el valor de la celda.
				celda.setCellValue(aa79bCelda.getDato());

				// Estilo
				if (StringUtils.isNotBlank(aa79bCelda.getEstilo())) {
					celda.setCellStyle(ExcelEstilo.getEstilo(libro, aa79bCelda.getEstilo(), null));
				}

				// Activamos la celda
				celda.setAsActiveCell();
			}

		}
	}

	/**
	 * @param templateName String
	 * @param nombre       String
	 * @param response     HttpServletResponse
	 * @param libro        XSSFWorkbook
	 * @throws IOException IOException
	 */
	public void writeResponse(String templateName, String nombre, HttpServletResponse response, XSSFWorkbook libro)
			throws IOException {

		Cookie cookie = new Cookie("fileDownload", "true");
		cookie.setPath("/");
		response.addCookie(cookie);
		response.getOutputStream().close();
		response.setHeader("Pragma", "cache");
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "private");
		// Content
		response.setContentType(Constants.CONTENT_TYPE_EXCEL);
		// Nombre Fichero
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + ".xlsx" + "\"");

		// Flush byte array to servlet output stream.
		ServletOutputStream out = response.getOutputStream();
		out.flush();
		libro.write(out);
		out.flush();
	}

	/**
	 *
	 * The method getCabeceras.
	 *
	 * @param columns String
	 * @param locale  Locale
	 * @return LinkedHashMap
	 */
	@Override()
	public Map<String, String> obtenerNombreColumnas(String columns, Locale locale) {

		LinkedHashMap<String, String> labels = new LinkedHashMap<String, String>();
		JSONArray jsonArr = new JSONArray(columns);
		for (int i = 0; i < jsonArr.length(); ++i) {
			JSONArray column = (JSONArray) jsonArr.get(i);
			String label = (String) column.get(1);
			String[] cabeceras = label.split("#");
			String value = this.appMessageSource.getMessage(cabeceras[0], null, locale);
			for (int x = 1; x < cabeceras.length; ++x) {
				value += " " + this.appMessageSource.getMessage(cabeceras[x], null, locale);
			}
			labels.put(label, value);
		}
		return labels;
	}

	/**
	 *
	 * The method getCabeceras.
	 *
	 * @param criterios los cirterios
	 * @param locale    Locale
	 * @return lista con las cabeceras
	 */
	@Override()
	public Map<String, String> obtenerNombreCriterios(String criterios, Locale locale) {
		LinkedHashMap<String, String> excelCriterios = new LinkedHashMap<String, String>();
		JSONArray jsonArr = new JSONArray(criterios);
		for (int i = 0; i < jsonArr.length(); ++i) {
			JSONArray criterio = (JSONArray) jsonArr.get(i);
			String label = (String) criterio.get(0);
			String valor = (String) criterio.get(1);
			excelCriterios.put(this.appMessageSource.getMessage(label, null, locale), valor);
		}
		return excelCriterios;
	}

	@Override()
	public ExcelModel getExcelModel(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo, String nombre) {
		return this.getExcelModel(criterios, columnas, resultadoBusqueda, locale, titulo, nombre, false);
	}

	@Override()
	public ExcelModel getExcelModelJson(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo, String nombre) {
		return this.getExcelModel(criterios, columnas, resultadoBusqueda, locale, titulo, nombre, true);
	}

	private ExcelModel getExcelModel(String criterios, String columnas, List<?> resultadoBusqueda, Locale locale,
			String titulo, String nombre, Boolean json) {
		try {
			String strTitulo = this.appMessageSource.getMessage(titulo, new Object[] {}, locale);
			final ExcelModel excelModel = new ExcelModel();
			excelModel.setNombre(nombre);
			String tituloHoja = StringUtils.substring(strTitulo, 0, Constants.TREINTAYUNO);
			tituloHoja = StringUtils.replace(strTitulo, "/", "-");
			final ExcelHoja excelHoja = new ExcelHoja(tituloHoja, 0);

			// Parametros
			ExcelCelda imagenCelda = new ExcelCelda();
			imagenCelda.setTipoCelda(Reports.TIPO_CELDA_IMAGEN);
			imagenCelda.setRutaImagen(Reports.RUTA_IMAGEN);
			imagenCelda.setPosicionX(0);
			imagenCelda.setPosicionX(0);

			ExcelCelda tituloCelda = new ExcelCelda(strTitulo, Constants.TRES, Constants.DOS, ExcelEstilo.TITLE);
			excelHoja.addParametro("imagen", imagenCelda);
			excelHoja.addParametro("titulo", tituloCelda);
			// Criterios
			if (StringUtils.isNotBlank(criterios)) {
				final ExcelCriterios excelCriterios = new ExcelCriterios(Constants.UNO, Constants.SEIS, Constants.DOS,
						false);
				excelCriterios.setEstiloCriterio(ExcelEstilo.TITLE);
				excelCriterios.setCriterios(this.obtenerNombreCriterios(criterios, locale));
				excelHoja.addContenido(excelCriterios);
			}
			// Tabla
			final ExcelTable tabla = json ? new ExcelTableJson(1, 0, true) : new ExcelTable(1, 0, true);
			tabla.setLabels(this.obtenerNombreColumnas(columnas, locale));
			tabla.addContenidoTabla(columnas, resultadoBusqueda);
			tabla.setEstiloCabecera(ExcelEstilo.HEADER);
			excelHoja.addContenido(tabla);

			excelModel.getListaHojas().add(excelHoja);

			return excelModel;
		} catch (Exception e) {
			throw new AppRuntimeException(e);
		}
	}

	@Override()
	public void generarExcel(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, Locale locale, String titulo, String fichero) {
		ExcelModel excelModel = this.getExcelModel(criterios, columnas, resultadoBusqueda, locale, titulo, fichero);
		this.generarExcel(template, excelModel, response);
	}

	@Override()
	public void generarExcelJson(String template, HttpServletResponse response, String criterios, String columnas,
			List<?> resultadoBusqueda, Locale locale, String titulo, String fichero) {
		ExcelModel excelModel = this.getExcelModel(criterios, columnas, resultadoBusqueda, locale, titulo, fichero,
				true);
		this.generarExcel(template, excelModel, response);
	}

	/**
	 * Este metodo sirve para generar el EXCEL
	 *
	 * @param templateName La plantilla
	 * @param excelModel   El modelo del PDF
	 * @throws Exception e
	 * @return byte[]
	 */
	@Override()
	public byte[] generarExcel(String templateName, ExcelModel excelModel) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String propiedad = System.getProperty("java.awt.headless");
		ExcelReportServiceImpl.LOGGER.info("EXCEL propiedad de servidor [java.awt.headless]: " + propiedad);
		// Generar el libro y los estilos del libro.
		XSSFWorkbook libro = this.crearLibro(templateName, excelModel);
		libro.write(bos);
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * Este metodo sirve para generar el EXCEL
	 *
	 * @param templateName La plantilla
	 * @param excelModel   El modelo del PDF
	 * @throws Exception e
	 * @return byte[]
	 */
	@Override()
	public byte[] generarExcelTrados(String templateName, ExcelModel excelModel) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String propiedad = System.getProperty("java.awt.headless");
		ExcelReportServiceImpl.LOGGER.info("EXCEL propiedad de servidor [java.awt.headless]: " + propiedad);
		// Generar el libro y los estilos del libro.
		XSSFWorkbook libro = this.crearLibroTrados(templateName, excelModel);
		libro.write(bos);
		bos.close();
		return bos.toByteArray();
	}

	@Override()
	public List<List<String>> leerFichero(InputStream fichero) throws IOException {
		List<List<String>> rdo = new ArrayList<List<String>>();
		Workbook wb = null;
		if (POIFSFileSystem.hasPOIFSHeader(fichero)) {
			POIFSFileSystem fs = new POIFSFileSystem(fichero);
			wb = new HSSFWorkbook(fs);
		} else {
			wb = new XSSFWorkbook(fichero);
		}
		Sheet sheet = wb.getSheetAt(0);
		Row row;
		Integer rows = sheet.getLastRowNum() - sheet.getFirstRowNum();
		// No of columns
		Integer cols = 0;
		Integer tmp = 0;

		// This trick ensures that we get the data properly even if it doesn't ,
		// start from first few rows
		for (int i = 0; i < Constants.DIEZ || i < rows; i++) {
			row = sheet.getRow(i);
			if (row != null) {
				tmp = row.getPhysicalNumberOfCells();
				if (tmp > cols) {
					cols = tmp;
				}
			}
		}

		for (int r = 0; r <= rows; r++) {
			row = sheet.getRow(r);
			if (row != null) {
				List<String> linea = new ArrayList<String>();

				linea = this.leerficheroCell(linea, cols, row);
				rdo.add(linea);
			}
		}
		return rdo;
	}

	private List<String> leerficheroCell(List<String> linea, Integer cols, Row row) {
		Cell cell = null;
		for (int c = 0; c < cols; c++) {
			cell = row.getCell((short) c);
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					linea.add(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					numericCellCase(linea, cell);
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					linea.add(String.valueOf(cell.getBooleanCellValue()));
					break;
				case Cell.CELL_TYPE_FORMULA:
					linea.add(cell.getCellFormula());
					break;
				default:
					linea.add(cell.getStringCellValue());
				}
			} else {
				linea.add("");
			}
		}
		return linea;
	}

	private void numericCellCase(List<String> linea, Cell cell) {
		double valorDouble = cell.getNumericCellValue();
		int valorInt = (int) valorDouble;
		if (valorDouble == valorInt) {
			linea.add(String.valueOf(valorInt));
		} else {
			linea.add(String.valueOf(valorDouble));
		}
	}

}
