package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

public class Aa79bSalidaConsultaTarea implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private Aa79bDescripcionIdioma tipoTarea;
	private Aa79bDescripcionIdioma estadoEjecucionTarea;
	private Long anyo;
	private Integer numExp;
	private String anyoNumExp;
	private Integer numPalXml;
	private Integer numPalConcor084;
	private Integer numPalConcor8594;
	private Integer numPalConcor95100;
	private Integer numPalConcor9599;
	private Integer numPalConcor100;
	private Integer numPalIzo;
	private Long fechaPrevista;
	private String horaPrevista;
	private Long fechaReal;
	private String horaReal;
	private Long fechaInicioInterpretacion;
	private String horaInicioInterpretacion;
	private Long fechaFinInterpretacion;
	private String horaFinInterpretacion;
	private Aa79bLoteCombo lotes;

	public Aa79bSalidaConsultaTarea() {
		// Constructor
	}

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return this.idTarea;
	}

	/**
	 * @param idTarea
	 *            the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the tipoTarea
	 */
	public Aa79bDescripcionIdioma getTipoTarea() {
		return this.tipoTarea;
	}

	/**
	 * @param tipoTarea
	 *            the tipoTarea to set
	 */
	public void setTipoTarea(Aa79bDescripcionIdioma tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	/**
	 * @return the estadoEjecucionTarea
	 */
	public Aa79bDescripcionIdioma getEstadoEjecucionTarea() {
		return this.estadoEjecucionTarea;
	}

	/**
	 * @param estadoEjecucionTarea
	 *            the estadoEjecucionTarea to set
	 */
	public void setEstadoEjecucionTarea(Aa79bDescripcionIdioma estadoEjecucionTarea) {
		this.estadoEjecucionTarea = estadoEjecucionTarea;
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
	}

	/**
	 * @param anyo
	 *            the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	/**
	 * @return the numExp
	 */
	public Integer getNumExp() {
		return this.numExp;
	}

	/**
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the anyoNumExp
	 */
	public String getAnyoNumExp() {
		this.setAnyoNumExp(null);
		if (this.anyo != null && this.numExp != null) {
			this.setAnyoNumExp(String.valueOf(this.anyo).substring(2) + "/" + String.format("%06d", this.numExp));
		}

		return this.anyoNumExp;
	}

	/**
	 * @param anyoNumExp
	 *            the anyoNumExp to set
	 */
	public void setAnyoNumExp(String anyoNumExp) {
		this.anyoNumExp = anyoNumExp;
	}

	/**
	 * @return the numPalXml
	 */
	public Integer getNumPalXml() {
		return this.numPalXml;
	}

	/**
	 * @param numPalXml
	 *            the numPalXml to set
	 */
	public void setNumPalXml(Integer numPalXml) {
		this.numPalXml = numPalXml;
	}

	/**
	 * @return the numPalConcor084
	 */
	public Integer getNumPalConcor084() {
		return this.numPalConcor084;
	}

	/**
	 * @param numPalConcor084
	 *            the numPalConcor084 to set
	 */
	public void setNumPalConcor084(Integer numPalConcor084) {
		this.numPalConcor084 = numPalConcor084;
	}

	/**
	 * @return the numPalConcor8594
	 */
	public Integer getNumPalConcor8594() {
		return this.numPalConcor8594;
	}

	/**
	 * @param numPalConcor8594
	 *            the numPalConcor8594 to set
	 */
	public void setNumPalConcor8594(Integer numPalConcor8594) {
		this.numPalConcor8594 = numPalConcor8594;
	}

	/**
	 * @return the numPalConcor95100
	 */
	public Integer getNumPalConcor95100() {
		return this.numPalConcor95100;
	}

	/**
	 * @param numPalConcor95100
	 *            the numPalConcor95100 to set
	 */
	public void setNumPalConcor95100(Integer numPalConcor95100) {
		this.numPalConcor95100 = numPalConcor95100;
	}

	/**
	 * @return the numPalIzo
	 */
	public Integer getNumPalIzo() {
		return this.numPalIzo;
	}

	/**
	 * @param numPalIzo
	 *            the numPalIzo to set
	 */
	public void setNumPalIzo(Integer numPalIzo) {
		this.numPalIzo = numPalIzo;
	}

	/**
	 * @return the fechaPrevista
	 */
	public Long getFechaPrevista() {
		return this.fechaPrevista;
	}

	/**
	 * @param fechaPrevista
	 *            the fechaPrevista to set
	 */
	public void setFechaPrevista(Long fechaPrevista) {
		this.fechaPrevista = fechaPrevista;
	}

	/**
	 * @return the horaPrevista
	 */
	public String getHoraPrevista() {
		return this.horaPrevista;
	}

	/**
	 * @param horaPrevista
	 *            the horaPrevista to set
	 */
	public void setHoraPrevista(String horaPrevista) {
		this.horaPrevista = horaPrevista;
	}

	/**
	 * @return the fechaReal
	 */
	public Long getFechaReal() {
		return this.fechaReal;
	}

	/**
	 * @param fechaReal
	 *            the fechaReal to set
	 */
	public void setFechaReal(Long fechaReal) {
		this.fechaReal = fechaReal;
	}

	/**
	 * @return the horaReal
	 */
	public String getHoraReal() {
		return this.horaReal;
	}

	/**
	 * @param horaReal
	 *            the horaReal to set
	 */
	public void setHoraReal(String horaReal) {
		this.horaReal = horaReal;
	}

	/**
	 * @return the fechaInicioInterpretacion
	 */
	public Long getFechaInicioInterpretacion() {
		return this.fechaInicioInterpretacion;
	}

	/**
	 * @param fechaInicioInterpretacion
	 *            the fechaInicioInterpretacion to set
	 */
	public void setFechaInicioInterpretacion(Long fechaInicioInterpretacion) {
		this.fechaInicioInterpretacion = fechaInicioInterpretacion;
	}

	/**
	 * @return the horaInicioInterpretacion
	 */
	public String getHoraInicioInterpretacion() {
		return this.horaInicioInterpretacion;
	}

	/**
	 * @param horaInicioInterpretacion
	 *            the horaInicioInterpretacion to set
	 */
	public void setHoraInicioInterpretacion(String horaInicioInterpretacion) {
		this.horaInicioInterpretacion = horaInicioInterpretacion;
	}

	/**
	 * @return the fechaFinInterpretacion
	 */
	public Long getFechaFinInterpretacion() {
		return this.fechaFinInterpretacion;
	}

	/**
	 * @param fechaFinInterpretacion
	 *            the fechaFinInterpretacion to set
	 */
	public void setFechaFinInterpretacion(Long fechaFinInterpretacion) {
		this.fechaFinInterpretacion = fechaFinInterpretacion;
	}

	/**
	 * @return the horaFinInterpretacion
	 */
	public String getHoraFinInterpretacion() {
		return this.horaFinInterpretacion;
	}

	/**
	 * @param horaFinInterpretacion
	 *            the horaFinInterpretacion to set
	 */
	public void setHoraFinInterpretacion(String horaFinInterpretacion) {
		this.horaFinInterpretacion = horaFinInterpretacion;
	}

	/**
	 * @return the lotes
	 */
	public Aa79bLoteCombo getLotes() {
		return this.lotes;
	}

	/**
	 * @param lotes
	 *            the lote to set
	 */
	public void setLotes(Aa79bLoteCombo lotes) {
		this.lotes = lotes;
	}

	public Integer getNumPalConcor9599() {
		return this.numPalConcor9599;
	}

	public void setNumPalConcor9599(Integer numPalConcor9599) {
		this.numPalConcor9599 = numPalConcor9599;
	}

	public Integer getNumPalConcor100() {
		return this.numPalConcor100;
	}

	public void setNumPalConcor100(Integer numPalConcor100) {
		this.numPalConcor100 = numPalConcor100;
	}

}
