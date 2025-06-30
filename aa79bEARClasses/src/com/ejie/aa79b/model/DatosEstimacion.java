package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class DatosEstimacion implements Serializable {

	private static final long serialVersionUID = 1255202353955300075L;
	private DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
	private TiposTarea tiposTarea = new TiposTarea();
	private Date fechaInicio;
	private String horaInicio;
	private Date fechaFin;
	private String horaFin;
	private String nivUsuario;
	private Long idTipoRelevancia;

	/**
	 * @return the datosTareaTrados
	 */
	public DatosTareaTrados getDatosTareaTrados() {
		return datosTareaTrados;
	}

	/**
	 * @param datosTareaTrados the datosTareaTrados to set
	 */
	public void setDatosTareaTrados(DatosTareaTrados datosTareaTrados) {
		this.datosTareaTrados = datosTareaTrados;
	}

	/**
	 * @return the tiposTarea
	 */
	public TiposTarea getTiposTarea() {
		return tiposTarea;
	}

	/**
	 * @param tiposTarea the tiposTarea to set
	 */
	public void setTiposTarea(TiposTarea tiposTarea) {
		this.tiposTarea = tiposTarea;
	}

	/**
	 * @return the fechaInicio
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
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
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
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
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getNivUsuario() {
		return nivUsuario;
	}

	public void setNivUsuario(String nivUsuario) {
		this.nivUsuario = nivUsuario;
	}

	public Long getIdTipoRelevancia() {
		return idTipoRelevancia;
	}

	public void setIdTipoRelevancia(Long idTipoRelevancia) {
		this.idTipoRelevancia = idTipoRelevancia;
	}

	@Override
	public String toString() {
		return "DatosEstimacion [datosTareaTrados=" + datosTareaTrados + ", tiposTarea=" + tiposTarea + ", fechaInicio="
				+ fechaInicio + ", horaInicio=" + horaInicio + ", fechaFin=" + fechaFin + ", horaFin=" + horaFin
				+ ", nivUsuario=" + nivUsuario + ", idTipoRelevancia=" + idTipoRelevancia + "]";
	}

}
