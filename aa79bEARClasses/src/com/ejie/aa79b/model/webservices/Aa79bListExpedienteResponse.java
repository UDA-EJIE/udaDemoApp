package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.util.List;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bListExpedienteResponse extends DatosSalidaWS implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Aa79bExpediente> listaAa79bExpediente;

	
	public Aa79bListExpedienteResponse() {
		//Constructor
	}

	/**
	 * @return the listaAa79bExpediente
	 */
	public List<Aa79bExpediente> getListaAa79bExpediente() {
		return listaAa79bExpediente;
	}

	/**
	 * @param listaAa79bExpediente the listaAa79bExpediente to set
	 */
	public void setListaAa79bExpediente(List<Aa79bExpediente> listaAa79bExpediente) {
		this.listaAa79bExpediente = listaAa79bExpediente;
	}

	@Override
	public String toString() {
		return "Aa79bListExpedienteResponse [listaAa79bExpediente=" + listaAa79bExpediente + "]";
	}
	
	
}
