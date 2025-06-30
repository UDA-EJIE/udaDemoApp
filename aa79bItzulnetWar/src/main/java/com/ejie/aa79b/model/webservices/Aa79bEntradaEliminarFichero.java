package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bEntradaEliminarFichero extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private String dni;
	private BigDecimal idDoc;
	private Integer tipoFichero;

	public Aa79bEntradaEliminarFichero() {
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

	/**
	 * @return the idDoc
	 */
	public BigDecimal getIdDoc() {
		return idDoc;
	}

	/**
	 * @param idDoc
	 *            the idDoc to set
	 */
	public void setIdDoc(BigDecimal idDoc) {
		this.idDoc = idDoc;
	}

	/**
	 * @return the tipoFichero
	 */
	public Integer getTipoFichero() {
		return tipoFichero;
	}

	/**
	 * @param tipoFichero
	 *            the tipoFichero to set
	 */
	public void setTipoFichero(Integer tipoFichero) {
		this.tipoFichero = tipoFichero;
	}

	@Override
	public String toString() {
		return "Aa79bEntradaEliminarFichero [idTarea=" + idTarea + ", dni=" + dni + ", idDoc=" + idDoc
				+ ", tipoFichero=" + tipoFichero + "]";
	}

}
