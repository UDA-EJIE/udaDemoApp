package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum TipoFacturacionEnum implements Serializable {
	EURO_INTERPRETE("I", "facturacion.eurointerprete"), EURO_HORA_INTERPRETE("H", "facturacion.eurohorainterprete");

    
	private String value;
	private String label;

	private TipoFacturacionEnum(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

}