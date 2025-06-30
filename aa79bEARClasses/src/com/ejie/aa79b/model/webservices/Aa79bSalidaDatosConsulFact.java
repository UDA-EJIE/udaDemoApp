package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.util.List;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bSalidaDatosConsulFact
 * 
 * @author mrodriguez
 */
public class Aa79bSalidaDatosConsulFact extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = -5627662631285809731L;

	private List<String> titulosExpediente;

	/**
	 * Constructor
	 */
	public Aa79bSalidaDatosConsulFact() {
		// Constructor
	}

	/**
	 * @return the titulosExpediente
	 */
	public List<String> getTitulosExpediente() {
		return titulosExpediente;
	}

	/**
	 * @param titulosExpediente
	 *            the titulosExpediente to set
	 */
	public void setTitulosExpediente(List<String> titulosExpediente) {
		this.titulosExpediente = titulosExpediente;
	}

}
