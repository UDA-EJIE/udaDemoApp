package com.ejie.aa79b.model;

import java.io.Serializable;

import com.ejie.aa79b.model.webservices.Aa79bExpediente;

/**
 * DatosExpediente
 * @author mrodriguez
 */
public class DatosExpediente extends DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = 6690977761160890591L;
	private Aa79bExpediente expediente;

	/**
	 * @return the expediente
	 */
	public Aa79bExpediente getExpediente() {
		return this.expediente;
	}

	/**
	 * @param expediente the expediente to set
	 */
	public void setExpediente(Aa79bExpediente expediente) {
		this.expediente = expediente;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ expediente: ").append(this.expediente).append(" ]");
		result.append("}");
		return result.toString();
	}

}
