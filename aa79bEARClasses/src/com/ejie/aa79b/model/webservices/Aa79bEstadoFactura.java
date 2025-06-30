package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bEstadoFactura implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long indEstadoFactura;
	private String estadoFacturaDescEs;
	private String estadoFacturaDescEu;

	public Aa79bEstadoFactura() {
		// Constructor
	}

	public Long getIndEstadoFactura() {
		return indEstadoFactura;
	}

	public void setIndEstadoFactura(Long indEstadoFactura) {
		this.indEstadoFactura = indEstadoFactura;
	}

	public String getEstadoFacturaDescEs() {
		return estadoFacturaDescEs;
	}

	public void setEstadoFacturaDescEs(String estadoFacturaDescEs) {
		this.estadoFacturaDescEs = estadoFacturaDescEs;
	}

	public String getEstadoFacturaDescEu() {
		return estadoFacturaDescEu;
	}

	public void setEstadoFacturaDescEu(String estadoFacturaDescEu) {
		this.estadoFacturaDescEu = estadoFacturaDescEu;
	}

}
