package com.ejie.aa79b.model.webservices;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Aa79bAuditoria implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private Integer estado;
	private String estadoDescEs;
	private String estadoDescEu;
	private Long fechaEnvio;
	private Long fechaConfirmacion;
	private BigDecimal idTareaAuditar;
	private String indEnviado;
	private Aa79bTarea tarea = new Aa79bTarea();
	private List<Aa79bAuditoriaSeccionExpediente> lAuditoriaSeccionExpediente = new ArrayList<Aa79bAuditoriaSeccionExpediente>();

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getEstadoDescEs() {
		return estadoDescEs;
	}

	public void setEstadoDescEs(String estadoDescEs) {
		this.estadoDescEs = estadoDescEs;
	}

	public String getEstadoDescEu() {
		return estadoDescEu;
	}

	public void setEstadoDescEu(String estadoDescEu) {
		this.estadoDescEu = estadoDescEu;
	}

	public Long getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Long fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Long getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(Long fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public BigDecimal getIdTareaAuditar() {
		return idTareaAuditar;
	}

	public void setIdTareaAuditar(BigDecimal idTareaAuditar) {
		this.idTareaAuditar = idTareaAuditar;
	}

	public String getIndEnviado() {
		return indEnviado;
	}

	public void setIndEnviado(String indEnviado) {
		this.indEnviado = indEnviado;
	}

	public Aa79bTarea getTarea() {
		return tarea;
	}

	public void setTarea(Aa79bTarea tarea) {
		this.tarea = tarea;
	}

	public List<Aa79bAuditoriaSeccionExpediente> getlAuditoriaSeccionExpediente() {
		return lAuditoriaSeccionExpediente;
	}

	public void setlAuditoriaSeccionExpediente(List<Aa79bAuditoriaSeccionExpediente> lAuditoriaSeccionExpediente) {
		this.lAuditoriaSeccionExpediente = lAuditoriaSeccionExpediente;
	}

}
