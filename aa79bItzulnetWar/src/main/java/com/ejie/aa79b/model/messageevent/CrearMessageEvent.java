package com.ejie.aa79b.model.messageevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "codigoExpediente", "codigoExpedienteExterno", "asunto", "mensaje", "fichero" })
@XmlRootElement(name = "comunicacion")
public class CrearMessageEvent {

	protected String codigoExpediente;
	protected String codigoExpedienteExterno;
	protected String asunto;
	protected String mensaje;
	protected String fichero;


	public String getCodigoExpediente() {
		return this.codigoExpediente;
	}
	public void setCodigoExpediente(String codigoExpediente) {
		this.codigoExpediente = codigoExpediente;
	}
	public String getCodigoExpedienteExterno() {
		return this.codigoExpedienteExterno;
	}
	public void setCodigoExpedienteExterno(String codigoExpedienteExterno) {
		this.codigoExpedienteExterno = codigoExpedienteExterno;
	}
	public String getAsunto() {
		return this.asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return this.mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getFichero() {
		return this.fichero;
	}
	public void setFichero(String fichero) {
		this.fichero = fichero;
	}



}
