package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum EstadoEFacturaEnum implements Serializable {
	SIN_INICIAR_PROCESO(1, "label.estadoEFactura.sinIniciarProceso"), PROCESO_INCOMPLETO(2,
			"label.estadoEFactura.procesoIncompleto"), ENVIADA_WS(3, "label.estadoEFactura.enviadaWS"), ENVIADA_IZENPE(
					4, "label.estadoEFactura.enviadaIzenpe"), ENVIADA_MANUAL(5,
							"label.estadoEFactura.enviadaManual"), REALIZADA_FACTURA_E(1,
									"label.estadoEFactura.realizadaFacturaE"), ERROR_ENVIAR(2,
											"label.estadoEFactura.errorEnviar"), ENVIADA_PORTAL(3,
													"label.estadoEFactura.enviadaPortal"), TASAS(4,
															"label.estadoEFactura.tasas"), FACTURA_RECHAZADA(5,
																	"label.estadoEFactura.facturaRechazada");

	private int value;
	private String label;

	private EstadoEFacturaEnum(int value, String label) {
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