package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DocumentosPostTraduccionBOE implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idTarea;
	private String indCorreciones;
	private BigDecimal idFicheroInforme;
	private DocumentoTareaAdjunto ficheroInforme;
	private Date fechaAltaInforme;
	private BigDecimal idFicheroDoc;
	private DocumentoTareaAdjunto ficheroDoc;
	private Date fechaAltaDoc;

	/**
	 * Method 'TiposRevision'.
	 */
	public DocumentosPostTraduccionBOE() {
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

	public BigDecimal getIdFicheroInforme() {
		return idFicheroInforme;
	}

	public void setIdFicheroInforme(BigDecimal idFicheroInforme) {
		this.idFicheroInforme = idFicheroInforme;
	}

	public Date getFechaAltaInforme() {
		return fechaAltaInforme;
	}

	public void setFechaAltaInforme(Date fechaAltaInforme) {
		this.fechaAltaInforme = fechaAltaInforme;
	}

	public BigDecimal getIdFicheroDoc() {
		return idFicheroDoc;
	}

	public void setIdFicheroDoc(BigDecimal idFicheroDoc) {
		this.idFicheroDoc = idFicheroDoc;
	}

	public Date getFechaAltaDoc() {
		return fechaAltaDoc;
	}

	public void setFechaAltaDoc(Date fechaAltaDoc) {
		this.fechaAltaDoc = fechaAltaDoc;
	}

	public DocumentoTareaAdjunto getFicheroInforme() {
		return ficheroInforme;
	}

	public void setFicheroInforme(DocumentoTareaAdjunto ficheroInforme) {
		this.ficheroInforme = ficheroInforme;
	}

	public DocumentoTareaAdjunto getFicheroDoc() {
		return ficheroDoc;
	}

	public void setFicheroDoc(DocumentoTareaAdjunto ficheroDoc) {
		this.ficheroDoc = ficheroDoc;
	}

	@Override
	public String toString() {
		return "DocumentosPostTraduccionBOE [idTarea=" + idTarea + ", indCorreciones=" + indCorreciones
				+ ", idFicheroInforme=" + idFicheroInforme + ", fechaAltaInforme=" + fechaAltaInforme
				+ ", idFicheroDoc=" + idFicheroDoc + ", fechaAltaDoc=" + fechaAltaDoc + "]";
	}

}
