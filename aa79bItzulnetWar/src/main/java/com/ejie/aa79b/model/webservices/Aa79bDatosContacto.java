package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

public class Aa79bDatosContacto implements Serializable {

	private static final long serialVersionUID = 7257990955352678159L;
	private String dni;
	private String nombreApellidos;
	private String telfijo;
	private String telmovil;
	private String email;

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the nombreApellidos
	 */
	public String getNombreApellidos() {
		return nombreApellidos;
	}

	/**
	 * @param nombreApellidos
	 *            the nombreApellidos to set
	 */
	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	/**
	 * @return the telfijo
	 */
	public String getTelfijo() {
		return telfijo;
	}

	/**
	 * @param telfijo
	 *            the telfijo to set
	 */
	public void setTelfijo(String telfijo) {
		this.telfijo = telfijo;
	}

	/**
	 * @return the telmovil
	 */
	public String getTelmovil() {
		return telmovil;
	}

	/**
	 * @param telmovil
	 *            the telmovil to set
	 */
	public void setTelmovil(String telmovil) {
		this.telmovil = telmovil;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Aa79bDatosContacto [dni=" + dni + ", nombreApellidos=" + nombreApellidos + ", telfijo=" + telfijo
				+ ", telmovil=" + telmovil + ", email=" + email + "]";
	}

}
