<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<script>
	var horasObligatorias = '${horasObligatorias}';
	var tipoSubida = {
			'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
			'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
	}
	
	var tablaIntermedia = {
			'tabla86' : <%=TablaIntermediaEnum.TABLA_86.getValue()%>,
			'tabla87' : <%=TablaIntermediaEnum.TABLA_87.getValue()%>,
			'tabla93' : <%=TablaIntermediaEnum.TABLA_93.getValue()%>
	}
	var extensionPDF = '<%=Constants.FILE_PDF%>';
</script>
<script
	src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/posttraduccionboe.js"
	type="text/javascript"></script>