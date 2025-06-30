package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum TipoRecursoEnum implements Serializable {
	INTERNO("I", "label.interno"), EXTERNO("P", "label.externo");

	private String value;
	private String label;

	private TipoRecursoEnum(String value, String label) {
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