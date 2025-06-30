<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoRequerimientoEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoAceptacionTareaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.RequiereTradosEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/actualizaciondatosfacturacion.js" type="text/javascript"></script>

<script type="text/javascript">
var labelEstadoEjecucionTareaPendiente='<spring:message code="comun.estadoPendienteEjecucion"/>';
var labelEstadoEjecucionTareaRetrasada='<spring:message code="comun.estadoRetrasada"/>';
var labelEstadoEjecucionTareaEjecutada='<spring:message code="comun.estadoEjecutada"/>';
var labelEstadoAsignacionTareapendienteAsignacion='<spring:message code="comun.estadoPendienteAsignacion"/>';
var labelEstadoAsignacionTareapendienteAceptacion='<spring:message code="comun.estadoPendienteAceptacion"/>';
var labelEstadoAsignacionTareapendienteAceptada='<spring:message code="comun.estadoAceptada"/>';
var labelEstadoAsignacionTareapendienteRechazada='<spring:message code="comun.estadoRechazada"/>';
var labelSinProyectoTrados='<spring:message code="comun.sinProyectoTrados"/>';
var labelVer='<spring:message code="label.ver"/>';

	var busqGen = {
			'estadoTareasAsign' : {
				'pendiente' : '<%=EstadoRequerimientoEnum.PENDIENTE.getValue()%>',
				'aceptada' : '<%=EstadoRequerimientoEnum.ACEPTADA.getValue()%>',
				'rechazada' : '<%=EstadoRequerimientoEnum.RECHAZADA.getValue()%>',
			},
			'estadoAsignacionTarea' : {
				'pendienteAsignacion': '<%=EstadoAceptacionTareaEnum.PENDIENTE_ASIGNACION.getValue()%>',
				'pendienteAceptacion': '<%=EstadoAceptacionTareaEnum.PENDIENTE_ACEPTACION.getValue()%>',
				'aceptada': '<%=EstadoAceptacionTareaEnum.ACEPTADA.getValue()%>',
				'rechazada': '<%=EstadoAceptacionTareaEnum.RECHAZADA.getValue()%>'
			},
			'estadoEjecucionTarea' : {
				'pendienteEjecucion': '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>',
				'retrasada': '<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>',
				'ejecutada': '<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>'
			},
			'requiereTrados' : {
				'noRequiereTrados' : '<%=RequiereTradosEnum.NO_REQUIERE_TRADOS.getValue()%>',
				'sinProyectoTrados' : '<%=RequiereTradosEnum.SIN_PROYECTO_TRADOS.getValue()%>',
				'pdteAsignacion' : '<%=RequiereTradosEnum.PDTE_ASIGNACION.getValue()%>',
				'pdteEjecucion' : '<%=RequiereTradosEnum.PDTE_EJECUCION.getValue()%>',
				'pdteRetrasada' : '<%=RequiereTradosEnum.PDTE_RETRASADA.getValue()%>',
				'tradosEjecutada' : '<%=RequiereTradosEnum.TRADOS_EJECUTADA.getValue()%>',
			}
	};
	
	var datosExp = {
			'tipoExp' : {
				'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>',
				'traduccion' : '<%=TipoExpedienteEnum.TRADUCCION.getValue()%>',
				'revision' : '<%=TipoExpedienteEnum.REVISION.getValue()%>'
			}
	};
	
</script>