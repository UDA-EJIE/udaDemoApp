package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum TipoExpedienteGrupoEnum implements Serializable {
	INTERPRETACION(0, "label.tipoExpediente.interpretacion", "IN"),
	TRADUCCION_REVISION(1, "label.tipoExpediente.traduccionRevision", "IT&BE");

	private int value;
	private String label;
	private String code;

	private TipoExpedienteGrupoEnum(int value, String label, String code) {
		this.value = value;
		this.label = label;
		this.code = code;
	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

	public String getCode() {
		return this.code;
	}

}