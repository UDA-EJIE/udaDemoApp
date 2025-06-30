package com.ejie.aa79b.model.webservices;

import java.math.BigDecimal;
import java.util.Date;

import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author dmuchuari
 *
 */
public class Aa79bTareasGestionInterpretacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private Long numInterpretes;
	private Long horasPrevistasInterpretacion;
	private String indVisible;
	private String indPresupuesto;
	private BigDecimal presupuesto;
	private Date fechaIni;
	private Date fechaFin;
	private String horaIni;
	private String horaFin;
	private Long importe;

	/**
	 * 
	 * @param idTarea
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * 
	 * @param indPresupuesto
	 */
	public void setIndPresupuesto(String indPresupuesto) {
		this.indPresupuesto = indPresupuesto;
	}

	/**
	 * 
	 * @param indVisible
	 */
	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	/**
	 * 
	 * @param horasInterpretacion
	 */
	public void setHorasPrevistasInterpretacion(Long horasInterpretacion) {
		this.horasPrevistasInterpretacion = horasInterpretacion;
	}

	/**
	 * 
	 * @param numInterpretes
	 */
	public void setNumInterpretes(Long numInterpretes) {
		this.numInterpretes = numInterpretes;
	}

	/**
	 * 
	 * @param presupuesto
	 */
	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}

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
	 * @param horaIni
	 */
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
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
	 * @param importe
	 */
	public void setImporte(Long importe) {
		this.importe = importe;
	}

	/**
	 * 
	 * @return horasInterpretacion
	 */
	public Long getHorasPrevistasInterpretacion() {
		return horasPrevistasInterpretacion;
	}

	/**
	 * 
	 * @return idTarea
	 */
	public BigDecimal getIdTarea() {
		return idTarea;
	}

	/**
	 * 
	 * @return indVisible
	 */
	public String getIndVisible() {
		return indVisible;
	}

	/**
	 * 
	 * @return presupuesto
	 */
	public BigDecimal getPresupuesto() {
		return presupuesto;
	}

	/**
	 * 
	 * @return indPresupuesto
	 */
	public String getIndPresupuesto() {
		return indPresupuesto;
	}

	/**
	 * 
	 * @return numInterpretes
	 */
	public Long getNumInterpretes() {
		return numInterpretes;
	}

	/**
	 * 
	 * @return fechaFin
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * 
	 * @return fechaIni
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaIni() {
		return fechaIni;
	}

	/**
	 * 
	 * @return horaFin
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * 
	 * @return horaIni
	 */
	public String getHoraIni() {
		return horaIni;
	}

	/**
	 * 
	 * @return importe
	 */
	public Long getImporte() {
		return importe;
	}

}
