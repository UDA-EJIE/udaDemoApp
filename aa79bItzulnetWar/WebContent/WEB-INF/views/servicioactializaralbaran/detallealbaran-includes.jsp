<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page	import="com.ejie.aa79b.model.enums.EstadoAlbaranEnum" %>

<script src="${staticsUrl}/aa79b/scripts/utils/aa79bUtils.js" type="text/javascript"></script>
<script type="text/javascript">
	var txtReferencia = '<spring:message code="label.reffact"/>';
    var txtImpPag = '<spring:message code="label.imppagado"/>';
	var txtTarea = '<spring:message code="label.tarea"/>';
	var txtNumPalavras = '<spring:message code="label.numpalavras"/>';
	var txtPrcIva = '<spring:message code="label.percIVA"/>';
	var txtImpPalavra = '<spring:message code="label.imppalabra"/>';
	var txtImpTarea = '<spring:message code="label.impTarea"/>';
	var txtImpRecargoFormato = '<spring:message code="label.impRecargoFormato"/>';
	var txtImpRecargoPremio = '<spring:message code="label.impRecargoPremio"/>';
	var txtImpPenalRetraso = '<spring:message code="label.impPenalRetraso"/>';
	var txtImpPenalCalidad = '<spring:message code="label.impPenalCalidad"/>';
	var txtTotal = '<spring:message code="label.total"/>';
	var txtImpBase = '<spring:message code="label.impBase"/>';
	var txtImpIva = '<spring:message code="label.impIva"/>';
	var txtIndAlbaran = '<spring:message code="label.indalbaran"/>';
	var txtNumExp = '<spring:message code="label.numExp"/>';
	
	var txtPalabras = '<spring:message code="label.palabras" />';
	var txtSi = '<spring:message code="comun.si" />';
	var txtNo = '<spring:message code="comun.no" />';
	
	var _TIP_TAREA_TRADUZIR = '<%=Constants.TIP_TAREA_TRADUZIR%>';
	var _TIP_TAREA_REVIZAR = '<%=Constants.TIP_TAREA_REVIZAR%>';
	var _IDTRADUZIR = <%=com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()%>;
	var _IDREVIZAR = <%=com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>;

	var idAlbaranTxt='${albaran.idAlbaran}';
	var idLoteTxt='${albaran.lotes.idLote}';
	var idEstado = '${albaran.estado}';
	var estadoAlbaranPendienteEnviarIzo = <%=EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getValue()%>;
	var estadoAlbaranEnviadoIzo = <%=EstadoAlbaranEnum.ENVIADO_IZO.getValue()%>;
	var estadoAlbaranPagado = <%=EstadoAlbaranEnum.PAGADO.getValue()%>;
	var txtEstadoAlbaranPendienteEnviarIzo = '<spring:message code="comun.estadoAlbaranPdteEnviar"/>';
	var txtEstadoAlbaranEnviadoIzo ='<spring:message code="comun.estadoAlbaranEnviado"/>';
	var txtEstadoAlbaranPagado = '<spring:message code="comun.estadoAlbaranPagado"/>';
	var refAlbaran = '${albaran.refAlbaran}';
	var importePrevistoAlb = '${albaran.importePrevisto}';
	
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/detallealbaran.js" type="text/javascript"></script>