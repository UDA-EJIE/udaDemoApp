package com.ejie.aa79b.model.enums;

public enum EstadoSubsanacionRequeridaEnum {

	PENDIENTE_DE_RESPUESTA("N", "label.pendienteDeRespuesta"), APORTADA("S", "label.aportada");

	private String value;
	private String label;

	private EstadoSubsanacionRequeridaEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

}
