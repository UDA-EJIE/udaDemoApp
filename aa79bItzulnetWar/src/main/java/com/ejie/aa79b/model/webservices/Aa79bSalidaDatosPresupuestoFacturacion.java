package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ejie.aa79b.model.DatosSalidaWS;
import com.ejie.aa79b.model.IdiomaWS;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bSalidaDatosPresupuestoFacturacion extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long anyo;
	private Integer numExp;
	private String anyoNumExpConcatenado;
	private Aa79bDescripcionIdioma tipoExpediente;
	private Date fechaSolicitud;
	private String horaSolicitud;
	private Integer idRequerimiento;
	private IdiomaWS idioma;
	private BigDecimal porcIva;
	private BigDecimal importeBase;
	private BigDecimal importeIva;
	private BigDecimal importeTotal;
	private List<Aa79bEntidadContacto> listaEntidadContacto;
	/* tradrev */
	private Aa79bDatosPresupYFactTradRev datosTradRev;
	/* tradrev */
	/* interpretacion */
	private Aa79bDatosPresupYFactInter datosInter;
	/* interpretacion */

	public Aa79bSalidaDatosPresupuestoFacturacion() {
		// Constructor
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
	 * @return the anyoNumExpConcatenado
	 */
	public String getAnyoNumExpConcatenado() {
		this.setAnyoNumExpConcatenado(null);
		if (this.anyo != null && this.numExp != null) {
			this.setAnyoNumExpConcatenado(
					String.valueOf(this.anyo).substring(2) + "/" + String.format("%06d", this.numExp));
		}
		return anyoNumExpConcatenado;
	}

	/**
	 * @param anyoNumExpConcatenado
	 *            the anyoNumExpConcatenado to set
	 */
	public void setAnyoNumExpConcatenado(String anyoNumExpConcatenado) {
		this.anyoNumExpConcatenado = anyoNumExpConcatenado;
	}

	/**
	 * @return the tipoExpediente
	 */
	public Aa79bDescripcionIdioma getTipoExpediente() {
		return tipoExpediente;
	}

	/**
	 * @param tipoExpediente
	 *            the tipoExpediente to set
	 */
	public void setTipoExpediente(Aa79bDescripcionIdioma tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	/**
	 * @return the fechaSolicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * @param fechaSolicitud
	 *            the fechaSolicitud to set
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * @return the horaSolicitud
	 */
	public String getHoraSolicitud() {
		return horaSolicitud;
	}

	/**
	 * @param horaSolicitud
	 *            the horaSolicitud to set
	 */
	public void setHoraSolicitud(String horaSolicitud) {
		this.horaSolicitud = horaSolicitud;
	}

	/**
	 * @return the idRequerimiento
	 */
	public Integer getIdRequerimiento() {
		return idRequerimiento;
	}

	/**
	 * @param idRequerimiento
	 *            the idRequerimiento to set
	 */
	public void setIdRequerimiento(Integer idRequerimiento) {
		this.idRequerimiento = idRequerimiento;
	}

	/**
	 * @return the idioma
	 */
	public IdiomaWS getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma
	 *            the idioma to set
	 */
	public void setIdioma(IdiomaWS idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the porcIva
	 */
	public BigDecimal getPorcIva() {
		return porcIva;
	}

	/**
	 * @param porcIva
	 *            the porcIva to set
	 */
	public void setPorcIva(BigDecimal porcIva) {
		this.porcIva = porcIva;
	}

	/**
	 * @return the importeBase
	 */
	public BigDecimal getImporteBase() {
		return importeBase;
	}

	/**
	 * @param importeBase
	 *            the importeBase to set
	 */
	public void setImporteBase(BigDecimal importeBase) {
		this.importeBase = importeBase;
	}

	/**
	 * @return the importeIva
	 */
	public BigDecimal getImporteIva() {
		return importeIva;
	}

	/**
	 * @param importeIva
	 *            the importeIva to set
	 */
	public void setImporteIva(BigDecimal importeIva) {
		this.importeIva = importeIva;
	}

	/**
	 * @return the importeTotal
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal
	 *            the importeTotal to set
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the listaEntidadContacto
	 */
	public List<Aa79bEntidadContacto> getListaEntidadContacto() {
		return listaEntidadContacto;
	}

	/**
	 * @param listaEntidadContacto
	 *            the listaEntidadContacto to set
	 */
	public void setListaEntidadContacto(List<Aa79bEntidadContacto> listaEntidadContacto) {
		this.listaEntidadContacto = listaEntidadContacto;
	}

	/**
	 * @return the datosTradRev
	 */
	public Aa79bDatosPresupYFactTradRev getDatosTradRev() {
		return datosTradRev;
	}

	/**
	 * @param datosTradRev
	 *            the datosTradRev to set
	 */
	public void setDatosTradRev(Aa79bDatosPresupYFactTradRev datosTradRev) {
		this.datosTradRev = datosTradRev;
	}

	/**
	 * @return the datosInter
	 */
	public Aa79bDatosPresupYFactInter getDatosInter() {
		return datosInter;
	}

	/**
	 * @param datosInter
	 *            the datosInter to set
	 */
	public void setDatosInter(Aa79bDatosPresupYFactInter datosInter) {
		this.datosInter = datosInter;
	}

}
