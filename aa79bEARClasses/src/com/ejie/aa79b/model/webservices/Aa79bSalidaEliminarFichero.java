package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bSalidaEliminarFichero extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean eliminado;

	public Aa79bSalidaEliminarFichero() {
		// Constructor
	}

	/**
	 * @return the eliminado
	 */
	public Boolean getEliminado() {
		return eliminado;
	}

	/**
	 * @param eliminado
	 *            the eliminado to set
	 */
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

}
