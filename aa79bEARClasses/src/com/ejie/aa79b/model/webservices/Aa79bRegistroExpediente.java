package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DatosSalidaWS;

public class Aa79bRegistroExpediente extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long anyo;
	private int numExp;
	
	
	public Aa79bRegistroExpediente() {
		//Constructor vacio
	}
	
	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return anyo;
	}
	/**
	 * @param anyo the anyo to set
	 */
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}
	/**
	 * @return the numExp
	 */
	public int getNumExp() {
		return numExp;
	}
	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(int numExp) {
		this.numExp = numExp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aa79bRegistroExpediente [ anyo=" + anyo + ", numExp=" + numExp + "]";
	}
	
}
