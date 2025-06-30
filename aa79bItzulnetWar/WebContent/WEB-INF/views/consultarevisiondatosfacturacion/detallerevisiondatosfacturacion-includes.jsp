<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.IdiomaEnum" %>
<script type="text/javascript">
	var txtImporteBase = '<spring:message code="label.importeBase"/>' + ' (€)';	
	var txtEntidadAsoc = '<spring:message code="label.entidadAsociada"/>';
	var txtContacto = '<spring:message code="label.contacto"/>';
	var txtDirPostal = '<spring:message code="label.direccionPostal"/>';
	var txtNumPal = '<spring:message code="label.documento.numPalabras"/>';
	var txtFacturable = '<spring:message code="label.facturable"/>';
	var txtPorFacturable = '<spring:message code="label.porFact"/>';
	var txtIVA = '<spring:message code="label.iva"/>';
	var txtImporteIva = '<spring:message code="label.importeIVA"/>' + ' (€)';
	var txtTotal = '<spring:message code="label.totalMayus"/>' + ' (€)';
	var txtEntidadContactoFact = '<spring:message code="label.entidadContactoAFacturar"/>';
	var txtCentroOrganico = '<spring:message code="label.centroOrganico"/>';
	var datosBasicos = ${datosBasicos};
	
	var urlConfigurarPersonal = '${appConfiguration['x54j.url']}&destino=busquedaTrabajador';
	var urlConfigurarEntidad = '${appConfiguration['x54j.url']}&destino=busquedaEntidad';
	
	var enums = {
		'idioma' : {
			'es' : '<%=IdiomaEnum.CASTELLANO.getValue()%>',
			'eu' : '<%=IdiomaEnum.EUSKERA.getValue()%>'
		}
	};
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceraExpGeneral.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/utils/aa79bImportesExpedienteUtils.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/detallerevisiondatosfacturacion.js" type="text/javascript"></script>