package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 *
 * @author grozadilla
 *
 */
public enum TipoPlantillaEnum implements Serializable {
	FACTURA_INTERPRETACION(1), FACTURA_TRADUCCION(2), MANUAL_PROV_TRADUCTOR_EU(3), MANUAL_PROV_INTERPRETACION_EU(
			4), MANUAL_PROV_CONSULTA_ALBARAN_EU(5), MANUAL_SOLICITANTE_EU(6), MANUAL_FACTURACION_SOLICITANTE_EU(
					7), MANUAL_PROV_TRADUCTOR_ES(8), MANUAL_PROV_INTERPRETACION_ES(9), MANUAL_PROV_CONSULTA_ALBARAN_ES(
							10), MANUAL_SOLICITANTE_ES(11), MANUAL_FACTURACION_SOLICITANTE_ES(12);


	private int value;

	private TipoPlantillaEnum(int value) {
		this.value = value;

	}

	public int getValue() {
		return this.value;
	}

}