package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bInformeTareasProveedor extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sidx;
	private String sord;
	private Aa79bEntradaConsultaTarea aa79bEntradaConsultaTarea;
	private String columns;
	private String criterios;
	private Long documentTypeLongValue;

	public Aa79bInformeTareasProveedor() {
		// Constructor
	}

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
	 * @return the aa79bEntradaConsultaTarea
	 */
	public Aa79bEntradaConsultaTarea getAa79bEntradaConsultaTarea() {
		return aa79bEntradaConsultaTarea;
	}

	/**
	 * @param aa79bEntradaConsultaTarea
	 *            the aa79bEntradaConsultaTarea to set
	 */
	public void setAa79bEntradaConsultaTarea(Aa79bEntradaConsultaTarea aa79bEntradaConsultaTarea) {
		this.aa79bEntradaConsultaTarea = aa79bEntradaConsultaTarea;
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

	/**
	 * @return the documentTypeLongValue
	 */
	public Long getDocumentTypeLongValue() {
		return documentTypeLongValue;
	}

	/**
	 * @param documentTypeLongValue
	 *            the documentTypeLongValue to set
	 */
	public void setDocumentTypeLongValue(Long documentTypeLongValue) {
		this.documentTypeLongValue = documentTypeLongValue;
	}

}
