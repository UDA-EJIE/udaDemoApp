package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SubidaTarea implements Serializable {

	private static final long serialVersionUID = 1L;
	private Fichero fichero;
	private BigDecimal idDocOriginal;
	private BigDecimal idTarea;
	private Integer idTablaIntermedia;
	private String idIdiomaDest;
	// tarea posttraduccionboe
	private Boolean indFicheroDoc;
	private String indCorrecciones;
	private Integer destinoUpload;

	private String oidDocRevisado;
	private boolean docOriginal;

	public SubidaTarea() {
		// Constructor vacio
	}

	/**
	 * @return the fichero
	 */
	public Fichero getFichero() {
		return fichero;
	}

	/**
	 * @param fichero the fichero to set
	 */
	public void setFichero(Fichero fichero) {
		this.fichero = fichero;
	}

	/**
	 * @return the idDocOriginal
	 */
	public BigDecimal getIdDocOriginal() {
		return idDocOriginal;
	}

	/**
	 * @param idDocOriginal the idDocOriginal to set
	 */
	public void setIdDocOriginal(BigDecimal idDocOriginal) {
		this.idDocOriginal = idDocOriginal;
	}

	/**
	 * @return the idTarea
	 */
	public BigDecimal getIdTarea() {
		return idTarea;
	}

	/**
	 * @param idTarea the idTarea to set
	 */
	public void setIdTarea(BigDecimal idTarea) {
		this.idTarea = idTarea;
	}

	/**
	 * @return the idTablaIntermedia
	 */
	public Integer getIdTablaIntermedia() {
		return idTablaIntermedia;
	}

	/**
	 * @param idTablaIntermedia the idTablaIntermedia to set
	 */
	public void setIdTablaIntermedia(Integer idTablaIntermedia) {
		this.idTablaIntermedia = idTablaIntermedia;
	}

	/**
	 * @return the idIdiomaDest
	 */
	public String getIdIdiomaDest() {
		return idIdiomaDest;
	}

	/**
	 * @param idIdiomaDest the idIdiomaDest to set
	 */
	public void setIdIdiomaDest(String idIdiomaDest) {
		this.idIdiomaDest = idIdiomaDest;
	}

	/**
	 * @return the indFicheroDoc
	 */
	public Boolean getIndFicheroDoc() {
		return indFicheroDoc;
	}

	/**
	 * @param indFicheroDoc the indFicheroDoc to set
	 */
	public void setIndFicheroDoc(Boolean indFicheroDoc) {
		this.indFicheroDoc = indFicheroDoc;
	}

	/**
	 * @return the indCorrecciones
	 */
	public String getIndCorrecciones() {
		return indCorrecciones;
	}

	/**
	 * @param indCorrecciones the indCorrecciones to set
	 */
	public void setIndCorrecciones(String indCorrecciones) {
		this.indCorrecciones = indCorrecciones;
	}

	/**
	 * @return the destinoUpload
	 */
	public Integer getDestinoUpload() {
		return destinoUpload;
	}

	/**
	 * @param destinoUpload the destinoUpload to set
	 */
	public void setDestinoUpload(Integer destinoUpload) {
		this.destinoUpload = destinoUpload;
	}

	/**
	 * @return the oidDocRevisado
	 */
	public String getOidDocRevisado() {
		return oidDocRevisado;
	}

	/**
	 * @param oidDocRevisado the oidDocRevisado to set
	 */
	public void setOidDocRevisado(String oidDocRevisado) {
		this.oidDocRevisado = oidDocRevisado;
	}

	public boolean isDocOriginal() {
		return docOriginal;
	}

	public void setDocOriginal(boolean docOriginal) {
		this.docOriginal = docOriginal;
	}

	@Override
	public String toString() {
		return "SubidaTarea [fichero=" + fichero + ", idDocOriginal=" + idDocOriginal + ", idTarea=" + idTarea
				+ ", idTablaIntermedia=" + idTablaIntermedia + ", idIdiomaDest=" + idIdiomaDest + "]";
	}

}
