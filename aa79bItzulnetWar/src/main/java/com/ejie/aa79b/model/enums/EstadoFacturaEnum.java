package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoFacturaEnum implements Serializable {
	ERRONEO(7, "label.estadoFactura.erroneo"), PENDIENTE_COBRO(1, "label.estadoFactura.pendienteCobro"), COBRADO(2,
			"label.estadoFactura.cobrado"), ANULADA(4, "label.estadoFactura.anulada"), DEVOLUCION_INGRESO(5,
					"label.estadoFactura.devolucionDeIngreso"), NO_ANULADA(6, "label.estadoFactura.noanulada");

	private int value;
	private String label;

	private EstadoFacturaEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}

}