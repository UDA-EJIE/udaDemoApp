package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * Enum para el tipo peticionario de una solicitud
 * 
 * @author grozadilla
 *
 */
public enum TipoPenalizacionRetrasoEnum implements Serializable {
	POR_HORAS(1, "label.porHoras"), POR_JORNADAS(2, "label.porJornadas");

	private int value;
	private String label;

	private TipoPenalizacionRetrasoEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

}