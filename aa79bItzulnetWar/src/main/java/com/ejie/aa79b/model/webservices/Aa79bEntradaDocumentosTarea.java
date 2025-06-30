package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.IdiomaWS;

/**
 * Aa79bEntradaDocumentosTarea
 * 
 * @author mrodriguez
 */
public class Aa79bEntradaDocumentosTarea extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = -8928340755515401226L;
	private String dni;
	private Long anyo;
	private Integer numExp;
	private BigDecimal idTarea;
	private BigDecimal idDoc;

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

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return anyo;
	}

	/**
	 * @param anyo
	 *            the anyo to set
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
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return idTarea;
	}

	/**
	 * @param idTarea
	 *            the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the idDoc
	 */
	public BigDecimal getIdDoc() {
		return idDoc;
	}

	/**
	 * @param idDoc
	 *            the idDoc to set
	 */
	public void setIdDoc(BigDecimal idDoc) {
		this.idDoc = idDoc;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("  [ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append(", [ idTarea: ").append(this.idTarea).append(" ]");
		result.append(", [ idDoc: ").append(this.idDoc).append(" ]");
		result.append("}");
		return result.toString();
	}

}
