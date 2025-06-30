package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bEntradaEjecutarTarea extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private String dni;

	public Aa79bEntradaEjecutarTarea() {
		// Constructor
	}

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return idTarea;
	}

	/**
	 * @param idTarea
	 *            the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Aa79bEntradaEjecutarTarea [idTarea=" + idTarea + ", dni=" + dni + "]";
	}

}
