<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
var labelFichero='<spring:message code="label.fichero"/>';
var labelTipo='<spring:message code="label.documento.tipo"/>';
var labelNumPalIzo='<spring:message code="label.documento.numPalabrasIZO"/>';
var labelNumPalSolic='<spring:message code="label.documento.numPalabrasSolicitante"/>';
var labelVisible='<spring:message code="label.documento.visible"/>';
var labelVer='<spring:message code="label.ver"/>';
var labelEliminar='<spring:message code="eliminar"/>';
var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var labelCastellano='<spring:message code="label.castellano"/>';
var labelEuskera='<spring:message code="label.euskera"/>';
var labelDescargar='<spring:message code="label.descargarFichero"/>';
var labelVersiones='<spring:message code="label.versionesAnteriores"/>';
var labelDocumentoOrigen='<spring:message code="label.documentoOrigen"/>';
var labelDocumentoRevisar='<spring:message code="label.documentoARevisar"/>';
var labelTituloAlt='<spring:message code="label.tituloAlt"/>';

var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}

</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/planificacionexpedientepestanadoc.js" type="text/javascript"></script>