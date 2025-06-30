package com.ejie.aa79b.model;

/**
 * EntradaGestoresRepresentante
 * @author mrodriguez
 */
public class EntradaObtenerDetalleSub implements java.io.Serializable {
	
	private static final long serialVersionUID = 6914946314137644266L;
	private String dni;
	private Long anyo;
	private Integer numExp;
	private String idioma;
	
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Long getAnyo() {
		return anyo;
	}
	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}
	public Integer getNumExp() {
		return numExp;
	}
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}
	
	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
	@Override
	public String toString() {
		return "EntradaObtenerDetalleSub [dni=" + dni + ", anyo=" + anyo + ", numExp=" + numExp + "]";
	}	
}
