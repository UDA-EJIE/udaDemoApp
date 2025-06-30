package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bEntradaAuditoria extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long anyo;
	private Integer numExp;
	private String anyoNumExp;
	private Long estado;

	public Aa79bEntradaAuditoria() {
		// Constructor
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
	 * @return the anyoNumExp
	 */
	public String getAnyoNumExp() {
		this.setAnyoNumExp(null);
		if (this.anyo != null && this.numExp != null) {
			this.setAnyoNumExp(String.valueOf(this.anyo).substring(2) + "/" + String.format("%06d", this.numExp));
		}

		return this.anyoNumExp;
	}

	/**
	 * @param anyoNumExp the anyoNumExp to set
	 */
	public void setAnyoNumExp(String anyoNumExp) {
		this.anyoNumExp = anyoNumExp;
	}

	/**
	 * @return the estado
	 */
	public Long getEstado() {
		return this.estado;
	}

	/**
	 * @param estado the id to set
	 */
	public void setEstado(Long estado) {
		this.estado = estado;
	}

}
