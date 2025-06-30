package com.ejie.aa79b.model;

import java.io.Serializable;

/**
 * DatosSalidaWS
 * @author mrodriguez
 */
public class DatosSalidaWS implements Serializable {
	
	private static final long serialVersionUID = -7129502208017232165L;
	private String estado;
	private String descripcion;
	
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return this.estado;
	}
	
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
	
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { ");
		result.append("[ estado: ").append(this.estado).append(" ]");
		result.append(", [ descripcion: ").append(this.descripcion).append(" ]");
		result.append("}");
		return result.toString();
	}

}
