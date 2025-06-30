package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum PlanifTareasEnum implements Serializable {
    SIN_ASIGNAR(1, "getTareasSinAsignarFromWhere"), ASIGNADAS(2, "getTareasAsignadasFromWhere"), FINALIZAN_HOY(3,
            "getTareasFinalizanHoyFromWhere"), RETRASADAS(4, "getTareasRetrasadasFromWhere");

    private int id;
    private String method;

    private PlanifTareasEnum(int id, String method) {
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
    public static PlanifTareasEnum getById(int id) {
        for (PlanifTareasEnum item : PlanifTareasEnum.values()) {
            if (id == item.getId()) {
                return item;
            }
        }
        return null;
    }

}