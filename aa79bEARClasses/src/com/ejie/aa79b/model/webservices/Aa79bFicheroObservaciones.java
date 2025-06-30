package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bFicheroObservaciones implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String rutaPif;
	private String contentType;
	private String extension;
	private String oidFichero;
	private Long tamano;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRutaPif() {
		return rutaPif;
	}

	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getOidFichero() {
		return oidFichero;
	}

	public void setOidFichero(String oidFichero) {
		this.oidFichero = oidFichero;
	}

	/**
	 * @return the tamano
	 */
	public Long getTamano() {
		return tamano;
	}

	/**
	 * @param tamano
	 *            the tamano to set
	 */
	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}
}
