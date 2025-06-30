package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author eaguirresarobe
 *
 */
public enum AuditoriaTipoSeccionEnum implements Serializable {
	VALORACION(1), CONDICION(2), LIBRE(3);

	private Integer value;

	private AuditoriaTipoSeccionEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
