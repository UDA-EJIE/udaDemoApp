package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bEntradaConsultaTarea extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private Long anyo;
	private Integer numExp;
	private Integer idTipoTarea;
	private String idsEstadoEjecTarea;
	private Long fechaDesdeEjecPrevTarea;
	private Long fechaHastaEjecPrevTarea;
	private Long fechaDesdeEjecRealTarea;
	private Long fechaHastaEjecRealTarea;
	private Integer idLote;
	private String dni;

	public Aa79bEntradaConsultaTarea() {
		// Constructor
	}

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
	 * @return the idTipoTarea
	 */
	public Integer getIdTipoTarea() {
		return idTipoTarea;
	}

	/**
	 * @param idTipoTarea
	 *            the idTipoTarea to set
	 */
	public void setIdTipoTarea(Integer idTipoTarea) {
		this.idTipoTarea = idTipoTarea;
	}

	/**
	 * @return the idsEstadoEjecTarea
	 */
	public String getIdsEstadoEjecTarea() {
		return idsEstadoEjecTarea;
	}

	/**
	 * @param idsEstadoEjecTarea
	 *            the idsEstadoEjecTarea to set
	 */
	public void setIdsEstadoEjecTarea(String idsEstadoEjecTarea) {
		this.idsEstadoEjecTarea = idsEstadoEjecTarea;
	}

	/**
	 * @return the fechaDesdeEjecPrevTarea
	 */
	public Long getFechaDesdeEjecPrevTarea() {
		return fechaDesdeEjecPrevTarea;
	}

	/**
	 * @param fechaDesdeEjecPrevTarea
	 *            the fechaDesdeEjecPrevTarea to set
	 */
	public void setFechaDesdeEjecPrevTarea(Long fechaDesdeEjecPrevTarea) {
		this.fechaDesdeEjecPrevTarea = fechaDesdeEjecPrevTarea;
	}

	/**
	 * @return the fechaHastaEjecPrevTarea
	 */
	public Long getFechaHastaEjecPrevTarea() {
		return fechaHastaEjecPrevTarea;
	}

	/**
	 * @param fechaHastaEjecPrevTarea
	 *            the fechaHastaEjecPrevTarea to set
	 */
	public void setFechaHastaEjecPrevTarea(Long fechaHastaEjecPrevTarea) {
		this.fechaHastaEjecPrevTarea = fechaHastaEjecPrevTarea;
	}

	/**
	 * @return the fechaDesdeEjecRealTarea
	 */
	public Long getFechaDesdeEjecRealTarea() {
		return fechaDesdeEjecRealTarea;
	}

	/**
	 * @param fechaDesdeEjecRealTarea
	 *            the fechaDesdeEjecRealTarea to set
	 */
	public void setFechaDesdeEjecRealTarea(Long fechaDesdeEjecRealTarea) {
		this.fechaDesdeEjecRealTarea = fechaDesdeEjecRealTarea;
	}

	/**
	 * @return the fechaHastaEjecRealTarea
	 */
	public Long getFechaHastaEjecRealTarea() {
		return fechaHastaEjecRealTarea;
	}

	/**
	 * @param fechaHastaEjecRealTarea
	 *            the fechaHastaEjecRealTarea to set
	 */
	public void setFechaHastaEjecRealTarea(Long fechaHastaEjecRealTarea) {
		this.fechaHastaEjecRealTarea = fechaHastaEjecRealTarea;
	}

	/**
	 * @return the idLote
	 */
	public Integer getIdLote() {
		return idLote;
	}

	/**
	 * @param idLote
	 *            the idLote to set
	 */
	public void setIdLote(Integer idLote) {
		this.idLote = idLote;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

}
