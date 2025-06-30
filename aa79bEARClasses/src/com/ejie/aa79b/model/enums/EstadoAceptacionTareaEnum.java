package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoAceptacionTareaEnum implements Serializable {
	SIN_TAREAS(0, "sinTarea", "comun.sinTareas"),
	PENDIENTE_ASIGNACION(1, "estadoPendienteAsignacion", "comun.estadoPendienteAsignacion"),
	PENDIENTE_ACEPTACION(2, "estadoPendienteAceptacion", "comun.estadoPendienteAceptacion"),
	ACEPTADA(3, "estadoAceptadaPdteEjecucion", "comun.estadoAceptada"),
	RECHAZADA(4, "estadoRechazada", "comun.estadoRechazada");

	private int value;
	private String css;
	private String label;

	private EstadoAceptacionTareaEnum(int value, String css, String label) {
		this.value = value;
		this.css = css;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getCss() {
		return css;
	}

	public String getLabel() {
		return label;
	}

}