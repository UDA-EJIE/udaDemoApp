package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * EntradaReceptorAutorizado
 * 
 * @author mrodriguez
 */
public class EntradaAceptarRechazarReq extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 3353343144070742523L;
	private BitacoraExpediente bitacoraExpediente;

	public BitacoraExpediente getBitacoraExpediente() {
		return bitacoraExpediente;
	}

	public void setBitacoraExpediente(BitacoraExpediente bitacoraExpediente) {
		this.bitacoraExpediente = bitacoraExpediente;
	}

	@Override
	public String toString() {
		return "EntradaAceptarRechazarReq [bitacoraExpediente=" + bitacoraExpediente + "]";
	}

}
