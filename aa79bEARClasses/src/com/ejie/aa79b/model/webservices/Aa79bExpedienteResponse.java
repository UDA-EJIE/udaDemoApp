package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bExpedienteResponse extends DatosSalidaWS implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Aa79bExpediente aa79bExpediente;
	
	

	public Aa79bExpedienteResponse() {
		//Constructor
	}

	/**
	 * @return the aa79bExpediente
	 */
	public Aa79bExpediente getAa79bExpediente() {
		return aa79bExpediente;
	}

	/**
	 * @param aa79bExpediente the aa79bExpediente to set
	 */
	public void setAa79bExpediente(Aa79bExpediente aa79bExpediente) {
		this.aa79bExpediente = aa79bExpediente;
	}

	
	@Override
	public String toString() {
		return "Aa79bExpedienteResponse [aa79bExpediente=" + aa79bExpediente + "]";
	}
	
	
	
	
}
