package com.ejie.aa79b.model;

import java.io.Serializable;
import java.util.List;

/**
 * GestoresRepresentante
 * @author mrodriguez
 */
public class GestoresRepresentante extends DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = -6020668196957425527L;
	private List<Gestor> gestores;

	/**
	 * @return the gestores
	 */
	public List<Gestor> getGestores() {
		return this.gestores;
	}

	/**
	 * @param gestores the gestores to set
	 */
	public void setGestores(List<Gestor> gestores) {
		this.gestores = gestores;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ gestores: ").append(this.gestores).append(" ]");
		result.append("}");
		return result.toString();
	}

}
