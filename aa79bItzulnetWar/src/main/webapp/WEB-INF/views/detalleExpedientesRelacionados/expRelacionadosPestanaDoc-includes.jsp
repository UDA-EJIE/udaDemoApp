<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<script type="text/javascript">
var anyoExp = ${anyo};
var numExp = ${numExp};
var labelFichero='<spring:message code="label.fichero"/>';
var labelTipo='<spring:message code="label.documento.tipo"/>';
var labelNumPalIzo='<spring:message code="label.documento.numPalabrasIZO"/>';
var labelVisible='<spring:message code="label.documento.visible"/>';
var labelVer='<spring:message code="label.ver"/>';
var labelEliminar='<spring:message code="eliminar"/>';
var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var labelCastellano='<spring:message code="label.castellano"/>';
var labelDescargar='<spring:message code="label.descargarFichero"/>';
var labelVersiones='<spring:message code="label.versionesAnteriores"/>';
var tipoDocApoyo = <%=ClasificacionDocumentosEnum.APOYO.getValue()%>;
var labelTipoDocReferencia = '<spring:message code="label.referencia"/>';
var tipoSubida = {
		'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/exprelacionadospestanadoc.js" type="text/javascript"></script>