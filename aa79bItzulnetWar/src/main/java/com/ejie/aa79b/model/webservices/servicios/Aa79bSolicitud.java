package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Aa79bSolicitud implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoAplicacion;
	private String referenciaExpediente;
	private AA79bDatosSolicitanteSolicitud datosSolicitante;
	private AA79bDatosFacturacion datosFacturacion;
	private String tipoExpediente;
	private String titulo;
	private String publicarBOPV;
	private String idioma;
	private AA79bDocumento documento;
	private AA79bDocumentoRevisar documentoRevisar;
	private AA79bDocumentoApoyo documentoApoyo;
	private String fechaHoraEntregaSolicitada;
	private Aa79bNotasIZO notasParaIZO;
	private String aceptaLOPD;
	//No modificar este bean

	public String getCodigoAplicacion() {
		return this.codigoAplicacion;
	}
	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}
	public String getReferenciaExpediente() {
		return this.referenciaExpediente;
	}
	public void setReferenciaExpediente(String referenciaExpediente) {
		this.referenciaExpediente = referenciaExpediente;
	}
	public AA79bDatosSolicitanteSolicitud getDatosSolicitante() {
		return this.datosSolicitante;
	}
	public void setDatosSolicitante(AA79bDatosSolicitanteSolicitud datosSolicitante) {
		this.datosSolicitante = datosSolicitante;
	}
	public AA79bDatosFacturacion getDatosFacturacion() {
		return this.datosFacturacion;
	}
	public void setDatosFacturacion(AA79bDatosFacturacion datosFacturacion) {
		this.datosFacturacion = datosFacturacion;
	}
	public String getTipoExpediente() {
		return this.tipoExpediente;
	}
	public void setTipoExpediente(String tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}
	public String getTitulo() {
		return this.titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPublicarBOPV() {
		return this.publicarBOPV;
	}
	public void setPublicarBOPV(String publicarBOPV) {
		this.publicarBOPV = publicarBOPV;
	}
	public String getIdioma() {
		return this.idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public AA79bDocumento getDocumento() {
		return this.documento;
	}
	public void setDocumento(AA79bDocumento documento) {
		this.documento = documento;
	}
	public AA79bDocumentoApoyo getDocumentoApoyo() {
		return this.documentoApoyo;
	}
	public void setDocumentoApoyo(AA79bDocumentoApoyo documentoApoyo) {
		this.documentoApoyo = documentoApoyo;
	}
	public String getFechaHoraEntregaSolicitada() {
		return this.fechaHoraEntregaSolicitada;
	}
	public void setFechaHoraEntregaSolicitada(String fechaHoraEntregaSolicitada) {
		this.fechaHoraEntregaSolicitada = fechaHoraEntregaSolicitada;
	}
	public Aa79bNotasIZO getNotasParaIZO() {
		return this.notasParaIZO;
	}
	public void setNotasParaIZO(Aa79bNotasIZO notasParaIZO) {
		this.notasParaIZO = notasParaIZO;
	}
	public String getAceptaLOPD() {
		return this.aceptaLOPD;
	}
	public void setAceptaLOPD(String aceptaLOPD) {
		this.aceptaLOPD = aceptaLOPD;
	}
	public AA79bDocumentoRevisar getDocumentoRevisar() {
		return this.documentoRevisar;
	}
	public void setDocumentoRevisar(AA79bDocumentoRevisar documentoRevisar) {
		this.documentoRevisar = documentoRevisar;
	}

}
