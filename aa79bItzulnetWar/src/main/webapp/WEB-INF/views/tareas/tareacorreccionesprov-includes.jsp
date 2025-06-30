<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
	var labelEncrip='<spring:message code="label.documento.encrip"/>';
	var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';

	var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
	}
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareacorreccionesprov.js" type="text/javascript"></script>
