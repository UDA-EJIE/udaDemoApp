package com.ejie.aa79b.model;

import java.io.Serializable;

public class EstadoIncidenciaFactura implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer indEstadoIncidencia;
	private String estadoIncidenciaDescEs;
	private String estadoIncidenciaDescEu;

	public EstadoIncidenciaFactura() {
		// Constructor
	}

	public Integer getIndEstadoIncidencia() {
		return indEstadoIncidencia;
	}

	public void setIndEstadoIncidencia(Integer indEstadoIncidencia) {
		this.indEstadoIncidencia = indEstadoIncidencia;
	}

	public String getEstadoIncidenciaDescEs() {
		return estadoIncidenciaDescEs;
	}

	public void setEstadoIncidenciaDescEs(String estadoIncidenciaDescEs) {
		this.estadoIncidenciaDescEs = estadoIncidenciaDescEs;
	}

	public String getEstadoIncidenciaDescEu() {
		return estadoIncidenciaDescEu;
	}

	public void setEstadoIncidenciaDescEu(String estadoIncidenciaDescEu) {
		this.estadoIncidenciaDescEu = estadoIncidenciaDescEu;
	}

}
