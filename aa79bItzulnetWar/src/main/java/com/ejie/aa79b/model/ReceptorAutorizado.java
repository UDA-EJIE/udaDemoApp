package com.ejie.aa79b.model;

public class ReceptorAutorizado extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long anyo;
	private Integer numExp;
	
	
	public ReceptorAutorizado() {
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
	public Integer getNumExp() {
		return numExp;
	}


	/**
	 * @param numExp the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReceptorAutorizado [anyo=" + anyo + ", numExp=" + numExp + "]";
	}
	
	
	
	

}
