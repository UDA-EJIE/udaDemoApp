package com.ejie.aa79b.model.enums;

public enum FiltroPlanificacionEnum {
	PENDIENTE_ENTREGA(1L,"filtro.expediente.pendienteEntrega","E"), 
	ENTREGA_HOY(2L,"filtro.expediente.entregaHoy","E"), 
	ENTREGA_7_DIAS(3L,"filtro.expediente.entrega7dias","E"), 
	NO_CONFORMIDAD_PDTE_IZO(4L,"filtro.expediente.noConformidad","E"), 
	SIN_PLANIFICAR(5L,"filtro.expediente.sinPlanificar","E"), 
	SIN_ASIGNADOR(6L,"filtro.expediente.sinAsignador","E"),
	SIN_ASIGNAR_EXPEDIENTE_7_DIAS(7L,"filtro.tarea.sinAsignar","T"), 
	ASIGNADA_NO_DISPONIBLE(8L,"filtro.tarea.asignadaNoDisponible","T"), 
	DEBEN_FINALIZAR(9L,"filtro.tarea.debenFinalizar","T"), 
	RETRASADAS(10L,"filtro.tarea.retrasadas","T"), 
	PENDIENTE_ACEPTACION(11L,"filtro.tramite.pendienteAceptacion","T"), 
	ACEPTADO(12L,"filtro.tramite.aceptado","T"), 
	RECHAZADO(13L,"filtro.tramite.rechazado","T"); 

	private Long value;
	private String label;
	private String tipoRecurso;

	private FiltroPlanificacionEnum(Long value, String label, String tipoRecurso) {
		this.value = value;
		this.label = label;
		this.tipoRecurso = tipoRecurso;
	}

	public Long getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}
	
	public String getTipoRecurso() {
		return this.tipoRecurso;
	}
}
