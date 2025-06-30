package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum TipoJornadaEnum implements Serializable {
	
	VACACIONES("V"),
	FORMACION("F"),
	BAJA("B"),
	OTROS("O"),
	INTERPRETACION("I");
	

	private String value;

	private TipoJornadaEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}