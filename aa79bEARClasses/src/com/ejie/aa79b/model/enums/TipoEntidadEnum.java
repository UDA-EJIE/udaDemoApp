package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 * 
 */
public enum TipoEntidadEnum implements Serializable {
	ENTIDAD("B", "label.tipoEntidad.entidad"), DEPARTAMENTO("E", "label.tipoEntidad.departamento"), EMPRESA("L",
			"label.tipoEntidad.empresa");

	private String value;
	private String label;

	private TipoEntidadEnum(String value, String label) {
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