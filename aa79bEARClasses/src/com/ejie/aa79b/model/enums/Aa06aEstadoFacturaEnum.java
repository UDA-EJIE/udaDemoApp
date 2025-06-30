package com.ejie.aa79b.model.enums;

import java.io.Serializable;

public enum Aa06aEstadoFacturaEnum implements Serializable {

	// Los estados indicados a continuación cumplen la siguiente equivalencia entre la aplicación W05 y AA06A
	// IdEstadoFacturaW05 | DescEstadoFacturaW05 | idEstadoFacturaAA06A | DescEstadoFacturaAA06A
	// 1 | Pendiente de cobro    | 1 | Pendiente de pago
	// 2 | Cobrado               | 2 | Pagado
	// 4 | Anulado               | 4 | Anulado
	// 5 | Devolución de ingreso | 1 | Pendiente de pago
	// 6 | No anuladas           | 1 | Pendiente de pago
	// 7 | Erroneo               | 1 | Pendiente de pago
	
	
	PENDIENTE_COBRO(1, "label.estadoFactura.pendientePago"), COBRADO(2, "label.estadoFactura.pagado"), ANULADO(4,
			"label.estadoFactura.anulado"), DEVOLUCION_INGRESO(5, "label.estadoFactura.pendientePago"), NO_ANULADAS(6, "label.estadoFactura.pendientePago"), ERRONEO(7,
					"label.estadoFactura.pendientePago");

	private int value;
	private String label;

	private Aa06aEstadoFacturaEnum(int value, String label) {
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
