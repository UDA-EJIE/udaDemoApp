package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum TipoTareaGestionAsociadaEnum implements Serializable {
	PROYECTO_TRADOS(1, "label.proyectoTrados"), TRADUCIR(2, "label.traducir"), CORREDACTAR(3, "label.corredactar"),
	REVISAR(4, "label.revisar"), NO_CONFORMIDAD_PROVEEDOR(5, "label.noConformidadProveedor"),
	GESTION_INTERPRETACION(6, "label.gestionInterpretacion"), PREPARAR_INTERPRETACION(7, ""),
	INTERPRETAR(8, "label.prepararInterpretacion"), NO_CONFORMIDAD_CLIENTE(9, "label.noConformidadCliente"),
	POST_TRADUCCION_BOE(10, "label.postTraduccionBoe"), REVISION_PAGO_PROVEEDOR(11, "label.revisionPagoProveedor"),
	INTRODUCCION_DATOS_PAGO_PROVEEDORES(12, "label.introduccionDatosPagoProveedores"),
	NOTIFICAR_CORRECCIONES_PROVEEDOR(13, "label.notificarCorreccionesProveedor"),
	ESTUDIAR_SUBSANACION(14, "label.estudiarSubsanacion"),
	ENTREGA_CLIENTE_TRADUCCION(19, "label.entregaClienteTraduccion"),
	ENTREGA_CLIENTE_REVISION(20, "label.entregaClienteRevision"), REVISAR_TRADUCCION(21, "label.revisarTraduccion"),
	TRAD_ENTREGA_CLIENTE_TRADUCCION(22, "label.tradEntregaClienteTraduccion"),
	NO_CONFORMIDAD_TRADUCTOR(23, "label.noConformidadTraductor"),
	REVISAR_TRADUCCION_INTERNA(24, "label.revisarTraduccionInterna");

	private int value;
	private String label;

	private TipoTareaGestionAsociadaEnum(int value, String label) {
		this.value = value;
		this.label = label;

	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

}