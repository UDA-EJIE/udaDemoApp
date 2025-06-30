package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

public class Aa79bObservacionesTareas implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private String obsvEjecucion;

	public Aa79bObservacionesTareas() {
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
	 * @return the obsvEjecucion
	 */
	public String getObsvEjecucion() {
		return obsvEjecucion;
	}

	/**
	 * @param obsvEjecucion the obsvEjecucion to set
	 */
	public void setObsvEjecucion(String obsvEjecucion) {
		this.obsvEjecucion = obsvEjecucion;
	}

}
