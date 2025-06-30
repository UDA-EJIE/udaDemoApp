package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * EntradaExpedientesRelacionados
 * @author mrodriguez
 */
public class EntradaExpedientesRelacionados extends IdiomaWS implements Serializable {
	
	private static final long serialVersionUID = 4605925963892701513L;
	private String dni;
	private Long anyo;
    private Integer numExp;
    private String dniGestor;
    
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
	 * @return the dniGestor
	 */
	public String getDniGestor() {
		return dniGestor;
	}

	/**
	 * @param dniGestor the dniGestor to set
	 */
	public void setDniGestor(String dniGestor) {
		this.dniGestor = dniGestor;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append("}");
		return result.toString();
	}

}
