package aa79b.util.common;

public enum AccionBitacoraEnum {
	
	ALTA_EXP(1, "label.accionBitacora.altaExpediente"),
	MODIFICAR_EXP(2, "label.accionBitacora.modifExpediente"),
	ELIMINAR_EXP(3, "label.accionBitacora.eliminarExpediente"),
	RECHAZO_EXP(4, "label.accionBitacora.rechazoExpediente"),
	REQU_SUBS_CLIENTE(5, "label.accionBitacora.reqSubsCliente"),
	SUBS_APORT_CLIENTE(6,"label.accionBitacora.subsAportCliente"),
	SUBS_ACEPT_IZO(7, "label.accionBitacora.subsAceptIzo"),
	SUBS_RECHAZ_IZO(8,"label.accionBitacora.subusRechazIzo"),
	EXP_ANULADO(9,"label.accionBitacora.expAnulado"),
	REQ_ACEPT_RECHAZ_PRESUP(10, "label.accionBitacora.reqAceptRechazPresup"),
	REQ_ACEPT_RECHAZ_FECHA_PROP_IZO(11, "label.accionBitacora.reqAceptRechazFechaPropIzo"),
	REQ_ACEPT_RECHAZ_PRESUP_FECHA_PROP_IZO(12, "label.accionBitacora.reqAceptRechazPresupFechaPropIzo"),
	ACEPT_PRESUP(13, "label.accionBitacora.aceptPresup"),
	RECHAZ_PRESUP(14, "label.accionBitacora.rechazPresup"),
	ACEPT_FECHA_PROP_IZO(15, "label.accionBitacora.acepFechaPropIzo"),
	RECHAZ_FECHA_PROP_IZO(16, "label.accionBitacora.rechazFechaPropIzo"),
	EXP_EN_CURSO(17, "label.accionBitacora.expEnCurso"),
	TRABAJO_FINAL_ENTREGADO_CLIENTE(18, "label.accionBitacora.trabajoFinalEntregadoCliente"),
	EXP_CERRADO(19, "label.accionBitacora.expCerrado"),
	EXP_FACTURADO(20, "label.accionBitacora.expFacturado"),
	ACEPT_PRESUP_FECHA_PROP_IZO(21, "label.accionBitacora.acepFechaPropIzo"),
	RECHAZ_PRESUP_FECHA_PROP_IZO(22, "label.accionBitacora.rechazFechaPropIzo"),
	NO_CONFORMIDAD_CLIENTE(23, "label.accionBitacora.noConformidad");
	
	
	private int value;
	private String label;

	private AccionBitacoraEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}
	
	public String getLabel(){
		return this.label;
	}

}
