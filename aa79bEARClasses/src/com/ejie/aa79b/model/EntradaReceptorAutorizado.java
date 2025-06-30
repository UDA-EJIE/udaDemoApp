package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * EntradaReceptorAutorizado
 * @author mrodriguez
 */
public class EntradaReceptorAutorizado extends IdiomaWS implements Serializable {
	
	private static final long serialVersionUID = 3353343144070742523L;
	private String dni;
	private Long anyo;
    private Integer numExp;
    private String dniReceptor;
    
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
	 * @return the dniReceptor
	 */
	public String getDniReceptor() {
		return this.dniReceptor;
	}

	/**
	 * @param dniReceptor the dniReceptor to set
	 */
	public void setDniReceptor(String dniReceptor) {
		this.dniReceptor = dniReceptor;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append(", [ dniReceptor: ").append(this.dniReceptor).append(" ]");
		result.append("}");
		return result.toString();
	}

}
