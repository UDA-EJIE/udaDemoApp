package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * EntradaReceptoresAutorizados
 * @author mrodriguez
 */
public class EntradaReceptoresAutorizados extends IdiomaWS implements Serializable {
	
	private static final long serialVersionUID = -5994508499534026242L;
	private String dni;
	private Long anyo;
    private Integer numExp;
    private ListaReceptoresAutorizados listaReceptoresAutorizados;
    
    /**
	 * @return the dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
	}

	/**
	 * @param anyo the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	/**
	 * @return the numExp
	 */
	public Integer getNumExp() {
		return this.numExp;
	}

	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}
	
	/**
	 * @return the listaReceptoresAutorizados
	 */
	public ListaReceptoresAutorizados getListaReceptoresAutorizados() {
		return this.listaReceptoresAutorizados;
	}

	/**
	 * @param listaReceptoresAutorizados the listaReceptoresAutorizados to set
	 */
	public void setListaReceptoresAutorizados(ListaReceptoresAutorizados listaReceptoresAutorizados) {
		this.listaReceptoresAutorizados = listaReceptoresAutorizados;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append(", [ listaReceptoresAutorizados: ").append(this.listaReceptoresAutorizados).append(" ]");
		result.append("}");
		return result.toString();
	}

}
