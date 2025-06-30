package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDatosSolicitanteComunicacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoDocumento;
	private String prefijoDocumento;
	private String nifSolicitante;
	private String sufijoDocumento;

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

}