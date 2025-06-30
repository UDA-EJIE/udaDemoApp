package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class FilterTarea implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer tipoTarea;
	private Integer estadoAceptTarea;
	private Integer estadoEjecTarea;
	private Date fechaDesdePlanifTarea;
	private Date fechaHastaPlanifTarea;
	private String persIzoAsignTarea;
	private Integer idLoteAsignTarea;
	private String recursoAsignacion;
	private Date fechaDesdePrevistaEjecucion;
	private Date fechaHastaPrevistaEjecucion;
	private Date fechaDesdeRealEjecucion;
	private Date fechaHastaRealEjecucion;
	private Date fechaAsignacionDesde;
	private Date fechaAsignacionHasta;

	public FilterTarea() {
		// Contructor
	}

	/**
	 * @return the tipoTarea
	 */
	public Integer getTipoTarea() {
		return tipoTarea;
	}

	/**
	 * @param tipoTarea
	 *            the tipoTarea to set
	 */
	public void setTipoTarea(Integer tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	/**
	 * @return the estadoAceptTarea
	 */
	public Integer getEstadoAceptTarea() {
		return estadoAceptTarea;
	}

	/**
	 * @param estadoAceptTarea
	 *            the estadoAceptTarea to set
	 */
	public void setEstadoAceptTarea(Integer estadoAceptTarea) {
		this.estadoAceptTarea = estadoAceptTarea;
	}

	/**
	 * @return the estadoEjecTarea
	 */
	public Integer getEstadoEjecTarea() {
		return estadoEjecTarea;
	}

	/**
	 * @param estadoEjecTarea
	 *            the estadoEjecTarea to set
	 */
	public void setEstadoEjecTarea(Integer estadoEjecTarea) {
		this.estadoEjecTarea = estadoEjecTarea;
	}

	/**
	 * @return the fechaDesdePlanifTarea
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaDesdePlanifTarea() {
		return fechaDesdePlanifTarea;
	}

	/**
	 * @param fechaDesdePlanifTarea
	 *            the fechaDesdePlanifTarea to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaDesdePlanifTarea(Date fechaDesdePlanifTarea) {
		this.fechaDesdePlanifTarea = fechaDesdePlanifTarea;
	}

	/**
	 * @return the fechaHastaPlanifTarea
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaHastaPlanifTarea() {
		return fechaHastaPlanifTarea;
	}

	/**
	 * @param fechaHastaPlanifTarea
	 *            the fechaHastaPlanifTarea to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaHastaPlanifTarea(Date fechaHastaPlanifTarea) {
		this.fechaHastaPlanifTarea = fechaHastaPlanifTarea;
	}

	/**
	 * @return the persIzoAsignTarea
	 */
	public String getPersIzoAsignTarea() {
		return persIzoAsignTarea;
	}

	/**
	 * @param persIzoAsignTarea
	 *            the persIzoAsignTarea to set
	 */
	public void setPersIzoAsignTarea(String persIzoAsignTarea) {
		this.persIzoAsignTarea = persIzoAsignTarea;
	}

	/**
	 * @return the idLoteAsignTarea
	 */
	public Integer getIdLoteAsignTarea() {
		return idLoteAsignTarea;
	}

	/**
	 * @param idLoteAsignTarea
	 *            the idLoteAsignTarea to set
	 */
	public void setIdLoteAsignTarea(Integer idLoteAsignTarea) {
		this.idLoteAsignTarea = idLoteAsignTarea;
	}

	public String getRecursoAsignacion() {
		return recursoAsignacion;
	}

	public void setRecursoAsignacion(String recursoAsignacion) {
		this.recursoAsignacion = recursoAsignacion;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaDesdePrevistaEjecucion() {
		return fechaDesdePrevistaEjecucion;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaDesdePrevistaEjecucion(Date fechaDesdePrevistaEjecucion) {
		this.fechaDesdePrevistaEjecucion = fechaDesdePrevistaEjecucion;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaHastaPrevistaEjecucion() {
		return fechaHastaPrevistaEjecucion;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaHastaPrevistaEjecucion(Date fechaHastaPrevistaEjecucion) {
		this.fechaHastaPrevistaEjecucion = fechaHastaPrevistaEjecucion;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaDesdeRealEjecucion() {
		return fechaDesdeRealEjecucion;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaDesdeRealEjecucion(Date fechaDesdeRealEjecucion) {
		this.fechaDesdeRealEjecucion = fechaDesdeRealEjecucion;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaHastaRealEjecucion() {
		return fechaHastaRealEjecucion;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaHastaRealEjecucion(Date fechaHastaRealEjecucion) {
		this.fechaHastaRealEjecucion = fechaHastaRealEjecucion;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaAsignacionDesde() {
		return fechaAsignacionDesde;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaAsignacionDesde(Date fechaAsignacionDesde) {
		this.fechaAsignacionDesde = fechaAsignacionDesde;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaAsignacionHasta() {
		return fechaAsignacionHasta;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaAsignacionHasta(Date fechaAsignacionHasta) {
		this.fechaAsignacionHasta = fechaAsignacionHasta;
	}

}
