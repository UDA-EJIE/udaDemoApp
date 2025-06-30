<%@include file="/WEB-INF/includeTemplate.inc"%>

<script type="text/javascript">
	var txtAnyoCalendario = '<spring:message code="label.calendarioServicio.anyoCalendario"/>';
	var txtVeranoDesde = '<spring:message code="label.calendarioServicio.veranoDesde"/>';
	var txtVeranoHasta = '<spring:message code="label.calendarioServicio.veranoHasta"/>';
	var txtDia= '<spring:message code="label.calendarioServicio.dia"/>';
	var txtTipoJornada= '<spring:message code="label.calendarioServicio.tipoJornada"/>';
	var txtObsvDia= '<spring:message code="label.calendarioServicio.obsvDia"/>';
	
	var iconoIVAP='${staticsUrl}/aa79b/images/ivap.png';
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/calendarioservicio.js" type="text/javascript"></script>