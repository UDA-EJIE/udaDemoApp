package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

public class Aa79bDocumentoTareaAdjunto implements Serializable {

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
	private Long fechaAlta;

	private BigDecimal idTarea;
	private BigDecimal idDocOriginal;
	private String reqEncriptadoOriginal;
	private String indEncriptado;

	public Aa79bDocumentoTareaAdjunto() {
		// Constructor
	}

	/**
	 * @return the idFichero
	 */
	public BigDecimal getIdFichero() {
		return idFichero;
	}

	/**
	 * @param idFichero
	 *            the idFichero to set
	 */
	public void setIdFichero(BigDecimal idFichero) {
		this.idFichero = idFichero;
	}

	/**
	 * @return the indVisible
	 */
	public String getIndVisible() {
		return indVisible;
	}

	/**
	 * @param indVisible
	 *            the indVisible to set
	 */
	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the tamano
	 */
	public Long getTamano() {
		return tamano;
	}

	/**
	 * @param tamano
	 *            the tamano to set
	 */
	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}

	/**
	 * @return the encriptado
	 */
	public String getEncriptado() {
		return encriptado;
	}

	/**
	 * @param encriptado
	 *            the encriptado to set
	 */
	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the rutaPif
	 */
	public String getRutaPif() {
		return rutaPif;
	}

	/**
	 * @param rutaPif
	 *            the rutaPif to set
	 */
	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @param bytes
	 *            the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the fechaAlta
	 */
	public Long getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	 * @return the idDocOriginal
	 */
	public BigDecimal getIdDocOriginal() {
		return idDocOriginal;
	}

	/**
	 * @param idDocOriginal
	 *            the idDocOriginal to set
	 */
	public void setIdDocOriginal(BigDecimal idDocOriginal) {
		this.idDocOriginal = idDocOriginal;
	}

	/**
	 * @return the reqEncriptadoOriginal
	 */
	public String getReqEncriptadoOriginal() {
		return reqEncriptadoOriginal;
	}

	/**
	 * @param reqEncriptadoOriginal
	 *            the reqEncriptadoOriginal to set
	 */
	public void setReqEncriptadoOriginal(String reqEncriptadoOriginal) {
		this.reqEncriptadoOriginal = reqEncriptadoOriginal;
	}

	/**
	 * @return the indEncriptado
	 */
	public String getIndEncriptado() {
		return indEncriptado;
	}

	/**
	 * @param indEncriptado
	 *            the indEncriptado to set
	 */
	public void setIndEncriptado(String indEncriptado) {
		this.indEncriptado = indEncriptado;
	}

	@Override
	public String toString() {
		return "Aa79bDocumentoTareaAdjunto [idFichero=" + idFichero + ", indVisible=" + indVisible + ", titulo="
				+ titulo + ", nombre=" + nombre + ", extension=" + extension + ", contentType=" + contentType
				+ ", tamano=" + tamano + ", encriptado=" + encriptado + ", oid=" + oid + ", rutaPif=" + rutaPif
				+ ", bytes=" + Arrays.toString(bytes) + ", error=" + error + ", fechaAlta=" + fechaAlta + ", idTarea="
				+ idTarea + ", idDocOriginal=" + idDocOriginal + ", reqEncriptadoOriginal=" + reqEncriptadoOriginal
				+ "]";
	}

}
