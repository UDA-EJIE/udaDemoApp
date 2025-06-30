package com.ejie.aa79b.model;

import java.io.Serializable;

import com.ejie.aa79b.model.webservices.Aa79bLibroRegistro;

/**
 * EntradaReceptorAutorizado
 * @author mrodriguez
 */
public class EntradaLibroRegistroSol extends IdiomaWS implements Serializable {
	
	private static final long serialVersionUID = 3353343144070742523L;
	private String dni;
	private Long anyo;
    private Integer numExp;
    private Aa79bLibroRegistro libroRegistro;
    
    /**
	 * @return the dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return this.anyo;
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
	public Integer getNumExp() {
		return this.numExp;
	}

	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public Aa79bLibroRegistro getLibroRegistro() {
		return libroRegistro;
	}

	public void setLibroRegistro(Aa79bLibroRegistro libroRegistro) {
		this.libroRegistro = libroRegistro;
	}

	@Override
	public String toString() {
		return "EntradaLibroRegistroSol [dni=" + dni + ", anyo=" + anyo + ", numExp=" + numExp + ", libroRegistro="
				+ libroRegistro + "]";
	}

	
}
