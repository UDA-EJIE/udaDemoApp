package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum AplicacionOrigenExpediente implements Serializable {
	P43("P43"), U78B("U78B");

	private String value;

	private AplicacionOrigenExpediente(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
