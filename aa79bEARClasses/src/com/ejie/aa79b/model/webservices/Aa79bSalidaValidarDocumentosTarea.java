package com.ejie.aa79b.model.webservices;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bSalidaValidarDocumentosTarea extends DatosSalidaWS {

	private static final long serialVersionUID = 1L;
	private Boolean valido;

	public Aa79bSalidaValidarDocumentosTarea() {
		// Constructor
	}

	/**
	 * @return the valido
	 */
	public Boolean getValido() {
		return valido;
	}

	/**
	 * @param valido
	 *            the valido to set
	 */
	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	@Override
	public String toString() {
		return "Aa79bSalidaValidarDocumentosTarea [valido=" + valido + "]";
	}

}
