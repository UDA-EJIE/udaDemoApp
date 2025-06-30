package com.ejie.aa79b.model.webservices;

import java.io.Serializable;

import com.ejie.aa79b.model.IdiomaWS;

public class Aa79bInformes extends IdiomaWS implements Serializable {

	private static final long serialVersionUID = 4973075200091250437L;

	private String dni;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
