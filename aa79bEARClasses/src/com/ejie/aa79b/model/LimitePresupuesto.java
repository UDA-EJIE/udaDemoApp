package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class LimitePresupuesto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date fechaPropuesta;
	private Date fechaLimite;
	private String horaPropuesta;
	private String horaLimite;
	private Boolean esInformado;

	public LimitePresupuesto() {
		// Constructor
	}

	/**
	 * @return the fechaPropuesta
	 */
	public Date getFechaPropuesta() {
		return fechaPropuesta;
	}

	/**
	 * @param fechaPropuesta
	 *            the fechaPropuesta to set
	 */
	public void setFechaPropuesta(Date fechaPropuesta) {
		this.fechaPropuesta = fechaPropuesta;
	}

	/**
	 * @return the fechaLimite
	 */
	public Date getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * @param fechaLimite
	 *            the fechaLimite to set
	 */
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * @return the horaPropuesta
	 */
	public String getHoraPropuesta() {
		return horaPropuesta;
	}

	/**
	 * @param horaPropuesta
	 *            the horaPropuesta to set
	 */
	public void setHoraPropuesta(String horaPropuesta) {
		this.horaPropuesta = horaPropuesta;
	}

	/**
	 * @return the horaLimite
	 */
	public String getHoraLimite() {
		return horaLimite;
	}

	/**
	 * @param horaLimite
	 *            the horaLimite to set
	 */
	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}

	/**
	 * @return the esInformado
	 */
	public Boolean getEsInformado() {
		return esInformado;
	}

	/**
	 * @param esInformado the esInformado to set
	 */
	public void setEsInformado(Boolean esInformado) {
		this.esInformado = esInformado;
	}

}
