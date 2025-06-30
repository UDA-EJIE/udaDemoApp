package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.util.List;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bListLoteCombo extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Aa79bLoteCombo> listaLoteCombo;

	public Aa79bListLoteCombo() {
		// Constructor
	}

	/**
	 * @return the listaLoteCombo
	 */
	public List<Aa79bLoteCombo> getListaLoteCombo() {
		return listaLoteCombo;
	}

	/**
	 * @param listaLoteCombo
	 *            the listaLoteCombo to set
	 */
	public void setListaLoteCombo(List<Aa79bLoteCombo> listaLoteCombo) {
		this.listaLoteCombo = listaLoteCombo;
	}

}
