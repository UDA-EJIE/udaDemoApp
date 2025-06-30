package com.ejie.aa79b.model.enums;

/**
 * 
 * @author eaguirresarobe
 *
 */
public enum RequiereTradosEnum {
	NO_REQUIERE_TRADOS(0, "comun.no"), SIN_PROYECTO_TRADOS(1, "comun.sinProyectoTrados"), PDTE_ASIGNACION(2,
			"comun.estadoPendienteAsignacion"), PDTE_EJECUCION(3, "comun.estadoPendienteEjecucion"), PDTE_RETRASADA(4,
					"comun.estadoRetrasada"), TRADOS_EJECUTADA(5, "comun.estadoEjecutada");

	private int value;
	private String label;

	private RequiereTradosEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return this.label;
	}

}
