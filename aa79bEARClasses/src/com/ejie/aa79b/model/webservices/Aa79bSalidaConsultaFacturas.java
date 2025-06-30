package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import com.ejie.aa79b.common.Constants;
import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.ejie.aa79b.model.DatosSalidaWS;
import com.ejie.aa79b.utils.DateUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Aa79bSalidaConsultaFacturas
 * 
 * @author mrodriguez
 */
public class Aa79bSalidaConsultaFacturas extends DatosSalidaWS implements Serializable {

	private static final long serialVersionUID = 3599215055971433114L;

	private Long anyo;
	private Integer numExp;
	private String anyoNumExp;
	private BigDecimal idFactura;
	private BigDecimal codConcatenado;
	private Long fechaEmision;
	private Date fechaEmisionDate;
	private String horaEmision;
	private BigDecimal importeBase;
	private BigDecimal importeIva;
	private BigDecimal importeTotal;
	private BigDecimal tipoIva;
	private Long idEstadoFactura;
	private String descEstadoFactura;

	/**
	 * Constructor
	 */
	public Aa79bSalidaConsultaFacturas() {
		// Constructor
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
	 * @return the anyoNumExp
	 */
	public String getAnyoNumExp() {
		return anyoNumExp;
	}

	/**
	 * @param anyoNumExp the anyoNumExp to set
	 */
	public void setAnyoNumExp(String anyoNumExp) {
		this.anyoNumExp = anyoNumExp;
	}

	/**
	 * @return the idFactura
	 */
	public BigDecimal getIdFactura() {
		return idFactura;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(BigDecimal idFactura) {
		this.idFactura = idFactura;
	}

	public BigDecimal getCodConcatenado() {
		return codConcatenado;
	}

	public void setCodConcatenado(BigDecimal codConcatenado) {
		this.codConcatenado = codConcatenado;
	}

	/**
	 * @return the fechaEmision
	 */
	public Long getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Long fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the importeBase
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteBase() {
		return importeBase;
	}

	/**
	 * @param importeBase the importeBase to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteBase(BigDecimal importeBase) {
		this.importeBase = importeBase;
	}

	/**
	 * @return the importeIva
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteIva() {
		return importeIva;
	}

	/**
	 * @param importeIva the importeIva to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteIva(BigDecimal importeIva) {
		this.importeIva = importeIva;
	}

	/**
	 * @return the importeTotal
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal the importeTotal to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the tipoIva
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getTipoIva() {
		return tipoIva;
	}

	/**
	 * @param tipoIva the tipoIva to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setTipoIva(BigDecimal tipoIva) {
		this.tipoIva = tipoIva;
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
	 * @return the fechaEmisionDate
	 */
	public Date getFechaEmisionDate() {
		return fechaEmisionDate;
	}

	/**
	 * @param fechaEmisionDate the fechaEmisionDate to set
	 */
	public void setFechaEmisionDate(Date fechaEmisionDate) {
		this.fechaEmisionDate = fechaEmisionDate;
	}

	/**
	 * @return the horaEmision
	 */
	public String getHoraEmision() {
		return horaEmision;
	}

	/**
	 * @param horaEmision the horaEmision to set
	 */
	public void setHoraEmision(String horaEmision) {
		this.horaEmision = horaEmision;
	}

	// INICIO - Métodos definidos para obtener la fecha y hora de emisión en el
	// idioma correspondiente al llamar a WS desde AA06
	/**
	 * 
	 * @return String
	 */
	public String getFechaHoraEmisionEu() {
		Locale locale = new Locale(Constants.LANG_EUSKERA);
		return DateUtils.obtFechaHoraFormateada(this.fechaEmisionDate, this.horaEmision, locale);
	}

	/**
	 * 
	 * @return String
	 */
	public String getFechaHoraEmisionEs() {
		Locale locale = new Locale(Constants.LANG_CASTELLANO);
		return DateUtils.obtFechaHoraFormateada(this.fechaEmisionDate, this.horaEmision, locale);
	}
	// FIN - Métodos definidos para obtener la fecha y hora de emisión en el
	// idioma correspondiente al llamar a WS desde AA06

	/**
	 * @return the descEstadoFactura
	 */
	public String getDescEstadoFactura() {
		return descEstadoFactura;
	}

	/**
	 * @param descEstadoFactura the descEstadoFactura to set
	 */
	public void setDescEstadoFactura(String descEstadoFactura) {
		this.descEstadoFactura = descEstadoFactura;
	}

}
