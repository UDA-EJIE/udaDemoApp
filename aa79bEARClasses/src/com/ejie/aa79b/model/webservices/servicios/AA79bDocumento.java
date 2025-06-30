package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDocumento implements Serializable{

	private static final long serialVersionUID = 1L;

	private String titulo;
	private String tipo;
	private String nombreFichero;
	private String rutaPIF;
	private AA79bDatosResponsable datosResponsable;
	private String numeroTotalPalabras;
	private String oidDokusi;

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
	public AA79bDatosResponsable getDatosResponsable() {
		return this.datosResponsable;
	}
	public void setDatosResponsable(AA79bDatosResponsable datosResponsable) {
		this.datosResponsable = datosResponsable;
	}
	public String getTipo() {
		return this.tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNumeroTotalPalabras() {
		return this.numeroTotalPalabras;
	}
	public void setNumeroTotalPalabras(String numeroPalabras) {
		this.numeroTotalPalabras = numeroPalabras;
	}
	public String getOidDokusi() {
		return this.oidDokusi;
	}
	public void setOidDokusi(String oidDokusi) {
		this.oidDokusi = oidDokusi;
	}
}
