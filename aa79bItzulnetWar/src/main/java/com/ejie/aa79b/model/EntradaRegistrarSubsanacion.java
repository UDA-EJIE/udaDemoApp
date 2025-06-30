package com.ejie.aa79b.model;

import java.io.Serializable;

import com.ejie.aa79b.model.webservices.Aa79bExpediente;

/**
 * EntradaNotasExpediente
 * @author mrodriguez
 */
public class EntradaRegistrarSubsanacion implements Serializable {
	
	private static final long serialVersionUID = -89123050353874656L;
	private String dni;
	private Aa79bExpediente expediente;

    /**
	 * @return the dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
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
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ expediente: ").append(this.expediente).append(" ]");
		result.append("}");
		return result.toString();
	}

}
