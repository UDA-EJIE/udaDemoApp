package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

public class Aa79bEjecucionTareas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2457341755307074724L;

	private BigDecimal idTarea;
	private String dniRecurso;
	private Long fechaEjecucion;
	private String horaEjecucion;
	private String horasTarea;
	private String indRealizada;
	private String indObservaciones;
	private Long porUsoEuskera;
	private int estado;

	public void setDniRecurso(String dniRecurso) {
		this.dniRecurso = dniRecurso;
	}

	public void setFechaEjecucion(Long fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public void setHoraEjecucion(String horaEjecucion) {
		this.horaEjecucion = horaEjecucion;
	}

	public void setHorasTarea(String horasTarea) {
		this.horasTarea = horasTarea;
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

	public Long getFechaEjecucion() {
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

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

}
