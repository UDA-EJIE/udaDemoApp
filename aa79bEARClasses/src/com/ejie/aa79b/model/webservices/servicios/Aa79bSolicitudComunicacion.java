package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Aa79bSolicitudComunicacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoAplicacion;
	private String referenciaExpediente;
	private AA79bDatosSolicitanteComunicacion datosSolicitanteComunicacion;
	private String numeroExpedienteIZO;
	private AA79bDatosComunicacion datosComunicacion;


	public String getCodigoAplicacion() {
		return this.codigoAplicacion;
	}
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}
	public String getReferenciaExpediente() {
		return this.referenciaExpediente;
	}
	public void setReferenciaExpediente(String referenciaExpediente) {
		this.referenciaExpediente = referenciaExpediente;
	}
	public AA79bDatosSolicitanteComunicacion getDatosSolicitanteComunicacion() {
		return this.datosSolicitanteComunicacion;
	}
	public void setDatosSolicitanteComunicacion(AA79bDatosSolicitanteComunicacion datosSolicitanteComunicacion) {
		this.datosSolicitanteComunicacion = datosSolicitanteComunicacion;
	}
	public String getNumeroExpedienteIZO() {
		return this.numeroExpedienteIZO;
	}
	public void setNumeroExpedienteIZO(String numeroExpedienteIZO) {
		this.numeroExpedienteIZO = numeroExpedienteIZO;
	}
	public AA79bDatosComunicacion getDatosComunicacion() {
		return this.datosComunicacion;
	}
	public void setDatosComunicacion(AA79bDatosComunicacion datosComunicacion) {
		this.datosComunicacion = datosComunicacion;
	}

}
