package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum EstadoGestorEnum implements Serializable {
	ALTA("V", "estado.alta"), BAJA("B", "estado.baja");

	private String value;
	private String label;

	private EstadoGestorEnum(String value, String label) {
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
