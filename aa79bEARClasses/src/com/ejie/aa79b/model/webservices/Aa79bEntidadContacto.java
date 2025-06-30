package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class Aa79bEntidadContacto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Long anyo;
	private Integer numExp;
	private String anyoNumExpConcatenado;
	private Aa79bDescripcionIdioma facturable;
	private Aa79bDescripcionIdioma pagado;
	private Aa79bDescripcionIdioma iva;
	private Aa79bDescripcionIdioma facturado;
	private BigDecimal numFactura;
	private BigDecimal codConcatenado;
	private Aa79bEntidad entidad;
	private Aa79bContacto contacto;

	public Aa79bEntidadContacto() {
		// Constructor
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the anyo
	 */
	public Long getAnyo() {
		return anyo;
	}

	/**
	 * @param anyo the anyo to set
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
	 * @param numExp the numExp to set
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
	 * @param anyoNumExpConcatenado the anyoNumExpConcatenado to set
	 */
	public void setAnyoNumExpConcatenado(String anyoNumExpConcatenado) {
		this.anyoNumExpConcatenado = anyoNumExpConcatenado;
	}

	/**
	 * @return the facturable
	 */
	public Aa79bDescripcionIdioma getFacturable() {
		return facturable;
	}

	/**
	 * @param facturable the facturable to set
	 */
	public void setFacturable(Aa79bDescripcionIdioma facturable) {
		this.facturable = facturable;
	}

	/**
	 * @return the pagado
	 */
	public Aa79bDescripcionIdioma getPagado() {
		return pagado;
	}

	/**
	 * @param pagado the pagado to set
	 */
	public void setPagado(Aa79bDescripcionIdioma pagado) {
		this.pagado = pagado;
	}

	/**
	 * @return the iva
	 */
	public Aa79bDescripcionIdioma getIva() {
		return iva;
	}

	/**
	 * @param iva the iva to set
	 */
	public void setIva(Aa79bDescripcionIdioma iva) {
		this.iva = iva;
	}

	/**
	 * @return the facturado
	 */
	public Aa79bDescripcionIdioma getFacturado() {
		return facturado;
	}

	/**
	 * @param facturado the facturado to set
	 */
	public void setFacturado(Aa79bDescripcionIdioma facturado) {
		this.facturado = facturado;
	}

	/**
	 * @return the numFactura
	 */
	public BigDecimal getNumFactura() {
		return numFactura;
	}

	/**
	 * @param numFactura the numFactura to set
	 */
	public void setNumFactura(BigDecimal numFactura) {
		this.numFactura = numFactura;
	}

	public BigDecimal getCodConcatenado() {
		return codConcatenado;
	}

	public void setCodConcatenado(BigDecimal codConcatenado) {
		this.codConcatenado = codConcatenado;
	}

	/**
	 * @return the entidad
	 */
	public Aa79bEntidad getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(Aa79bEntidad entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the contacto
	 */
	public Aa79bContacto getContacto() {
		return contacto;
	}

	/**
	 * @param contacto the contacto to set
	 */
	public void setContacto(Aa79bContacto contacto) {
		this.contacto = contacto;
	}

}
