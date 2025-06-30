package com.ejie.aa79b.model;

import java.util.List;

public class ListaReceptoresAutorizados implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<ReceptorAutorizado> listaReceptoresAutorizados;
	
	public ListaReceptoresAutorizados(){
//		Constructor
	}

	/**
	 * @return the listaReceptoresAutorizados
	 */
	public List<ReceptorAutorizado> getListaReceptoresAutorizados() {
		return listaReceptoresAutorizados;
	}

	/**
	 * @param listaReceptoresAutorizados the listaReceptoresAutorizados to set
	 */
	public void setListaReceptoresAutorizados(List<ReceptorAutorizado> listaReceptoresAutorizados) {
		this.listaReceptoresAutorizados = listaReceptoresAutorizados;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListaReceptoresAutorizados [listaReceptoresAutorizados=" + listaReceptoresAutorizados + "]";
	}
	
	

}
