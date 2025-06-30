package com.ejie.aa79b.model.excel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ejie.aa79b.common.Constants;

/**
 * @author dlopez2
 * 
 */
public final class ExcelHoja {
	private Map<String, ExcelCelda> parametros = new LinkedHashMap<String, ExcelCelda>();
	private List<ExcelContent> contenido = new ArrayList<ExcelContent>();
	private String nombreHoja = "";
	private int numeroHoja = 0;

	/**
	 * @param nombreHoja
	 *            String
	 * @param numeroHoja
	 *            int
	 */
	public ExcelHoja(String nombreHoja, int numeroHoja) {
		this.nombreHoja = StringUtils.substring(nombreHoja, 0, Constants.TREINTAYUNO);
		this.numeroHoja = numeroHoja;
	}

	/**
	 * @return String
	 */
	public Map<String, ExcelCelda> getParametros() {
		return this.parametros;
	}

	/**
	 * @param key
	 *            String
	 * @param celda
	 *            Aa79bExcelCelda
	 */
	public void addParametro(String key, ExcelCelda celda) {
		this.parametros.put(key, (celda != null) ? celda : new ExcelCelda());
	}

	/**
	 * @param key
	 *            String
	 */
	public void removeParametro(String key) {
		this.parametros.remove(key);
	}

	/**
	 * @return String
	 */
	public List<ExcelContent> getContenido() {
		return this.contenido;
	}

	/**
	 * @param excelContent
	 *            String
	 */
	public void addContenido(ExcelContent excelContent) {
		this.contenido.add(excelContent);
	}

	/**
	 * @param index
	 *            String
	 */
	public void removeContenido(int index) {
		this.contenido.remove(index);
	}

	/**
	 * @return String
	 */
	public String getNombreHoja() {
		return this.nombreHoja;
	}

	/**
	 * @param nombreHoja
	 *            String
	 */
	public void setNombreHoja(String nombreHoja) {
		this.nombreHoja = nombreHoja;
	}

	/**
	 * @return int
	 */
	public int getNumeroHoja() {
		return this.numeroHoja;
	}

	/**
	 * @param numeroHoja
	 *            int
	 */
	public void setNumeroHoja(int numeroHoja) {
		this.numeroHoja = numeroHoja;
	}

}
