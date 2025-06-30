package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author grozadilla
 *
 */
public enum AccionesEnum implements Serializable {
    ALTA(1L, "accion.alta"), MODIFICACION(2L, "accion.modificacion"), BORRADO(3L, "accion.borrado"), MANUAL(4L, "accion.manual");

    private Long value;
    private String label;
    
    private AccionesEnum(Long value, String label) {
        this.value = value;
        this.label = label;
    }

    public Long getValue() {
        return value;
    }
    
    public String getLabel() {
		return label;
	}
    

}