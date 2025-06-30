package com.ejie.aa79b.model.enums;

public enum AccesoExpedienteEnum {
	ALTA("A"), ESTUDIO("C");

	private String value;

	private AccesoExpedienteEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
