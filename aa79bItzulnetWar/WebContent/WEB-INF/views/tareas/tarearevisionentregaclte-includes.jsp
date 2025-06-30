<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TablaIntermediaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoFirmaEnum" %>
<script>
	var horasObligatorias = '${horasObligatorias}';
	var labelARevisar = '<spring:message code="label.aRevisar"/>';
	var labelRevisado = '<spring:message code="label.revisado"/>';
	var labelJustificanteRev='<spring:message code="label.justificanteRevision"/>';
	var labelDocumentoARevisar = '<spring:message code="label.documentoARevisar"/>';
	var labelUltimoEnRevisar = '<spring:message code="label.ultimoEnRevisar"/>';
	var labelDocXliff='<spring:message code="label.documentoXliff"/>';
	var labelDocsXliff='<spring:message code="label.documentosXliff"/>';
	var labelDocumentoRevisado = '<spring:message code="label.documentoRevisado"/>';
	var labelDocumentosOriginales = '<spring:message code="label.documentosOriginales"/>';
	var labelRevisionesRealizadas = '<spring:message code="label.revisionesRealizadas"/>';
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
			'tabla92' : <%=TablaIntermediaEnum.TABLA_92.getValue()%>,
			'tabla93' : <%=TablaIntermediaEnum.TABLA_93.getValue()%>
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
<script type="text/javascript">
var esTecnicoIzo = false;
<sec:authorize access="hasRole('ROLE_AA79B_TECNICO_GESTOR')">
esTecnicoIzo = true;
</sec:authorize>

</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tarearevisionentregaclte.js" type="text/javascript"></script>