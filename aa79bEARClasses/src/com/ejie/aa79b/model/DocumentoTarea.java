package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DocumentoTarea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idTarea;
	private BigDecimal idTareaRel;
	private DocumentosExpediente documentoOriginal;
	private DocumentoTareaAdjunto documentoAdjunto;
	private DocumentoTareaAdjunto documentoFinalOriginal;
	private DocumentoTareaAdjunto documentoJustificante;
	private DocumentoTareaAdjunto documentoFinalFirmado;
	private DocumentoTareaAdjunto documentoOriginalFirmado;
	private String groupBy;
	private BigDecimal idTareaAgrupacion;
	private Date fechaEjecucionTarea;

	public BigDecimal getIdTarea() {
		return this.idTarea;
	}

	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	public DocumentosExpediente getDocumentoOriginal() {
		return this.documentoOriginal;
	}

	public void setDocumentoOriginal(DocumentosExpediente documentoOriginal) {
		this.documentoOriginal = documentoOriginal;
	}

	public DocumentoTareaAdjunto getDocumentoAdjunto() {
		return this.documentoAdjunto;
	}

	public void setDocumentoAdjunto(DocumentoTareaAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	public DocumentoTareaAdjunto getDocumentoFinalOriginal() {
		return documentoFinalOriginal;
	}

	public void setDocumentoFinalOriginal(DocumentoTareaAdjunto documentoFinalOriginal) {
		this.documentoFinalOriginal = documentoFinalOriginal;
	}

	public DocumentoTareaAdjunto getDocumentoJustificante() {
		return this.documentoJustificante;
	}

	public void setDocumentoJustificante(DocumentoTareaAdjunto documentoJustificante) {
		this.documentoJustificante = documentoJustificante;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	/**
	 * @return the idTareaRel
	 */
	public BigDecimal getIdTareaRel() {
		return idTareaRel;
	}

	/**
	 * @param idTareaRel the idTareaRel to set
	 */
	public void setIdTareaRel(BigDecimal idTareaRel) {
		this.idTareaRel = idTareaRel;
	}

	/**
	 * @return the idTareaAgrupacion
	 */
	public BigDecimal getIdTareaAgrupacion() {
		return idTareaAgrupacion;
	}

	/**
	 * @param idTareaAgrupacion the idTareaAgrupacion to set
	 */
	public void setIdTareaAgrupacion(BigDecimal idTareaAgrupacion) {
		this.idTareaAgrupacion = idTareaAgrupacion;
	}

	/**
	 * @return the fechaEjecucionTarea
	 */
	public Date getFechaEjecucionTarea() {
		return fechaEjecucionTarea;
	}

	/**
	 * @param fechaEjecucionTarea the fechaEjecucionTarea to set
	 */
	public void setFechaEjecucionTarea(Date fechaEjecucionTarea) {
		this.fechaEjecucionTarea = fechaEjecucionTarea;
	}

	@Override
	public String toString() {
		return "DocumentoTareaExpediente [idTarea=" + this.idTarea + ", idTareaRel=" + this.idTareaRel
				+ ", documentoOriginal=" + this.documentoOriginal + ", documentoAdjunto=" + this.documentoAdjunto
				+ ", documentoJustificante=" + this.documentoJustificante + "]";
	}

	/**
	 * @return the documentoFinalFirmado
	 */
	public DocumentoTareaAdjunto getDocumentoFinalFirmado() {
		return documentoFinalFirmado;
	}

	/**
	 * @param documentoFinalFirmado the documentoFinalFirmado to set
	 */
	public void setDocumentoFinalFirmado(DocumentoTareaAdjunto documentoFinalFirmado) {
		this.documentoFinalFirmado = documentoFinalFirmado;
	}

	/**
	 * @return the documentoOriginalFirmado
	 */
	public DocumentoTareaAdjunto getDocumentoOriginalFirmado() {
		return documentoOriginalFirmado;
	}

	/**
	 * @param documentoOriginalFirmado the documentoOriginalFirmado to set
	 */
	public void setDocumentoOriginalFirmado(DocumentoTareaAdjunto documentoOriginalFirmado) {
		this.documentoOriginalFirmado = documentoOriginalFirmado;
	}

}
