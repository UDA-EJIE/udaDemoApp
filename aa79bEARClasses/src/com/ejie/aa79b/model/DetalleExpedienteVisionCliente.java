package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

public class DetalleExpedienteVisionCliente extends Expediente implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date fechaLimite;
	private Date fechaEntrega;
	private String textoObs;
	private String tipoRedaccionObs;
	private String participantesObs;
	private Solicitante representante;
	private String indConfidencialDescEs;
	private String indConfidencialDescEu;
	private String horaEntrega;

	public DetalleExpedienteVisionCliente() {
		// Constructor
	}

	/**
	 * @return the fechaLimite
	 */
	public Date getFechaLimite() {
		return fechaLimite;
	}

	/**
	 * @param fechaLimite
	 *            the fechaLimite to set
	 */
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega
	 *            the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the horaEntrega
	 */
	public String getHoraEntrega() {
		return horaEntrega;
	}

	/**
	 * @param horaEntrega
	 *            the horaEntrega to set
	 */
	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	/**
	 * @param fechaHoraEntrega
	 *            the horaEntrega to set
	 */
	public String setFechaHoraEntrega(String horaEntrega) {
		if (this.fechaEntrega != null) {
			return this.fechaEntrega + this.horaEntrega;
		}
		return "";
	}

	/**
	 * @return the textoObs
	 */
	public String getTextoObs() {
		return textoObs;
	}

	/**
	 * @param textoObs
	 *            the textoObs to set
	 */
	public void setTextoObs(String textoObs) {
		this.textoObs = textoObs;
	}

	/**
	 * @return the tipoRedaccionObs
	 */
	public String getTipoRedaccionObs() {
		return tipoRedaccionObs;
	}

	/**
	 * @param tipoRedaccionObs
	 *            the tipoRedaccionObs to set
	 */
	public void setTipoRedaccionObs(String tipoRedaccionObs) {
		this.tipoRedaccionObs = tipoRedaccionObs;
	}

	/**
	 * @return the participantesObs
	 */
	public String getParticipantesObs() {
		return participantesObs;
	}

	/**
	 * @param participantesObs
	 *            the participantesObs to set
	 */
	public void setParticipantesObs(String participantesObs) {
		this.participantesObs = participantesObs;
	}

	/**
	 * @return the representante
	 */
	public Solicitante getRepresentante() {
		return representante;
	}

	/**
	 * @param representante
	 *            the representante to set
	 */
	public void setRepresentante(Solicitante representante) {
		this.representante = representante;
	}

	/**
	 * @return the indConfidencialDescEs
	 */
	public String getIndConfidencialDescEs() {
		return indConfidencialDescEs;
	}

	/**
	 * @param indConfidencialDescEs
	 *            the indConfidencialDescEs to set
	 */
	public void setIndConfidencialDescEs(String indConfidencialDescEs) {
		this.indConfidencialDescEs = indConfidencialDescEs;
	}

	/**
	 * @return the indConfidencialDescEu
	 */
	public String getIndConfidencialDescEu() {
		return indConfidencialDescEu;
	}

	/**
	 * @param indConfidencialDescEu
	 *            the indConfidencialDescEu to set
	 */
	public void setIndConfidencialDescEu(String indConfidencialDescEu) {
		this.indConfidencialDescEu = indConfidencialDescEu;
	}

}
