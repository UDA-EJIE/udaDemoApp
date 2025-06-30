package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

/**
 * Aa79bEntradaConsultaFacturas
 * 
 * @author mrodriguez
 */
public class Aa79bEntradaConsultaFacturas extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = -463319874943675005L;

	private String dni;
	private Long idFactura;
	private Long fechaEmisionIni;
	private Long fechaEmisionFin;
	private Long idEstadoFactura;
	private String dniContacto;
	private String nombreContacto;
	private String apellidosContacto;
	private Long anyoExp;
	private Integer numExp;
	private String tituloExp;

	/**
	 * Constructor
	 */
	public Aa79bEntradaConsultaFacturas() {
		// Constructor
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the idFactura
	 */
	public Long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura
	 *            the idFactura to set
	 */
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the fechaEmisionIni
	 */
	public Long getFechaEmisionIni() {
		return fechaEmisionIni;
	}

	/**
	 * @param fechaEmisionIni
	 *            the fechaEmisionIni to set
	 */
	public void setFechaEmisionIni(Long fechaEmisionIni) {
		this.fechaEmisionIni = fechaEmisionIni;
	}

	/**
	 * @return the fechaEmisionFin
	 */
	public Long getFechaEmisionFin() {
		return fechaEmisionFin;
	}

	/**
	 * @param fechaEmisionFin
	 *            the fechaEmisionFin to set
	 */
	public void setFechaEmisionFin(Long fechaEmisionFin) {
		this.fechaEmisionFin = fechaEmisionFin;
	}

	/**
	 * @return the idEstadoFactura
	 */
	public Long getIdEstadoFactura() {
		return idEstadoFactura;
	}

	/**
	 * @param idEstadoFactura
	 *            the idEstadoFactura to set
	 */
	public void setIdEstadoFactura(Long idEstadoFactura) {
		this.idEstadoFactura = idEstadoFactura;
	}

	/**
	 * @return the nombreContacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * @param nombreContacto
	 *            the nombreContacto to set
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/**
	 * @return the apellidosContacto
	 */
	public String getApellidosContacto() {
		return apellidosContacto;
	}

	/**
	 * @param apellidosContacto
	 *            the apellidosContacto to set
	 */
	public void setApellidosContacto(String apellidosContacto) {
		this.apellidosContacto = apellidosContacto;
	}

	/**
	 * @return the anyoExp
	 */
	public Long getAnyoExp() {
		return anyoExp;
	}

	/**
	 * @param anyoExp
	 *            the anyoExp to set
	 */
	public void setAnyoExp(Long anyoExp) {
		this.anyoExp = anyoExp;
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
	 * @return the tituloExp
	 */
	public String getTituloExp() {
		return tituloExp;
	}

	/**
	 * @param tituloExp
	 *            the tituloExp to set
	 */
	public void setTituloExp(String tituloExp) {
		this.tituloExp = tituloExp;
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

}
