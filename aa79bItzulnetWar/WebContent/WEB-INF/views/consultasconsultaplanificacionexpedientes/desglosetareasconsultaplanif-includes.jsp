<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum" %>
<script type="text/javascript">
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
	var idiomaDestino;
	var expedienteConfidencial;
	var origen;
	var tablaSelector = null;
	var ordenTarea;
	var fechaFinalIZO;
	var horaFinalIZO;
	var tipoExp;
	var consultaPlanif = true;
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
				'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>'
			},
			'label' : {
				'pdteEjecucion' : '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getLabel()%>',
				'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getLabel()%>'
			}
		};
	var consExp = {
			'estadoExpediente' : {
				'cerrado' : '<%=EstadoExpedienteEnum.CERRADO.getValue()%>',
				'enCurso' : '<%=EstadoExpedienteEnum.EN_CURSO.getValue()%>'
			}
	};
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/desglosetareasconsultaplanif.js" type="text/javascript"></script>