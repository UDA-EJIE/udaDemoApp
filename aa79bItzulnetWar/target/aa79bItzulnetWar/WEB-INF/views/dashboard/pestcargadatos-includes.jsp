<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
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
	
	var labelFichero='<spring:message code="label.fichero"/>';
	var labelTipo='<spring:message code="label.documento.tipo"/>';
	var labelNumPalIzo='<spring:message code="label.documento.numPalabrasIZO"/>';
	var labelVisible='<spring:message code="label.documento.visible"/>';
	var labelVer='<spring:message code="label.ver"/>';
	var labelEliminar='<spring:message code="eliminar"/>';
	var labelEncrip='<spring:message code="label.documento.encrip"/>';
	var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
	var labelCastellano='<spring:message code="label.castellano"/>';
	var labelDescargar='<spring:message code="label.descargarFichero"/>';
	var labelVersiones='<spring:message code="label.versionesAnteriores"/>';
	
	var estadoAceptacion = {
			'value' : {
				'pdteAceptacion' : '<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()%>',
				'aceptada' : '<%=EstadoAceptacionTareaEnum.ACEPTADA.getValue()%>',
				'rechazada' : '<%=EstadoAceptacionTareaEnum.RECHAZADA.getValue()%>'
			},
			'label' : {
				'pdteAceptacion' : '<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getLabel()%>',
				'aceptada' : '<%=EstadoAceptacionTareaEnum.ACEPTADA.getLabel()%>',
				'rechazada' : '<%=EstadoAceptacionTareaEnum.RECHAZADA.getLabel()%>'
			}
		};
	var estadoEjecucion = {
			'value' : {
				'pdteEjecucion' : '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>',
				'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>',
				'ejecutada' : '<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>'
			},
			'label' : {
				'pdteEjecucion' : '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>',
				'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>',
				'ejecutada' : '<%=EstadoEjecucionTareaEnum.EJECUTADA.getLabel()%>'
			}
		};
	var consultaPlanif = false;
	var tablaSelector = 'busquedaDatosCarga';
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/confirmartarea.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/dashboardpestcargadatos.js" type="text/javascript"></script>

