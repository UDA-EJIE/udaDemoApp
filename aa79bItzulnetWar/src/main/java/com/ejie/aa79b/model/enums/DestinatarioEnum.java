package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * Enum para el tipo peticionario de una solicitud
 * 
 * @author grozadilla
 *
 */
public enum DestinatarioEnum implements Serializable {
	SOLICITANTE(1, "label.solicitante")
	, PERSONAL_IZO(2, "label.personalIzo")
	, PROVEEDOR(3, "label.proveedor");
	
	private Integer value;
	private String label;

	private DestinatarioEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

}