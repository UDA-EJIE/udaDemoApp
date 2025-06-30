package com.ejie.aa79b.model;

import java.io.Serializable;

import com.ejie.aa79b.model.webservices.Aa79bExpediente;

/**
 * EntradaReceptorAutorizado
 * 
 * @author mrodriguez
 */
public class EntradaDocumentosExpediente extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 3353343144070742523L;
	private String dni;
	private Aa79bExpediente expediente;
	private Boolean comprPermisos = true;

	/**
	 * @return the dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	public Aa79bExpediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Aa79bExpediente expediente) {
		this.expediente = expediente;
	}

	/**
	 * @return the comprPermisos
	 */
	public Boolean getComprPermisos() {
		return comprPermisos;
	}

	/**
	 * @param comprPermisos the comprPermisos to set
	 */
	public void setComprPermisos(Boolean comprPermisos) {
		this.comprPermisos = comprPermisos;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.expediente).append(" ]");
		result.append("}");
		return result.toString();
	}

}
