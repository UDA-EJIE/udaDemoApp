package com.ejie.aa79b.model.enums;

import java.io.Serializable;

import com.ejie.aa79b.common.Constants;

public enum ModoDetalleExpedienteEnum implements Serializable {

	VENTANA_NUEVA(Constants.CERO), PESTANYA(Constants.UNO);

	private Integer value;

	private ModoDetalleExpedienteEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

}