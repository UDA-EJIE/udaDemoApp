package com.ejie.aa79b.model;

import java.io.Serializable;

public class DatosConfeccionarFactura implements Serializable {

	private static final long serialVersionUID = 1L;

	private Entidad datosEntidad;
	private DatosContacto datosContacto;
	private Direccion dirFacturacContacto;
	private Long porcentajeIva;

	public DatosConfeccionarFactura() {
		// Constructor
	}

	/**
	 * @return the datosEntidad
	 */
	public Entidad getDatosEntidad() {
		return datosEntidad;
	}

	/**
	 * @param datosEntidad
	 *            the datosEntidad to set
	 */
	public void setDatosEntidad(Entidad datosEntidad) {
		this.datosEntidad = datosEntidad;
	}

	/**
	 * @return the dirFacturacContacto
	 */
	public Direccion getDirFacturacContacto() {
		return dirFacturacContacto;
	}

	/**
	 * @param dirFacturacContacto
	 *            the dirFacturacContacto to set
	 */
	public void setDirFacturacContacto(Direccion dirFacturacContacto) {
		this.dirFacturacContacto = dirFacturacContacto;
	}

	/**
	 * @return the porcentajeIva
	 */
	public Long getPorcentajeIva() {
		return porcentajeIva;
	}

	/**
	 * @param porcentajeIva
	 *            the porcentajeIva to set
	 */
	public void setPorcentajeIva(Long porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public DatosContacto getDatosContacto() {
		return datosContacto;
	}

	public void setDatosContacto(DatosContacto datosContacto) {
		this.datosContacto = datosContacto;
	}

}
