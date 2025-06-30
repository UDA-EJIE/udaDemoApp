package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bLoteCombo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombreLote;
	private String descEs;
	private String descEu;
	private String estado;

	public Aa79bLoteCombo() {
		// Constructor
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nombreLote
	 */
	public String getNombreLote() {
		return nombreLote;
	}

	/**
	 * @param nombreLote
	 *            the nombreLote to set
	 */
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}

	/**
	 * @return the descEs
	 */
	public String getDescEs() {
		return descEs;
	}

	/**
	 * @param descEs
	 *            the descEs to set
	 */
	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}

	/**
	 * @return the descEu
	 */
	public String getDescEu() {
		return descEu;
	}

	/**
	 * @param descEu
	 *            the descEu to set
	 */
	public void setDescEu(String descEu) {
		this.descEu = descEu;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
