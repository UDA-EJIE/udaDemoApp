package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ExpedienteConsulta extends ExpedientePlanificacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fechaAltaDesde;
	private Date fechaAltaHasta;
	private String subsRequerida;
	private String idsMetadatosBusqueda;
	private String idsExpedientesRelacionados;
	private Long tipoDocumento;
	private String tiposDocumentoListagg;


	public ExpedienteConsulta() {
		// Contructor
	}

	/**
	 * @return the fechaAltaDesde
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaAltaDesde() {
		return this.fechaAltaDesde;
	}

	/**
	 * @param fechaAltaDesde the fechaAltaDesde to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaAltaDesde(Date fechaAltaDesde) {
		this.fechaAltaDesde = fechaAltaDesde;
	}

	/**
	 * @return the fechaAltaHasta
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaAltaHasta() {
		return this.fechaAltaHasta;
	}

	/**
	 * @param fechaAltaHasta the fechaAltaHasta to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaAltaHasta(Date fechaAltaHasta) {
		this.fechaAltaHasta = fechaAltaHasta;
	}

	/**
	 * @return the subsRequerida
	 */
	public String getSubsRequerida() {
		return this.subsRequerida;
	}

	/**
	 * @param subsRequerida the subsRequerida to set
	 */
	public void setSubsRequerida(String subsRequerida) {
		this.subsRequerida = subsRequerida;
	}

	/**
	 * @return the idsMetadatosBusqueda
	 */
	@Override
	public String getIdsMetadatosBusqueda() {
		return this.idsMetadatosBusqueda;
	}

	/**
	 * @param idsMetadatosBusqueda the idsMetadatosBusqueda to set
	 */
	@Override
	public void setIdsMetadatosBusqueda(String idsMetadatosBusqueda) {
		this.idsMetadatosBusqueda = idsMetadatosBusqueda;
	}

	/**
	 * @return the idsExpedientesRelacionados
	 */
	public String getIdsExpedientesRelacionados() {
		return this.idsExpedientesRelacionados;
	}

	/**
	 * @param idsExpedientesRelacionados the idsExpedientesRelacionados to set
	 */
	public void setIdsExpedientesRelacionados(String idsExpedientesRelacionados) {
		this.idsExpedientesRelacionados = idsExpedientesRelacionados;
	}

	/**
	 * @return the tipoDocumento
	 */
	@Override
	public Long getTipoDocumento() {
		return this.tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	@Override
	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the tiposDocumentoListagg
	 */
	public String getTiposDocumentoListagg() {
		return this.tiposDocumentoListagg;
	}

	/**
	 * @param tiposDocumentoListagg the tiposDocumentoListagg to set
	 */
	public void setTiposDocumentoListagg(String tiposDocumentoListagg) {
		this.tiposDocumentoListagg = tiposDocumentoListagg;
	}

}
