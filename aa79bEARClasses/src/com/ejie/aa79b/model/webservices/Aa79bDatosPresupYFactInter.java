package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bDatosPresupYFactInter implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numInterpretes;
	private String horasInterpretacion;
	private Aa79bDescripcionIdioma tipoActo;
	private Aa79bDescripcionIdioma programada;
	private Aa79bDescripcionIdioma enCAE;

	public Aa79bDatosPresupYFactInter() {
		// Constructor
	}

	/**
	 * @return the numInterpretes
	 */
	public Integer getNumInterpretes() {
		return numInterpretes;
	}

	/**
	 * @param numInterpretes
	 *            the numInterpretes to set
	 */
	public void setNumInterpretes(Integer numInterpretes) {
		this.numInterpretes = numInterpretes;
	}

	/**
	 * @return the horasInterpretacion
	 */
	public String getHorasInterpretacion() {
		return horasInterpretacion;
	}

	/**
	 * @param horasInterpretacion
	 *            the horasInterpretacion to set
	 */
	public void setHorasInterpretacion(String horasInterpretacion) {
		this.horasInterpretacion = horasInterpretacion;
	}

	/**
	 * @return the tipoActo
	 */
	public Aa79bDescripcionIdioma getTipoActo() {
		return tipoActo;
	}

	/**
	 * @param tipoActo
	 *            the tipoActo to set
	 */
	public void setTipoActo(Aa79bDescripcionIdioma tipoActo) {
		this.tipoActo = tipoActo;
	}

	/**
	 * @return the programada
	 */
	public Aa79bDescripcionIdioma getProgramada() {
		return programada;
	}

	/**
	 * @param programada
	 *            the programada to set
	 */
	public void setProgramada(Aa79bDescripcionIdioma programada) {
		this.programada = programada;
	}

	/**
	 * @return the enCAE
	 */
	public Aa79bDescripcionIdioma getEnCAE() {
		return enCAE;
	}

	/**
	 * @param enCAE
	 *            the enCAE to set
	 */
	public void setEnCAE(Aa79bDescripcionIdioma enCAE) {
		this.enCAE = enCAE;
	}

}
