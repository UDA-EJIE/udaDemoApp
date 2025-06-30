package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * Enum para el tipo peticionario de una solicitud
 * 
 * @author grozadilla
 *
 */
public enum TipoPeticionarioEnum implements Serializable {
	ADMIN_PUBLICA("A", "label.administracionPublica"), PARTICULAR("P", "label.particular");

	private String value;
	private String label;

	private TipoPeticionarioEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

}