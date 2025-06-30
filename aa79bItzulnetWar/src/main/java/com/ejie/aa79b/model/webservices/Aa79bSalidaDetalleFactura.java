package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ejie.aa79b.model.DatosSalidaWS;

/**
 * Aa79bSalidaDetalleFactura
 * 
 * @author mrodriguez
 */
public class Aa79bSalidaDetalleFactura extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = -2044805537232197325L;

	private Long idFactura;
	private Long codConcatenado;
	private Long idEstadoFactura;
	private Date fechaEmision;
	private BigDecimal importeBase;
	private BigDecimal importeIva;
	private BigDecimal importeTotal;
	private BigDecimal tipoIva;
	private String tipoExpFact;
	private Aa79bEmpresasProveedoras entidad;
	private Aa79bDatosContacto datosContacto;

	/**
	 * Constructor
	 */
	public Aa79bSalidaDetalleFactura() {
		// Constructor
	}

	/**
	 * @return the idFactura
	 */
	public Long getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getCodConcatenado() {
		return codConcatenado;
	}

	public void setCodConcatenado(Long codConcatenado) {
		this.codConcatenado = codConcatenado;
	}

	/**
	 * @return the idEstadoFactura
	 */
	public Long getIdEstadoFactura() {
		return idEstadoFactura;
	}

	/**
	 * @param idEstadoFactura the idEstadoFactura to set
	 */
	public void setIdEstadoFactura(Long idEstadoFactura) {
		this.idEstadoFactura = idEstadoFactura;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the importeBase
	 */
	public BigDecimal getImporteBase() {
		return importeBase;
	}

	/**
	 * @param importeBase the importeBase to set
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
	 * @param importeIva the importeIva to set
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
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the tipoIva
	 */
	public BigDecimal getTipoIva() {
		return tipoIva;
	}

	/**
	 * @param tipoIva the tipoIva to set
	 */
	public void setTipoIva(BigDecimal tipoIva) {
		this.tipoIva = tipoIva;
	}

	/**
	 * @return the entidad
	 */
	public Aa79bEmpresasProveedoras getEntidad() {
		return entidad;
	}

	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(Aa79bEmpresasProveedoras entidad) {
		this.entidad = entidad;
	}

	/**
	 * @return the datosContacto
	 */
	public Aa79bDatosContacto getDatosContacto() {
		return datosContacto;
	}

	/**
	 * @param datosContacto the datosContacto to set
	 */
	public void setDatosContacto(Aa79bDatosContacto datosContacto) {
		this.datosContacto = datosContacto;
	}

	/**
	 * @return the tipoExpFact
	 */
	public String getTipoExpFact() {
		return tipoExpFact;
	}

	/**
	 * @param tipoExpFact the tipoExpFact to set
	 */
	public void setTipoExpFact(String tipoExpFact) {
		this.tipoExpFact = tipoExpFact;
	}

}
