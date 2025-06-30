package com.ejie.aa79b.model.webservices;

import java.util.List;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bSalidaDetalleAuditoria
 * 
 * @author xtotorika
 */
public class Aa79bSalidaDetalleAuditoria extends DatosSalidaWS implements java.io.Serializable {

	private static final long serialVersionUID = 6914946314137644266L;
	private Aa79bAuditoria auditoria;
	private List<Aa79bAuditoriaSeccionExpediente> lAuditoriaSeccionExpediente;

	public Aa79bAuditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Aa79bAuditoria auditoria) {
		this.auditoria = auditoria;
	}

	public List<Aa79bAuditoriaSeccionExpediente> getlAuditoriaSeccionExpediente() {
		return lAuditoriaSeccionExpediente;
	}

	public void setlAuditoriaSeccionExpediente(List<Aa79bAuditoriaSeccionExpediente> lAuditoriaSeccionExpediente) {
		this.lAuditoriaSeccionExpediente = lAuditoriaSeccionExpediente;
	}

}
