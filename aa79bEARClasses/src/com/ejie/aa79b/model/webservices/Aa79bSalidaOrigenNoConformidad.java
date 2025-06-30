package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bSalidaOrigenNoConformidad extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer tipoTarea;
	private BigDecimal idTareaRelacionada;

	public Aa79bSalidaOrigenNoConformidad() {
		// Constructor
	}

	/**
	 * @return the tipoTarea
	 */
	public Integer getTipoTarea() {
		return tipoTarea;
	}

	/**
	 * @param tipoTarea
	 *            the tipoTarea to set
	 */
	public void setTipoTarea(Integer tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	/**
	 * @return the idTareaRelacionada
	 */
	public BigDecimal getIdTareaRelacionada() {
		return idTareaRelacionada;
	}

	/**
	 * @param idTareaRelacionada
	 *            the idTareaRelacionada to set
	 */
	public void setIdTareaRelacionada(BigDecimal idTareaRelacionada) {
		this.idTareaRelacionada = idTareaRelacionada;
	}

	@Override
	public String toString() {
		return "Aa79bSalidaOrigenNoConformidad [tipoTarea=" + tipoTarea + ", idTareaRelacionada=" + idTareaRelacionada
				+ "]";
	}

}
