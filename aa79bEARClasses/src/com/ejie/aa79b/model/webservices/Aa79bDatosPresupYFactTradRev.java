package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bDatosPresupYFactTradRev implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aa79bDescripcionIdioma idiomaDestino;
	private Aa79bDescripcionIdioma relevancia;
	private Aa79bDescripcionIdioma bopv;
	private BigDecimal tarifaPal;
	private Integer numTotalPal;
	private Integer numPalConcor084;
	private Integer numPalConcor8594;
	private Integer numPalConcor95100;
	private Aa79bDescripcionIdioma urgencia;
	private Aa79bDescripcionIdioma dificultad;

	public Aa79bDatosPresupYFactTradRev() {
		// Constructor
	}

	/**
	 * @return the idiomaDestino
	 */
	public Aa79bDescripcionIdioma getIdiomaDestino() {
		return idiomaDestino;
	}

	/**
	 * @param idiomaDestino
	 *            the idiomaDestino to set
	 */
	public void setIdiomaDestino(Aa79bDescripcionIdioma idiomaDestino) {
		this.idiomaDestino = idiomaDestino;
	}

	/**
	 * @return the relevancia
	 */
	public Aa79bDescripcionIdioma getRelevancia() {
		return relevancia;
	}

	/**
	 * @param relevancia
	 *            the relevancia to set
	 */
	public void setRelevancia(Aa79bDescripcionIdioma relevancia) {
		this.relevancia = relevancia;
	}

	/**
	 * @return the bopv
	 */
	public Aa79bDescripcionIdioma getBopv() {
		return bopv;
	}

	/**
	 * @param bopv
	 *            the bopv to set
	 */
	public void setBopv(Aa79bDescripcionIdioma bopv) {
		this.bopv = bopv;
	}

	/**
	 * @return the tarifaPal
	 */
	public BigDecimal getTarifaPal() {
		return tarifaPal;
	}

	/**
	 * @param tarifaPal
	 *            the tarifaPal to set
	 */
	public void setTarifaPal(BigDecimal tarifaPal) {
		this.tarifaPal = tarifaPal;
	}

	/**
	 * @return the numTotalPal
	 */
	public Integer getNumTotalPal() {
		return numTotalPal;
	}

	/**
	 * @param numTotalPal
	 *            the numTotalPal to set
	 */
	public void setNumTotalPal(Integer numTotalPal) {
		this.numTotalPal = numTotalPal;
	}

	/**
	 * @return the numPalConcor084
	 */
	public Integer getNumPalConcor084() {
		return numPalConcor084;
	}

	/**
	 * @param numPalConcor084
	 *            the numPalConcor084 to set
	 */
	public void setNumPalConcor084(Integer numPalConcor084) {
		this.numPalConcor084 = numPalConcor084;
	}

	/**
	 * @return the numPalConcor8594
	 */
	public Integer getNumPalConcor8594() {
		return numPalConcor8594;
	}

	/**
	 * @param numPalConcor8594
	 *            the numPalConcor8594 to set
	 */
	public void setNumPalConcor8594(Integer numPalConcor8594) {
		this.numPalConcor8594 = numPalConcor8594;
	}

	/**
	 * @return the numPalConcor95100
	 */
	public Integer getNumPalConcor95100() {
		return numPalConcor95100;
	}

	/**
	 * @param numPalConcor95100
	 *            the numPalConcor95100 to set
	 */
	public void setNumPalConcor95100(Integer numPalConcor95100) {
		this.numPalConcor95100 = numPalConcor95100;
	}

	/**
	 * @return the urgencia
	 */
	public Aa79bDescripcionIdioma getUrgencia() {
		return urgencia;
	}

	/**
	 * @param urgencia
	 *            the urgencia to set
	 */
	public void setUrgencia(Aa79bDescripcionIdioma urgencia) {
		this.urgencia = urgencia;
	}

	/**
	 * @return the dificultad
	 */
	public Aa79bDescripcionIdioma getDificultad() {
		return dificultad;
	}

	/**
	 * @param dificultad
	 *            the dificultad to set
	 */
	public void setDificultad(Aa79bDescripcionIdioma dificultad) {
		this.dificultad = dificultad;
	}

}
