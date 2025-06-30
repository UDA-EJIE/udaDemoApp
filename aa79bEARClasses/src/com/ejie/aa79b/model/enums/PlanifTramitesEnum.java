package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum PlanifTramitesEnum implements Serializable {
    PENDIENTES_ACEPTACION(1, "getTramitesPendientesAcepFromWhere"), ACEPTADAS(2,
            "getTramitesAceptadasFromWhere"), RECHAZADAS(3, "getTramitesRechazadasFromWhere");

    private int id;
    private String method;

    private PlanifTramitesEnum(int id, String method) {
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
     * Obtiene un PlanifTramitesEnum por la clave
     * 
     * @param key
     *            la clave
     * @return PlanifTramitesEnum
     * 
     * @author aresua
     */
    public static PlanifTramitesEnum getById(int id) {
        for (PlanifTramitesEnum item : PlanifTramitesEnum.values()) {
            if (id == item.getId()) {
                return item;
            }
        }
        return null;
    }

}