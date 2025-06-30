package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum DashboardCargaTareasEnum implements Serializable {
	PDTES_ACEPTACION(1, ""), RETRASADAS(2, ""), FINALIZAN_HOY(3, ""), FINALIZAN_7_DIAS(4, ""), EN_DIAS_NO_DISPONIBLES(5,
			"");

	private int id;
	private String method;

	private DashboardCargaTareasEnum(int id, String method) {
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
	 * Obtiene un PlanifTareasEnum por la clave
	 * 
	 * @param key
	 *            la clave
	 * @return PlanifTareasEnum
	 * 
	 * @author aresua
	 */
	public static DashboardCargaTareasEnum getById(int id) {
		for (DashboardCargaTareasEnum item : DashboardCargaTareasEnum.values()) {
			if (id == item.getId()) {
				return item;
			}
		}
		return null;
	}

}