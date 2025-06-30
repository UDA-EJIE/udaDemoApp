package com.ejie.aa79b.model;

import java.io.Serializable;

public class EstadoFacturaE implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer indEstadoEFactura;
	private String estadoEFacturaDescEs;
	private String estadoEFacturaDescEu;

	public EstadoFacturaE() {
		// Constructor
	}

	public Integer getIndEstadoEFactura() {
		return indEstadoEFactura;
	}

	public void setIndEstadoEFactura(Integer indEstadoEFactura) {
		this.indEstadoEFactura = indEstadoEFactura;
	}

	public String getEstadoEFacturaDescEs() {
		return estadoEFacturaDescEs;
	}

	public void setEstadoEFacturaDescEs(String estadoEFacturaDescEs) {
		this.estadoEFacturaDescEs = estadoEFacturaDescEs;
	}

	public String getEstadoEFacturaDescEu() {
		return estadoEFacturaDescEu;
	}

	public void setEstadoEFacturaDescEu(String estadoEFacturaDescEu) {
		this.estadoEFacturaDescEu = estadoEFacturaDescEu;
	}

}
