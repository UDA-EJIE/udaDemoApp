package com.ejie.x21a.model;

import java.sql.Timestamp;
import java.util.Date;

import org.hdiv.services.SecureIdentifiable;

public class IberdokFile implements java.io.Serializable, SecureIdentifiable<String>{
	
	private static final long serialVersionUID = 1L;
	
	private String id;	
	private String idModelo;
	private String semilla;
	private String idDocumento;
	private Integer docFinalizado;
	private String documentoFinal;
	private String file;
	private String nombre;
	private String idCorrelacion;
	private String usuario;
	private Timestamp fechaApp;
	private Timestamp fechaIberdok;
	
	public IberdokFile() {
		super();
	}
	public IberdokFile(String id) {
		super();
		this.id = id;
	}
	public IberdokFile(String idModelo, String semilla, String idDocumento, Integer docFinalizado) {
		this.idModelo = idModelo;
		this.semilla = semilla;
		this.idDocumento = idDocumento;
		this.docFinalizado = docFinalizado;
	}
	public IberdokFile(String id, String idModelo, String semilla, String idDocumento, Integer docFinalizado) {
		this.id = id;
		this.idModelo = idModelo;
		this.semilla = semilla;
		this.idDocumento = idDocumento;
		this.docFinalizado = docFinalizado;
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
	public Integer getDocFinalizado() {
		return docFinalizado;
	}
	public void setDocFinalizado(Integer docFinalizado) {
		this.docFinalizado = docFinalizado;
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
	public String getIdCorrelacion() {
		return idCorrelacion;
	}
	public void setIdCorrelacion(String idCorrelacion) {
		this.idCorrelacion = idCorrelacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFechaApp() {
		return fechaApp;
	}
	public void setFechaApp(Timestamp fechaApp) {
		this.fechaApp = fechaApp;
	}
	public Timestamp getFechaIberdok() {
		return fechaIberdok;
	}
	public void setFechaIberdok(Timestamp fechaIberdok) {
		this.fechaIberdok = fechaIberdok;
	}
	@Override
	public String toString() {
		return "IberdokFile [id=" + id + ", idModelo=" + idModelo + ", semilla=" + semilla + ", idDocumento="
				+ idDocumento + ", docFinalizado=" + docFinalizado + ", documentoFinal=" + documentoFinal + ", file="
				+ file + ", nombre=" + nombre + ", idCorrelacion=" + idCorrelacion + "]";
	}
}
