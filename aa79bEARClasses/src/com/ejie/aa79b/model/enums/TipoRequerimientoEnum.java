package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum TipoRequerimientoEnum implements Serializable {
	SUBSANACION(1, "label.subsanacion"), ACEPTACION_PRESUPUESTO(2, "label.aceptacionPresupuesto"), ACEPTACION_FECHA_FIN(
			3,
			"label.aceptacionFechaEntrega"), ACEPTACION_PRESUPUESTO_FECHA_FIN(4, "label.aceptacionPresupuestoYFecha");

	private int value;
	private String label;

	private TipoRequerimientoEnum(int value, String label) {
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