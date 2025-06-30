package com.ejie.aa79b.model.webservices;

public class Aa79bEstadoFaseExpediente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long idEstado;
	private String descEstadoEu;
	private String descEstadoEs;
	private String descEstadoAbrEu;
	private String descEstadoAbrEs;
	private String estadoClassStyle;

	private Long idFase;
	private String descFaseEu;
	private String descFaseEs;
	private String descFaseAbrEu;
	private String descFaseAbrEs;

	private Long idEstadoAux;
	private Long idFaseAux;

	public Aa79bEstadoFaseExpediente() {
		// Constructor
	}

	/**
	 * @return the idEstado
	 */
	public Long getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado
	 *            the idEstado to set
	 */
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the descEstadoEu
	 */
	public String getDescEstadoEu() {
		return descEstadoEu;
	}

	/**
	 * @param descEstadoEu
	 *            the descEstadoEu to set
	 */
	public void setDescEstadoEu(String descEstadoEu) {
		this.descEstadoEu = descEstadoEu;
	}

	/**
	 * @return the descEstadoEs
	 */
	public String getDescEstadoEs() {
		return descEstadoEs;
	}

	/**
	 * @param descEstadoEs
	 *            the descEstadoEs to set
	 */
	public void setDescEstadoEs(String descEstadoEs) {
		this.descEstadoEs = descEstadoEs;
	}

	/**
	 * @return the descEstadoAbrEu
	 */
	public String getDescEstadoAbrEu() {
		return descEstadoAbrEu;
	}

	/**
	 * @param descEstadoAbrEu
	 *            the descEstadoAbrEu to set
	 */
	public void setDescEstadoAbrEu(String descEstadoAbrEu) {
		this.descEstadoAbrEu = descEstadoAbrEu;
	}

	/**
	 * @return the descEstadoAbrEs
	 */
	public String getDescEstadoAbrEs() {
		return descEstadoAbrEs;
	}

	/**
	 * @param descEstadoAbrEs
	 *            the descEstadoAbrEs to set
	 */
	public void setDescEstadoAbrEs(String descEstadoAbrEs) {
		this.descEstadoAbrEs = descEstadoAbrEs;
	}

	/**
	 * @return the estadoClassStyle
	 */
	public String getEstadoClassStyle() {
		return estadoClassStyle;
	}

	/**
	 * @param estadoClassStyle
	 *            the estadoClassStyle to set
	 */
	public void setEstadoClassStyle(String estadoClassStyle) {
		this.estadoClassStyle = estadoClassStyle;
	}

	/**
	 * @return the idFase
	 */
	public Long getIdFase() {
		return idFase;
	}

	/**
	 * @param idFase
	 *            the idFase to set
	 */
	public void setIdFase(Long idFase) {
		this.idFase = idFase;
	}

	/**
	 * @return the descFaseEu
	 */
	public String getDescFaseEu() {
		return descFaseEu;
	}

	/**
	 * @param descFaseEu
	 *            the descFaseEu to set
	 */
	public void setDescFaseEu(String descFaseEu) {
		this.descFaseEu = descFaseEu;
	}

	/**
	 * @return the descFaseEs
	 */
	public String getDescFaseEs() {
		return descFaseEs;
	}

	/**
	 * @param descFaseEs
	 *            the descFaseEs to set
	 */
	public void setDescFaseEs(String descFaseEs) {
		this.descFaseEs = descFaseEs;
	}

	/**
	 * @return the descFaseAbrEu
	 */
	public String getDescFaseAbrEu() {
		return descFaseAbrEu;
	}

	/**
	 * @param descFaseAbrEu
	 *            the descFaseAbrEu to set
	 */
	public void setDescFaseAbrEu(String descFaseAbrEu) {
		this.descFaseAbrEu = descFaseAbrEu;
	}

	/**
	 * @return the descFaseAbrEs
	 */
	public String getDescFaseAbrEs() {
		return descFaseAbrEs;
	}

	/**
	 * @param descFaseAbrEs
	 *            the descFaseAbrEs to set
	 */
	public void setDescFaseAbrEs(String descFaseAbrEs) {
		this.descFaseAbrEs = descFaseAbrEs;
	}

	public Long getIdEstadoAux() {
		return idEstadoAux;
	}

	public void setIdEstadoAux(Long idEstadoAux) {
		this.idEstadoAux = idEstadoAux;
	}

	public Long getIdFaseAux() {
		return idFaseAux;
	}

	public void setIdFaseAux(Long idFaseAux) {
		this.idFaseAux = idFaseAux;
	}

	@Override
	public String toString() {
		return "Aa79bEstadoFaseExpediente [idEstado=" + idEstado + ", descEstadoEu=" + descEstadoEu + ", descEstadoEs="
				+ descEstadoEs + ", descEstadoAbrEu=" + descEstadoAbrEu + ", descEstadoAbrEs=" + descEstadoAbrEs
				+ ", estadoClassStyle=" + estadoClassStyle + ", idFase=" + idFase + ", descFaseEu=" + descFaseEu
				+ ", descFaseEs=" + descFaseEs + ", descFaseAbrEu=" + descFaseAbrEu + ", descFaseAbrEs=" + descFaseAbrEs
				+ "]";
	}

}
