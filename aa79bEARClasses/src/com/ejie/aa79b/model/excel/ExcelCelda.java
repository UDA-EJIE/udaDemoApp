package com.ejie.aa79b.model.excel;

import com.ejie.aa79b.common.Reports;

/**
 * @author dlopez2
 *
 */
public class ExcelCelda implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * El dato a mostrar dato String
	 */
	private String dato = "";
	/**
	 * Posición X de la celda posicionX String
	 */
	private int posicionX = 0;
	/**
	 * Posición Y de la celda posicionY String
	 */
	private int posicionY = 0;

	/**
	 * Estilo de la celda de la celda estilo String
	 */
	private String estilo = "";

	/**
	 * Indica el tipo de celda (imagen o texto) estilo String
	 */
	private String tipoCelda = Reports.TIPO_CELDA_TEXTO;

	/**
	 * nombreImagen String
	 */
	private String rutaImagen = "";

	/**
	 * 
	 */
	public ExcelCelda() {
		//Constructor vacío
	}

	/**
	 * @param dato
	 *            String
	 */
	public ExcelCelda(String dato) {
		this.dato = dato;
	}

	/**
	 * @param dato
	 *            String
	 * @param posicionX
	 *            int
	 * @param posicionY
	 *            int
	 * @param estilo
	 *            String
	 */
	public ExcelCelda(String dato, int posicionX, int posicionY, String estilo) {
		this.dato = dato;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.estilo = estilo;
	}

	/**
	 * @return String
	 */
	public String getDato() {
		return this.dato;
	}

	/**
	 * @param dato
	 *            String
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}

	/**
	 * @return int
	 */
	public int getPosicionX() {
		return this.posicionX;
	}

	/**
	 * @param posicionX
	 *            int
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
	 * @param posicionY
	 *            int
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * @return String
	 */
	public String getEstilo() {
		return this.estilo;
	}

	/**
	 * @param estilo
	 *            String
	 */
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	/**
	 * @return String
	 */
	public String getTipoCelda() {
		return this.tipoCelda;
	}

	/**
	 * @param tipoCelda
	 *            String
	 */
	public void setTipoCelda(String tipoCelda) {
		this.tipoCelda = tipoCelda;
	}

	/**
	 * @return String
	 */
	public String getRutaImagen() {
		return this.rutaImagen;
	}

	/**
	 * @param rutaImagen
	 *            String
	 */
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
}
