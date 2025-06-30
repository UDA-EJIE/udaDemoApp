package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * EntradaDatosExpediente
 * 
 * @author mrodriguez
 */
public class EntradaDatosExpediente extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 8116794400216870938L;
	private String dni;
	private Long anyo;
	private Integer numExp;
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

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
	}

	/**
	 * @param anyo
	 *            the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	/**
	 * @return the numExp
	 */
	public Integer getNumExp() {
		return this.numExp;
	}

	/**
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the comprPermisos
	 */
	public Boolean getComprPermisos() {
		return comprPermisos;
	}

	/**
	 * @param comprPermisos
	 *            the comprPermisos to set
	 */
	public void setComprPermisos(Boolean comprPermisos) {
		this.comprPermisos = comprPermisos;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append("}");
		return result.toString();
	}

}
