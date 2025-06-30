package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79BInformeConsultaTareas implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sidx;
	private String sord;
	private Aa79bSalidaConsultaTarea aa79BSalidaConsultaTarea;
	private String columns;
	private String criterios;

	/**
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx
	 *            the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord
	 *            the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}

	/**
	 * @return the aa79BSalidaConsultaTarea
	 */
	public Aa79bSalidaConsultaTarea getAa79BSalidaConsultaTarea() {
		return aa79BSalidaConsultaTarea;
	}

	/**
	 * @param aa79bSalidaConsultaTarea
	 *            the aa79BSalidaConsultaTarea to set
	 */
	public void setAa79BSalidaConsultaTarea(Aa79bSalidaConsultaTarea aa79bSalidaConsultaTarea) {
		aa79BSalidaConsultaTarea = aa79bSalidaConsultaTarea;
	}

	/**
	 * @return the columns
	 */
	public String getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(String columns) {
		this.columns = columns;
	}

	/**
	 * @return the criterios
	 */
	public String getCriterios() {
		return criterios;
	}

	/**
	 * @param criterios
	 *            the criterios to set
	 */
	public void setCriterios(String criterios) {
		this.criterios = criterios;
	}

}
