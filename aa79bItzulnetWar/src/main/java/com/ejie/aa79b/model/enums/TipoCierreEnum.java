package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum TipoCierreEnum implements Serializable {
	FINALIZADO(1), RECHAZADO(2), ANULADO(3);

	private int value;

	private TipoCierreEnum(int value) {
		this.value = value;

	}

	public int getValue() {
		return this.value;
	}

}
