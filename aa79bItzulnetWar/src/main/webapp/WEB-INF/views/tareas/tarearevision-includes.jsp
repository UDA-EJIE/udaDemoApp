<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<script type="text/javascript">
var labelDocRevisar='<spring:message code="label.documentoARevisar"/>';
var labelDocRevisado='<spring:message code="label.documentoRevisado"/>';
var labelJustificanteRevision='<spring:message code="label.justificanteRevision"/>';
var labelDocFinal='<spring:message code="label.documentoFinal"/>';
var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var labelCastellano='<spring:message code="label.castellano"/>';
var labelES='<spring:message code="label.es"/>';
var labelEU='<spring:message code="label.eu"/>';
var labelEuskera='<spring:message code="label.euskera"/>';
var labelvisibleClte='<spring:message code="label.visibleCliente"/>';
var horasObligatorias = '${horasObligatorias}';

var traduccion = '<spring:message code="label.traduccion"/>';
var revision = '<spring:message code="label.revision"/>';
var apoyo = '<spring:message code="label.apoyo"/>';
var interpretacion = '<spring:message code="label.interpretacion"/>';
var labelNumPal='<spring:message code="label.documento.numPalabras"/>';

var labelDocusRevisar='<spring:message code="label.documentosARevisar"/>';
var labelDocXliff='<spring:message code="label.documentoXliff"/>';
var labelDocsXliff='<spring:message code="label.documentosXliff"/>';

var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}

var tablaIntermedia = {
		'tabla87' : <%=TablaIntermediaEnum.TABLA_87.getValue()%>,
		'tabla93' : <%=TablaIntermediaEnum.TABLA_93.getValue()%>
}

</script>
<script type="text/javascript">
var esTecnicoIzo = false;
<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
esTecnicoIzo = true;
</sec:authorize>

</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tarearevision.js" type="text/javascript"></script>