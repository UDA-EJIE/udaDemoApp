package com.ejie.aa79b.model;

import java.util.Date;

import com.ejie.aa79b.common.JsonFechaHoraDeserializer;
import com.ejie.aa79b.common.JsonFechaHoraSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author ejruiz
 *
 */

public class Fichero implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String nombre;
	private String oid;
	private String rutaPif;
	private Date fechaCarga;
	private String cabecera;
	private byte[] bytes;
	private String contentType;
	private String error;

	private String extension;
	private Long tamano;
	private String encriptado;

	/**
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return this.codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
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
	 * @param nombre
	 *            the nombre to set
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
	 * @param oid
	 *            the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the cabecera
	 */
	public String getCabecera() {
		return this.cabecera;
	}

	/**
	 * @param cabecera
	 *            the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * @return the rutaPif
	 */
	public String getRutaPif() {
		return this.rutaPif;
	}

	/**
	 * @param rutaPif
	 *            the rutaPif to set
	 */
	public void setRutaPif(String rutaPif) {
		this.rutaPif = rutaPif;
	}

	/**
	 * @return the fechaCarga
	 */
	@JsonSerialize(using = JsonFechaHoraSerializer.class)
	public Date getFechaCarga() {
		return this.fechaCarga;
	}

	/**
	 * @param fechaCarga
	 *            the fechaCarga to set
	 */
	@JsonDeserialize(using = JsonFechaHoraDeserializer.class)
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public byte[] getBytes() {
		return this.bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
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

}
