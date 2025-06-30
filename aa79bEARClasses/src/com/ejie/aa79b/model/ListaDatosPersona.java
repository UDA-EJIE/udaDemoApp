package com.ejie.aa79b.model;

import java.util.List;

public class ListaDatosPersona implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private List<DatosPersona> listaDatosPersona;

	public ListaDatosPersona(){
    	//Constructor vacio
	}
	
	/**
	 * @return the listaDatosPersona
	 */
	public List<DatosPersona> getListaDatosPersona() {
		return listaDatosPersona;
	}

	/**
	 * @param listaDatosPersona the listaDatosPersona to set
	 */
	public void setListaDatosPersona(List<DatosPersona> listaDatosPersona) {
		this.listaDatosPersona = listaDatosPersona;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListaDatosPersona [listaDatosPersona=" + listaDatosPersona + "]";
	}

}
