package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bContacto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aa79bDireccionNora dirNoraContacto;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;

	public Aa79bContacto() {
		// Constructor
	}

	/**
	 * @return the dirNoraContacto
	 */
	public Aa79bDireccionNora getDirNoraContacto() {
		return dirNoraContacto;
	}

	/**
	 * @param dirNoraContacto the dirNoraContacto to set
	 */
	public void setDirNoraContacto(Aa79bDireccionNora dirNoraContacto) {
		this.dirNoraContacto = dirNoraContacto;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getApellidos() {
		final StringBuilder apellidos = new StringBuilder();
		if (this.apellido1 != null) {
			apellidos.append(this.apellido1);
			if (this.apellido2 != null) {
				apellidos.append(" ");
			}
		}
		if (this.apellido2 != null) {
			apellidos.append(this.apellido2);
		}
		return apellidos.toString();
	}

}
