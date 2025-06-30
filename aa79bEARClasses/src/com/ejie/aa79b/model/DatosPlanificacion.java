package com.ejie.aa79b.model;

import java.io.Serializable;

public class DatosPlanificacion implements Serializable {

	private static final long serialVersionUID = 2526906080496589374L;
	private DatosTareaTrados datosTareaTrados = new DatosTareaTrados();
	private DatosPagoProveedores datosPagoProveedores = new DatosPagoProveedores();
	private String fechaHoraInicio;
	private String fechaHoraFin;
	private String fechaHoraEntregaIZO;
	private String indTareasEntrega;
	private Long idEstadoExpediente;
	private Long idFaseExpediente;
	private String grupoTrabajo;

	/**
	 * @return the datosTareaTrados
	 */
	public DatosTareaTrados getDatosTareaTrados() {
		return this.datosTareaTrados;
	}

	/**
	 * @param datosTareaTrados the datosTareaTrados to set
	 */
	public void setDatosTareaTrados(DatosTareaTrados datosTareaTrados) {
		this.datosTareaTrados = datosTareaTrados;
	}

	public DatosPagoProveedores getDatosPagoProveedores() {
		return datosPagoProveedores;
	}

	public void setDatosPagoProveedores(DatosPagoProveedores datosPagoProveedores) {
		this.datosPagoProveedores = datosPagoProveedores;
	}

	/**
	 * @return the fechaHoraInicio
	 */
	public String getFechaHoraInicio() {
		return this.fechaHoraInicio;
	}

	/**
	 * @param fechaHoraInicio the fechaHoraInicio to set
	 */
	public void setFechaHoraInicio(String fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	/**
	 * @return the fechaHoraFin
	 */
	public String getFechaHoraFin() {
		return this.fechaHoraFin;
	}

	/**
	 * @param fechaHoraFin the fechaHoraFin to set
	 */
	public void setFechaHoraFin(String fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	/**
	 * @return the fechaHoraEntregaIZO
	 */
	public String getFechaHoraEntregaIZO() {
		return this.fechaHoraEntregaIZO;
	}

	/**
	 * @param fechaHoraEntregaIZO the fechaHoraEntregaIZO to set
	 */
	public void setFechaHoraEntregaIZO(String fechaHoraEntregaIZO) {
		this.fechaHoraEntregaIZO = fechaHoraEntregaIZO;
	}

	/**
	 * @return the indTareasEntrega
	 */
	public String getIndTareasEntrega() {
		return indTareasEntrega;
	}

	/**
	 * @param indTareasEntrega the indTareasEntrega to set
	 */
	public void setIndTareasEntrega(String indTareasEntrega) {
		this.indTareasEntrega = indTareasEntrega;
	}

	/**
	 * @return the idEstadoExpediente
	 */
	public Long getIdEstadoExpediente() {
		return idEstadoExpediente;
	}

	/**
	 * @param idEstadoExpediente the idEstadoExpediente to set
	 */
	public void setIdEstadoExpediente(Long idEstadoExpediente) {
		this.idEstadoExpediente = idEstadoExpediente;
	}

	/**
	 * @return the idFaseExpediente
	 */
	public Long getIdFaseExpediente() {
		return idFaseExpediente;
	}

	/**
	 * @param idFaseExpediente the idFaseExpediente to set
	 */
	public void setIdFaseExpediente(Long idFaseExpediente) {
		this.idFaseExpediente = idFaseExpediente;
	}

	public String getGrupoTrabajo() {
		return grupoTrabajo;
	}

	public void setGrupoTrabajo(String grupoTrabajo) {
		this.grupoTrabajo = grupoTrabajo;
	}

	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append(" [ datosTareaTrados: ").append(this.datosTareaTrados).append(" ]");
		result.append(", [ fechaHoraInicio: ").append(this.fechaHoraInicio).append(" ]");
		result.append(", [ fechaHoraFin: ").append(this.fechaHoraFin).append(" ]");
		result.append(", [ fechaHoraEntregaIZO: ").append(this.fechaHoraEntregaIZO).append(" ]");
		result.append(", [ indTareasEntrega: ").append(this.indTareasEntrega).append(" ]");
		result.append(", [ idEstadoExpediente: ").append(this.idEstadoExpediente).append(" ]");
		result.append(", [ idFaseExpediente: ").append(this.idFaseExpediente).append(" ]");
		result.append("}");
		return result.toString();
	}

}
