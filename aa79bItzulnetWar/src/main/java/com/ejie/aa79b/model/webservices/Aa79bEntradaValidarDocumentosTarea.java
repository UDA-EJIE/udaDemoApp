package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bEntradaValidarDocumentosTarea extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dni;
	private BigDecimal idTarea;
	private Long idTipoTarea;
	private BigDecimal idTareaRel;
	private Long idTipoTareaRel;

	public Aa79bEntradaValidarDocumentosTarea() {
		// Constructor
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
	 * @return the idTipoTarea
	 */
	public Long getIdTipoTarea() {
		return idTipoTarea;
	}

	/**
	 * @param idTipoTarea
	 *            the idTipoTarea to set
	 */
	public void setIdTipoTarea(Long idTipoTarea) {
		this.idTipoTarea = idTipoTarea;
	}

	/**
	 * @return the idTareaRel
	 */
	public BigDecimal getIdTareaRel() {
		return idTareaRel;
	}

	/**
	 * @param idTareaRel
	 *            the idTareaRel to set
	 */
	public void setIdTareaRel(BigDecimal idTareaRel) {
		this.idTareaRel = idTareaRel;
	}

	/**
	 * @return the idTipoTareaRel
	 */
	public Long getIdTipoTareaRel() {
		return idTipoTareaRel;
	}

	/**
	 * @param idTipoTareaRel
	 *            the idTipoTareaRel to set
	 */
	public void setIdTipoTareaRel(Long idTipoTareaRel) {
		this.idTipoTareaRel = idTipoTareaRel;
	}

	@Override
	public String toString() {
		return "Aa79bEntradaValidarDocumentosTarea [dni=" + dni + ", idTarea=" + idTarea + ", idTipoTarea="
				+ idTipoTarea + ", idTareaRel=" + idTareaRel + ", idTipoTareaRel=" + idTipoTareaRel + "]";
	}

}
