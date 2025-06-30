<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<script>
	var tipoSubida = {
			'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
			'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
	}

	var horasObligatorias = '${horasObligatorias}';
	var labelPestanaTradosTarea = '<spring:message code="label.pestanaTradosTarea"/>';
	var labelPestanaTradosDetallePresupuesto = '<spring:message code="label.pestanaTradosDetallePresupuesto"/>';
	var labelPestanaTradosInforFichero = '<spring:message code="label.pestanaTradosInforFichero"/>';
</script>

<script src="${staticsUrl}/aa79b/scripts/utils/aa79bUtils.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareatrados.js" type="text/javascript"></script>
