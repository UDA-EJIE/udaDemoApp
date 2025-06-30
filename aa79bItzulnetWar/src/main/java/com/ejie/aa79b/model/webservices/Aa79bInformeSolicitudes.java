package com.ejie.aa79b.model.webservices;

import java.math.BigDecimal;
import java.util.Date;

import com.ejie.aa79b.common.JsonFechaHoraSerializer;
import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.ejie.aa79b.common.jackson.JsonTarifaSerializer;
import com.ejie.aa79b.model.IdiomaWS;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Aa79bInformeSolicitudes extends IdiomaWS implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long anyo;
	private Integer numExp;
	private String anyoNumExp;
	private String tipoExpDesc;
	private String bopvDesc;
	private String solicitante;
	private Integer numPalIzo;
	private BigDecimal tarifaPal;
	private String finalizado;
	private String tipoDocumento;
	private String tipoTraduccion;
	private BigDecimal importeTotal;
	private String descripcion;
	private String solaskidea;
	private Date fechaAlta;
	private Date fechaFinIzo;
	private Date fechaEntregaReal;
	private String lIdFactura;

	public Long getAnyo() {
		return anyo;
	}

	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	public Integer getNumExp() {
		return numExp;
	}

	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public String getAnyoNumExp() {
		return anyoNumExp;
	}

	public void setAnyoNumExp(String anyoNumExp) {
		this.anyoNumExp = anyoNumExp;
	}

	public String getTipoExpDesc() {
		return tipoExpDesc;
	}

	public void setTipoExpDesc(String tipoExpDesc) {
		this.tipoExpDesc = tipoExpDesc;
	}

	public String getBopvDesc() {
		return bopvDesc;
	}

	public void setBopvDesc(String bopvDesc) {
		this.bopvDesc = bopvDesc;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public Integer getNumPalIzo() {
		return numPalIzo;
	}

	public void setNumPalIzo(Integer numPalIzo) {
		this.numPalIzo = numPalIzo;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public BigDecimal getTarifaPal() {
		return tarifaPal;
	}

	@JsonSerialize(using = JsonTarifaSerializer.class)
	public BigDecimal getTarifaPalEur() {
		return this.tarifaPal;
	}

	public void setTarifaPal(BigDecimal tarifaPal) {
		this.tarifaPal = tarifaPal;
	}

	public String getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(String finalizado) {
		this.finalizado = finalizado;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoTraduccion() {
		return tipoTraduccion;
	}

	public void setTipoTraduccion(String tipoTraduccion) {
		this.tipoTraduccion = tipoTraduccion;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteTotalEur() {
		return this.importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSolaskidea() {
		return solaskidea;
	}

	public void setSolaskidea(String solaskidea) {
		this.solaskidea = solaskidea;
	}

	@JsonSerialize(using = JsonFechaHoraSerializer.class)
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@JsonSerialize(using = JsonFechaHoraSerializer.class)
	public Date getFechaFinIzo() {
		return fechaFinIzo;
	}

	public void setFechaFinIzo(Date fechaFinIzo) {
		this.fechaFinIzo = fechaFinIzo;
	}

	@JsonSerialize(using = JsonFechaHoraSerializer.class)
	public Date getFechaEntregaReal() {
		return fechaEntregaReal;
	}

	public void setFechaEntregaReal(Date fechaEntregaReal) {
		this.fechaEntregaReal = fechaEntregaReal;
	}

	public String getlIdFactura() {
		return lIdFactura;
	}

	public void setlIdFactura(String lIdFactura) {
		this.lIdFactura = lIdFactura;
	}

}
