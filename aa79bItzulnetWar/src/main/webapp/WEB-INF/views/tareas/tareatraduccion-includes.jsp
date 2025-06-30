<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<script type="text/javascript">
var labelDocTraducir='<spring:message code="label.documentoTraducir"/>';
var labelDocTraducido='<spring:message code="label.documentoTraducido"/>';
var labelDocFinal='<spring:message code="label.documentoFinal"/>';
var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var labelCastellano='<spring:message code="label.castellano"/>';
var labelES='<spring:message code="label.es"/>';
var labelEU='<spring:message code="label.eu"/>';
var labelEuskera='<spring:message code="label.euskera"/>';
var labelvisibleClte='<spring:message code="label.visibleCliente"/>';
var horasObligatorias = '${horasObligatorias}';
var labelDocusEntregar='<spring:message code="label.documentosAEntregar"/>';
var labelDocXliff='<spring:message code="label.documentoXliff"/>';
var labelDocsXliff='<spring:message code="label.documentosXliff"/>';

var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}

var tablaIntermedia = {
		'tabla87' : <%=TablaIntermediaEnum.TABLA_87.getValue()%>
}

</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareatraduccion.js" type="text/javascript"></script>