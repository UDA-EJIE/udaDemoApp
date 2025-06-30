package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDatosFacturacionTramitagune implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoDocumento;
	private String prefijoDocumento;
	private String nifContacto;
	private String sufijoDocumento;
	private String observaciones;

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
	public String getNifContacto() {
		return this.nifContacto;
	}
	public void setNifContacto(String nifContacto) {
		this.nifContacto = nifContacto;
	}
	public String getSufijoDocumento() {
		return this.sufijoDocumento;
	}
	public void setSufijoDocumento(String sufijoDocumento) {
		this.sufijoDocumento = sufijoDocumento;
	}
	public String getObservaciones() {
		return this.observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


}
