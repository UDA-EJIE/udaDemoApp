package com.ejie.aa79b.model;

import java.io.Serializable;

public class EstadoErrorFactura implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer indEstadoError;
	private String estadoErrorDescEs;
	private String estadoErrorDescEu;

	public EstadoErrorFactura() {
		// Constructor
	}

	public Integer getIndEstadoError() {
		return indEstadoError;
	}

	public void setIndEstadoError(Integer indEstadoError) {
		this.indEstadoError = indEstadoError;
	}

	public String getEstadoErrorDescEs() {
		return estadoErrorDescEs;
	}

	public void setEstadoErrorDescEs(String estadoErrorDescEs) {
		this.estadoErrorDescEs = estadoErrorDescEs;
	}

	public String getEstadoErrorDescEu() {
		return estadoErrorDescEu;
	}

	public void setEstadoErrorDescEu(String estadoErrorDescEu) {
		this.estadoErrorDescEu = estadoErrorDescEu;
	}

}
