package com.ejie.aa79b.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ejie.aa79b.common.Constants;

public class Informe implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String proveedorOIzo;
	private Long anyo;
	private String mes;
	private String tipoInforme;
	private Integer numExp;
	private String bopv;
	private String sede;
	private String tipoExpediente;
	private String solicitante;
	private String tipoSolicitante;
	private String contacto;
	private String contactoDni;
	private String tarea;
	private String codAsignadoA;
	private String asignadoA;
	private Date fechaSolicitud;
	private Date fechaFin;
	private Date fechaEntregaReal;
	private Date fechaPrevista;
	private Date fechaEjecTarea;
	private String terminado;
	private String tipoTraduccion;
	private String tipoTraduccion0;
	private BigDecimal ingreso;
	private String iva;
	private BigDecimal importeConIVA;
	private BigDecimal codTarea;
	private String rechazado;
	private String motivoRechazo;
	private String anulado;
	private String motivoAnulado;
	private String titulo;
	private String indPublicarBopv;

	public String getProveedorOIzo() {
		return this.proveedorOIzo;
	}

	public void setProveedorOIzo(String proveedorOIzo) {
		this.proveedorOIzo = proveedorOIzo;
	}

	public Long getAnyo() {
		return this.anyo;
	}

	public void setAnyo(Long anyo) {
		this.anyo = anyo;
	}

	public String getMes() {
		return this.mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getTipoInforme() {
		return this.tipoInforme;
	}

	public void setTipoInforme(String tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public Integer getNumExp() {
		return this.numExp;
	}

	public void setNumExp(Integer numExp) {
		this.numExp = numExp;
	}

	public String getBopv() {
		return this.bopv;
	}

	public void setBopv(String bopv) {
		this.bopv = bopv;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getTipoExpediente() {
		return this.tipoExpediente;
	}

	public void setTipoExpediente(String tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	public String getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getTipoSolicitante() {
		return this.tipoSolicitante;
	}

	public void setTipoSolicitante(String tipoSolicitante) {
		this.tipoSolicitante = tipoSolicitante;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getContactoDni() {
		return this.contactoDni;
	}

	public void setContactoDni(String contactoDni) {
		this.contactoDni = contactoDni;
	}

	public String getTarea() {
		return this.tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getCodAsignadoA() {
		return this.codAsignadoA;
	}

	public void setCodAsignadoA(String codAsignadoA) {
		this.codAsignadoA = codAsignadoA;
	}

	public String getAsignadoA() {
		return this.asignadoA;
	}

	public void setAsignadoA(String asignadoA) {
		this.asignadoA = asignadoA;
	}

	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaEntregaReal() {
		return this.fechaEntregaReal;
	}

	public void setFechaEntregaReal(Date fechaEntregaReal) {
		this.fechaEntregaReal = fechaEntregaReal;
	}

	public Date getFechaPrevista() {
		return this.fechaPrevista;
	}

	public void setFechaPrevista(Date fechaPrevista) {
		this.fechaPrevista = fechaPrevista;
	}

	public Date getFechaEjecTarea() {
		return this.fechaEjecTarea;
	}

	public void setFechaEjecTarea(Date fechaEjecTarea) {
		this.fechaEjecTarea = fechaEjecTarea;
	}

	public String getTerminado() {
		return this.terminado;
	}

	public void setTerminado(String terminado) {
		this.terminado = terminado;
	}

	public String getTipoTraduccion() {
		return this.tipoTraduccion;
	}

	public void setTipoTraduccion(String tipoTraduccion) {
		this.tipoTraduccion = tipoTraduccion;
	}

	public String getTipoTraduccion0() {
		return this.tipoTraduccion0;
	}

	public void setTipoTraduccion0(String tipoTraduccion0) {
		this.tipoTraduccion0 = tipoTraduccion0;
	}

	public BigDecimal getIngreso() {
		return this.ingreso;
	}

	public void setIngreso(BigDecimal ingreso) {
		this.ingreso = ingreso;
	}

	public String getIva() {
		return this.iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public BigDecimal getImporteConIVA() {
		return this.importeConIVA;
	}

	public void setImporteConIVA(BigDecimal importeConIVA) {
		this.importeConIVA = importeConIVA;
	}

	public BigDecimal getCodTarea() {
		return this.codTarea;
	}

	public void setCodTarea(BigDecimal codTarea) {
		this.codTarea = codTarea;
	}

	public String getRechazado() {
		return this.rechazado;
	}

	public void setRechazado(String rechazado) {
		this.rechazado = rechazado;
	}

	public String getMotivoRechazo() {
		return this.motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public String getAnulado() {
		return this.anulado;
	}

	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}

	public String getMotivoAnulado() {
		return this.motivoAnulado;
	}

	public void setMotivoAnulado(String motivoAnulado) {
		this.motivoAnulado = motivoAnulado;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIndPublicarBopv() {
		return this.indPublicarBopv;
	}

	public void setIndPublicarBopv(String indPublicarBopv) {
		this.indPublicarBopv = indPublicarBopv;
	}

	public String getIndPublicarBopvIdioma(String idioma) {
		if (idioma.equalsIgnoreCase(Constants.LANG_CASTELLANO)) {
			if(this.getIndPublicarBopv().equalsIgnoreCase("S")) {
				return "SÍ";
			}else {
				return "NO";
			}
		}else {
			if(this.getIndPublicarBopv().equalsIgnoreCase("S")) {
				return "BAI";
			}else {
				return "EZ";
			}
		}
	}
}
