package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoExpedienteEnum implements Serializable {
	ALTA_EXPEDIENTE(1), EN_ESTUDIO(2), EN_CURSO(3), RECHAZADO(4), ANULADO(5), CERRADO(6), FINALIZADO(7);

	private int value;

	private EstadoExpedienteEnum(int value) {
		this.value = value;
		
	}

	public int getValue() {
		return value;
	}
	
}