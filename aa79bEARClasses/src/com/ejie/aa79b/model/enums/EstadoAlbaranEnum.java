package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoAlbaranEnum implements Serializable {
	PENDIENTE_ASOCIAR_ALBARAN(1,"comun.estadoAlbaranPdteAsociar"), 
	PENDIENTE_ENVIAR_IZO(2,"comun.estadoAlbaranPdteEnviar"), 
	ENVIADO_IZO(3,"comun.estadoAlbaranEnviado"),
	PAGADO(4,"comun.estadoAlbaranPagado");

	private int value;
	private String label;

	private EstadoAlbaranEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}
	
	public String getLabel() {
		return label;
	}
}