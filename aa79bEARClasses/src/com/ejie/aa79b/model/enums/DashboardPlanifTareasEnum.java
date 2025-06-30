package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author javarona
 *
 */
public enum DashboardPlanifTareasEnum implements Serializable {
	SIN_ASIGNAR_HOY(1, "getTareasSinAsignarFromWhere"), SIN_ASIGNAR(2, "getTareasSinAsignarFromWhere"), ASIGNADAS(3,
			"getTareasAsignadasFromWhere"), FINALIZAN_HOY(4,
					"getTareasFinalizanHoyFromWhere"), RETRASADAS(5, "getTareasRetrasadasFromWhere");

	private int id;
	private String method;

	private DashboardPlanifTareasEnum(int id, String method) {
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
	public static DashboardPlanifTareasEnum getById(int id) {
		for (DashboardPlanifTareasEnum item : DashboardPlanifTareasEnum.values()) {
			if (id == item.getId()) {
				return item;
			}
		}
		return null;
	}

}