<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page	import="com.ejie.aa79b.model.enums.EstadoAlbaranEnum" %>

<script src="${staticsUrl}/aa79b/scripts/utils/aa79bUtils.js" type="text/javascript"></script>


<script type="text/javascript">
    var txtReferencia = '<spring:message code="label.reffact"/>';
    var txtImpPag = '<spring:message code="label.imppagado"/>';
	var txtTarea = '<spring:message code="label.tarea"/>';
	var txtNumPalavras = '<spring:message code="label.numpalavras"/>';
	var txtPrcIva = '<spring:message code="label.percIVA"/>';
	var txtImpPalavra = '<spring:message code="label.imppalabra"/> (€)';
	var txtImpTarea = '<spring:message code="label.impTarea"/> (€)';
	var txtImpRecargoFormato = '<spring:message code="label.impRecargoFormato"/>';
	var txtImpRecargoPremio = '<spring:message code="label.impRecargoPremio"/>';
	var txtImpPenalRetraso = '<spring:message code="label.impPenalRetraso"/>';
	var txtImpPenalCalidad = '<spring:message code="label.impPenalCalidad"/>';
	var txtTotal = '<spring:message code="label.total"/> (€)';
	var txtImpBase = '<spring:message code="label.impBase"/> (€)';
	var txtImpIva = '<spring:message code="label.impIva"/> (€)';
	var txtIndAlbaran = '<spring:message code="label.indalbaran"/>';
	var txtNumExp = '<spring:message code="label.numExp"/>';
	
	var txtPalabras = '<spring:message code="label.palabras" />';
	var txtSi = '<spring:message code="comun.si" />';
	var txtNo = '<spring:message code="comun.no" />';
	
	var _TIP_TAREA_TRADUZIR = '<%=Constants.TIP_TAREA_TRADUZIR%>';
	var _TIP_TAREA_REVIZAR = '<%=Constants.TIP_TAREA_REVIZAR%>';
	var _IDTRADUZIR = <%=com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum.TRADUCIR.getValue()%>;
	var _IDREVIZAR = <%=com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum.REVISAR.getValue()%>;
	
	var idAlbaranTxt='${pagamento.idAlbaran}';
	var idLoteTxt='${pagamento.idLote}';
	var idEstado = '${pagamento.estadoAlbaran}';
	var estadoAlbaranPagado = <%=EstadoAlbaranEnum.PAGADO.getValue()%>;
	
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/pagamentosdetalle.js" type="text/javascript"></script>

