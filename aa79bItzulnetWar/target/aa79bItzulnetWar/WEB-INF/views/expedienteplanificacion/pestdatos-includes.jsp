<%@include file="/WEB-INF/includeTemplate.inc"%>

<script type="text/javascript">
	var txtExpedientes = '<spring:message code="comun.expedientes"/>';
	var txtTareas = '<spring:message code="comun.tareas"/>';
	var txtTramites = '<spring:message code="comun.tramitesClientes"/>';
	var dniUsuSesion = ${xlnetAuthenticationProvider.userCredentials.nif};
	
	var txtNumExp = '<spring:message code="label.numExp"/>';
	var txtTipo = '<spring:message code="label.tipo"/>';
	var txtGestorExp = '<spring:message code="label.gestorExp"/>';
	var txtFechaHoraSol = '<spring:message code="label.fechaHoraSol"/>';
	var txtConformidad = '<spring:message code="label.conformidad"/>';
	var txtTareasSinAsignar = '<spring:message code="label.tareasSinAsignar"/>';
	var txtFechaHoraExpiracionPlazo = '<spring:message code="label.fechaHoraExpiracionPlazo"/>';
	var txtEstadoReq = '<spring:message code="label.estadoRequerimiento"/>';
	var txtTipoTarea = '<spring:message code="comun.tipoDeTarea"/>';
	var txtRecursoAsignado = '<spring:message code="label.recursoAsignado"/>';
	var txtFechaHoraAsignacion = '<spring:message code="label.fechaHoraAsignacion"/>';
	var txtEstadoAceptacion = '<spring:message code="label.estadoAceptacion"/>';
	var txtEstadoEjecucion = '<spring:message code="label.estadoEjecucion"/>';
	var txtHorasPrevistas = '<spring:message code="label.horasPrevistas"/>';
	var txtFechaHoraFinalizacion = '<spring:message code="comun.fechaHoraFinalizacion"/>';
	var txtFechaHoraEntrega = '<spring:message code="label.fechaHoraEntrega"/>';
	var txtRequerimiento = '<spring:message code="label.requerimiento"/>';
	
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/pestdatos.js" type="text/javascript"></script>
