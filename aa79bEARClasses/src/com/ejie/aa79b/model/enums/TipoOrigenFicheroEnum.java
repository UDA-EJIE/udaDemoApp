package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum TipoOrigenFicheroEnum implements Serializable {

	INTERNO("I"), EXTERNO("E");

	private String value;

	private TipoOrigenFicheroEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}