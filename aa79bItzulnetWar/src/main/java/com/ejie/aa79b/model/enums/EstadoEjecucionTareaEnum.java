package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoEjecucionTareaEnum implements Serializable {
	PENDIENTE_EJECUCION(1,"estadoAceptadaPdteEjecucion","comun.estadoPendienteEjecucion"), 
	RETRASADA(2,"estadoRetrasada","comun.estadoRetrasada"), 
	EJECUTADA(3,"estadoEjecutada","comun.estadoEjecutada");

	private int value;
	private String css;
	private String label;

	private EstadoEjecucionTareaEnum(int value, String css, String label) {
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