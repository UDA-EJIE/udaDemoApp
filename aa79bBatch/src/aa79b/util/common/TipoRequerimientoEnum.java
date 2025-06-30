package aa79b.util.common;

import java.io.Serializable;

public enum TipoRequerimientoEnum implements Serializable {
	
	SUBSANACION(1), 
	ACEPTACION_PRESUPUESTO(2), 
	ACEPTACION_FECHA_FIN(3), 
	ACEPTACION_PRESUPUESTO_FECHA_FIN(4);

	private int value;

	private TipoRequerimientoEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
