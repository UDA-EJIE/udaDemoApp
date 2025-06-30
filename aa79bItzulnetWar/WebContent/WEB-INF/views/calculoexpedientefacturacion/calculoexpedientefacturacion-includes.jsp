<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.ModoDetalleExpedienteEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>


<script type="text/javascript">


var txtEntidadContactoFact = '<spring:message code="label.entidadContactoAFacturar"/>';
var tiposExpediente =  {
			'interpretacion' : '<%=TipoExpedienteGrupoEnum.INTERPRETACION.getValue()%>',
			'tradRev' : '<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue()%>',
		};
var modoDetalleEnum = {
		'nuevaVentana': '<%=ModoDetalleExpedienteEnum.VENTANA_NUEVA.getValue()%>',
		'pestanya': '<%=ModoDetalleExpedienteEnum.PESTANYA.getValue()%>'
};
	
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cabeceraExpGeneral.js" type="text/javascript"></script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/calculoexpedientefacturacion.js" type="text/javascript"></script>