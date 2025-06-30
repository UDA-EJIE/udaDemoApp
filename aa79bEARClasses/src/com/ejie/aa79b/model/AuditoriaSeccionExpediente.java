package com.ejie.aa79b.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ejie.aa79b.common.JsonNumbersDeserializer;
import com.ejie.aa79b.common.jackson.JsonImporteSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author eaguirresarobe
 *
 */
public class AuditoriaSeccionExpediente implements Serializable {

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
	private List<AuditoriaCampoSeccionExpediente> lCamposSeccion = new ArrayList<AuditoriaCampoSeccionExpediente>();

	/**
	 * Method 'AuditoriaSeccionExpediente'
	 */
	public AuditoriaSeccionExpediente() {
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
	 * @return the idSeccion
	 */
	public Integer getIdSeccion() {
		return idSeccion;
	}

	/**
	 * @param idSeccion the idSeccion to set
	 */
	public void setIdSeccion(Integer idSeccion) {
		this.idSeccion = idSeccion;
	}

	/**
	 * @return the tipoSeccion
	 */
	public Integer getTipoSeccion() {
		return tipoSeccion;
	}

	/**
	 * @param tipoSeccion the tipoSeccion to set
	 */
	public void setTipoSeccion(Integer tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}

	/**
	 * @return the indRespuesta
	 */
	public String getIndRespuesta() {
		return indRespuesta;
	}

	/**
	 * @param indRespuesta the indRespuesta to set
	 */
	public void setIndRespuesta(String indRespuesta) {
		this.indRespuesta = indRespuesta;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the valorMinAprobado
	 */
	public Integer getValorMinAprobado() {
		return valorMinAprobado;
	}

	/**
	 * @param valorMinAprobado the valorMinAprobado to set
	 */
	public void setValorMinAprobado(Integer valorMinAprobado) {
		this.valorMinAprobado = valorMinAprobado;
	}

	/**
	 * @return the valorMinPeligro
	 */
	public Integer getValorMinPeligro() {
		return valorMinPeligro;
	}

	/**
	 * @param valorMinPeligro the valorMinPeligro to set
	 */
	public void setValorMinPeligro(Integer valorMinPeligro) {
		this.valorMinPeligro = valorMinPeligro;
	}

	/**
	 * @return the resulAuditoria
	 */
	@JsonSerialize(using = JsonImporteSerializer.class)
	public BigDecimal getResulAuditoria() {
		return resulAuditoria;
	}

	/**
	 * @param resulAuditoria the resulAuditoria to set
	 */
	@JsonDeserialize(using = JsonNumbersDeserializer.class)
	public void setResulAuditoria(BigDecimal resulAuditoria) {
		this.resulAuditoria = resulAuditoria;
	}

	/**
	 * @return the notaAuditoria
	 */
	public Integer getNotaAuditoria() {
		return notaAuditoria;
	}

	/**
	 * @param notaAuditoria the notaAuditoria to set
	 */
	public void setNotaAuditoria(Integer notaAuditoria) {
		this.notaAuditoria = notaAuditoria;
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
	 * @return the camposSeccionCount
	 */
	public Long getCamposSeccionCount() {
		return camposSeccionCount;
	}

	/**
	 * @param camposSeccionCount the camposSeccionCount to set
	 */
	public void setCamposSeccionCount(Long camposSeccionCount) {
		this.camposSeccionCount = camposSeccionCount;
	}

	/**
	 * @return the lCamposSeccion
	 */
	public List<AuditoriaCampoSeccionExpediente> getlCamposSeccion() {
		return lCamposSeccion;
	}

	/**
	 * @param lCamposSeccion the lCamposSeccion to set
	 */
	public void setlCamposSeccion(List<AuditoriaCampoSeccionExpediente> lCamposSeccion) {
		this.lCamposSeccion = lCamposSeccion;
	}

}
