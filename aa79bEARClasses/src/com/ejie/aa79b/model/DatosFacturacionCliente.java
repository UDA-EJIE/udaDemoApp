package com.ejie.aa79b.model;

import java.io.Serializable;

public class DatosFacturacionCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numTotalPal;
	private Integer numPalConcor084;
	private Integer numPalConcor8594;
	private Integer numPalConcor95100;

	public DatosFacturacionCliente() {
		// Constructor
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

}
