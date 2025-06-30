/**
 * 
 */
package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * @author eaguirresarobe
 *
 */
public class ExpedienteFacturar implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long anyo;
	private Integer numExp;
	private String tipoEntidadAsoc;
	private Integer idEntidadAsoc;
	private String dniContacto;
	private Long porFactura;

	public ExpedienteFacturar() {
		// Constructor
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return anyo;
	}

	/**
	 * @param anyo
	 *            the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	/**
	 * @return the numExp
	 */
	public Integer getNumExp() {
		return numExp;
	}

	/**
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the tipoEntidadAsoc
	 */
	public String getTipoEntidadAsoc() {
		return tipoEntidadAsoc;
	}

	/**
	 * @param tipoEntidadAsoc
	 *            the tipoEntidadAsoc to set
	 */
	public void setTipoEntidadAsoc(String tipoEntidadAsoc) {
		this.tipoEntidadAsoc = tipoEntidadAsoc;
	}

	/**
	 * @return the idEntidadAsoc
	 */
	public Integer getIdEntidadAsoc() {
		return idEntidadAsoc;
	}

	/**
	 * @param idEntidadAsoc
	 *            the idEntidadAsoc to set
	 */
	public void setIdEntidadAsoc(Integer idEntidadAsoc) {
		this.idEntidadAsoc = idEntidadAsoc;
	}

	/**
	 * @return the dniContacto
	 */
	public String getDniContacto() {
		return dniContacto;
	}

	/**
	 * @param dniContacto
	 *            the dniContacto to set
	 */
	public void setDniContacto(String dniContacto) {
		this.dniContacto = dniContacto;
	}

	/**
	 * @return the porFactura
	 */
	public Long getPorFactura() {
		return porFactura;
	}

	/**
	 * @param porFactura
	 *            the porFactura to set
	 */
	public void setPorFactura(Long porFactura) {
		this.porFactura = porFactura;
	}

}
