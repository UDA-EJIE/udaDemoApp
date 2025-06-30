<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoFirmaEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
var horasObligatorias = '${horasObligatorias}';
var labelDocTraducir='<spring:message code="label.documentoTraducir"/>';
var labelUltimoEnTraducir='<spring:message code="label.documentoUltimoEnTraducir"/>';
var labelDocsTraducir='<spring:message code="label.documentosTraducir"/>';
var labelTraducRealizadas='<spring:message code="label.traduccionesRealizadas"/>';
var labelDocusEntregar='<spring:message code="label.documentosAEntregar"/>';
var labelDocFinal='<spring:message code="label.documentoFinal"/>';
var labelDocXliff='<spring:message code="label.documentoXliff"/>';
var labelDocsXliff='<spring:message code="label.documentosXliff"/>';
var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var labelCastellano='<spring:message code="label.castellano"/>';
var labelEuskera='<spring:message code="label.euskera"/>';
var labelES='<spring:message code="label.es"/>';
var labelEU='<spring:message code="label.eu"/>';
var tipoExpedienteTrad='<%=TipoExpedienteEnum.TRADUCCION.getValue()%>';
var horasObligatorias = '${horasObligatorias}';
var labelDocumentoATraducir = '<spring:message code="label.documentoTraducir"/>';
var labelDocumentoTraducido = '<spring:message code="label.documentoTraducido"/>';
var labelDocumentosOriginales = '<spring:message code="label.documentosOriginales"/>';
var labelEncrip = '<spring:message code="label.documento.encrip"/>';
var labelNoEncrip = '<spring:message code="label.documento.noencrip"/>';
var labelCastellano = '<spring:message code="label.castellano"/>';
var labelES = '<spring:message code="label.es"/>';
var labelEU = '<spring:message code="label.eu"/>';
var labelEuskera = '<spring:message code="label.euskera"/>';
var labelvisibleClte = '<spring:message code="label.visibleCliente"/>';
var tituloObservaciones ='<spring:message code="label.observaciones"/>';
var labelFirmado = '<spring:message code="label.firma"/>';

var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}

var tablaIntermedia = {
		'tabla87' : <%=TablaIntermediaEnum.TABLA_87.getValue()%>,
		'tabla92' : <%=TablaIntermediaEnum.TABLA_92.getValue()%>
		
}
var estadoFirma = {
		'sinFirmar' : <%=EstadoFirmaEnum.SINFIRMAR.getValue()%>,
		'firmando' : <%=EstadoFirmaEnum.FIRMANDO.getValue()%>,
		'error' : <%=EstadoFirmaEnum.ERROR.getValue()%>,
		'firmado' : <%=EstadoFirmaEnum.FIRMADO.getValue()%>
}
var estadoFirmaLabel = {
		'sinFirmar' : '<spring:message code="label.estadoFirma.sinFirmar"/>',
		'firmando' : '<spring:message code="label.estadoFirma.firmando"/>',
		'error' : '<spring:message code="label.error"/>',
		'firmado' : '<spring:message code="label.estadoFirma.firmado"/>'
}
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareaentregacliente.js" type="text/javascript"></script>