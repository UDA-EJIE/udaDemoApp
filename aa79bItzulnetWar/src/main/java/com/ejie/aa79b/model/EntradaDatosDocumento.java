package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.ejie.aa79b.model.webservices.Aa79bFicheroDocExp;

/**
 * EntradaReceptorAutorizado
 *
 * @author mrodriguez
 */
public class EntradaDatosDocumento extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 3353343144070742523L;
	private String dni;
	private Long anyo;
	private Integer numExp;
	private List<Aa79bFicheroDocExp> listaFicheros;
	private String idsFichero;
	private BigDecimal idTarea;
	private BigDecimal idDoc;
	private boolean auditoria;

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

	public List<Aa79bFicheroDocExp> getListaFicheros() {
		return this.listaFicheros;
	}

	public void setListaFicheros(List<Aa79bFicheroDocExp> listIdsFichero) {
		this.listaFicheros = listIdsFichero;
	}

	public String getIdsFichero() {
		return this.idsFichero;
	}

	public void setIdsFichero(String idsFichero) {
		this.idsFichero = idsFichero;
	}

	@Override
	public String toString() {
		return "EntradaDatosDocumento [dni=" + this.dni + ", anyo=" + this.anyo + ", numExp=" + this.numExp
				+ ", idsFichero=" + this.idsFichero + "]";
	}

	public BigDecimal getIdTarea() {
		return this.idTarea;
	}

	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	public BigDecimal getIdDoc() {
		return this.idDoc;
	}

	public void setIdDoc(BigDecimal idDoc) {
		this.idDoc = idDoc;
	}

	/**
	 * @return the auditoria
	 */
	public boolean isAuditoria() {
		return auditoria;
	}

	/**
	 * @param auditoria the auditoria to set
	 */
	public void setAuditoria(boolean auditoria) {
		this.auditoria = auditoria;
	}

}
