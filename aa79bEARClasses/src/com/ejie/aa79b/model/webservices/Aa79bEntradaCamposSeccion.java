package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bEntradaCamposSeccion extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idAuditoria;
	private Integer idSeccion;

	/**
	 * Method 'Aa79bEntradaCamposSeccion'
	 */
	public Aa79bEntradaCamposSeccion() {
		// constructor
	}

	public Long getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Integer getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

}
