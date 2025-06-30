package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

/**
 * Aa79bInformeFacturas
 * 
 * @author mrodriguez
 */
public class Aa79bInformeFacturas extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 5671204216000408655L;

	private Aa79bEntradaConsultaFacturas filterConsultaFacturas;
	private String columns;
	private String criterios;
	private String sidx;
	private String sord;

	/**
	 * @return the filterConsultaFacturas
	 */
	public Aa79bEntradaConsultaFacturas getFilterConsultaFacturas() {
		return filterConsultaFacturas;
	}

	/**
	 * @param filterConsultaFacturas
	 *            the filterConsultaFacturas to set
	 */
	public void setFilterConsultaFacturas(Aa79bEntradaConsultaFacturas filterConsultaFacturas) {
		this.filterConsultaFacturas = filterConsultaFacturas;
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

}
