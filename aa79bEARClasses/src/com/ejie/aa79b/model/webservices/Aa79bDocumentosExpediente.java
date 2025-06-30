package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Aa79bDocumentosExpediente implements Serializable {

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
	private String claseDocumentoDescEs;
	private String tipoDocumentoDescEs;
	private String claseDocumentoDescEu;
	private String tipoDocumentoDescEu;

	private List<Aa79bFicheroDocExp> ficheros = new ArrayList<Aa79bFicheroDocExp>();
	private List<Aa79bTradosExp> tradosExp;

	private boolean llamadaPL = false;

	public Aa79bDocumentosExpediente() {
		// Constructor
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
	 * @return the claseDocumento
	 */
	public Long getClaseDocumento() {
		return claseDocumento;
	}

	/**
	 * @param claseDocumento
	 *            the claseDocumento to set
	 */
	public void setClaseDocumento(Long claseDocumento) {
		this.claseDocumento = claseDocumento;
	}

	/**
	 * @return the tipoDocumento
	 */
	public Long getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the numPalSolic
	 */
	public Integer getNumPalSolic() {
		return numPalSolic;
	}

	/**
	 * @param numPalSolic
	 *            the numPalSolic to set
	 */
	public void setNumPalSolic(Integer numPalSolic) {
		this.numPalSolic = numPalSolic;
	}

	/**
	 * @return the numPalIzo
	 */
	public Integer getNumPalIzo() {
		return numPalIzo;
	}

	/**
	 * @param numPalIzo
	 *            the numPalIzo to set
	 */
	public void setNumPalIzo(Integer numPalIzo) {
		this.numPalIzo = numPalIzo;
	}

	/**
	 * @return the indVisible
	 */
	public String getIndVisible() {
		return indVisible;
	}

	/**
	 * @param indVisible
	 *            the indVisible to set
	 */
	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}

	/**
	 * @return the claseDocumentoDesc
	 */
	public String getClaseDocumentoDesc() {
		return claseDocumentoDesc;
	}

	/**
	 * @param claseDocumentoDesc
	 *            the claseDocumentoDesc to set
	 */
	public void setClaseDocumentoDesc(String claseDocumentoDesc) {
		this.claseDocumentoDesc = claseDocumentoDesc;
	}

	/**
	 * @return the tipoDocumentoDesc
	 */
	public String getTipoDocumentoDesc() {
		return tipoDocumentoDesc;
	}

	/**
	 * @param tipoDocumentoDesc
	 *            the tipoDocumentoDesc to set
	 */
	public void setTipoDocumentoDesc(String tipoDocumentoDesc) {
		this.tipoDocumentoDesc = tipoDocumentoDesc;
	}

	/**
	 * @return the claseDocumentoDescEs
	 */
	public String getClaseDocumentoDescEs() {
		return claseDocumentoDescEs;
	}

	/**
	 * @param claseDocumentoDescEs
	 *            the claseDocumentoDescEs to set
	 */
	public void setClaseDocumentoDescEs(String claseDocumentoDescEs) {
		this.claseDocumentoDescEs = claseDocumentoDescEs;
	}

	/**
	 * @return the tipoDocumentoDescEs
	 */
	public String getTipoDocumentoDescEs() {
		return tipoDocumentoDescEs;
	}

	/**
	 * @param tipoDocumentoDescEs
	 *            the tipoDocumentoDescEs to set
	 */
	public void setTipoDocumentoDescEs(String tipoDocumentoDescEs) {
		this.tipoDocumentoDescEs = tipoDocumentoDescEs;
	}

	/**
	 * @return the claseDocumentoDescEu
	 */
	public String getClaseDocumentoDescEu() {
		return claseDocumentoDescEu;
	}

	/**
	 * @param claseDocumentoDescEu
	 *            the claseDocumentoDescEu to set
	 */
	public void setClaseDocumentoDescEu(String claseDocumentoDescEu) {
		this.claseDocumentoDescEu = claseDocumentoDescEu;
	}

	/**
	 * @return the tipoDocumentoDescEu
	 */
	public String getTipoDocumentoDescEu() {
		return tipoDocumentoDescEu;
	}

	/**
	 * @param tipoDocumentoDescEu
	 *            the tipoDocumentoDescEu to set
	 */
	public void setTipoDocumentoDescEu(String tipoDocumentoDescEu) {
		this.tipoDocumentoDescEu = tipoDocumentoDescEu;
	}

	/**
	 * @return the ficheros
	 */
	public List<Aa79bFicheroDocExp> getFicheros() {
		return ficheros;
	}

	/**
	 * @param ficheros
	 *            the ficheros to set
	 */
	public void setFicheros(List<Aa79bFicheroDocExp> ficheros) {
		this.ficheros = ficheros;
	}

	/**
	 * @return the tradosExp
	 */
	public List<Aa79bTradosExp> getTradosExp() {
		return tradosExp;
	}

	/**
	 * @param tradosExp
	 *            the tradosExp to set
	 */
	public void setTradosExp(List<Aa79bTradosExp> tradosExp) {
		this.tradosExp = tradosExp;
	}

	/**
	 * @return the llamadaPL
	 */
	public boolean isLlamadaPL() {
		return llamadaPL;
	}

	/**
	 * @param llamadaPL
	 *            the llamadaPL to set
	 */
	public void setLlamadaPL(boolean llamadaPL) {
		this.llamadaPL = llamadaPL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Aa79bDocumentosExpediente [idDoc=" + idDoc + ", anyo=" + anyo + ", numExp=" + numExp
				+ ", claseDocumento=" + claseDocumento + ", tipoDocumento=" + tipoDocumento + ", titulo=" + titulo
				+ ", numPalSolic=" + numPalSolic + ", numPalIzo=" + numPalIzo + ", indVisible=" + indVisible
				+ ", claseDocumentoDesc=" + claseDocumentoDesc + ", tipoDocumentoDesc=" + tipoDocumentoDesc
				+ ", claseDocumentoDescEs=" + claseDocumentoDescEs + ", tipoDocumentoDescEs=" + tipoDocumentoDescEs
				+ ", claseDocumentoDescEu=" + claseDocumentoDescEu + ", tipoDocumentoDescEu=" + tipoDocumentoDescEu
				+ ", ficheros=" + ficheros + ", tradosExp=" + tradosExp + ", llamadaPL=" + llamadaPL + "]";
	}

}
