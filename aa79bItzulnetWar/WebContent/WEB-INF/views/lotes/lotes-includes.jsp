<%@include file="/WEB-INF/includeTemplate.inc"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>

<script type="text/javascript">
	var txtCodEmpresa = '<spring:message code="label.codEmpresa"/>';
	var txtCif = '<spring:message code="label.cif"/>';
	var txtNombre = '<spring:message code="label.nombre"/>';
	var txtLotesVigentes = '<spring:message code="label.lotesVigentes"/>';
	var txtNif = '<spring:message code="label.nif"/>';
	var txtNombreAp = '<spring:message code="label.nombreapellidos"/>';
	var txtTipoPersona = '<spring:message code="label.tipoPersona"/>';
	var txtSituacion = '<spring:message code="label.situacion"/>';
	var txtCod = '<spring:message code="label.cod"/>';
	var txtFechaIni = '<spring:message code="label.fechaIni"/>';
	var txtFechaFin = '<spring:message code="label.fechaFin"/>';
	var txtImporteAsig = '<spring:message code="label.importeAsignado"/>';
	var txtImporteConsumido = '<spring:message code="label.importeConsumido"/>';
	var txtPrevisto = '<spring:message code="label.previsto"/>';
	var txtRestante = '<spring:message code="label.restante"/>';
	var txtDisponible = '<spring:message code="label.importeDisponible"/>';
</script>

<script type="text/javascript">
	var x54j_url = "${appConfiguration['x54j.url']}";
	var x54u_url = "${appConfiguration['x54u.url']}";
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/lotes.js" type="text/javascript"></script>
