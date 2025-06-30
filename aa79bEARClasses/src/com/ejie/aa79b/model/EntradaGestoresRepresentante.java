package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * EntradaGestoresRepresentante
 * 
 * @author mrodriguez
 */
public class EntradaGestoresRepresentante implements Serializable {

	private static final long serialVersionUID = 6914946314137644266L;
	private String dni;
	private Long anyo;

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
		return anyo;
	}

	/**
	 * @param anyo
	 *            the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append("}");
		return result.toString();
	}

}
