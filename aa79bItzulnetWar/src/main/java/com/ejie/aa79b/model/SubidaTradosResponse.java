package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SubidaTradosResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal presupuesto;
	private String errorMsg;

	public SubidaTradosResponse() {
		// Constructor
	}

	/**
	 * @return the presupuesto
	 */
	public BigDecimal getPresupuesto() {
		return presupuesto;
	}

	/**
	 * @param presupuesto
	 *            the presupuesto to set
	 */
	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubidaTradosResponse [presupuesto=" + presupuesto + ", errorMsg=" + errorMsg + "]";
	}

}
