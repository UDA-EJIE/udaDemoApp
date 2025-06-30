package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporte3DecimalSerializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ConfigHorasPrevistasProveedorExt implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal numPalTradHora;
	private BigDecimal palHoraConcor100;
	private BigDecimal palHoraConcor9599;
	private BigDecimal palHoraConcor8594;
	private BigDecimal palHoraConcor084;
	private BigDecimal palSegConcor100;
	private BigDecimal palSegConcor9599;
	private BigDecimal palSegConcor8594;
	private BigDecimal palSegConcor084;
	private BigDecimal palReExtHora;
	private BigDecimal palTradSeg;
	private BigDecimal palRevSeg;
	private String nivUsuario;

	public ConfigHorasPrevistasProveedorExt() {
		// Constructor vacío
	}

	public Integer getId() {
		return this.id;
	}

	public ConfigHorasPrevistasProveedorExt(Integer id) {
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getNumPalTradHora() {
		return this.numPalTradHora;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setNumPalTradHora(BigDecimal numPalTradHora) {
		this.numPalTradHora = numPalTradHora;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalHoraConcor100(BigDecimal palHoraConcor100) {
		this.palHoraConcor100 = palHoraConcor100;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPalHoraConcor100() {
		return this.palHoraConcor100;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalHoraConcor9599(BigDecimal palHoraConcor9599) {
		this.palHoraConcor9599 = palHoraConcor9599;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPalHoraConcor9599() {
		return this.palHoraConcor9599;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalHoraConcor8594(BigDecimal palHoraConcor8594) {
		this.palHoraConcor8594 = palHoraConcor8594;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPalHoraConcor8594() {
		return this.palHoraConcor8594;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPalHoraConcor084() {
		return this.palHoraConcor084;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalHoraConcor084(BigDecimal palHoraConcor084) {
		this.palHoraConcor084 = palHoraConcor084;
	}

	@JsonSerialize(using = JsonImporte3DecimalSerializer.class)
	public BigDecimal getPalSegConcor100() {
		return this.palSegConcor100;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalSegConcor100(BigDecimal palSegConcor100) {
		this.palSegConcor100 = palSegConcor100;
	}

	@JsonSerialize(using = JsonImporte3DecimalSerializer.class)
	public BigDecimal getPalSegConcor9599() {
		return this.palSegConcor9599;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalSegConcor9599(BigDecimal palSegConcor9599) {
		this.palSegConcor9599 = palSegConcor9599;
	}


	@JsonSerialize(using = JsonImporte3DecimalSerializer.class)
	public BigDecimal getPalSegConcor8594() {
		return this.palSegConcor8594;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalSegConcor8594(BigDecimal palSegConcor8594) {
		this.palSegConcor8594 = palSegConcor8594;
	}

	@JsonSerialize(using = JsonImporte3DecimalSerializer.class)
	public BigDecimal getPalSegConcor084() {
		return this.palSegConcor084;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalSegConcor084(BigDecimal palSegConcor084) {
		this.palSegConcor084 = palSegConcor084;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalReExtHora(BigDecimal palReExtHora) {
		this.palReExtHora = palReExtHora;
	}

	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPalReExtHora() {
		return this.palReExtHora;
	}

	@JsonSerialize(using = JsonImporte3DecimalSerializer.class)
	public BigDecimal getPalTradSeg() {
		return this.palTradSeg;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalTradSeg(BigDecimal palTradSeg) {
		this.palTradSeg = palTradSeg;
	}

	@JsonSerialize(using = JsonImporte3DecimalSerializer.class)
	public BigDecimal getPalRevSeg() {
		return this.palRevSeg;
	}

	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPalRevSeg(BigDecimal palRevSeg) {
		this.palRevSeg = palRevSeg;
	}

	public String getNivUsuario() {
		return this.nivUsuario;
	}

	public void setNivUsuario(String nivUsuario) {
		this.nivUsuario = nivUsuario;
	}

}
