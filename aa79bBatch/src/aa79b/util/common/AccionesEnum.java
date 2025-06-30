package aa79b.util.common;

import java.io.Serializable;

/**
 * 
 * @author mrodriguez
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
        return this.value;
    }
    
    public String getLabel() {
		return this.label;
	}
    

}
