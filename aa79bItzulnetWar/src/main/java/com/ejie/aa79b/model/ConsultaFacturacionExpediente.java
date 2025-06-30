package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ConsultaFacturacionExpediente implements Serializable {

	private static final long serialVersionUID = 1L;
	private ContactoFacturacionExpediente contactoFacturacionExpediente;
	private Integer expedientesCount;

	private Integer idTipoExpediente;
	private Date fechaDesdeSolicitud;
	private Date fechaHastaSolicitud;
	private Date fechaDesdeEntregaReal;
	private Date fechaHastaEntregaReal;
	private Entidad entidadAFacturar;
	private Solicitante contactoAFacturar;
	private Integer codEntidadBoe;
	private Boolean contactoVinculado;

	public ConsultaFacturacionExpediente() {
		// Constructor
	}

	/**
	 * @return the contactoFacturacionExpediente
	 */
	public ContactoFacturacionExpediente getContactoFacturacionExpediente() {
		return this.contactoFacturacionExpediente;
	}

	/**
	 * @param contactoFacturacionExpediente
	 *            the contactoFacturacionExpediente to set
	 */
	public void setContactoFacturacionExpediente(ContactoFacturacionExpediente contactoFacturacionExpediente) {
		this.contactoFacturacionExpediente = contactoFacturacionExpediente;
	}

	/**
	 * @return the expedientesCount
	 */
	public Integer getExpedientesCount() {
		return this.expedientesCount;
	}

	/**
	 * @param expedientesCount
	 *            the expedientesCount to set
	 */
	public void setExpedientesCount(Integer expedientesCount) {
		this.expedientesCount = expedientesCount;
	}

	/**
	 * @return the idTipoExpediente
	 */
	public Integer getIdTipoExpediente() {
		return this.idTipoExpediente;
	}

	/**
	 * @param idTipoExpediente
	 *            the idTipoExpediente to set
	 */
	public void setIdTipoExpediente(Integer idTipoExpediente) {
		this.idTipoExpediente = idTipoExpediente;
	}

	/**
	 * @return the fechaDesdeSolicitud
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaDesdeSolicitud() {
		return this.fechaDesdeSolicitud;
	}

	/**
	 * @param fechaDesdeSolicitud
	 *            the fechaDesdeSolicitud to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaDesdeSolicitud(Date fechaDesdeSolicitud) {
		this.fechaDesdeSolicitud = fechaDesdeSolicitud;
	}

	/**
	 * @return the fechaHastaSolicitud
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaHastaSolicitud() {
		return this.fechaHastaSolicitud;
	}

	/**
	 * @param fechaHastaSolicitud
	 *            the fechaHastaSolicitud to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaHastaSolicitud(Date fechaHastaSolicitud) {
		this.fechaHastaSolicitud = fechaHastaSolicitud;
	}

	/**
	 * @return the fechaDesdeEntregaReal
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaDesdeEntregaReal() {
		return this.fechaDesdeEntregaReal;
	}

	/**
	 * @param fechaDesdeEntregaReal
	 *            the fechaDesdeEntregaReal to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaDesdeEntregaReal(Date fechaDesdeEntregaReal) {
		this.fechaDesdeEntregaReal = fechaDesdeEntregaReal;
	}

	/**
	 * @return the fechaHastaEntregaReal
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaHastaEntregaReal() {
		return this.fechaHastaEntregaReal;
	}

	/**
	 * @param fechaHastaEntregaReal
	 *            the fechaHastaEntregaReal to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaHastaEntregaReal(Date fechaHastaEntregaReal) {
		this.fechaHastaEntregaReal = fechaHastaEntregaReal;
	}

	/**
	 * @return the entidadAFacturar
	 */
	public Entidad getEntidadAFacturar() {
		return this.entidadAFacturar;
	}

	/**
	 * @param entidadAFacturar
	 *            the entidadAFacturar to set
	 */
	public void setEntidadAFacturar(Entidad entidadAFacturar) {
		this.entidadAFacturar = entidadAFacturar;
	}

	/**
	 * @return the contactoAFacturar
	 */
	public Solicitante getContactoAFacturar() {
		return this.contactoAFacturar;
	}

	/**
	 * @param contactoAFacturar
	 *            the contactoAFacturar to set
	 */
	public void setContactoAFacturar(Solicitante contactoAFacturar) {
		this.contactoAFacturar = contactoAFacturar;
	}

	/**
	 * @return the codEntidadBoe
	 */
	public Integer getCodEntidadBoe() {
		return codEntidadBoe;
	}

	/**
	 * @param codEntidadBoe
	 *            the codEntidadBoe to set
	 */
	public void setCodEntidadBoe(Integer codEntidadBoe) {
		this.codEntidadBoe = codEntidadBoe;
	}

	/**
	 * @return the contactoVinculado
	 */
	public Boolean getContactoVinculado() {
		return contactoVinculado;
	}

	/**
	 * @param contactoVinculado
	 *            the contactoVinculado to set
	 */
	public void setContactoVinculado(Boolean contactoVinculado) {
		this.contactoVinculado = contactoVinculado;
	}

}
