package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;

public class DocumentoTareaAdjunto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idFichero;
	private String indVisible;
	private String titulo;

	private String nombre;
	private String extension;
	private String contentType;
	private Long tamano;
	private String encriptado;
	private String oid;
	private String rutaPif;

	private byte[] bytes;
	private String error;
	private Date fechaAlta;

	private BigDecimal idTarea;
	private BigDecimal idDocOriginal;
	private BigDecimal idDocOriginalFinal;
	private String reqEncriptadoOriginal;
	private int estadoFirma;

	/**
	 * Method 'TiposRevision'.
	 */
	public DocumentoTareaAdjunto() {
		/* ... */
	}

	/**
	 * Method 'TiposRevision'.
	 *
	 * @param id018 Long
	 */
	public DocumentoTareaAdjunto(BigDecimal idFichero088) {
		this.idFichero = idFichero088;
	}

	public BigDecimal getIdFichero() {
		return this.idFichero;
	}

	public void setIdFichero(BigDecimal idFichero) {
		this.idFichero = idFichero;
	}

	public String getIndVisible() {
		return this.indVisible;
	}

	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getTamano() {
		return this.tamano;
	}

	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}

	public String getEncriptado() {
		return this.encriptado;
	}

	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getRutaPif() {
		return this.rutaPif;
	}

	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	public byte[] getBytes() {
		return this.bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public BigDecimal getIdTarea() {
		return this.idTarea;
	}

	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	public BigDecimal getIdDocOriginal() {
		return this.idDocOriginal;
	}

	public void setIdDocOriginal(BigDecimal idDocOriginal) {
		this.idDocOriginal = idDocOriginal;
	}

	public BigDecimal getIdDocOriginalFinal() {
		return this.idDocOriginalFinal;
	}

	public void setIdDocOriginalFinal(BigDecimal idDocOriginalFinal) {
		this.idDocOriginalFinal = idDocOriginalFinal;
	}

	public String getReqEncriptadoOriginal() {
		return this.reqEncriptadoOriginal;
	}

	public void setReqEncriptadoOriginal(String reqEncriptadoOriginal) {
		this.reqEncriptadoOriginal = reqEncriptadoOriginal;
	}

	public String getTamanoKB() {
		if (this.tamano != null) {
			float x = (float) this.tamano / 1024;
			return new DecimalFormat("0.##").format(x);
		}
		return null;
	}

	/**
	 * @return the estadoFirma
	 */
	public int getEstadoFirma() {
		return this.estadoFirma;
	}

	/**
	 * @param estadoFirma the estadoFirma to set
	 */
	public void setEstadoFirma(int estadoFirma) {
		this.estadoFirma = estadoFirma;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DocumentoTareaAdjunto [idFichero=" + this.idFichero + ", indVisible=" + this.indVisible + ", titulo=" + this.titulo
				+ ", nombre=" + this.nombre + ", extension=" + this.extension + ", contentType=" + this.contentType + ", tamano="
				+ this.tamano + ", encriptado=" + this.encriptado + ", oid=" + this.oid + ", rutaPif=" + this.rutaPif + ", bytes="
				+ Arrays.toString(this.bytes) + ", error=" + this.error + ", fechaAlta=" + this.fechaAlta + ", idTarea=" + this.idTarea
				+ ", idDocOriginal=" + this.idDocOriginal + ", reqEncriptadoOriginal=" + this.reqEncriptadoOriginal
				+ ", estadoFirma=" + this.estadoFirma + "]";
	}
}
