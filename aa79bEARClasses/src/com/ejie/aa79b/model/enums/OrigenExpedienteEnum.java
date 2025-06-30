package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum OrigenExpedienteEnum implements Serializable {
	OFICIO("O"), SOLICITANTE("S"), WEB_SERVICE("W");

	private String value;

	private OrigenExpedienteEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}