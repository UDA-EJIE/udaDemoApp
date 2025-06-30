package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * EntradaNotasExpediente
 * 
 * @author mrodriguez
 */
public class EntradaDatosSolicitud implements Serializable {

	private static final long serialVersionUID = -89123050353874656L;
	private String dni;
	private Long idTipoTarea;
	private Long anyo;
	private Integer numExp;
	private String idioma;
	private Long idRequerimiento;
	private Integer accionRequerimiento;
	private String motivoRechazo;
	private Long tipoRequerimiento;
	private BigDecimal idTarea;

	/**
	 * @return the dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the anyo
	 */
	public Long getIdTipoTarea() {
		return this.idTipoTarea;
	}

	/**
	 * @param anyo
	 *            the anyo to set
	 */
	public void setIdTipoTarea(Long idTipoTarea) {
		this.idTipoTarea = idTipoTarea;
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

	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return this.idioma;
	}

	/**
	 * @param dni
	 *            the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Long getIdRequerimiento() {
		return idRequerimiento;
	}

	public void setIdRequerimiento(Long idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	public Integer getAccionRequerimiento() {
		return accionRequerimiento;
	}

	public void setAccionRequerimiento(Integer accionRequerimiento) {
		this.accionRequerimiento = accionRequerimiento;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append("}");
		return result.toString();
	}

	/**
	 * @return the tipoRequerimiento
	 */
	public Long getTipoRequerimiento() {
		return tipoRequerimiento;
	}

	/**
	 * @param tipoRequerimiento
	 *            the tipoRequerimiento to set
	 */
	public void setTipoRequerimiento(Long tipoRequerimiento) {
		this.tipoRequerimiento = tipoRequerimiento;
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

}
