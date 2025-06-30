package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Aa79bDatosFacturacionExpInter
 * 
 * @author mrodriguez
 */
public class Aa79bDatosFacturacionExpInter implements Serializable {

	private static final long serialVersionUID = 8641413661751620375L;

	private Integer numInterpretes;
	private String horasInterpretacion;
	private BigDecimal importeBase;
	private BigDecimal importeIva;
	private BigDecimal importeTotal;

	/**
	 * @return the numInterpretes
	 */
	public Integer getNumInterpretes() {
		return numInterpretes;
	}

	/**
	 * @param numInterpretes
	 *            the numInterpretes to set
	 */
	public void setNumInterpretes(Integer numInterpretes) {
		this.numInterpretes = numInterpretes;
	}

	/**
	 * @return the horasInterpretacion
	 */
	public String getHorasInterpretacion() {
		return horasInterpretacion;
	}

	/**
	 * @param horasInterpretacion
	 *            the horasInterpretacion to set
	 */
	public void setHorasInterpretacion(String horasInterpretacion) {
		this.horasInterpretacion = horasInterpretacion;
	}

	/**
	 * @return the importeBase
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteBase() {
		return importeBase;
	}

	/**
	 * @param importeBase
	 *            the importeBase to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteBase(BigDecimal importeBase) {
		this.importeBase = importeBase;
	}

	/**
	 * @return the importeIva
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteIva() {
		return importeIva;
	}

	/**
	 * @param importeIva
	 *            the importeIva to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteIva(BigDecimal importeIva) {
		this.importeIva = importeIva;
	}

	/**
	 * @return the importeTotal
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal
	 *            the importeTotal to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

}
