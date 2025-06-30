package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum NivelUsuarioEnum implements Serializable {
	NORMAL("N", "nivelUsuario.normal"), VIP("V", "nivelUsuario.vip");

	private String value;
	private String label;

	private NivelUsuarioEnum(String value, String label) {
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