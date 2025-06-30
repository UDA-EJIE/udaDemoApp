package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ejie.aa79b.model.IdiomaWS;

/**
 * Aa79bEntradaTarea
 * 
 * @author aresua
 */
public class Aa79bEntradaTarea extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = -3695012946036974390L;
	private BigDecimal idTarea;
	private String dni;
	private String usuario;
	private Long anyo;
	private Integer numExp;
	private Integer idLote;
	private Long fechaIniEjecucion;
	private Long fechaFinEjecucion;
	private Aa79bTiposTarea tipoTarea = new Aa79bTiposTarea();
	private Aa79bEjecucionTareas ejecucionTareas;
	private Aa79bDatosPagoProveedores datosPagoProveedores;
	private Aa79bObservacionesTareas observacionesTareas;
	private List<Aa79bTarea> tareasList = new ArrayList<Aa79bTarea>();
	private String tareasListStr;
	private String estadoListStr;

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return idTarea;
	}

	/**
	 * @param idTarea
	 *            the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the tipoTarea
	 */
	public Aa79bTiposTarea getTipoTarea() {
		return tipoTarea;
	}

	/**
	 * @param tipoTarea
	 *            the tipoTarea to set
	 */
	public void setTipoTarea(Aa79bTiposTarea tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	/**
	 * @return the ejecucionTareas
	 */
	public Aa79bEjecucionTareas getEjecucionTareas() {
		return ejecucionTareas;
	}

	/**
	 * @param ejecucionTareas
	 *            the ejecucionTareas to set
	 */
	public void setEjecucionTareas(Aa79bEjecucionTareas ejecucionTareas) {
		this.ejecucionTareas = ejecucionTareas;
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

	public Integer getIdLote() {
		return idLote;
	}

	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	public Long getFechaIniEjecucion() {
		return fechaIniEjecucion;
	}

	public void setFechaIniEjecucion(Long fechaIniEjecucion) {
		this.fechaIniEjecucion = fechaIniEjecucion;
	}

	public Long getFechaFinEjecucion() {
		return fechaFinEjecucion;
	}

	public void setFechaFinEjecucion(Long fechaFinEjecucion) {
		this.fechaFinEjecucion = fechaFinEjecucion;
	}

	public Aa79bDatosPagoProveedores getDatosPagoProveedores() {
		return datosPagoProveedores;
	}

	public void setDatosPagoProveedores(Aa79bDatosPagoProveedores datosPagoProveedores) {
		this.datosPagoProveedores = datosPagoProveedores;
	}

	/**
	 * @return the observacionesTareas
	 */
	public Aa79bObservacionesTareas getObservacionesTareas() {
		return observacionesTareas;
	}

	/**
	 * @param observacionesTareas
	 *            the observacionesTareas to set
	 */
	public void setObservacionesTareas(Aa79bObservacionesTareas observacionesTareas) {
		this.observacionesTareas = observacionesTareas;
	}

	public List<Aa79bTarea> getTareasList() {
		return tareasList;
	}

	public void setTareasList(List<Aa79bTarea> tareasList) {
		this.tareasList = tareasList;
	}

	public String getTareasListStr() {
		return tareasListStr;
	}

	public void setTareasListStr(String tareasListStr) {
		this.tareasListStr = tareasListStr;
	}

	public String getEstadoListStr() {
		return estadoListStr;
	}

	public void setEstadoListStr(String estadoListStr) {
		this.estadoListStr = estadoListStr;
	}

}
