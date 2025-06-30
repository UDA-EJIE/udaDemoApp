package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum PlanifExpedientesEnum implements Serializable {
	PENDIENTES_ENTREGA(1, "getExpPendienteEntregarFromWhere"), HOY(2, "getExpHoyFromWhere"), SIETE_DIAS(3,
			"getExpSieteDiasFromWhere"), NO_CONFORMIDAD(4, "getExpNoConformidadFromWhere"), SIN_PLANIFICAR(5,
					"getExpSinPlanificarFromWhere"), SIN_ASIGNADOR(6, "getExpSinAsignadorFromWhere");

	private int id;
	private String method;

	private PlanifExpedientesEnum(int id, String method) {
		this.id = id;
		this.method = method;
	}

	public int getId() {
		return id;
	}

	public String getMethod() {
		return method;
	}

	/**
	 * Obtiene un PlanifExpedientesEnum por la clave
	 * 
	 * @param key
	 *            la clave
	 * @return PlanifExpedientesEnum
	 * 
	 * @author aresua
	 */
	public static PlanifExpedientesEnum getById(int id) {
		for (PlanifExpedientesEnum item : PlanifExpedientesEnum.values()) {
			if (id == item.getId()) {
				return item;
			}
		}
		return null;
	}

}