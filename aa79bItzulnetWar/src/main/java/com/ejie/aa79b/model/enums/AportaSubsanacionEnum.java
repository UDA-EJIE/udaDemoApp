package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum AportaSubsanacionEnum implements Serializable {
	SI("S", "comun.si"), NO("N", "comun.no"), NO_REQUERIDA("NR", "comun.noRequerida");

	private String value;
	private String label;

	private AportaSubsanacionEnum(String value, String label) {
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