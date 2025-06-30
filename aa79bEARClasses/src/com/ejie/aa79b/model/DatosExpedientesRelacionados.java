package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * DatosExpedientesRelacionados
 * @author mrodriguez
 */
public class DatosExpedientesRelacionados extends DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = -5727053391646054923L;
	private ListaExpedientesRelacionados expedientesRelacionados = new ListaExpedientesRelacionados();

	/**
	 * @return the expedientesRelacionados
	 */
	public ListaExpedientesRelacionados getExpedientesRelacionados() {
		return this.expedientesRelacionados;
	}

	/**
	 * @param expedientesRelacionados the expedientesRelacionados to set
	 */
	public void setExpedientesRelacionados(ListaExpedientesRelacionados expedientesRelacionados) {
		this.expedientesRelacionados = expedientesRelacionados;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ expedientesRelacionados: ").append(this.expedientesRelacionados).append(" ]");
		result.append("}");
		return result.toString();
	}
	
}
