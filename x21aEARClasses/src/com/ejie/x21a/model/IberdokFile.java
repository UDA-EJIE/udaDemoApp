package com.ejie.x21a.model;

import org.hdiv.services.SecureIdentifiable;

public class IberdokFile implements java.io.Serializable, SecureIdentifiable<String> {
	
	private static final long serialVersionUID = 1L;
	
	private String id;	
	private String idModelo;
	private String semilla;
	private String idDocumento;
	private String estado;
	private String documentoFinal;
	private String file;
	private String nombre;
	
	public IberdokFile() {
		super();
	}
	public IberdokFile(String id) {
		super();
		this.id = id;
	}
	public IberdokFile(String idModelo, String semilla, String idDocumento, String estado) {
		this.idModelo = idModelo;
		this.semilla = semilla;
		this.idDocumento = idDocumento;
		this.estado = estado;
	}
	public IberdokFile(String id, String idModelo, String semilla, String idDocumento, String estado) {
		this.id = id;
		this.idModelo = idModelo;
		this.semilla = semilla;
		this.idDocumento = idDocumento;
		this.estado = estado;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdModelo() {
		return idModelo;
	}
	public void setIdModelo(String idModelo) {
		this.idModelo = idModelo;
	}
	public String getSemilla() {
		return semilla;
	}
	public void setSemilla(String semilla) {
		this.semilla = semilla;
	}
	public String getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDocumentoFinal() {
		return documentoFinal;
	}
	public void setDocumentoFinal(String documentoFinal) {
		this.documentoFinal = documentoFinal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
