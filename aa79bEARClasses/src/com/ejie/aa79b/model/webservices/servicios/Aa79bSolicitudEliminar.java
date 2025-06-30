package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Aa79bSolicitudEliminar implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoAplicacion;
	private String referenciaExpediente;
	private AA79bDatosSolicitanteEliminar datosSolicitante;
	private String numeroExpedienteIZO;
	private String motivos;


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
	public AA79bDatosSolicitanteEliminar getDatosSolicitante() {
		return this.datosSolicitante;
	}
	public void setDatosSolicitante(AA79bDatosSolicitanteEliminar datosSolicitante) {
		this.datosSolicitante = datosSolicitante;
	}
	public String getNumeroExpedienteIZO() {
		return this.numeroExpedienteIZO;
	}
	public void setNumeroExpedienteIZO(String numeroExpedienteIZO) {
		this.numeroExpedienteIZO = numeroExpedienteIZO;
	}
	public String getMotivos() {
		return this.motivos;
	}
	public void setMotivos(String motivos) {
		this.motivos = motivos;
	}


}
