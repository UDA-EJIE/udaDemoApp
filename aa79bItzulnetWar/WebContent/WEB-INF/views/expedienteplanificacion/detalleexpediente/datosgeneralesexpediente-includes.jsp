<%@include file="/WEB-INF/includeTemplate.inc"%>
<%-- <script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/buscadorpersonas.js" type="text/javascript"></script> --%>
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
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/planifdatosgeneralesexpediente.js" type="text/javascript"></script>