package com.ejie.aa79b.model;

import java.io.Serializable;

public class FilterFactura implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numEstadoFacturas;
	private Long idFactura;
	private EstadoFactura estadoFactura;

	public FilterFactura() {
		// Contructor
	}

	public String getNumEstadoFacturas() {
		return numEstadoFacturas;
	}

	public void setNumEstadoFacturas(String numEstadoFacturas) {
		this.numEstadoFacturas = numEstadoFacturas;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public EstadoFactura getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(EstadoFactura estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

}
