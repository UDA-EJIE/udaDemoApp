package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bDatosInterpretacion implements Serializable {

	/**
	 * serialVersionUID: long.
	 */
	private static final long serialVersionUID = 1L;

	private Aa79bDescripcionIdioma modoInterpretacion;
	private Aa79bDescripcionIdioma tipoActo;
	private Aa79bDescripcionIdioma tipoPeticionario;
	private String indProgramada;
	private Long fechaIni;
	private String horaIni;
	private Long fechaFin;
	private String horaFin;
	private Aa79bDireccionNora dirNora;
	private String indPresupuesto;
	private String indObservaciones;
	private String observaciones;
	private String personaContacto;
	private String emailContacto;
	private String telefonoContacto;
	private Aa79bDatosFacturacionExpInter datosFacturacion;
	private String indFacturable;

	/**
	 * @return the modoInterpretacion
	 */
	public Aa79bDescripcionIdioma getModoInterpretacion() {
		return this.modoInterpretacion;
	}

	/**
	 * @param modoInterpretacion
	 *            the modoInterpretacion to set
	 */
	public void setModoInterpretacion(Aa79bDescripcionIdioma modoInterpretacion) {
		this.modoInterpretacion = modoInterpretacion;
	}

	/**
	 * @return the tipoActo
	 */
	public Aa79bDescripcionIdioma getTipoActo() {
		return this.tipoActo;
	}

	/**
	 * @param tipoActo
	 *            the tipoActo to set
	 */
	public void setTipoActo(Aa79bDescripcionIdioma tipoActo) {
		this.tipoActo = tipoActo;
	}

	/**
	 * @return the tipoPeticionario
	 */
	public Aa79bDescripcionIdioma getTipoPeticionario() {
		return this.tipoPeticionario;
	}

	/**
	 * @param tipoPeticionario
	 *            the tipoPeticionario to set
	 */
	public void setTipoPeticionario(Aa79bDescripcionIdioma tipoPeticionario) {
		this.tipoPeticionario = tipoPeticionario;
	}

	/**
	 * @return the indProgramada
	 */
	public String getIndProgramada() {
		return this.indProgramada;
	}

	/**
	 * @param indProgramada
	 *            the indProgramada to set
	 */
	public void setIndProgramada(String indProgramada) {
		this.indProgramada = indProgramada;
	}

	/**
	 * @return the horaIni
	 */
	public String getHoraIni() {
		return this.horaIni;
	}

	/**
	 * @param horaIni
	 *            the horaIni to set
	 */
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	/**
	 * @return the fechaIni
	 */
	public Long getFechaIni() {
		return fechaIni;
	}

	/**
	 * @param fechaIni
	 *            the fechaIni to set
	 */
	public void setFechaIni(Long fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * @return the fechaFin
	 */
	public Long getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin
	 *            the fechaFin to set
	 */
	public void setFechaFin(Long fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return this.horaFin;
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
	public Aa79bDireccionNora getDirNora() {
		return this.dirNora;
	}

	/**
	 * @param dirNora
	 *            the dirNora to set
	 */
	public void setDirNora(Aa79bDireccionNora dirNora) {
		this.dirNora = dirNora;
	}

	/**
	 * @return the indPresupuesto
	 */
	public String getIndPresupuesto() {
		return this.indPresupuesto;
	}

	/**
	 * @param indPresupuesto
	 *            the indPresupuesto to set
	 */
	public void setIndPresupuesto(String indPresupuesto) {
		this.indPresupuesto = indPresupuesto;
	}

	/**
	 * @return the indObservaciones
	 */
	public String getIndObservaciones() {
		return this.indObservaciones;
	}

	/**
	 * @param indObservaciones
	 *            the indObservaciones to set
	 */
	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the personaContacto
	 */
	public String getPersonaContacto() {
		return this.personaContacto;
	}

	/**
	 * @param personaContacto
	 *            the personaContacto to set
	 */
	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	/**
	 * @return the emailContacto
	 */
	public String getEmailContacto() {
		return this.emailContacto;
	}

	/**
	 * @param emailContacto
	 *            the emailContacto to set
	 */
	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	/**
	 * @return the telefonoContacto
	 */
	public String getTelefonoContacto() {
		return this.telefonoContacto;
	}

	/**
	 * @param telefonoContacto
	 *            the telefonoContacto to set
	 */
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	/**
	 * @return the datosFacturacion
	 */
	public Aa79bDatosFacturacionExpInter getDatosFacturacion() {
		return datosFacturacion;
	}

	/**
	 * @param datosFacturacion
	 *            the datosFacturacion to set
	 */
	public void setDatosFacturacion(Aa79bDatosFacturacionExpInter datosFacturacion) {
		this.datosFacturacion = datosFacturacion;
	}

	/**
	 * @return the indFacturable
	 */
	public String getIndFacturable() {
		return indFacturable;
	}

	/**
	 * @param indFacturable
	 *            the indFacturable to set
	 */
	public void setIndFacturable(String indFacturable) {
		this.indFacturable = indFacturable;
	}

}
