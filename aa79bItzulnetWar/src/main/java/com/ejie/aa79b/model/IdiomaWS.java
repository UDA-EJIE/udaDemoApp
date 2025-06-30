package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * DatosSalidaWS
 * @author mrodriguez
 */
public class IdiomaWS implements Serializable {
	
	private static final long serialVersionUID = -7129502208017232165L;
	private String idioma;
	
	/**
	 * @return the estado
	 */
	public String getIdioma() {
		return this.idioma;
	}
	
	/**
	 * @param estado the estado to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ idioma: ").append(this.idioma).append(" ]");
		result.append("}");
		return result.toString();
	}

}
