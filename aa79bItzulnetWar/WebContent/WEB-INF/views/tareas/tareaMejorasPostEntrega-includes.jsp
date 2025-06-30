<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<script>

	var labelLerrokoa='<spring:message code="label.lerrokoa"/>';
	var labelDocXliff='<spring:message code="label.documentoXliff"/>';
	var labelDocsXliff='<spring:message code="label.documentosXliff"/>';
	
	var horasObligatorias = '${horasObligatorias}';
	var tipoSubida = {
			'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
			'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
	}
	
	var extensionPDF = '<%=Constants.FILE_PDF%>';
</script>
<script
	src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareaMejorasPostEntrega.js"
	type="text/javascript"></script>