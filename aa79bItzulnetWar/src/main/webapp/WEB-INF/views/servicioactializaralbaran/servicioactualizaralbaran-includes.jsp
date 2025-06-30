<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAlbaranEnum"%>
<script type="text/javascript">
var estadoAlbaran = {
		'value' : {
			'pagado' : '<%=EstadoAlbaranEnum.PAGADO.getValue()%>',
			'enviadoIzo' : '<%=EstadoAlbaranEnum.ENVIADO_IZO.getValue()%>',
			'pdteAsociarAlbaran' : '<%=EstadoAlbaranEnum.PENDIENTE_ASOCIAR_ALBARAN.getValue()%>',
			'pdteEnviarIzoAlbaran' : '<%=EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getValue()%>'
		},
		'label' : {
			'pagado' : '<%=EstadoAlbaranEnum.PAGADO.getLabel()%>',
			'enviadoIzo' : '<%=EstadoAlbaranEnum.ENVIADO_IZO.getLabel()%>',
			'pdteAsociarAlbaran' : '<%=EstadoAlbaranEnum.PENDIENTE_ASOCIAR_ALBARAN.getLabel()%>',
			'pdteEnviarIzoAlbaran' : '<%=EstadoAlbaranEnum.PENDIENTE_ENVIAR_IZO.getLabel()%>'
		}
	};
	var txtImpPrevisto = '<spring:message code="label.impprevisto"/>' + ' (€)';
	var txtImpFactura = '<spring:message code="label.imppagado"/>' + ' (€)';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/servicioactualizacionalbaran.js" type="text/javascript"></script>