package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

/**
 * @author javarona
 * 
 */
public class Aa79bFacturaExpediente implements Serializable {

	/**
	 * serialVersionUID: long.
	 */
	private static final long serialVersionUID = 1L;

	private Long fechaEmision;
	private Long idFactura;
	private Long codConcatenado;
	private Aa79bEstadoFactura estadoFactura;

	/**
	 * @return the fechaEmision
	 */
	public Long getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Long fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the idFactura
	 */
	public Long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getCodConcatenado() {
		return codConcatenado;
	}

	public void setCodConcatenado(Long codConcatenado) {
		this.codConcatenado = codConcatenado;
	}

	/**
	 * @return the estadoFactura
	 */
	public Aa79bEstadoFactura getEstadoFactura() {
		return estadoFactura;
	}

	/**
	 * @param estadoFactura the estadoFactura to set
	 */
	public void setEstadoFactura(Aa79bEstadoFactura estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

}
