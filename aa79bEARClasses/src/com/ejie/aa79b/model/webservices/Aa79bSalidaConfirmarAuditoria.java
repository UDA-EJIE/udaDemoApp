package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bSalidaConfirmarAuditoria extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long fechaConfirmacion;

	public Aa79bSalidaConfirmarAuditoria() {
		// Constructor
	}

	public Long getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(Long fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

}
