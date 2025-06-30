package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.util.List;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bSalidaDatosDescargaDocumentos
 * 
 * @author javarona
 */
public class Aa79bSalidaDatosDescargaDocumentos extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Aa79bFicheroDocExp> listaFicheros;

	/**
	 * @return the listaFicheros
	 */
	public List<Aa79bFicheroDocExp> getListaFicheros() {
		return listaFicheros;
	}

	/**
	 * @param listaFicheros
	 *            the listaFicheros to set
	 */
	public void setListaFicheros(List<Aa79bFicheroDocExp> listaFicheros) {
		this.listaFicheros = listaFicheros;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aa79bSalidaDatosDescargaDocumentos [listaFicheros=" + listaFicheros + "]";
	}

}
