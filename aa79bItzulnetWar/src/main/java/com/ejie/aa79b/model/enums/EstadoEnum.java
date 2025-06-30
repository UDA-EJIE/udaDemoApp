package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum EstadoEnum implements Serializable {
	ALTA("A", "estado.alta"), BAJA("B", "estado.baja");

	private String value;
	private String label;

	private EstadoEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

}