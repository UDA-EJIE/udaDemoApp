package com.ejie.aa79b.model;

import java.util.Date;

public class InformeInterpretacion extends Informe implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Date fechaIni;
	private String numInterpretesFact;
	private String horasFacturadas;
	private String tipoActoDesc;

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getNumInterpretesFact() {
		return numInterpretesFact;
	}

	public void setNumInterpretesFact(String numInterpretesFact) {
		this.numInterpretesFact = numInterpretesFact;
	}

	public String getHorasFacturadas() {
		return horasFacturadas;
	}

	public void setHorasFacturadas(String horasFacturadas) {
		this.horasFacturadas = horasFacturadas;
	}

	public String getTipoActoDesc() {
		return tipoActoDesc;
	}

	public void setTipoActoDesc(String tipoActoDesc) {
		this.tipoActoDesc = tipoActoDesc;
	}

}
