package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Aa79bAuditoriaSeccionExpediente implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idAuditoria;
	private Integer idSeccion;
	private Integer tipoSeccion;
	private String indRespuesta;
	private String nombre;
	private String indObservaciones;
	private Integer orden;
	private Integer valorMinAprobado;
	private Integer valorMinPeligro;
	private BigDecimal resulAuditoria;
	private Integer notaAuditoria;
	private String observ;
	private Long camposSeccionCount;
	private List<Aa79bAuditoriaCampoSeccionExpediente> lCamposSeccion = new ArrayList<Aa79bAuditoriaCampoSeccionExpediente>();

	/**
	 * Method 'Aa79bAuditoriaSeccionExpediente'
	 */
	public Aa79bAuditoriaSeccionExpediente() {
		// constructor
	}

	public Long getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Integer getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	public Integer getTipoSeccion() {
		return tipoSeccion;
	}

	public void setTipoSeccion(Integer tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}

	public String getIndRespuesta() {
		return indRespuesta;
	}

	public void setIndRespuesta(String indRespuesta) {
		this.indRespuesta = indRespuesta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIndObservaciones() {
		return indObservaciones;
	}

	public void setIndObservaciones(String indObservaciones) {
		this.indObservaciones = indObservaciones;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getValorMinAprobado() {
		return valorMinAprobado;
	}

	public void setValorMinAprobado(Integer valorMinAprobado) {
		this.valorMinAprobado = valorMinAprobado;
	}

	public Integer getValorMinPeligro() {
		return valorMinPeligro;
	}

	public void setValorMinPeligro(Integer valorMinPeligro) {
		this.valorMinPeligro = valorMinPeligro;
	}

	public BigDecimal getResulAuditoria() {
		return resulAuditoria;
	}

	public void setResulAuditoria(BigDecimal resulAuditoria) {
		this.resulAuditoria = resulAuditoria;
	}

	public Integer getNotaAuditoria() {
		return notaAuditoria;
	}

	public void setNotaAuditoria(Integer notaAuditoria) {
		this.notaAuditoria = notaAuditoria;
	}

	public String getObserv() {
		return observ;
	}

	public void setObserv(String observ) {
		this.observ = observ;
	}

	public Long getCamposSeccionCount() {
		return camposSeccionCount;
	}

	public void setCamposSeccionCount(Long camposSeccionCount) {
		this.camposSeccionCount = camposSeccionCount;
	}

	public List<Aa79bAuditoriaCampoSeccionExpediente> getlCamposSeccion() {
		return lCamposSeccion;
	}

	public void setlCamposSeccion(List<Aa79bAuditoriaCampoSeccionExpediente> lCamposSeccion) {
		this.lCamposSeccion = lCamposSeccion;
	}

}
