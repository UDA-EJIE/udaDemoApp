package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Aa79bNotasIZO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String notas;
	private String nombreFichero;
	private String rutaPIF;
	
	public String getNotas() {
		return this.notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
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
