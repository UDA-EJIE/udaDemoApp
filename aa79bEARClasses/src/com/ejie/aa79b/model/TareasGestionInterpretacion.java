package com.ejie.aa79b.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author dmuchuari
 *
 */
public class TareasGestionInterpretacion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal idTarea;
	private Long numInterpretes;
	private String horasPrevistasInterpretacion;
	private String indVisible;
	private String indPresupuesto;
	private BigDecimal presupuesto;
	private Date fechaIni;
	private Date fechaFin;
	private String horaIni;
	private String horaFin;
	private BigDecimal importe;
	private Long idRequerimiento;
	private Date fechaLimite;
	private String horaLimite;
	// añadido para documentoPresupuestoInterpretación
	private String urlOrdenPrecios;
	private Date fechaLimiteSeleccionable;
	private String horaLimiteSeleccionable;
	private Long anyo;
	private Integer numExp;

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
	public void setHorasPrevistasInterpretacion(String horasInterpretacion) {
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
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
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
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * 
	 * @param idRequerimiento
	 */
	public void setIdRequerimiento(Long idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	/**
	 * 
	 * @return horasInterpretacion
	 */
	public String getHorasPrevistasInterpretacion() {
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
	@JsonSerialize(using = JsonImporteSerializer.class)
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
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * 
	 * @return idRequerimiento
	 */
	public Long getIdRequerimiento() {
		return idRequerimiento;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaLimite() {
		return fechaLimite;
	}

	public String getHoraLimite() {
		return horaLimite;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}

	public Date getFechaHoraLimite() {
		Date fechaDate = this.fechaLimite;
		if (this.horaLimite != null) {
			fechaDate = DateUtils.obtFechaHoraFormateada(this.fechaLimite, this.horaLimite);
		}
		return fechaDate;
	}

	/**
	 * @return the urlOrdenPrecios
	 */
	public String getUrlOrdenPrecios() {
		return urlOrdenPrecios;
	}

	/**
	 * @param urlOrdenPrecios
	 *            the urlOrdenPrecios to set
	 */
	public void setUrlOrdenPrecios(String urlOrdenPrecios) {
		this.urlOrdenPrecios = urlOrdenPrecios;
	}

	/**
	 * @return the fechaLimiteSeleccionable
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getFechaLimiteSeleccionable() {
		return fechaLimiteSeleccionable;
	}

	/**
	 * @param fechaLimitePropuesta
	 *            the fechaLimitePropuesta to set
	 */
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaLimiteSeleccionable(Date fechaLimiteSeleccionable) {
		this.fechaLimiteSeleccionable = fechaLimiteSeleccionable;
	}

	/**
	 * @return the horaLimiteSeleccionable
	 */
	public String getHoraLimiteSeleccionable() {
		return horaLimiteSeleccionable;
	}

	/**
	 * @param horaLimiteSeleccionable
	 *            the horaLimiteSeleccionable to set
	 */
	public void setHoraLimiteSeleccionable(String horaLimiteSeleccionable) {
		this.horaLimiteSeleccionable = horaLimiteSeleccionable;
	}

}
