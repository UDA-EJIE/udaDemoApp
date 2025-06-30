package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

public class Aa79bFicheroDocExp implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String nombre;
	private String nombreDniAlta;
	private String apel1DniAlta;
	private String apel2DniAlta;
	private String rutaPif;
	private byte[] bytes;
	private String contentType;
	private String extension;
	private Long tamano;
	private String encriptado;
	private Aa79bContactoDoc contacto;
	// Vienen de DocumentoExpediente
	private Long fechaAlta;
	private String horaAlta;
	private BigDecimal idDocRel;
	private BigDecimal idDocInsertado; // Para facilitar el caso en que hay dos
										// ficheros que se guardan (2 idDoc
										// distintos en la lista de ficheros).
										// Ahora realmente valdría para guardar
										// el idDoc definitivo...
	private String error;
	private String oid;
	private String nombreUpload;
	private String origen;
	private String titulo;

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nombreDniAlta
	 */
	public String getNombreDniAlta() {
		return nombreDniAlta;
	}

	/**
	 * @param nombreDniAlta
	 *            the nombreDniAlta to set
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
	 * @param apel1DniAlta
	 *            the apel1DniAlta to set
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
	 * @param apel2DniAlta
	 *            the apel2DniAlta to set
	 */
	public void setApel2DniAlta(String apel2DniAlta) {
		this.apel2DniAlta = apel2DniAlta;
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

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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

	public Aa79bContactoDoc getContacto() {
		return this.contacto;
	}

	public void setContacto(Aa79bContactoDoc contacto) {
		this.contacto = contacto;
	}

	public BigDecimal getIdDocRel() {
		return this.idDocRel;
	}

	public void setIdDocRel(BigDecimal idDocRel) {
		this.idDocRel = idDocRel;
	}

	public BigDecimal getIdDocInsertado() {
		return this.idDocInsertado;
	}

	public void setIdDocInsertado(BigDecimal idDocInsertado) {
		this.idDocInsertado = idDocInsertado;
	}

	/**
	 * @return the fechaAlta
	 */
	public Long getFechaAlta() {
		return this.fechaAlta;
	}

	/**
	 * @param fechaAlta
	 *            the fechaAlta to set
	 */
	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the horaAlta
	 */
	public String getHoraAlta() {
		return horaAlta;
	}

	/**
	 * @param horaAlta
	 *            the horaAlta to set
	 */
	public void setHoraAlta(String horaAlta) {
		this.horaAlta = horaAlta;
	}

	public String getError() {
		return this.error;
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

	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the nombreUpload
	 */
	public String getNombreUpload() {
		return nombreUpload;
	}

	/**
	 * @param nombreUpload
	 *            the nombreUpload to set
	 */
	public void setNombreUpload(String nombreUpload) {
		this.nombreUpload = nombreUpload;
	}

	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * @param origen
	 *            the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
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

}
