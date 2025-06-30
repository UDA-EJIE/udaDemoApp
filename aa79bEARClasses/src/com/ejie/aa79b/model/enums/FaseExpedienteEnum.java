package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum FaseExpedienteEnum implements Serializable {
	ALTA(1), PENDIENTE_ESTUDIO(2), ESTUDIO_EXPEDIENTE(3), PDTE_TRAM_SUBSANACION(4),  PDTE_PROYECTO_TRADOS(6), PDTE_TRAM_CLIENTE(8), PDTE_EJECT_TAREAS(
					9), PDTE_REV_FACTURACION(10), RECHAZADO(11), ANULADO(12), CERRADO(13), FINALIZADO(14);

	private int value;

	private FaseExpedienteEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}