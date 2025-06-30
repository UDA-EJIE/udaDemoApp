package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDatosSolicitanteSolicitud implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoEntidad;
	private String codigoEntidad;
	private String tipoDocumento;
	private String prefijoDocumento;
	private String nifSolicitante;
	private String sufijoDocumento;
	private String esGrupoBoletin;

	public String getTipoEntidad() {
		return this.tipoEntidad;
	}
	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}
	public String getCodigoEntidad() {
		return this.codigoEntidad;
	}
	public void setCodigoEntidad(String codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}
	public String getTipoDocumento() {
		return this.tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getPrefijoDocumento() {
		return this.prefijoDocumento;
	}
	public void setPrefijoDocumento(String prefijoDocumento) {
		this.prefijoDocumento = prefijoDocumento;
	}
	public String getNifSolicitante() {
		return this.nifSolicitante;
	}
	public void setNifSolicitante(String nifSolicitante) {
		this.nifSolicitante = nifSolicitante;
	}
	public String getSufijoDocumento() {
		return this.sufijoDocumento;
	}
	public void setSufijoDocumento(String sufijoDocumento) {
		this.sufijoDocumento = sufijoDocumento;
	}
	public String getEsGrupoBoletin() {
		return this.esGrupoBoletin;
	}
	public void setEsGrupoBoletin(String esGrupoBoletin) {
		this.esGrupoBoletin = esGrupoBoletin;
	}

}
