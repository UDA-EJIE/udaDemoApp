package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bContactoDoc implements Serializable {
	private static final long serialVersionUID = 1L;
    private String persona;
    private String email;
    private String telefono;
    private String direccion;
    private String entidad;
    
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
}
