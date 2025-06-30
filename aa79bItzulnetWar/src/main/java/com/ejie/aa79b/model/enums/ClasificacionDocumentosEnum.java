package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum ClasificacionDocumentosEnum implements Serializable {
	TRADUCCION(1L, "label.traduccion"), REVISION(2L, "label.revision"), APOYO(3L, "label.referencia"), TRABAJO(4L,
			"label.trabajo");

	private Long value;
	private String label;

	private ClasificacionDocumentosEnum(Long value, String label) {
		this.value = value;
		this.label = label;
	}

	public Long getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

}