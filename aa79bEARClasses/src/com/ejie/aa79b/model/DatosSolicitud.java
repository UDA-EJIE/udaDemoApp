package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;
import com.ejie.aa79b.model.webservices.Aa79bSolicitud;

/**
 * DatosExpediente
 * @author mrodriguez
 */
public class DatosSolicitud extends DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = 6690977761160890591L;
	private Aa79bSolicitud solicitud;
	private List<DocumentosExpediente> documentosExp;
	private List<Aa79bExpedienteRelacionado> expedienteRelacionado = new ArrayList<Aa79bExpedienteRelacionado>();

	
	public Aa79bSolicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Aa79bSolicitud solicitud) {
		this.solicitud = solicitud;
	}

	public List<DocumentosExpediente> getDocumentosExp() {
		return documentosExp;
	}

	public void setDocumentosExp(List<DocumentosExpediente> documentosExp) {
		this.documentosExp = documentosExp;
	}

	public List<Aa79bExpedienteRelacionado> getExpedientesRelacionados() {
		return expedienteRelacionado;
	}

	public void setExpedientesRelacionados(List<Aa79bExpedienteRelacionado> expedienteRelacionado) {
		this.expedienteRelacionado = expedienteRelacionado;
	}

	@Override
	public String toString() {
		return "DatosSolicitud [solicitud=" + solicitud + ", documentosExp=" + documentosExp
				+ ", expedienteRelacionado=" + expedienteRelacionado + "]";
	}
	
}
