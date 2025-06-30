package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.Date;

import com.ejie.aa79b.common.Constants;

public class TradosExp implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long idFicheroTrados;
	private String indVisible;
	private Integer presupuesto;
	private Date fechaEntrega;
	private Integer tarifaPal;
	private Integer numTotalPal;
	private Integer numPalConcor084090;
	private Integer numPalConcor8594090;
	private Integer numPalConcor95100090;
	private Integer numPalConcor9599090;
	private Integer numPalConcor100090;

	public TradosExp() {
		// Constructor
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idFicheroTrados
	 */
	public Long getIdFicheroTrados() {
		return this.idFicheroTrados;
	}

	/**
	 * @param idFicheroTrados the idFicheroTrados to set
	 */
	public void setIdFicheroTrados(Long idFicheroTrados) {
		this.idFicheroTrados = idFicheroTrados;
	}

	/**
	 * @return the indVisible
	 */
	public String getIndVisible() {
		return this.indVisible;
	}

	/**
	 * @param indVisible the indVisible to set
	 */
	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	/**
	 * @return the presupuesto
	 */
	public Integer getPresupuesto() {
		return this.presupuesto;
	}

	/**
	 * @param presupuesto the presupuesto to set
	 */
	public void setPresupuesto(Integer presupuesto) {
		this.presupuesto = presupuesto;
	}

	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the tarifaPal
	 */
	public Integer getTarifaPal() {
		return this.tarifaPal;
	}

	/**
	 * @param tarifaPal the tarifaPal to set
	 */
	public void setTarifaPal(Integer tarifaPal) {
		this.tarifaPal = tarifaPal;
	}

	/**
	 * @return the numTotalPal
	 */
	public Integer getNumTotalPal() {
		return this.numTotalPal;
	}

	/**
	 * @param numTotalPal the numTotalPal to set
	 */
	public void setNumTotalPal(Integer numTotalPal) {
		this.numTotalPal = numTotalPal;
	}

	/**
	 * @return the numPalConcor084090
	 */
	public Integer getNumPalConcor084090() {
		return this.numPalConcor084090;
	}

	/**
	 * @param numPalConcor084090 the numPalConcor084090 to set
	 */
	public void setNumPalConcor084090(Integer numPalConcor084090) {
		this.numPalConcor084090 = numPalConcor084090;
	}

	/**
	 * @return the numPalConcor8594090
	 */
	public Integer getNumPalConcor8594090() {
		return this.numPalConcor8594090;
	}

	/**
	 * @param numPalConcor8594090 the numPalConcor8594090 to set
	 */
	public void setNumPalConcor8594090(Integer numPalConcor8594090) {
		this.numPalConcor8594090 = numPalConcor8594090;
	}

	/**
	 * @return the numPalConcor95100090
	 */
	public Integer getNumPalConcor95100090() {
		return this.numPalConcor95100090;
	}

	/**
	 * @param numPalConcor95100090 the numPalConcor95100090 to set
	 */
	public void setNumPalConcor95100090(Integer numPalConcor95100090) {
		this.numPalConcor95100090 = numPalConcor95100090;
	}

	public String getNumTotalPalConTramos() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		sbNumPal.append("<div class=\"numPalIzoConcor2\"><b>0-84%: </b>");
		sbNumPal.append(this.getNumPalConcor084090());
		sbNumPal.append("<br /><b>85-94%: </b>");
		sbNumPal.append(this.getNumPalConcor8594090());
		sbNumPal.append("<br /><b>95-100%: </b>");
		sbNumPal.append(this.getNumPalConcor95100090());
		sbNumPal.append("<br /></div>");
		return sbNumPal.toString();
	}

	public String getNumTotalPalConTramosPerfectMatch() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		sbNumPal.append("<div class=\"numPalIzoConcor2\"><b>0-84%: </b>");
		sbNumPal.append(this.getNumPalConcor084090());
		sbNumPal.append("<br /><b>85-94%: </b>");
		sbNumPal.append(this.getNumPalConcor8594090());
		sbNumPal.append("<br /><b>95-99%: </b>");
		sbNumPal.append(this.getNumPalConcor9599090());
		sbNumPal.append("<br /><b>100%: </b>");
		sbNumPal.append(this.getNumPalConcor100090());
		sbNumPal.append("<br /></div>");
		return sbNumPal.toString();
	}

	public String getNumTotalPalConTramosExcel() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		String contenido = this.getNumTotalPalConTramos().toString().replaceAll("<br />", "\n");
		contenido = contenido.toString().replaceAll("\\<[^>]*>", "");
		sbNumPal.append(contenido);
		return sbNumPal.toString();
	}

	public String getNumTotalPalConTramosPerfectMatchExcel() {
		StringBuilder sbNumPal = new StringBuilder(Constants.SB_INIT);
		String contenido = this.getNumTotalPalConTramosPerfectMatch().toString().replaceAll("<br />", "\n");
		contenido = contenido.toString().replaceAll("\\<[^>]*>", "");
		sbNumPal.append(contenido);
		return sbNumPal.toString();
	}

	@Override
	public String toString() {
		return "TradosExp [id=" + this.id + ", idFicheroTrados=" + this.idFicheroTrados + ", indVisible=" + this.indVisible
				+ ", presupuesto=" + this.presupuesto + ", fechaEntrega=" + this.fechaEntrega + ", tarifaPal=" + this.tarifaPal
				+ ", numTotalPal=" + this.numTotalPal + ", numPalConcor084090=" + this.numPalConcor084090
				+ ", numPalConcor8594090=" + this.numPalConcor8594090 + ", numPalConcor95100090=" + this.numPalConcor95100090
				+ "]";
	}

	public Integer getNumPalConcor9599090() {
		return this.numPalConcor9599090;
	}

	public void setNumPalConcor9599090(Integer numPalConcor9599090) {
		this.numPalConcor9599090 = numPalConcor9599090;
	}

	public Integer getNumPalConcor100090() {
		return this.numPalConcor100090;
	}

	public void setNumPalConcor100090(Integer numPalConcor100090) {
		this.numPalConcor100090 = numPalConcor100090;
	}

}
