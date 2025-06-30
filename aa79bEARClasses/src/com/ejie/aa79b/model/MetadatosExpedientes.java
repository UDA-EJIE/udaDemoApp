package com.ejie.aa79b.model;

import java.io.Serializable;

public class MetadatosExpedientes implements Serializable {

	private static final long serialVersionUID = 1L;

	private ListaExpediente listaExpedientes;
	private ListaCategExp listaMetadatos;

	public MetadatosExpedientes() {
		// Constructor vacio
	}

	/**
	 * @return the listaExpedientes
	 */
	public ListaExpediente getListaExpedientes() {
		return listaExpedientes;
	}

	/**
	 * @param listaExpedientes
	 *            the listaExpedientes to set
	 */
	public void setListaExpedientes(ListaExpediente listaExpedientes) {
		this.listaExpedientes = listaExpedientes;
	}

	/**
	 * @return the listaMetadatos
	 */
	public ListaCategExp getListaMetadatos() {
		return listaMetadatos;
	}

	/**
	 * @param listaMetadatos
	 *            the listaMetadatos to set
	 */
	public void setListaMetadatos(ListaCategExp listaMetadatos) {
		this.listaMetadatos = listaMetadatos;
	}

}
