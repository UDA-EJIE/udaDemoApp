package com.ejie.aa79b.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author dmuchuari
 *
 */
public class TareaIntPagoProveed implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Date fechaIni;
	private Date fechaFin;
	private String horaIni;
	private String horaFin;
	private String horasPrevistas;
	private String nomPersonAsignado;
	private String horasRealesAsignadas;
	private BigDecimal importeBase;
	private Long porcentIVA;
	private BigDecimal importeIVA;
	private BigDecimal total;

	/**
	 * 
	 * @param fechaFin
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * 
	 * @param fechaIni
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * 
	 * @param horaFin
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * 
	 * @param horaIni
	 */
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	/**
	 * 
	 * @param horasPrevistas
	 */
	public void setHorasPrevistas(String horasPrevistas) {
		this.horasPrevistas = horasPrevistas;
	}

	/**
	 * 
	 * @param horasRealesAsignadas
	 */
	public void setHorasRealesAsignadas(String horasRealesAsignadas) {
		this.horasRealesAsignadas = horasRealesAsignadas;
	}

	/**
	 * 
	 * @param nomPersonAsignado
	 */
	public void setNomPersonAsignado(String nomPersonAsignado) {
		this.nomPersonAsignado = nomPersonAsignado;
	}

	/**
	 * 
	 * @return fechaFin
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	/**
	 * 
	 * @return fechaIni
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaIni() {
		return this.fechaIni;
	}

	/**
	 * 
	 * @return horaFin
	 */
	public String getHoraFin() {
		return this.horaFin;
	}

	/**
	 * 
	 * @return horaIni
	 */
	public String getHoraIni() {
		return this.horaIni;
	}

	/**
	 * 
	 * @return horasPrevistas
	 */
	public String getHorasPrevistas() {
		return this.horasPrevistas;
	}

	/**
	 * 
	 * @return horasRealesAsignadas
	 */
	public String getHorasRealesAsignadas() {
		return this.horasRealesAsignadas;
	}

	/**
	 * 
	 * @return nomPersonAsignado
	 */
	public String getNomPersonAsignado() {
		return this.nomPersonAsignado;
	}

	/**
	 * 
	 * @param importeBase
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteBase(BigDecimal importeBase) {
		this.importeBase = importeBase;
	}

	/**
	 * 
	 * @param importeIVA
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteIVA(BigDecimal importeIVA) {
		this.importeIVA = importeIVA;
	}

	/**
	 * 
	 * @param porcentIVA
	 */
	public void setPorcentIVA(Long porcentIVA) {
		this.porcentIVA = porcentIVA;
	}

	/**
	 * 
	 * @param total
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * 
	 * @return importeBase
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteBase() {
		return this.importeBase;
	}

	/**
	 * 
	 * @return importeIVA
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteIVA() {
		return this.importeIVA;
	}

	/**
	 * 
	 * @return porcentIVA
	 */
	public Long getPorcentIVA() {
		return this.porcentIVA;
	}

	/**
	 * 
	 * @return total
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getTotal() {
		return this.total;
	}
}
