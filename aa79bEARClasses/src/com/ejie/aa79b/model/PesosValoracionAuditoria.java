package com.ejie.aa79b.model;

import java.math.BigDecimal;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonPorcentajeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PesosValoracionAuditoria implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descEu;
	private BigDecimal porNivel0;
	private BigDecimal porNivel1;
	private BigDecimal porNivel3;
	private BigDecimal porNivel5;
	private String estado;
	private String descEstadoEs;
	private String descEstadoEu;

	public PesosValoracionAuditoria() {
		/* CONSTRUCTOR */
	}

	public PesosValoracionAuditoria(Long id) {
		this.id = id;
	}

	public PesosValoracionAuditoria(Long id, String descEu, BigDecimal porNivel0, BigDecimal porNivel1,
			BigDecimal porNivel3, BigDecimal porNivel5, String estado) {
		super();
		this.id = id;
		this.descEu = descEu;
		this.porNivel0 = porNivel0;
		this.porNivel1 = porNivel1;
		this.porNivel3 = porNivel3;
		this.porNivel5 = porNivel5;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescEu() {
		return descEu;
	}

	public void setDescEu(String descEu) {
		this.descEu = descEu;
	}

	@JsonSerialize(using = JsonPorcentajeSerializer.class)
	public BigDecimal getPorNivel0() {
		return porNivel0;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel0(BigDecimal porNivel0) {
		this.porNivel0 = porNivel0;
	}

	@JsonSerialize(using = JsonPorcentajeSerializer.class)
	public BigDecimal getPorNivel1() {
		return porNivel1;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel1(BigDecimal porNivel1) {
		this.porNivel1 = porNivel1;
	}

	@JsonSerialize(using = JsonPorcentajeSerializer.class)
	public BigDecimal getPorNivel3() {
		return porNivel3;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel3(BigDecimal porNivel3) {
		this.porNivel3 = porNivel3;
	}

	@JsonSerialize(using = JsonPorcentajeSerializer.class)
	public BigDecimal getPorNivel5() {
		return porNivel5;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel5(BigDecimal porNivel5) {
		this.porNivel5 = porNivel5;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescEstadoEs() {
		return this.descEstadoEs;
	}

	public void setDescEstadoEs(String descEstadoEs) {
		this.descEstadoEs = descEstadoEs;
	}

	public String getDescEstadoEu() {
		return this.descEstadoEu;
	}

	public void setDescEstadoEu(String descEstadoEu) {
		this.descEstadoEu = descEstadoEu;
	}

	@Override
	public String toString() {
		return "PesosValoracionAuditoria [id=" + id + ", descEu=" + descEu + ", porNivel0=" + porNivel0 + ", porNivel1="
				+ porNivel1 + ", porNivel3=" + porNivel3 + ", porNivel5=" + porNivel5 + ", estado=" + estado + "]";
	}

}
