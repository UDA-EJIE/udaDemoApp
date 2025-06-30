<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum" %>
<script type="text/javascript">
var labelTraduccion='<spring:message code="label.traduccion"/>';
var labelRevision='<spring:message code="label.revision"/>';
var labelDocsTraducir='<spring:message code="label.documentosTraducir"/>';
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
var labelSubsanarDocumentoTraducido='<spring:message code="tabla.subsanarDocumentoTraducido"/>';
var labelDocRevisar='<spring:message code="label.documentoARevisar"/>';
var labelDocumentoRevision='<spring:message code="label.documentoDeRevision"/>';
var labelSubsanarDocumento='<spring:message code="label.subsanarDocumento"/>';
var labelJustificanteRevision='<spring:message code="label.justificanteRevision"/>';

var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}

var tablaIntermedia = {
		'tabla87' : <%=TablaIntermediaEnum.TABLA_87.getValue()%>
}
var tipoTarea = {
		'revisar' : '<%=TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>',
		'traducir' : '<%=TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()%>',
}

</script>
<script type="text/javascript">
var esTecnicoIzo = false;
<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
esTecnicoIzo = true;
</sec:authorize>

</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareanoconformidadproveedor.js" type="text/javascript"></script>