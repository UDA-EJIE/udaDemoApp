<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorpersonas.js" type="text/javascript"></script>
<script type="text/javascript">
	var urlConfigurarPersonal = '${appConfiguration['x54j.url']}&destino=busquedaTrabajador';
	/*JOSE*/
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
	var clasificacionDocTraduccion = '<%=ClasificacionDocumentosEnum.TRADUCCION.getValue()%>';
	var clasificacionDocRevision = '<%=ClasificacionDocumentosEnum.REVISION.getValue()%>';
	var txtTipoExpedienteInterpretacion = '<spring:message code="label.tipoExpediente.interpretacion"/>';
	var txtTipoExpedienteTraduccion ='<spring:message code="label.traduccion"/>';
	var txtTipoExpedienteRevision = '<spring:message code="label.revision"/>';
	
	
// 	var indConfidencial = false;
</script>


<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/datosgeneralesexpediente.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/datosgeneralesexpediente-doc.js" type="text/javascript"></script>
