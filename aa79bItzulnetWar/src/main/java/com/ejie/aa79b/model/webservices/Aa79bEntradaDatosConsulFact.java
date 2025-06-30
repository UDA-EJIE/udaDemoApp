package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

/**
 * Aa79bEntradaDatosConsulFact
 * 
 * @author mrodriguez
 */
public class Aa79bEntradaDatosConsulFact extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = -4607566140897475162L;
	private String dni;

	/**
	 * Constructor
	 */
	public Aa79bEntradaDatosConsulFact() {
		// Constructor
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

}
