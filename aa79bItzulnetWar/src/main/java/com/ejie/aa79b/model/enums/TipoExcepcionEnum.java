package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum TipoExcepcionEnum implements Serializable {
	PORENTIDAD("0", "label.excepcion.porEntidadSolicitante"), PORSOLICITANTE("1",
			"label.excepcion.porContactoSolicitante");

	private String value;
	private String label;

	private TipoExcepcionEnum(String value, String label) {
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