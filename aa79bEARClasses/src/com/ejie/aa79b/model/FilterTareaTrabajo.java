package com.ejie.aa79b.model;

import java.io.Serializable;

public class FilterTareaTrabajo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer tipoTarea;
	private String tieneSinAsignar;
	private String tieneSinEjecutar;
	private PersonalIZO personaAsignada;

	public FilterTareaTrabajo() {
		// Contructor
	}

	/**
	 * @return the tipoTarea
	 */
	public Integer getTipoTarea() {
		return this.tipoTarea;
	}

	/**
	 * @param tipoTarea
	 *            the tipoTarea to set
	 */
	public void setTipoTarea(Integer tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	public String getTieneSinAsignar() {
		return this.tieneSinAsignar;
	}

	public void setTieneSinAsignar(String tieneSinAsignar) {
		this.tieneSinAsignar = tieneSinAsignar;
	}

	public String getTieneSinEjecutar() {
		return this.tieneSinEjecutar;
	}

	public void setTieneSinEjecutar(String tieneSinEjecutar) {
		this.tieneSinEjecutar = tieneSinEjecutar;
	}

	public PersonalIZO getPersonaAsignada() {
		return this.personaAsignada;
	}

	public void setPersonaAsignada(PersonalIZO personaAsignada) {
		this.personaAsignada = personaAsignada;
	}



}
