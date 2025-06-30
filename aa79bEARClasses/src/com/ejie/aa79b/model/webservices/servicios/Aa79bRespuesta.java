package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

public class Aa79bRespuesta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroExpedienteIZO;
	private String numeroRegistro;
	private String referenciaExpediente;
	private Integer codigo;
	private String detalleEuskera;
	private String detalleCastellano;
	private String informacionAdicional;
	private String fechaHoraEntrega;

	public String getNumeroExpedienteIZO() {
		return this.numeroExpedienteIZO;
	}

	public void setNumeroExpedienteIZO(String numeroExpedienteIZO) {
		this.numeroExpedienteIZO = numeroExpedienteIZO;
	}

	public String getNumeroRegistro() {
		return this.numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getReferenciaExpediente() {
		return this.referenciaExpediente;
	}

	public void setReferenciaExpediente(String referenciaExpediente) {
		this.referenciaExpediente = referenciaExpediente;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDetalleEuskera() {
		return this.detalleEuskera;
	}

	public void setDetalleEuskera(String detalleEuskera) {
		this.detalleEuskera = detalleEuskera;
	}

	public String getDetalleCastellano() {
		return this.detalleCastellano;
	}

	public void setDetalleCastellano(String detalleCastellano) {
		this.detalleCastellano = detalleCastellano;
	}

	public String getInformacionAdicional() {
		return this.informacionAdicional;
	}

	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public String getFechaHoraEntrega() {
		return fechaHoraEntrega;
	}

	public void setFechaHoraEntrega(String fechaHoraEntrega) {
		this.fechaHoraEntrega = fechaHoraEntrega;
	}

}
