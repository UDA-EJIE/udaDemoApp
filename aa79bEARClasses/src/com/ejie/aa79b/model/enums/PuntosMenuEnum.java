package com.ejie.aa79b.model.enums;

import java.io.Serializable;

/**
 * 
 * @author aresua
 *
 */
public enum PuntosMenuEnum implements Serializable {
	MTO_GESTION_GRUPOS(1L), MTO_CALENDARIO_PERSONAL(2L), MTO_CALENDARIO_SERVICIO(3L), MTO_HORARIO_ATENCION(
			4L), MTO_HORARIO_LABORAL(5L), MTO_LOTES(6L), MTO_ORDEN_PRECIOS(7L), MTO_DATOS_FACTURACION(
					8L), MTO_DATOS_BASICOS(9L), MTO_FORMATOS_FICHERO(10L), MTO_LIBRO_REGISTRO(11L), MTO_TIPOS_TAREA(
							12L), MTO_TIPOS_REVISION(13L), MTO_AYUDA(14L), MTO_TIPOS_INTERPRETACION(
									15L), TRAMITACION_EXPEDIENTES(16L), SOLICITANTES_ALTA(
											17L), PLANIFICACION_EXPEDIENTES(18L), REQUERIR_SUBSANACION(
													19L), CARGA_TRABAJO(20L), CONSULTA_ALBARANES_PROVEDOR(
															21L), CONSULTA_EXPEDIENTES(
																	22L), SERVICIO_FICHEROS_CONFIDENCIALES(
																			23L), SERVICIO_ACT_DATOS_FACTURACION(
																					24L), SERVICIO_CAMBIO_ESTADO_EXP(
																							25L), CLONACION_EXPEDIENTES(
																									26L), CATEGORIZACION_EXPEDIENTES(
																											27L), FIRMA_DOCUMENTOS(
																													28L);

	private Long value;

	private PuntosMenuEnum(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

}