package aa79b.util.common;

import java.io.Serializable;

/**
 * 
 * @author mrodriguez
 *
 */
public enum MotivosAnulacionEnum implements Serializable {
	
	OTROS(1), PLAZO_SUBS_EXPIRADO(2), SUBS_RECHAZADA_IZO(3), PRESUP_NO_ACEPTADO_PLAZO(4), 
	FECHA_PROP_NO_ACEPTADA_PLAZO(5), FECHA_PROP_RECHAZADA(6), PRESUPUESTO_RECHAZADO(7);

	private int value;

	private MotivosAnulacionEnum(int value) {
		this.value = value;
		
	}

	public int getValue() {
		return this.value;
	}

}
