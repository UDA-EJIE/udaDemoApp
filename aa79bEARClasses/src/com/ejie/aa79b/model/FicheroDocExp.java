package com.ejie.aa79b.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.ejie.aa79b.common.JsonFechaHoraDeserializer;
import com.ejie.aa79b.common.JsonFechaHoraSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author ejruiz
 *
 */

public class FicheroDocExp implements java.io.Serializable {

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
	private Contacto contacto;

	// Vienen de DocumentoExpediente
	private Date fechaAlta;
	private String dniAlta;
	private BigDecimal idDocVersion;
	private BigDecimal idDocRel;

	private BigDecimal idDocInsertado;
	// Para facilitar el caso en que hay dos ficheros que se guardan (2 idDoc
	// distintos en la lista de ficheros). Ahora realmente valdría para guardar
	// el idDoc definitivo...

	private String nombreDniAlta;
	private String apel1DniAlta;
	private String apel2DniAlta;

	// Para indicar si el fichero lo ha subido un recurso INTERNO o EXTERNO
	private String origen;

	// Para la descarga de documentos (traducciones) intermedios
	private String tituloAux;
	private String descTarea;

	private String titulo;

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
	 * @return the cabecera
	 */
	public String getCabecera() {
		return this.cabecera;
	}

	/**
	 * @param cabecera the cabecera to set
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
	 * @param rutaPif the rutaPif to set
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
	 * @param fechaCarga the fechaCarga to set
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

	public String getTamanoKB() {
		if (this.tamano != null) {
			float x = (float) this.tamano / 1024;
			return new DecimalFormat("0.##").format(x);
		} else {
			return null;
		}

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

	public Contacto getContacto() {
		return this.contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public BigDecimal getIdDocInsertado() {
		return this.idDocInsertado;
	}

	public void setIdDocInsertado(BigDecimal idDocInsertado) {
		this.idDocInsertado = idDocInsertado;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getDniAlta() {
		return this.dniAlta;
	}

	public void setDniAlta(String dniAlta) {
		this.dniAlta = dniAlta;
	}

	public BigDecimal getIdDocVersion() {
		return this.idDocVersion;
	}

	public void setIdDocVersion(BigDecimal idDocVersion) {
		this.idDocVersion = idDocVersion;
	}

	public BigDecimal getIdDocRel() {
		return this.idDocRel;
	}

	public void setIdDocRel(BigDecimal idDocRel) {
		this.idDocRel = idDocRel;
	}

	/**
	 * @return the nombreDniAlta
	 */
	public String getNombreDniAlta() {
		return nombreDniAlta;
	}

	/**
	 * @param nombreDniAlta the nombreDniAlta to set
	 */
	public void setNombreDniAlta(String nombreDniAlta) {
		this.nombreDniAlta = nombreDniAlta;
	}

	/**
	 * @return the apel1DniAlta
	 */
	public String getApel1DniAlta() {
		return apel1DniAlta;
	}

	/**
	 * @param apel1DniAlta the apel1DniAlta to set
	 */
	public void setApel1DniAlta(String apel1DniAlta) {
		this.apel1DniAlta = apel1DniAlta;
	}

	/**
	 * @return the apel2DniAlta
	 */
	public String getApel2DniAlta() {
		return apel2DniAlta;
	}

	/**
	 * @param apel2DniAlta the apel2DniAlta to set
	 */
	public void setApel2DniAlta(String apel2DniAlta) {
		this.apel2DniAlta = apel2DniAlta;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	/**
	 * @return the tituloAux
	 */
	public String getTituloAux() {
		return tituloAux;
	}

	/**
	 * @param tituloAux the tituloAux to set
	 */
	public void setTituloAux(String tituloAux) {
		this.tituloAux = tituloAux;
	}

	/**
	 * @return the descTarea
	 */
	public String getDescTarea() {
		return descTarea;
	}

	/**
	 * @param descTarea the descTarea to set
	 */
	public void setDescTarea(String descTarea) {
		this.descTarea = descTarea;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
