package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.ejie.aa79b.common.jackson.JsonMonedaSerializer;
import com.ejie.x38.serialization.JsonDateDeserializer;
import com.ejie.x38.serialization.JsonDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author mrodriguez
 *
 */
public class DatosFacturacionExpedienteInterpretacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numInterpretes;
	private String horasInterpretacion;
	private Date fechaPago;
	private BigDecimal importeBase;
	private BigDecimal importeIva;
	private BigDecimal importeTotal;
	private BigDecimal tipoIva;
	private BigDecimal tipoTotal;
	private EstadoFactura estadoFactura;
	private EstadoFacturaE estadoFacturaE;
	private EstadoIncidenciaFactura estadoIncidenciaFactura;
	private EstadoErrorFactura estadoErrorFactura;
	private Boolean cae;
	private BigDecimal precioMinimoInterpRealiz;
	private BigDecimal precioHoraInterprete;
	private BigDecimal precioJornadaCompleta;
	private BigDecimal precioMediaJornada;
	private BigDecimal precioHoraFraccAdic;
	private Integer jornadaCompletaHorasDesde;
	private Integer jornadaCompletaHorasHasta;
	private Integer mediaJornadaHorasDesde;
	private Integer mediaJornadaHorasHasta;
	private String indRevisado;
	private BigDecimal porcentajeFacturacion;
	private String tipoFacturacion;

	public DatosFacturacionExpedienteInterpretacion() {
		// Constructor
	}

	/**
	 * @return the numInterpretes
	 */
	public Integer getNumInterpretes() {
		return this.numInterpretes;
	}

	/**
	 * @param numInterpretes the numInterpretes to set
	 */
	public void setNumInterpretes(Integer numInterpretes) {
		this.numInterpretes = numInterpretes;
	}

	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getFechaPago() {
		return this.fechaPago;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the horasInterpretacion
	 */
	public String getHorasInterpretacion() {
		return this.horasInterpretacion;
	}

	/**
	 * @param horasInterpretacion the horasInterpretacion to set
	 */
	public void setHorasInterpretacion(String horasInterpretacion) {
		this.horasInterpretacion = horasInterpretacion;
	}

	/**
	 * @return the importeBase
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getImporteBase() {
		return this.importeBase;
	}

	/**
	 * @return the importeBase
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteBaseEur() {
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
		return this.importeIva;
	}

	/**
	 * @return the importeIvaEur
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteIvaEur() {
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
		return this.importeTotal;
	}

	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getImporteTotalEur() {
		return this.importeTotal;
	}

	/**
	 * @param importeTotal the importeTotal to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public EstadoFactura getEstadoFactura() {
		return this.estadoFactura;
	}

	public void setEstadoFactura(EstadoFactura estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

	public EstadoFacturaE getEstadoFacturaE() {
		return this.estadoFacturaE;
	}

	public void setEstadoFacturaE(EstadoFacturaE estadoFacturaE) {
		this.estadoFacturaE = estadoFacturaE;
	}

	public EstadoIncidenciaFactura getEstadoIncidenciaFactura() {
		return this.estadoIncidenciaFactura;
	}

	public void setEstadoIncidenciaFactura(EstadoIncidenciaFactura estadoIncidenciaFactura) {
		this.estadoIncidenciaFactura = estadoIncidenciaFactura;
	}

	public EstadoErrorFactura getEstadoErrorFactura() {
		return this.estadoErrorFactura;
	}

	public void setEstadoErrorFactura(EstadoErrorFactura estadoErrorFactura) {
		this.estadoErrorFactura = estadoErrorFactura;
	}

	/**
	 * @return the tipoIva
	 */
	public BigDecimal getTipoIva() {
		return this.tipoIva;
	}

	/**
	 * @param tipoIva the tipoIva to set
	 */
	public void setTipoIva(BigDecimal tipoIva) {
		this.tipoIva = tipoIva;
	}

	/**
	 * @return the tipoTotal
	 */
	public BigDecimal getTipoTotal() {
		return this.tipoTotal;
	}

	/**
	 * @param tipoTotal the tipoTotal to set
	 */
	public void setTipoTotal(BigDecimal tipoTotal) {
		this.tipoTotal = tipoTotal;
	}

	/**
	 * @return the cae
	 */
	public Boolean getCae() {
		return this.cae;
	}

	/**
	 * @param cae the cae to set
	 */
	public void setCae(Boolean cae) {
		this.cae = cae;
	}

	/**
	 * @return the precioMinimoInterpRealiz
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPrecioMinimoInterpRealiz() {
		return this.precioMinimoInterpRealiz;
	}

	/**
	 * @return the precioMinimoInterpRealiz
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getPrecioMinimoInterpRealizEur() {
		return precioMinimoInterpRealiz;
	}

	/**
	 * @param precioMinimoInterpRealiz the precioMinimoInterpRealiz to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPrecioMinimoInterpRealiz(BigDecimal precioMinimoInterpRealiz) {
		this.precioMinimoInterpRealiz = precioMinimoInterpRealiz;
	}

	/**
	 * @return the precioHoraInterprete
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPrecioHoraInterprete() {
		return this.precioHoraInterprete;
	}

	/**
	 * @return the precioHoraInterprete
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getPrecioHoraInterpreteEur() {
		return precioHoraInterprete;
	}

	/**
	 * @param precioHoraInterprete the precioHoraInterprete to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPrecioHoraInterprete(BigDecimal precioHoraInterprete) {
		this.precioHoraInterprete = precioHoraInterprete;
	}

	/**
	 * @return the precioJornadaCompleta
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPrecioJornadaCompleta() {
		return this.precioJornadaCompleta;
	}

	/**
	 * @return the precioJornadaCompleta
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getPrecioJornadaCompletaEur() {
		return precioJornadaCompleta;
	}

	/**
	 * @param precioJornadaCompleta the precioJornadaCompleta to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPrecioJornadaCompleta(BigDecimal precioJornadaCompleta) {
		this.precioJornadaCompleta = precioJornadaCompleta;
	}

	/**
	 * @return the precioMediaJornada
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPrecioMediaJornada() {
		return this.precioMediaJornada;
	}

	/**
	 * @return the precioMediaJornada
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getPrecioMediaJornadaEur() {
		return precioMediaJornada;
	}

	/**
	 * @param precioMediaJornada the precioMediaJornada to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPrecioMediaJornada(BigDecimal precioMediaJornada) {
		this.precioMediaJornada = precioMediaJornada;
	}

	/**
	 * @return the precioHoraFraccAdic
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPrecioHoraFraccAdic() {
		return this.precioHoraFraccAdic;
	}

	/**
	 * @return the precioHoraFraccAdic
	 */
	@JsonSerialize(using = JsonMonedaSerializer.class)
	public BigDecimal getPrecioHoraFraccAdicEur() {
		return precioHoraFraccAdic;
	}

	/**
	 * @param precioHoraFraccAdic the precioHoraFraccAdic to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPrecioHoraFraccAdic(BigDecimal precioHoraFraccAdic) {
		this.precioHoraFraccAdic = precioHoraFraccAdic;
	}

	/**
	 * @return the jornadaCompletaHorasDesde
	 */
	public Integer getJornadaCompletaHorasDesde() {
		return this.jornadaCompletaHorasDesde;
	}

	/**
	 * @param jornadaCompletaHorasDesde the jornadaCompletaHorasDesde to set
	 */
	public void setJornadaCompletaHorasDesde(Integer jornadaCompletaHorasDesde) {
		this.jornadaCompletaHorasDesde = jornadaCompletaHorasDesde;
	}

	/**
	 * @return the jornadaCompletaHorasHasta
	 */
	public Integer getJornadaCompletaHorasHasta() {
		return this.jornadaCompletaHorasHasta;
	}

	/**
	 * @param jornadaCompletaHorasHasta the jornadaCompletaHorasHasta to set
	 */
	public void setJornadaCompletaHorasHasta(Integer jornadaCompletaHorasHasta) {
		this.jornadaCompletaHorasHasta = jornadaCompletaHorasHasta;
	}

	/**
	 * @return the mediaJornadaHorasDesde
	 */
	public Integer getMediaJornadaHorasDesde() {
		return this.mediaJornadaHorasDesde;
	}

	/**
	 * @param mediaJornadaHorasDesde the mediaJornadaHorasDesde to set
	 */
	public void setMediaJornadaHorasDesde(Integer mediaJornadaHorasDesde) {
		this.mediaJornadaHorasDesde = mediaJornadaHorasDesde;
	}

	/**
	 * @return the mediaJornadaHorasHasta
	 */
	public Integer getMediaJornadaHorasHasta() {
		return this.mediaJornadaHorasHasta;
	}

	/**
	 * @param mediaJornadaHorasHasta the mediaJornadaHorasHasta to set
	 */
	public void setMediaJornadaHorasHasta(Integer mediaJornadaHorasHasta) {
		this.mediaJornadaHorasHasta = mediaJornadaHorasHasta;
	}

	/**
	 * @return the indRevisado
	 */
	public String getIndRevisado() {
		return this.indRevisado;
	}

	/**
	 * @param indRevisado the indRevisado to set
	 */
	public void setIndRevisado(String indRevisado) {
		this.indRevisado = indRevisado;
	}

	/**
	 * @return the porcentajeFacturacion
	 */
	public BigDecimal getPorcentajeFacturacion() {
		return porcentajeFacturacion;
	}

	/**
	 * @param porcentajeFacturacion the porcentajeFacturacion to set
	 */
	public void setPorcentajeFacturacion(BigDecimal porcentajeFacturacion) {
		this.porcentajeFacturacion = porcentajeFacturacion;
	}

	/**
	 * 
	 * @return String
	 */
	public String getTipoFacturacion() {
		return tipoFacturacion;
	}

	/**
	 * 
	 * @param tipoFacturacion String
	 */
	public void setTipoFacturacion(String tipoFacturacion) {
		this.tipoFacturacion = tipoFacturacion;
	}

}
