package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.model.DocumentoTareaAdjunto;
import com.ejie.aa79b.model.IdiomaWS;

/**
 * EntradaNotasExpediente
 * 
 * @author mrodriguez
 */
public class EntradaAdjuntarFicheroWS extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = -89123050353874656L;
	private String dni;
	private Long anyo;
	private Integer numExp;
	private DocumentoTareaAdjunto documentoTareaAdjunto;
	private Integer tipoFichero;
	private BigDecimal idTarea;
	private String idiomaDest;

	/**
	 * @return the dni
	 */
	public String getDni() {
		return this.dni;
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
		return this.anyo;
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
		return this.numExp;
	}

	/**
	 * @param numExp
	 *            the numExp to set
	 */
	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public DocumentoTareaAdjunto getDocumentoTareaAdjunto() {
		return documentoTareaAdjunto;
	}

	public void setDocumentoTareaAdjunto(DocumentoTareaAdjunto documentoTareaAdjunto) {
		this.documentoTareaAdjunto = documentoTareaAdjunto;
	}

	public Integer getTipoFichero() {
		return tipoFichero;
	}

	public void setTipoFichero(Integer tipoFichero) {
		this.tipoFichero = tipoFichero;
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
	 * @return the idiomaDest
	 */
	public String getIdiomaDest() {
		return idiomaDest;
	}

	/**
	 * @param idiomaDest
	 *            the idiomaDest to set
	 */
	public void setIdiomaDest(String idiomaDest) {
		this.idiomaDest = idiomaDest;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ dni: ").append(this.dni).append(" ]");
		result.append(", [ anyo: ").append(this.anyo).append(" ]");
		result.append(", [ numExp: ").append(this.numExp).append(" ]");
		result.append("}");
		return result.toString();
	}

}
