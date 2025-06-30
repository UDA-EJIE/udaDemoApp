package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * FicheroWS
 * 
 * @author mrodriguez
 */
public class FicheroWS extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 182006064490932035L;
	private String nombre;
	private String rutaPif;
	private String contentType;
	private String extension;
	private String oid;
	private Long tamano;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the rutaPif
	 */
	public String getRutaPif() {
		return this.rutaPif;
	}

	/**
	 * @param rutaPif
	 *            the rutaPif to set
	 */
	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ nombre: ").append(this.nombre).append(" ]");
		result.append(", [ rutaPif: ").append(this.rutaPif).append(" ]");
		result.append(", [ contentType: ").append(this.contentType).append(" ]");
		result.append(", [ extension: ").append(this.extension).append(" ]");
		result.append("}");
		return result.toString();
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
