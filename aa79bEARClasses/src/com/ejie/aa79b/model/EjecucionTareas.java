package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.i18n.LocaleContextHolder;

import com.ejie.aa79b.utils.DateUtils;

public class EjecucionTareas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2457341755307074724L;

	private BigDecimal idTarea;
	private String dniRecurso;
	private Date fechaEjecucion;
	private String horaEjecucion;
	private String horasTarea;
	private String indRealizada;
	private String indObservaciones;
	private Long porUsoEuskera;
	private String usuariosPrevEjecTarea;

	public void setDniRecurso(String dniRecurso) {
		this.dniRecurso = dniRecurso;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public void setHoraEjecucion(String horaEjecucion) {
		this.horaEjecucion = horaEjecucion;
	}

	public void setHorasTarea(String horasTarea) {
		this.horasTarea = horasTarea;
	}

	public String getFechaHoraEjecucion() {
		return DateUtils.obtFechaHoraFormateada(this.fechaEjecucion, this.horaEjecucion,
				LocaleContextHolder.getLocale());
	}

	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	public void setIndRealizada(String indRealizada) {
		this.indRealizada = indRealizada;
	}

	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	public String getDniRecurso() {
		return dniRecurso;
	}

	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}

	public String getHoraEjecucion() {
		return horaEjecucion;
	}

	public String getHorasTarea() {
		return horasTarea;
	}

	public BigDecimal getIdTarea() {
		return idTarea;
	}

	public String getIndRealizada() {
		return indRealizada;
	}

	public String getIndObservaciones() {
		return indObservaciones;
	}

	public Long getPorUsoEuskera() {
		return porUsoEuskera;
	}

	public void setPorUsoEuskera(Long porUsoEuskera) {
		this.porUsoEuskera = porUsoEuskera;
	}

	public String getUsuariosPrevEjecTarea() {
		return usuariosPrevEjecTarea;
	}

	public void setUsuariosPrevEjecTarea(String usuariosPrevEjecTarea) {
		this.usuariosPrevEjecTarea = usuariosPrevEjecTarea;
	}

}
