package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

public class CalendarioIcsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long anyo;
	private Integer numExp;
	private String tipoExp;
	private String titulo;
	private Date fechaInicio;
	private String horaInicio;
	private Date fechaFin;
	private String horaFin;
	private DireccionNora dirNora;

	public CalendarioIcsModel() {
		// Constructor
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return anyo;
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
		return numExp;
	}

	/**
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the tipoExp
	 */
	public String getTipoExp() {
		return tipoExp;
	}

	/**
	 * @param tipoExp
	 *            the tipoExp to set
	 */
	public void setTipoExp(String tipoExp) {
		this.tipoExp = tipoExp;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the horaInicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio
	 *            the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin
	 *            the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return the dirNora
	 */
	public DireccionNora getDirNora() {
		return dirNora;
	}

	/**
	 * @param dirNora
	 *            the dirNora to set
	 */
	public void setDirNora(DireccionNora dirNora) {
		this.dirNora = dirNora;
	}

	public String getAnyoNumExpConcatenado() {
		if (this.anyo != null && this.numExp != null) {
			return "" + String.valueOf(this.anyo).substring(2) + "/" + String.format("%06d", this.numExp);
		}
		return null;
	}

	@Override
	public String toString() {
		return "CalendarioIcsModel [anyo=" + anyo + ", numExp=" + numExp + ", titulo=" + titulo + ", fechaInicio="
				+ fechaInicio + ", horaInicio=" + horaInicio + ", fechaFin=" + fechaFin + ", horaFin=" + horaFin + "]";
	}

}
