<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoFacturacionEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum" %>
<script type="text/javascript">
	var txtEntidadAsoc = '<spring:message code="label.entidadAsociada"/>';
	var txtContacto = '<spring:message code="label.contacto"/>';
	var txtDirPostal = '<spring:message code="label.direccionPostal"/>';
	var txtFacturable = '<spring:message code="label.facturable"/>';
	var txtPorFacturable = '<spring:message code="label.porFact"/>';
	var txtIVA = '<spring:message code="label.iva"/>';
	var txtImporteBase = '<spring:message code="label.importeBase"/>';
	var txtImporteIva = '<spring:message code="label.importeIVA"/>';
	var txtTotal = '<spring:message code="label.totalMayus"/>';	
	var txtEntidadContactoFact = '<spring:message code="label.entidadContactoAFacturar"/>';
	
	var datos = {
		'activo' : {
			'si' : '<%=ActivoEnum.SI.getValue()%>',
			'no' : '<%=ActivoEnum.NO.getValue()%>'
		},
		'tipoFacturacion' : {
			'euroHoraInterprete' : '<%=TipoFacturacionEnum.EURO_HORA_INTERPRETE.getValue()%>',
			'euroInterprete': '<%=TipoFacturacionEnum.EURO_INTERPRETE.getValue()%>'
		}	
	}
	
	var urlConfigurarPersonal = '${appConfiguration['x54j.url']}&destino=busquedaTrabajador';
	var urlConfigurarEntidad = '${appConfiguration['x54j.url']}&destino=busquedaEntidad';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceraExpGeneral.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/utils/aa79bImportesExpedienteUtils.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/detalledatosfacturacioninter.js" type="text/javascript"></script>