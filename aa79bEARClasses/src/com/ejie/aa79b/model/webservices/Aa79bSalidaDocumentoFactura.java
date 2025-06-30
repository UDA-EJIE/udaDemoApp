package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bSalidaDocumentoFactura
 * 
 * @author mrodriguez
 */
public class Aa79bSalidaDocumentoFactura extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = -3036528778163592650L;

	private Aa79bFicheroDocExp fichero;

	/**
	 * @return the fichero
	 */
	public Aa79bFicheroDocExp getFichero() {
		return fichero;
	}

	/**
	 * @param fichero
	 *            the fichero to set
	 */
	public void setFichero(Aa79bFicheroDocExp fichero) {
		this.fichero = fichero;
	}

}
