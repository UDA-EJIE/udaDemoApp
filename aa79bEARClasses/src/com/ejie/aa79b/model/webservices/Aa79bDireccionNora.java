package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.DireccionNora;

public class Aa79bDireccionNora implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private DireccionNora direccion;

	/**
	 * @return the direccion
	 */
	public DireccionNora getDireccion() {
		return this.direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(DireccionNora direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Intended only for logging and debugging.
	 * 
	 * Here, the contents of every main field are placed into the result.
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName()).append(" Object { " ); 
		result.append("[ direccion: ").append(this.direccion).append(" ]");
		result.append("}");
		
		return result.toString();
	}
	
}
