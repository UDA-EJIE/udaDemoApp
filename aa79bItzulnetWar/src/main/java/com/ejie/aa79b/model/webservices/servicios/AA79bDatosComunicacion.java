package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDatosComunicacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String asunto;
	private String texto;
	private String fichero;


	public String getAsunto() {
		return this.asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getTexto() {
		return this.texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getFichero() {
		return this.fichero;
	}
	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

}