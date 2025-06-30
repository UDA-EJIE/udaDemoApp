package com.ejie.aa79b.model;

import java.util.List;

/**
 * Modelo generado para pasar al controlador listas de objetos tipo Expediente
 * desde las js
 * 
 */

public class ListaContactoFacturacionExpediente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private List<ContactoFacturacionExpediente> listaContactoFacturacionExpediente;

	/**
	 * Method 'ListaExpediente'.
	 */
	public ListaContactoFacturacionExpediente() {
		// Constructor vacio
	}

	public List<ContactoFacturacionExpediente> getContactoFacturacionExpediente() {
		return listaContactoFacturacionExpediente;
	}

	public void setContactoFacturacionExpediente(
			List<ContactoFacturacionExpediente> listaContactoFacturacionExpediente) {
		this.listaContactoFacturacionExpediente = listaContactoFacturacionExpediente;
	}

}
