package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum DashboardEstudioExpedEnum implements Serializable {
	EN_ESTUDIO(1, "getExpPendienteEntregarFromWhere"), EN_ESTUDIO_Y_PDTE_TRAMIT_CLTE(2,
			"getExpHoyFromWhere"), PDTES_ANULACION(3, "getExpSieteDiasFromWhere");

	private int id;
	private String method;

	private DashboardEstudioExpedEnum(int id, String method) {
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
	 * Obtiene un DashboardEstudioExpedEnum por la clave
	 * 
	 * @param key
	 *            la clave
	 * @return DashboardEstudioExpedEnum
	 * 
	 * @author javarona
	 */
	public static DashboardEstudioExpedEnum getById(int id) {
		for (DashboardEstudioExpedEnum item : DashboardEstudioExpedEnum.values()) {
			if (id == item.getId()) {
				return item;
			}
		}
		return null;
	}

}