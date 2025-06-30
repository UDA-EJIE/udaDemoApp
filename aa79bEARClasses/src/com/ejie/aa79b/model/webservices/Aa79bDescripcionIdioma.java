package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

/**
 * Aa79bDescripcionIdioma
 * @author mrodriguez
 */
public class Aa79bDescripcionIdioma implements Serializable {
	
	private static final long serialVersionUID = -4397765206963153544L;
	private Long id;
	private String codigo;
    private String descEu;
    private String descEs;
    
	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descEu
	 */
	public String getDescEu() {
		return this.descEu;
	}
	
	/**
	 * @param descEu the descEu to set
	 */
	public void setDescEu(String descEu) {
		this.descEu = descEu;
	}
	
	/**
	 * @return the descEs
	 */
	public String getDescEs() {
		return this.descEs;
	}
	
	/**
	 * @param descEs the descEs to set
	 */
	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}
    
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ id: ").append(this.id).append(" ]");
		result.append(", [ codigo: ").append(this.codigo).append(" ]");
		result.append(", [ descEu: ").append(this.descEu).append(" ]");
		result.append(", [ descEs: ").append(this.descEs).append(" ]");
		result.append("}");
		return result.toString();
	}

}
