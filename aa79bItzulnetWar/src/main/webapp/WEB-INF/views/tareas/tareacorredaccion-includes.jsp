<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
var horasObligatorias = '${horasObligatorias}';
var labelDocTraducir='<spring:message code="label.documentoTraducir"/>';
var labelDocsTraducir='<spring:message code="label.documentosTraducir"/>';
var labelDocFinal='<spring:message code="label.documentoFinal"/>';
var labelDocXliff='<spring:message code="label.documentoXliff"/>';
var labelDocsXliff='<spring:message code="label.documentosXliff"/>';
var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var labelCastellano='<spring:message code="label.castellano"/>';
var labelEuskera='<spring:message code="label.euskera"/>';
var labelES='<spring:message code="label.es"/>';
var labelEU='<spring:message code="label.eu"/>';
var labelvisibleClte='<spring:message code="label.visibleCliente"/>';
var tipoExpedienteTrad='<%=TipoExpedienteEnum.TRADUCCION.getValue()%>';

var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}

var tablaIntermedia = {
		'tabla92' : <%=TablaIntermediaEnum.TABLA_92.getValue()%>,
		'tabla96' : <%=TablaIntermediaEnum.TABLA_96.getValue()%>
}
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareacorredaccion.js" type="text/javascript"></script>