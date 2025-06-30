<%@page import="com.ejie.aa79b.model.enums.EstadoEjecucionTareaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script>

	var datosTareas = {
		'estadoEjecucion' : {
			'pendienteEjecucion' : '<%=EstadoEjecucionTareaEnum.PENDIENTE_EJECUCION.getValue()%>',
			'retrasada' : '<%=EstadoEjecucionTareaEnum.RETRASADA.getValue()%>',
			'ejecutada' : '<%=EstadoEjecucionTareaEnum.EJECUTADA.getValue()%>'
		}
	};
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/otrostrabajos/tareastrabajo.js" type="text/javascript"></script>
