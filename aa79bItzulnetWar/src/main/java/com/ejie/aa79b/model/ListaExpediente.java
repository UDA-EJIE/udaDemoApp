package com.ejie.aa79b.model;

import java.util.List;

/**
 * Modelo generado para pasar al controlador listas de objetos tipo Expediente desde las js
 * 
 */

public class ListaExpediente implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	

    private List<Expediente> listaExpediente;
    
    /** 
	 * Method 'ListaExpediente'.
	 */
    public ListaExpediente() {
    	//Constructor vacio
    }

	/**
	 * @return the listaExpediente
	 */
	public List<Expediente> getListaExpediente() {
		return listaExpediente;
	}

	/**
	 * @param listaExpediente the listaExpediente to set
	 */
	public void setListaExpediente(List<Expediente> listaExpediente) {
		this.listaExpediente = listaExpediente;
	}
    
    

}
