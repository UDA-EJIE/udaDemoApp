package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDocumentoRevisar implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreFichero;
	private String rutaPIF;
	private String oidDokusi;
	private AA79bDatosResponsable datosResponsable;

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
	public String getOidDokusi() {
		return this.oidDokusi;
	}
	public void setOidDokusi(String oidDokusi) {
		this.oidDokusi = oidDokusi;
	}
	public AA79bDatosResponsable getDatosResponsable() {
		return this.datosResponsable;
	}
	public void setDatosResponsable(AA79bDatosResponsable datosResponsable) {
		this.datosResponsable = datosResponsable;
	}

}
