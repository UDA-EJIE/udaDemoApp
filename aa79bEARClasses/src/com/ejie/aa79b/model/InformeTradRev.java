package com.ejie.aa79b.model;

import java.math.BigDecimal;

public class InformeTradRev extends Informe implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numPalabrasSolic;
	private Integer numPalabrasIzo;
	private BigDecimal tarifaPalabras;
	private TradosExp tradosExp;
	private TradosExp tradosTarea;
	private String penalizacionCalidad;
	private BigDecimal importePenalizacionCalidad;

	public Integer getNumPalabrasSolic() {
		return numPalabrasSolic;
	}

	public void setNumPalabrasSolic(Integer numPalabrasSolic) {
		this.numPalabrasSolic = numPalabrasSolic;
	}

	public Integer getNumPalabrasIzo() {
		return numPalabrasIzo;
	}

	public void setNumPalabrasIzo(Integer numPalabrasIzo) {
		this.numPalabrasIzo = numPalabrasIzo;
	}

	public BigDecimal getTarifaPalabras() {
		return tarifaPalabras;
	}

	public void setTarifaPalabras(BigDecimal tarifaPalabras) {
		this.tarifaPalabras = tarifaPalabras;
	}

	public TradosExp getTradosExp() {
		return tradosExp;
	}

	public void setTradosExp(TradosExp tradosExp) {
		this.tradosExp = tradosExp;
	}

	public TradosExp getTradosTarea() {
		return tradosTarea;
	}

	public void setTradosTarea(TradosExp tradosTarea) {
		this.tradosTarea = tradosTarea;
	}

	public String getPenalizacionCalidad() {
		return penalizacionCalidad;
	}

	public void setPenalizacionCalidad(String penalizacionCalidad) {
		this.penalizacionCalidad = penalizacionCalidad;
	}

	public BigDecimal getImportePenalizacionCalidad() {
		return importePenalizacionCalidad;
	}

	public void setImportePenalizacionCalidad(BigDecimal importePenalizacionCalidad) {
		this.importePenalizacionCalidad = importePenalizacionCalidad;
	}

}
