package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum DashboardPlanifExpedientesEnum implements Serializable {
	PENDIENTES_ENTREGA(1, "getExpPendienteEntregarFromWhere"), HOY(2, "getExpHoyFromWhere"), SIETE_DIAS(3,
			"getExpSieteDiasFromWhere"), SIN_PLANIFICAR(4, "getExpSinPlanificarFromWhere");

	private int id;
	private String method;

	private DashboardPlanifExpedientesEnum(int id, String method) {
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
	public static DashboardPlanifExpedientesEnum getById(int id) {
		for (DashboardPlanifExpedientesEnum item : DashboardPlanifExpedientesEnum.values()) {
			if (id == item.getId()) {
				return item;
			}
		}
		return null;
	}

}