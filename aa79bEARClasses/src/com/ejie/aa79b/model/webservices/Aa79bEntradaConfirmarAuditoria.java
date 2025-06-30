package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bEntradaConfirmarAuditoria extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aa79bAuditoria auditoria;

	public Aa79bEntradaConfirmarAuditoria() {
		// Constructor
	}

	public Aa79bAuditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Aa79bAuditoria auditoria) {
		this.auditoria = auditoria;
	}

}
