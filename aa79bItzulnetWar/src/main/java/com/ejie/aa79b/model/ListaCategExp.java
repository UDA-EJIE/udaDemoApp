package com.ejie.aa79b.model;

import java.util.List;

public class ListaCategExp implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<CategExp> listaCategExp;

	public ListaCategExp() {
//		Constructor vacio
	}

	/**
	 * @return the listaCategExp
	 */
	public List<CategExp> getListaCategExp() {
		return listaCategExp;
	}

	/**
	 * @param listaCategExp the listaCategExp to set
	 */
	public void setListaCategExp(List<CategExp> listaCategExp) {
		this.listaCategExp = listaCategExp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListaCategExp [listaCategExp=" + listaCategExp + "]";
	}
	
	
	

}
