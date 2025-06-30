package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Aa79bDocumentoExpediente implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal idDoc;
	private Long anyo;
	private Integer numExp;
	private Long claseDocumento;
	private Long tipoDocumento;
	private String titulo;
	private Integer numPalSolic;
	private Integer numPalIzo;
	private String indVisible;
	private String claseDocumentoDesc;
	private String tipoDocumentoDesc;
	private List<Aa79bFicheroDocExp> ficheros = new ArrayList<Aa79bFicheroDocExp>();
	private String estado;
	private boolean llamadaPL = false;

	private Aa79bFicheroDocExp documentoFinal = new Aa79bFicheroDocExp();
	private Aa79bFicheroDocExp documentoOriginalFinal = new Aa79bFicheroDocExp();
	private Aa79bFicheroDocExp justificanteRevision = new Aa79bFicheroDocExp();

	private Aa79bFicheroDocExp documentoOriginalFirmado = new Aa79bFicheroDocExp();
	private Aa79bFicheroDocExp documentoFinalFirmado = new Aa79bFicheroDocExp();

	private String isBopv;

	public BigDecimal getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(BigDecimal idDoc) {
		this.idDoc = idDoc;
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

	public Long getClaseDocumento() {
		return claseDocumento;
	}

	public void setClaseDocumento(Long claseDocumento) {
		this.claseDocumento = claseDocumento;
	}

	public Long getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNumPalSolic() {
		return numPalSolic;
	}

	public void setNumPalSolic(Integer numPalSolic) {
		this.numPalSolic = numPalSolic;
	}

	public Integer getNumPalIzo() {
		return numPalIzo;
	}

	public void setNumPalIzo(Integer numPalIzo) {
		this.numPalIzo = numPalIzo;
	}

	public String getIndVisible() {
		return indVisible;
	}

	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	public String getClaseDocumentoDesc() {
		return claseDocumentoDesc;
	}

	public void setClaseDocumentoDesc(String claseDocumentoDesc) {
		this.claseDocumentoDesc = claseDocumentoDesc;
	}

	public String getTipoDocumentoDesc() {
		return tipoDocumentoDesc;
	}

	public void setTipoDocumentoDesc(String tipoDocumentoDesc) {
		this.tipoDocumentoDesc = tipoDocumentoDesc;
	}

	public List<Aa79bFicheroDocExp> getFicheros() {
		return ficheros;
	}

	public void setFicheros(List<Aa79bFicheroDocExp> ficheros) {
		this.ficheros = ficheros;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the llamadaPL
	 */
	public boolean isLlamadaPL() {
		return llamadaPL;
	}

	/**
	 * @param llamadaPL the llamadaPL to set
	 */
	public void setLlamadaPL(boolean llamadaPL) {
		this.llamadaPL = llamadaPL;
	}

	public Aa79bFicheroDocExp getDocumentoFinal() {
		return documentoFinal;
	}

	public void setDocumentoFinal(Aa79bFicheroDocExp documentoFinal) {
		this.documentoFinal = documentoFinal;
	}

	public Aa79bFicheroDocExp getDocumentoOriginalFinal() {
		return documentoOriginalFinal;
	}

	public void setDocumentoOriginalFinal(Aa79bFicheroDocExp documentoOriginalFinal) {
		this.documentoOriginalFinal = documentoOriginalFinal;
	}

	public Aa79bFicheroDocExp getJustificanteRevision() {
		return justificanteRevision;
	}

	public void setJustificanteRevision(Aa79bFicheroDocExp justificanteRevision) {
		this.justificanteRevision = justificanteRevision;
	}

	/**
	 * @return the documentoOriginalFirmado
	 */
	public Aa79bFicheroDocExp getDocumentoOriginalFirmado() {
		return documentoOriginalFirmado;
	}

	/**
	 * @param documentoOriginalFirmado the documentoOriginalFirmado to set
	 */
	public void setDocumentoOriginalFirmado(Aa79bFicheroDocExp documentoOriginalFirmado) {
		this.documentoOriginalFirmado = documentoOriginalFirmado;
	}

	/**
	 * @return the documentoFinalFirmado
	 */
	public Aa79bFicheroDocExp getDocumentoFinalFirmado() {
		return documentoFinalFirmado;
	}

	/**
	 * @param documentoFinalFirmado the documentoFinalFirmado to set
	 */
	public void setDocumentoFinalFirmado(Aa79bFicheroDocExp documentoFinalFirmado) {
		this.documentoFinalFirmado = documentoFinalFirmado;
	}

	public String getIsBopv() {
		return isBopv;
	}

	public void setIsBopv(String isBopv) {
		this.isBopv = isBopv;
	}

}
