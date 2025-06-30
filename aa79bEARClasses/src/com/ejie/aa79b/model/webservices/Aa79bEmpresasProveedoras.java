package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bEmpresasProveedoras implements Serializable {

	private static final long serialVersionUID = -6712699057898824459L;
	private Long codigo;
	private String tipo;
	private String descAmpEs;
	private String descAmpEu;
	private String descEs;
	private String descEu;
	private String cif;

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the descAmpEs
	 */
	public String getDescAmpEs() {
		return descAmpEs;
	}

	/**
	 * @param descAmpEs
	 *            the descAmpEs to set
	 */
	public void setDescAmpEs(String descAmpEs) {
		this.descAmpEs = descAmpEs;
	}

	/**
	 * @return the descAmpEu
	 */
	public String getDescAmpEu() {
		return descAmpEu;
	}

	/**
	 * @param descAmpEu
	 *            the descAmpEu to set
	 */
	public void setDescAmpEu(String descAmpEu) {
		this.descAmpEu = descAmpEu;
	}

	/**
	 * @return the descEs
	 */
	public String getDescEs() {
		return descEs;
	}

	/**
	 * @param descEs
	 *            the descEs to set
	 */
	public void setDescEs(String descEs) {
		this.descEs = descEs;
	}

	/**
	 * @return the descEu
	 */
	public String getDescEu() {
		return descEu;
	}

	/**
	 * @param descEu
	 *            the descEu to set
	 */
	public void setDescEu(String descEu) {
		this.descEu = descEu;
	}

	/**
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @param cif
	 *            the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append(" [ codigo: ").append(this.codigo).append(" ]");
		result.append(", [ tipo: ").append(this.tipo).append(" ]");
		result.append(", [ descAmpEs: ").append(this.descAmpEs).append(" ]");
		result.append(", [ descAmpEu: ").append(this.descAmpEu).append(" ]");
		result.append(", [ descEs: ").append(this.descEs).append(" ]");
		result.append(", [ descEu: ").append(this.descEu).append(" ]");
		result.append(", [ cif: ").append(this.cif).append(" ]");
		result.append("}");
		return result.toString();
	}

}
