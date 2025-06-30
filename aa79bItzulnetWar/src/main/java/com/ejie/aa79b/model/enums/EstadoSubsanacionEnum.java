package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum EstadoSubsanacionEnum implements Serializable {

	PENDIENTE(1), ACEPTADO(2), RECHAZADO(3);

	private int value;

	private EstadoSubsanacionEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
