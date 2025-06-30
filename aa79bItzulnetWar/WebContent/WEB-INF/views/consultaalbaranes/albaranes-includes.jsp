<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
	var txtRefAlbaran = '<spring:message code="label.refalbaran"/>';
	var txtDtCreacion = '<spring:message code="label.fechaAlta"/>';
	var txtNumTareas = '<spring:message code="label.ntareas"/>';
	var txtNumExpAsociados = '<spring:message code="label.nexpedientes"/>';
	var txtEstadoAlbaran = '<spring:message code="label.estadoAlbaran"/>';
	var txtImpPrevisto = '<spring:message code="label.impprevisto"/>' + ' (€)';
	var txtImpFactura = '<spring:message code="label.imppagado"/>' + ' (€)';
	
	var idEmpConsultaExterna = '${idEmpConsultaExterna}';
	var idLoteConsultaExterna = '${idLoteConsultaExterna}';
</script>
<script src="${staticsUrl}/aa79b/scripts/utils/aa79bUtils.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/consultaalbaranes.js" type="text/javascript"></script>