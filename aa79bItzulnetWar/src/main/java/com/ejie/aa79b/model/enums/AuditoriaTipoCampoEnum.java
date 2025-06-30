package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author eaguirresarobe
 *
 */
public enum AuditoriaTipoCampoEnum implements Serializable {
	VALORACION(1), CONDICION(2);

	private Integer value;

	private AuditoriaTipoCampoEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
