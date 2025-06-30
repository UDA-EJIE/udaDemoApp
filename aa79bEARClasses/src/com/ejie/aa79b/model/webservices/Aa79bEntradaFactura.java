package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

/**
 * Aa79bEntradaDetalleFactura
 * 
 * @author mrodriguez
 */
public class Aa79bEntradaFactura extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = -4119506530138612057L;

	private String dni;
	private Long idFactura;

	/**
	 * Constructor
	 */
	public Aa79bEntradaFactura() {
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

}
