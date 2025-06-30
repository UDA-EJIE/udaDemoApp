<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>

<script>
	var horasObligatorias = '${horasObligatorias}';
	var labelDocTraducir='<spring:message code="label.documentoTraducir"/>';
	var labelDocTraducido='<spring:message code="label.documentoTraducido"/>';
	var labelDocRevisado='<spring:message code="label.documentoRevisado"/>';
	var labelEncrip='<spring:message code="label.documento.encrip"/>';
	var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
	var labelCastellano='<spring:message code="label.castellano"/>';
	var labelES='<spring:message code="label.es"/>';
	var labelEU='<spring:message code="label.eu"/>';
	var labelEuskera='<spring:message code="label.euskera"/>';
	var labelDocusRevisarTradInt='<spring:message code="label.documentosARevisar"/>';
	var labelDocsXliff='<spring:message code="label.documentosXliff"/>';
	var labelDocXliff='<spring:message code="label.documentoXliff"/>';
	var tituloObservaciones ='<spring:message code="label.observaciones"/>';
	
	var tipoSubida = {
			'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
			'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
	}
	
	var tablaIntermedia = {
			'tabla87' : <%=TablaIntermediaEnum.TABLA_87.getValue()%>,
			'tabla92' : <%=TablaIntermediaEnum.TABLA_92.getValue()%>,
			'tabla93' : <%=TablaIntermediaEnum.TABLA_93.getValue()%>
	}
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tarearevisiontraduccioninterna.js" type="text/javascript"></script>