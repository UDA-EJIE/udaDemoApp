package com.ejie.aa79b.model;

import java.math.BigDecimal;

public class Plantillas implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id0A7;
	private String nombrePlantilla0A7;
	private String varPlantilla0A7;
	private BigDecimal idFicheroPlantilla0A7;

	private Fichero fichero;
	private Long codigo;
	private String titulo;
	private String extension;
	private String contentType;
	private Long tamano;
	private String encriptado;
	private String rutaPif;
	private String oidFichero;
	private String nombre;

	/**
	 * Method 'Motivosrechazo'.
	 */
	public Plantillas() {
		// Constructor
	}

	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ id0A7: ").append(this.id0A7).append(" ]");
		result.append(", [ nombrePlantilla0A7: ").append(this.nombrePlantilla0A7).append(" ]");
		result.append(", [ varPlantilla0A7: ").append(this.varPlantilla0A7).append(" ]");
		result.append(", [ idFicheroPlantilla0A7: ").append(this.idFicheroPlantilla0A7).append(" ]");
		result.append(", [ codigo: ").append(this.codigo).append(" ]");
		result.append(", [ titulo: ").append(this.titulo).append(" ]");
		result.append(", [ extension: ").append(this.extension).append(" ]");
		result.append(", [ contentType: ").append(this.contentType).append(" ]");
		result.append(", [ tamano: ").append(this.tamano).append(" ]");
		result.append(", [ encriptado: ").append(this.encriptado).append(" ]");
		result.append(", [ rutaPif: ").append(this.rutaPif).append(" ]");
		result.append(", [ oidFichero: ").append(this.oidFichero).append(" ]");
		result.append(", [ nombre: ").append(this.nombre).append(" ]");
		result.append("}");
		return result.toString();
	}

	public Long getId0A7() {
		return id0A7;
	}

	public void setId0A7(Long id0A7) {
		this.id0A7 = id0A7;
	}

	public String getNombrePlantilla0A7() {
		return nombrePlantilla0A7;
	}

	public void setNombrePlantilla0A7(String nombrePlantilla0A7) {
		this.nombrePlantilla0A7 = nombrePlantilla0A7;
	}

	public String getVarPlantilla0A7() {
		return varPlantilla0A7;
	}

	public void setVarPlantilla0A7(String varPlantilla0A7) {
		this.varPlantilla0A7 = varPlantilla0A7;
	}

	public BigDecimal getIdFicheroPlantilla0A7() {
		return idFicheroPlantilla0A7;
	}

	public void setIdFicheroPlantilla0A7(BigDecimal idFicheroPlantilla0A7) {
		this.idFicheroPlantilla0A7 = idFicheroPlantilla0A7;
	}

	public Fichero getFichero() {
		return fichero;
	}

	public void setFichero(Fichero fichero) {
		this.fichero = fichero;
	}

	public String getOidFichero() {
		return oidFichero;
	}

	public void setOidFichero(String oidFichero) {
		this.oidFichero = oidFichero;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getTamano() {
		return tamano;
	}

	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}

	public String getEncriptado() {
		return encriptado;
	}

	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}

	public String getRutaPif() {
		return rutaPif;
	}

	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
