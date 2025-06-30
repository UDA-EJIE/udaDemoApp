package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bLotes implements Serializable {

	private static final long serialVersionUID = 1333538690273213597L;
	private Integer idLote;
	private String nombreLote;
	private String descLoteEu;
	private String descLoteEs;
	private Aa79bPersona contacto;
	private Aa79bEmpresasProveedoras empresasProveedoras;

	/**
	 * @return the idLote
	 */
	public Integer getIdLote() {
		return idLote;
	}

	/**
	 * @param idLote the idLote to set
	 */
	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	/**
	 * @return the nombreLote
	 */
	public String getNombreLote() {
		return nombreLote;
	}

	/**
	 * @param nombreLote the nombreLote to set
	 */
	public void setNombreLote(String nombreLote) {
		this.nombreLote = nombreLote;
	}

	/**
	 * @return the descLoteEu
	 */
	public String getDescLoteEu() {
		return this.descLoteEu;
	}

	/**
	 * @param descLoteEu the descLoteEu to set
	 */
	public void setDescLoteEu(String descLoteEu) {
		this.descLoteEu = descLoteEu;
	}

	/**
	 * @return the descLoteEs
	 */
	public String getDescLoteEs() {
		return this.descLoteEs;
	}

	/**
	 * @param descLoteEs the descLoteEs to set
	 */
	public void setDescLoteEs(String descLoteEs) {
		this.descLoteEs = descLoteEs;
	}

	public Aa79bPersona getContacto() {
		return contacto;
	}

	public void setContacto(Aa79bPersona contacto) {
		this.contacto = contacto;
	}

	/**
	 * @return the empresasProveedoras
	 */
	public Aa79bEmpresasProveedoras getEmpresasProveedoras() {
		return empresasProveedoras;
	}

	/**
	 * @param empresasProveedoras the empresasProveedoras to set
	 */
	public void setEmpresasProveedoras(Aa79bEmpresasProveedoras empresasProveedoras) {
		this.empresasProveedoras = empresasProveedoras;
	}

	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append(" [ idLote: ").append(this.idLote).append(" ]");
		result.append(", [ nombreLote: ").append(this.nombreLote).append(" ]");
		result.append("}");
		return result.toString();
	}

}
