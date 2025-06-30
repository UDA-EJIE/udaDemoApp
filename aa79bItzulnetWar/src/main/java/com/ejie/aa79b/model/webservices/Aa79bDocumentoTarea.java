package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

public class Aa79bDocumentoTarea implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private Aa79bDocumentosExpediente documentoOriginal;
	private Aa79bDocumentoTareaAdjunto documentoAdjunto;
	private Aa79bDocumentoTareaAdjunto documentoJustificante;

	public Aa79bDocumentoTarea() {
		// Constructor
	}

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return idTarea;
	}

	/**
	 * @param idTarea
	 *            the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the documentoOriginal
	 */
	public Aa79bDocumentosExpediente getDocumentoOriginal() {
		return documentoOriginal;
	}

	/**
	 * @param documentoOriginal
	 *            the documentoOriginal to set
	 */
	public void setDocumentoOriginal(Aa79bDocumentosExpediente documentoOriginal) {
		this.documentoOriginal = documentoOriginal;
	}

	/**
	 * @return the documentoAdjunto
	 */
	public Aa79bDocumentoTareaAdjunto getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	/**
	 * @param documentoAdjunto
	 *            the documentoAdjunto to set
	 */
	public void setDocumentoAdjunto(Aa79bDocumentoTareaAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	/**
	 * @return the documentoJustificante
	 */
	public Aa79bDocumentoTareaAdjunto getDocumentoJustificante() {
		return documentoJustificante;
	}

	/**
	 * @param documentoJustificante
	 *            the documentoJustificante to set
	 */
	public void setDocumentoJustificante(Aa79bDocumentoTareaAdjunto documentoJustificante) {
		this.documentoJustificante = documentoJustificante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aa79bDocumentoTarea [idTarea=" + idTarea + ", documentoOriginal=" + documentoOriginal
				+ ", documentoAdjunto=" + documentoAdjunto + ", documentoJustificante=" + documentoJustificante + "]";
	}

}
