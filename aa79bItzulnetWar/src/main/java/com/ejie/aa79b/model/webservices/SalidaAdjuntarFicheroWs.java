package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.DatosSalidaWS;

public class SalidaAdjuntarFicheroWs extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal idFichero;

	public SalidaAdjuntarFicheroWs() {
		// Constructor
	}

	/**
	 * @return the idFichero
	 */
	public BigDecimal getIdFichero() {
		return idFichero;
	}

	/**
	 * @param idFichero
	 *            the idFichero to set
	 */
	public void setIdFichero(BigDecimal idFichero) {
		this.idFichero = idFichero;
	}

}
