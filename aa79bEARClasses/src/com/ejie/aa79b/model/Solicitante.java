package com.ejie.aa79b.model;

public class Solicitante extends Persona {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String gestorExpedientes;
	private String gestorFacturas;
	private String coordinador;
	private String gestorExpedientesVIP;
	private String gestorExpedientesVIPDescEs;
	private String gestorExpedientesVIPDescEu;
	private String gestorExpedientesBOPV;

	private Boolean solicitanteVinculado;
	private String grupoBoletin;

	/**
	 * @return the gestorExpedientes
	 */
	public String getGestorExpedientes() {
		return this.gestorExpedientes;
	}

	/**
	 * @param gestorExpedientes
	 *            the gestorExpedientes to set
	 */
	public void setGestorExpedientes(String gestorExpedientes) {
		this.gestorExpedientes = gestorExpedientes;
	}

	/**
	 * @return the gestorFacturas
	 */
	public String getGestorFacturas() {
		return this.gestorFacturas;
	}

	/**
	 * @param gestorFacturas
	 *            the gestorFacturas to set
	 */
	public void setGestorFacturas(String gestorFacturas) {
		this.gestorFacturas = gestorFacturas;
	}

	/**
	 * @return the coordinador
	 */
	public String getCoordinador() {
		return this.coordinador;
	}

	/**
	 * @param coordinador
	 *            the coordinador to set
	 */
	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}

	/**
	 * @return the gestorExpedientesVIP
	 */
	public String getGestorExpedientesVIP() {
		return this.gestorExpedientesVIP;
	}

	/**
	 * @param gestorExpedientesVIP
	 *            the gestorExpedientesVIP to set
	 */
	public void setGestorExpedientesVIP(String gestorExpedientesVIP) {
		this.gestorExpedientesVIP = gestorExpedientesVIP;
	}

	/**
	 * @return the gestorExpedientesVIPDescEs
	 */
	public String getGestorExpedientesVIPDescEs() {
		return this.gestorExpedientesVIPDescEs;
	}

	/**
	 * @param gestorExpedientesVIPDescEs
	 *            the gestorExpedientesVIPDescEs to set
	 */
	public void setGestorExpedientesVIPDescEs(String gestorExpedientesVIPDescEs) {
		this.gestorExpedientesVIPDescEs = gestorExpedientesVIPDescEs;
	}

	/**
	 * @return the gestorExpedientesVIPDescEu
	 */
	public String getGestorExpedientesVIPDescEu() {
		return this.gestorExpedientesVIPDescEu;
	}

	/**
	 * @param gestorExpedientesVIPDescEu
	 *            the gestorExpedientesVIPDescEu to set
	 */
	public void setGestorExpedientesVIPDescEu(String gestorExpedientesVIPDescEu) {
		this.gestorExpedientesVIPDescEu = gestorExpedientesVIPDescEu;
	}

	/**
	 * @return the gestorExpedientesBOPV
	 */
	public String getGestorExpedientesBOPV() {
		return this.gestorExpedientesBOPV;
	}

	/**
	 * @param gestorExpedientesBOPV
	 *            the gestorExpedientesBOPV to set
	 */
	public void setGestorExpedientesBOPV(String gestorExpedientesBOPV) {
		this.gestorExpedientesBOPV = gestorExpedientesBOPV;
	}

	public Boolean getSolicitanteVinculado() {
		return this.solicitanteVinculado;
	}

	public void setSolicitanteVinculado(Boolean solicitanteVinculado) {
		this.solicitanteVinculado = solicitanteVinculado;
	}

	public String getGrupoBoletin() {
		return this.grupoBoletin;
	}

	public void setGrupoBoletin(String grupoBoletin) {
		this.grupoBoletin = grupoBoletin;
	}

}
