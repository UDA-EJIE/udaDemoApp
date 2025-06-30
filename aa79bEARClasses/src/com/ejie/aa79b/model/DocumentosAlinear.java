package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DocumentosAlinear implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idTarea;
	private String indCorreciones;
	private BigDecimal idFicheroLerroko;
	private DocumentoTareaAdjunto ficheroLerroko;
	private Date fechaAltaLerroko;

	/**
	 * Method 'DocumentosAlinear'.
	 */
	public DocumentosAlinear() {
		/* ... */
	}

	public BigDecimal getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	public String getIndCorreciones() {
		return indCorreciones;
	}

	public void setIndCorreciones(String indCorreciones) {
		this.indCorreciones = indCorreciones;
	}

	public BigDecimal getIdFicheroLerroko() {
		return idFicheroLerroko;
	}

	public void setIdFicheroLerroko(BigDecimal idFicheroLerroko) {
		this.idFicheroLerroko = idFicheroLerroko;
	}

	public Date getFechaAltaLerroko() {
		return fechaAltaLerroko;
	}

	public void setFechaAltaLerroko(Date fechaAltaLerroko) {
		this.fechaAltaLerroko = fechaAltaLerroko;
	}

	public DocumentoTareaAdjunto getFicheroLerroko() {
		return ficheroLerroko;
	}

	public void setFicheroLerroko(DocumentoTareaAdjunto ficheroLerroko) {
		this.ficheroLerroko = ficheroLerroko;
	}

}
