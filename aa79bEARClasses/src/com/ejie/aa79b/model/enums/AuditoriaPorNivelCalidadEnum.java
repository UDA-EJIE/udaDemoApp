package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author eaguirresarobe
 *
 */
public enum AuditoriaPorNivelCalidadEnum implements Serializable {
	NIVEL_CERO(0), NIVEL_UNO(1), NIVEL_TRES(3), NIVEL_CINCO(5);

	private Integer value;

	private AuditoriaPorNivelCalidadEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}
}
