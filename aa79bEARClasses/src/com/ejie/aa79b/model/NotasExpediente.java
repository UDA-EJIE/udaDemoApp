package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * NotasExpediente
 * @author mrodriguez
 */
public class NotasExpediente extends DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = -6733622884073863313L;
	private String observaciones;
	private boolean existeFichero = false;

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return this.observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	/**
	 * @return the existeFichero
	 */
	public boolean isExisteFichero() {
		return this.existeFichero;
	}

	/**
	 * @param existeFichero the existeFichero to set
	 */
	public void setExisteFichero(boolean existeFichero) {
		this.existeFichero = existeFichero;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ observaciones: ").append(this.observaciones).append(" ]");
		result.append(", [ existeFichero: ").append(this.existeFichero).append(" ]");
		result.append("}");
		return result.toString();
	}

}
