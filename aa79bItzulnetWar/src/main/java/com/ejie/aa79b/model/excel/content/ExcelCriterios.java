package com.ejie.aa79b.model.excel.content;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.Reports;
import com.ejie.aa79b.model.excel.ExcelContent;
import com.ejie.aa79b.model.excel.ExcelEstilo;

/**
 * @author dlopez2
 *
 */
public class ExcelCriterios extends ExcelContent {
	/**
	 * Los criterios a mostrar
	 */
	private Map<String, String> criterios = new LinkedHashMap<String, String>();
	/**
	 * Posición X del criterio posicionX String
	 */
	private int posicionX = 0;
	/**
	 * Posición Y del criterio posicionY String
	 */
	private int posicionY = 0;

	/**
	 * Indica en cuantas columnas se van a mostrar los criterios numColumnas int
	 */
	private int numColumnas = 1;

	/**
	 * Si el campo es true, los criterios se pintarán a partir de la última celda
	 * activa celdaActiva boolean
	 */
	private boolean celdaActiva = false;

	/**
	 * Indica el estilo del criterio estiloCriterio String
	 */
	private String estiloCriterio = "";

	/**
	 * Indica el estilo del dato del criterio estiloDato String
	 */
	private String estiloDato = "";

	/**
	 * @param posicionX   int
	 * @param posicionY   int
	 * @param numColumnas int
	 * @param celdaActiva boolean
	 */
	public ExcelCriterios(int posicionX, int posicionY, int numColumnas, boolean celdaActiva) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.numColumnas = numColumnas;
		this.celdaActiva = celdaActiva;
	}

	/**
	 * @return int
	 */
	public int getPosicionX() {
		return this.posicionX;
	}

	/**
	 * @param posicionX int
	 */
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * @return int
	 */
	public int getPosicionY() {
		return this.posicionY;
	}

	/**
	 * @param posicionY int
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * @return int
	 */
	public int getNumColumnas() {
		return this.numColumnas;
	}

	/**
	 * @param numColumnas int
	 */
	public void setNumColumnas(int numColumnas) {
		this.numColumnas = numColumnas;
	}

	/**
	 * @return celdaActiva
	 */
	public boolean isCeldaActiva() {
		return this.celdaActiva;
	}

	/**
	 * @param celdaActiva boolean
	 */
	public void setCeldaActiva(boolean celdaActiva) {
		this.celdaActiva = celdaActiva;
	}

	/**
	 * @return String
	 */
	public String getEstiloCriterio() {
		return this.estiloCriterio;
	}

	/**
	 * @param estiloCriterio String
	 */
	public void setEstiloCriterio(String estiloCriterio) {
		this.estiloCriterio = estiloCriterio;
	}

	/**
	 * @return String
	 */
	public String getEstiloDato() {
		return this.estiloDato;
	}

	/**
	 * @param estiloDato String
	 */
	public void setEstiloDato(String estiloDato) {
		this.estiloDato = estiloDato;
	}

	/**
	 * @return String
	 */
	public Map<String, String> getCriterios() {
		return this.criterios;
	}

	/**
	 * @param key   String
	 * @param value String
	 */
	public void addCriterio(String key, String value) {
		this.criterios.put(key, (value != null) ? value : "");
	}

	/**
	 * @param criterios Map
	 */
	public void setCriterios(Map<String, String> criterios) {
		this.criterios = criterios;
	}

	/**
	 * @param key String
	 */
	public void removeCriterio(String key) {
		this.criterios.remove(key);
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

		// criterios
		int numCriteriosfila = 0;
		int posX = 0;
		int posY = 0;
		if (this.celdaActiva) {
			posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + 1;
		}
		for (String key : this.criterios.keySet()) {
			final String value = this.criterios.get(key);

			if (this.numColumnas == numCriteriosfila) {
				posY++;
				posX = 0;
				numCriteriosfila = 0;
			}

			// Obtenemos la fila y celda.

			XSSFRow fila = hoja.getRow(this.posicionY + posY);
			if (fila == null) {
				fila = hoja.createRow(this.posicionY + posY);
			}
			XSSFCell celdaTexto = fila.getCell(this.posicionX + posX);
			if (celdaTexto == null) {
				celdaTexto = fila.createCell(this.posicionX + posX);
			}
			XSSFCell celdaValor = fila.getCell(this.posicionX + posX + 1);
			if (celdaValor == null) {
				celdaValor = fila.createCell(this.posicionX + posX + 1);
			}

			// Ponemos el tipo de celda.
			celdaTexto.setCellType(XSSFCell.CELL_TYPE_STRING);

			// Escribimos el valor de la celda.
			celdaTexto.setCellValue(key.trim());

			// Estilo
			if (StringUtils.isNotBlank(this.getEstiloCriterio())) {
				celdaTexto.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloCriterio(), null));
			}

			// Activamos la celda
			celdaTexto.setAsActiveCell();

			// Ponemos el tipo de celda.
			celdaValor.setCellType(XSSFCell.CELL_TYPE_STRING);

			// Escribimos el valor de la celda.
			celdaValor.setCellValue(value);

			// Estilo
			if (StringUtils.isNotBlank(this.getEstiloDato())) {
				celdaValor.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloDato(), null));
			}

			// Activamos la celda
			celdaValor.setAsActiveCell();

			posX = posX + Constants.TRES;
			numCriteriosfila++;
		}

		// Autoajustar tamaÃ±o columnas
		int columnNumber = (this.numColumnas * Constants.TRES) - 1;
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

		// criterios
		int numCriteriosfila = 0;
		int posX = 0;
		int posY = 0;
		if (this.celdaActiva) {
			posY = Reports.obtenerFilaActual(hoja.getActiveCell()) + 1;
		}
		for (String key : this.criterios.keySet()) {
			final String value = this.criterios.get(key);

			if (this.numColumnas == numCriteriosfila) {
				posY++;
				posX = 0;
				numCriteriosfila = 0;
			}

			// Obtenemos la fila y celda.

			XSSFRow fila = hoja.getRow(this.posicionY + posY);
			if (fila == null) {
				fila = hoja.createRow(this.posicionY + posY);
			}
			XSSFCell celdaTexto = fila.getCell(this.posicionX + posX);
			if (celdaTexto == null) {
				celdaTexto = fila.createCell(this.posicionX + posX);
			}
			XSSFCell celdaValor = fila.getCell(this.posicionX + posX + 1);
			if (celdaValor == null) {
				celdaValor = fila.createCell(this.posicionX + posX + 1);
			}

			// Ponemos el tipo de celda.
			celdaTexto.setCellType(XSSFCell.CELL_TYPE_STRING);

			// Escribimos el valor de la celda.
			celdaTexto.setCellValue(key.trim());

			// Estilo
			if (StringUtils.isNotBlank(this.getEstiloCriterio())) {
				celdaTexto.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloCriterio(), null));
			}

			// Activamos la celda
			celdaTexto.setAsActiveCell();

			// Ponemos el tipo de celda.
			celdaValor.setCellType(XSSFCell.CELL_TYPE_STRING);

			// Escribimos el valor de la celda.
			celdaValor.setCellValue(value);

			// Estilo
			if (StringUtils.isNotBlank(this.getEstiloDato())) {
				celdaValor.setCellStyle(ExcelEstilo.getEstilo(libro, this.getEstiloDato(), null));
			}

			// Activamos la celda
			celdaValor.setAsActiveCell();

			posX = posX + Constants.TRES;
			numCriteriosfila++;
		}

		// Autoajustar tamaÃ±o columnas
		int columnNumber = (this.numColumnas * Constants.TRES) - 1;
		for (int i = 0; i < columnNumber; i++) {
			hoja.autoSizeColumn(this.posicionX + i);
		}

	}

	/**
	 * @param key String
	 */
	public void deleteCriterio(String key) {
		this.criterios.remove(key);

	}

}
