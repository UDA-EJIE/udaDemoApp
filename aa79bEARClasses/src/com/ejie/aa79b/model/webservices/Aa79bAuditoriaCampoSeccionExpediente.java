package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;

public class Aa79bAuditoriaCampoSeccionExpediente implements Serializable {

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
	 * Method 'Aa79bAuditoriaCampoSeccionExpediente'
	 */
	public Aa79bAuditoriaCampoSeccionExpediente() {
		// constructor
	}

	public Long getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Integer getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(Integer idCampo) {
		this.idCampo = idCampo;
	}

	public Integer getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(Integer tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public String getNombreEu() {
		return nombreEu;
	}

	public void setNombreEu(String nombreEu) {
		this.nombreEu = nombreEu;
	}

	public String getIndObservaciones() {
		return indObservaciones;
	}

	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	public String getNotaOk() {
		return notaOk;
	}

	public void setNotaOk(String notaOk) {
		this.notaOk = notaOk;
	}

	public String getNotaNoOk() {
		return notaNoOk;
	}

	public void setNotaNoOk(String notaNoOk) {
		this.notaNoOk = notaNoOk;
	}

	public BigDecimal getPorNivel0() {
		return porNivel0;
	}

	public void setPorNivel0(BigDecimal porNivel0) {
		this.porNivel0 = porNivel0;
	}

	public BigDecimal getPorNivel1() {
		return porNivel1;
	}

	public void setPorNivel1(BigDecimal porNivel1) {
		this.porNivel1 = porNivel1;
	}

	public BigDecimal getPorNivel3() {
		return porNivel3;
	}

	public void setPorNivel3(BigDecimal porNivel3) {
		this.porNivel3 = porNivel3;
	}

	public BigDecimal getPorNivel5() {
		return porNivel5;
	}

	public void setPorNivel5(BigDecimal porNivel5) {
		this.porNivel5 = porNivel5;
	}

	public String getIndObligatorio() {
		return indObligatorio;
	}

	public void setIndObligatorio(String indObligatorio) {
		this.indObligatorio = indObligatorio;
	}

	public Integer getIdSeccionPadre() {
		return idSeccionPadre;
	}

	public void setIdSeccionPadre(Integer idSeccionPadre) {
		this.idSeccionPadre = idSeccionPadre;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getObserv() {
		return observ;
	}

	public void setObserv(String observ) {
		this.observ = observ;
	}

	public Integer getNivelCalidad() {
		return nivelCalidad;
	}

	public void setNivelCalidad(Integer nivelCalidad) {
		this.nivelCalidad = nivelCalidad;
	}

	public BigDecimal getPorNivelCalidad() {
		return porNivelCalidad;
	}

	public void setPorNivelCalidad(BigDecimal porNivelCalidad) {
		this.porNivelCalidad = porNivelCalidad;
	}

	public String getIndMarcado() {
		return indMarcado;
	}

	public void setIndMarcado(String indMarcado) {
		this.indMarcado = indMarcado;
	}

}
