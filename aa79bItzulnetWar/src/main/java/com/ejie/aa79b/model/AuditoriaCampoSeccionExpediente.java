package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class AuditoriaCampoSeccionExpediente implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idAuditoria;
	private Integer idCampo;
	private Integer tipoCampo;
	private String nombreEu;
	private String indObservaciones;
	private String notaOk;
	private String notaNoOk;
	private BigDecimal porNivel0;
	private BigDecimal porNivel1;
	private BigDecimal porNivel3;
	private BigDecimal porNivel5;
	private String indObligatorio;
	private Integer idSeccionPadre;
	private Integer orden;
	private String observ;
	private Integer nivelCalidad;
	private BigDecimal porNivelCalidad;
	private String indMarcado;

	/**
	 * Method 'AuditoriaCampoSeccionExpediente'
	 */
	public AuditoriaCampoSeccionExpediente() {
		// constructor
	}

	/**
	 * @return the idAuditoria
	 */
	public Long getIdAuditoria() {
		return idAuditoria;
	}

	/**
	 * @param idAuditoria the idAuditoria to set
	 */
	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	/**
	 * @return the idCampo
	 */
	public Integer getIdCampo() {
		return idCampo;
	}

	/**
	 * @param idCampo the idCampo to set
	 */
	public void setIdCampo(Integer idCampo) {
		this.idCampo = idCampo;
	}

	/**
	 * @return the tipoCampo
	 */
	public Integer getTipoCampo() {
		return tipoCampo;
	}

	/**
	 * @param tipoCampo the tipoCampo to set
	 */
	public void setTipoCampo(Integer tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	/**
	 * @return the nombreEu
	 */
	public String getNombreEu() {
		return nombreEu;
	}

	/**
	 * @param nombreEu the nombreEu to set
	 */
	public void setNombreEu(String nombreEu) {
		this.nombreEu = nombreEu;
	}

	/**
	 * @return the indObservaciones
	 */
	public String getIndObservaciones() {
		return indObservaciones;
	}

	/**
	 * @param indObservaciones the indObservaciones to set
	 */
	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	/**
	 * @return the notaOk
	 */
	public String getNotaOk() {
		return notaOk;
	}

	/**
	 * @param notaOk the notaOk to set
	 */
	public void setNotaOk(String notaOk) {
		this.notaOk = notaOk;
	}

	/**
	 * @return the notaNoOk
	 */
	public String getNotaNoOk() {
		return notaNoOk;
	}

	/**
	 * @param notaNoOk the notaNoOk to set
	 */
	public void setNotaNoOk(String notaNoOk) {
		this.notaNoOk = notaNoOk;
	}

	/**
	 * @return the porNivel0
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPorNivel0() {
		return porNivel0;
	}

	/**
	 * @param porNivel0 the porNivel0 to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel0(BigDecimal porNivel0) {
		this.porNivel0 = porNivel0;
	}

	/**
	 * @return the porNivel1
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPorNivel1() {
		return porNivel1;
	}

	/**
	 * @param porNivel1 the porNivel1 to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel1(BigDecimal porNivel1) {
		this.porNivel1 = porNivel1;
	}

	/**
	 * @return the porNivel3
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPorNivel3() {
		return porNivel3;
	}

	/**
	 * @param porNivel3 the porNivel3 to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel3(BigDecimal porNivel3) {
		this.porNivel3 = porNivel3;
	}

	/**
	 * @return the porNivel5
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPorNivel5() {
		return porNivel5;
	}

	/**
	 * @param porNivel5 the porNivel5 to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivel5(BigDecimal porNivel5) {
		this.porNivel5 = porNivel5;
	}

	/**
	 * @return the indObligatorio
	 */
	public String getIndObligatorio() {
		return indObligatorio;
	}

	/**
	 * @param indObligatorio the indObligatorio to set
	 */
	public void setIndObligatorio(String indObligatorio) {
		this.indObligatorio = indObligatorio;
	}

	/**
	 * @return the idSeccionPadre
	 */
	public Integer getIdSeccionPadre() {
		return idSeccionPadre;
	}

	/**
	 * @param idSeccionPadre the idSeccionPadre to set
	 */
	public void setIdSeccionPadre(Integer idSeccionPadre) {
		this.idSeccionPadre = idSeccionPadre;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the observ
	 */
	public String getObserv() {
		return observ;
	}

	/**
	 * @param observ the observ to set
	 */
	public void setObserv(String observ) {
		this.observ = observ;
	}

	/**
	 * @return the nivelCalidad
	 */
	public Integer getNivelCalidad() {
		return nivelCalidad;
	}

	/**
	 * @param nivelCalidad the nivelCalidad to set
	 */
	public void setNivelCalidad(Integer nivelCalidad) {
		this.nivelCalidad = nivelCalidad;
	}

	/**
	 * @return the porNivelCalidad
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getPorNivelCalidad() {
		return porNivelCalidad;
	}

	/**
	 * @param porNivelCalidad the porNivelCalidad to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setPorNivelCalidad(BigDecimal porNivelCalidad) {
		this.porNivelCalidad = porNivelCalidad;
	}

	/**
	 * @return the indMarcado
	 */
	public String getIndMarcado() {
		return indMarcado;
	}

	/**
	 * @param indMarcado the indMarcado to set
	 */
	public void setIndMarcado(String indMarcado) {
		this.indMarcado = indMarcado;
	}

}
