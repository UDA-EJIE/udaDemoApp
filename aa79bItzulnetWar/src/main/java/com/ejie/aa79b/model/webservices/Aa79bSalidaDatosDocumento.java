package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bSalidaDatosDocumento
 * 
 * @author mrodriguez
 */
public class Aa79bSalidaDatosDocumento extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = -7938608558245632455L;
	private String rutaPif;
	private String nombre;

	/**
	 * @return the rutaPif
	 */
	public String getRutaPif() {
		return rutaPif;
	}

	/**
	 * @param rutaPif
	 *            the rutaPif to set
	 */
	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append(" [ rutaPif: ").append(this.rutaPif).append(" ]");
		result.append(", [ nombre: ").append(this.nombre).append(" ]");
		result.append("}");
		return result.toString();
	}

}
