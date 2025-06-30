package com.ejie.aa79b.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParametrosEmail implements java.io.Serializable {

	private static final long serialVersionUID = -821040244549232064L;

	private Map<String, String> infoEu = new LinkedHashMap<String, String>();
	private Map<String, String> infoEs = new LinkedHashMap<String, String>();
	private String anyoNumExpediente;
	private String numTrabajo;

	private int tipoAviso;
	List<String> listaDestinatarios = new ArrayList<String>();

	public Map<String, String> getInfoEu() {
		return this.infoEu;
	}

	public void setInfoEu(Map<String, String> infoEu) {
		this.infoEu = infoEu;
	}

	public Map<String, String> getInfoEs() {
		return this.infoEs;
	}

	public void setInfoEs(Map<String, String> infoEs) {
		this.infoEs = infoEs;
	}

	public String getAnyoNumExpediente() {
		return this.anyoNumExpediente;
	}

	public void setAnyoNumExpediente(String anyoNumExpediente) {
		this.anyoNumExpediente = anyoNumExpediente;
	}

	public int getTipoAviso() {
		return this.tipoAviso;
	}

	public void setTipoAviso(int tipoAviso) {
		this.tipoAviso = tipoAviso;
	}

	public List<String> getListaDestinatarios() {
		return this.listaDestinatarios;
	}

	public void setListaDestinatarios(List<String> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	public String getNumTrabajo() {
		return this.numTrabajo;
	}

	public void setNumTrabajo(String numTrabajo) {
		this.numTrabajo = numTrabajo;
	}

}
