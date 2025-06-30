package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author eaguirresarobe
 *
 */
public class AuditoriaDocumentoSeccionExpediente implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long idAuditoria;
	private Integer idSeccion;
	private BigDecimal idFicheroInterno;
	private Integer codigo;
	private String nombre;
	private String oid;
	private String rutaPif;
	private String tituloFichero;
	private byte[] bytes;
	private String contentType;
	private String error;
	private String extension;
	private Long tamano;
	private String encriptado;

	/**
	 *
	 */
	public AuditoriaDocumentoSeccionExpediente() {
		// constructor vacio
	}

	/**
	 * @return the idAuditoria
	 */
	public Long getIdAuditoria() {
		return this.idAuditoria;
	}

	/**
	 * @param idAuditoria the idAuditoria to set
	 */
	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	/**
	 * @return the idSeccion
	 */
	public Integer getIdSeccion() {
		return this.idSeccion;
	}

	/**
	 * @param idSeccion the idSeccion to set
	 */
	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	/**
	 * @return the idFicheroInterno
	 */
	public BigDecimal getIdFicheroInterno() {
		return this.idFicheroInterno;
	}

	/**
	 * @param idFicheroInterno the idFicheroInterno to set
	 */
	public void setIdFicheroInterno(BigDecimal idFicheroInterno) {
		this.idFicheroInterno = idFicheroInterno;
	}

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return this.codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return this.oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the rutaPif
	 */
	public String getRutaPif() {
		return this.rutaPif;
	}

	/**
	 * @param rutaPif the rutaPif to set
	 */
	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	/**
	 * @return the tituloFichero
	 */
	public String getTituloFichero() {
		return this.tituloFichero;
	}

	/**
	 * @param tituloFichero the tituloFichero to set
	 */
	public void setTituloFichero(String tituloFichero) {
		this.tituloFichero = tituloFichero;
	}

	/**
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return this.bytes;
	}

	/**
	 * @param bytes the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the extension
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return the tamano
	 */
	public Long getTamano() {
		return this.tamano;
	}

	/**
	 * @param tamano the tamano to set
	 */
	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}

	/**
	 * @return the encriptado
	 */
	public String getEncriptado() {
		return this.encriptado;
	}

	/**
	 * @param encriptado the encriptado to set
	 */
	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}
}
