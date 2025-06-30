package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDocumentoApoyo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String nombreFichero;
	private String rutaPIF;
	
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNombreFichero() {
		return this.nombreFichero;
	}
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	public String getRutaPIF() {
		return this.rutaPIF;
	}
	public void setRutaPIF(String rutaPIF) {
		this.rutaPIF = rutaPIF;
	}
}
