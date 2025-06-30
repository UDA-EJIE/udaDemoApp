package com.ejie.aa79b.model.webservices.servicios;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AA79bDatosResponsable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombreApellidos;
	private String email;
	private String telefono;
	private String entidad;
	private String direccion;
	
	public String getNombreApellidos() {
		return this.nombreApellidos;
	}
	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return this.telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEntidad() {
		return this.entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getDireccion() {
		return this.direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
