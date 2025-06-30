package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ejie.aa79b.model.webservices.Aa79bExpedienteRelacionado;

/**
 * ListaExpedientesRelacionados
 * @author mrodriguez
 */
public class ListaExpedientesRelacionados implements Serializable {
	
	private static final long serialVersionUID = -512303526893895767L;
	private List<Aa79bExpedienteRelacionado> expedienteRelacionado = new ArrayList<Aa79bExpedienteRelacionado>();
	
	/**
	 * @return the expedienteRelacionado
	 */
	public List<Aa79bExpedienteRelacionado> getExpedienteRelacionado() {
		return this.expedienteRelacionado;
	}


	/**
	 * @param expedienteRelacionado the expedienteRelacionado to set
	 */
	public void setExpedienteRelacionado(List<Aa79bExpedienteRelacionado> expedienteRelacionado) {
		this.expedienteRelacionado = expedienteRelacionado;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ expedienteRelacionado: ").append(this.expedienteRelacionado).append(" ]");
		result.append("}");
		return result.toString();
	}

}
