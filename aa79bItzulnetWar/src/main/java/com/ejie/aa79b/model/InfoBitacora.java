package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * InfoBitacora
 * @author mrodriguez
 */
public class InfoBitacora extends DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = -7118388590026674218L;
	private BitacorasSolicitante bitacorasSolicitante = new BitacorasSolicitante();

	/**
	 * @return the bitacorasSolicitante
	 */
	public BitacorasSolicitante getBitacorasSolicitante() {
		return this.bitacorasSolicitante;
	}

	/**
	 * @param bitacorasSolicitante the bitacorasSolicitante to set
	 */
	public void setBitacorasSolicitante(BitacorasSolicitante bitacorasSolicitante) {
		this.bitacorasSolicitante = bitacorasSolicitante;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ bitacorasSolicitante: ").append(this.bitacorasSolicitante).append(" ]");
		result.append("}");
		return result.toString();
	}

}
