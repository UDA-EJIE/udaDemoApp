package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum EstadoTareasAsignadasEnum implements Serializable {
	SIN_TAREAS(0, "label.sinTareas"), PENDIENTE_ASIGNACION(1, "comun.estadoPendienteAsignacion"),
	PENDIENTE_ACEPTACION(2, "comun.estadoPendienteAceptacion"), ACEPTADAS(3, "comun.aceptado"),
	RECHAZADAS(4, "comun.estadoRechazada");

	private int value;
	private String label;

	private EstadoTareasAsignadasEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
