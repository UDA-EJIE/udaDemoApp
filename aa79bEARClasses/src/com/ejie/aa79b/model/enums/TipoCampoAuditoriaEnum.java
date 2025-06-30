package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum TipoCampoAuditoriaEnum implements Serializable {
	CAMPO_VALORACION(1, "label.valoracion"), CAMPO_CONDICION(2, "label.condicion"), CAMPO_TEXTO(3, "label.campo.texto");

	private int value;
	private String label;

	private TipoCampoAuditoriaEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}
