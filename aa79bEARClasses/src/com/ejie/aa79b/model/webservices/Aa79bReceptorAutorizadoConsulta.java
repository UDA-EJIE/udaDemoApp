package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bReceptorAutorizadoConsulta extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean esReceptor;
	private Integer countExpReceptor;

	public Aa79bReceptorAutorizadoConsulta() {
		// Constructor
	}

	/**
	 * @return the esReceptor
	 */
	public Boolean getEsReceptor() {
		return esReceptor;
	}

	/**
	 * @param esReceptor
	 *            the esReceptor to set
	 */
	public void setEsReceptor(Boolean esReceptor) {
		this.esReceptor = esReceptor;
	}

	/**
	 * @return the countExpReceptor
	 */
	public Integer getCountExpReceptor() {
		return countExpReceptor;
	}

	/**
	 * @param countExpReceptor
	 *            the countExpReceptor to set
	 */
	public void setCountExpReceptor(Integer countExpReceptor) {
		this.countExpReceptor = countExpReceptor;
	}

}
