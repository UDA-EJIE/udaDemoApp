package aa79b.util.common;

import java.io.Serializable;

/**
 *
 * @author aresua
 *
 */
public enum TipoTareaGestionAsociadaEnum implements Serializable {
	PROYECTO_TRADOS(1, ""),
	TRADUCIR(2, "label.traducir"),
	CORREDACTAR(3, ""),
	REVISAR(4, "label.revisar"),
	NO_CONFORMIDAD_PROVEEDOR(5, ""),
	GESTION_INTERPRETACION(6, ""),
	PREPARAR_INTERPRETACION(7, ""),
	INTERPRETAR(8, "P"),
	NO_CONFORMIDAD_CLIENTE(9, ""),
	POST_TRADUCCION_BOE(10, ""),
	REVISION_PAGO_PROVEEDOR(11, ""),
	INTRODUCCION_DATOS_PAGO_PROVEEDORES(12, ""),
	NOTIFICAR_CORRECCIONES_PROVEEDOR(13, ""),
	ESTUDIAR_SUBSANACION(14, ""),
	ENTREGA_CLIENTE_TRADUCCION(19,""),
	ENTREGA_CLIENTE_REVISION(20,""),
	REVISAR_TRADUCCION(21,""),
	TRAD_ENTREGA_CLIENTE_TRADUCCION(22,""),
	NO_CONFORMIDAD_TRADUCTOR(23, ""),
	REVISAR_TRADUCCION_INTERNA(24, "");



	private int value;
	private String label;

	private TipoTareaGestionAsociadaEnum(int value, String label) {
		this.value = value;
		this.label = label;

	}

	public int getValue() {
		return this.value;
	}
	public String getLabel() {
		return this.label;
	}


}