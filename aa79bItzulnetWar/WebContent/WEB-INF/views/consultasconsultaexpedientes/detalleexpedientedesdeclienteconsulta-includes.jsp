<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum" %>
<script>
var estadosExp = {
				'anulado' : '<%=EstadoExpedienteEnum.ANULADO.getValue()%>',
				'rechazado' : '<%=EstadoExpedienteEnum.RECHAZADO.getValue()%>'
	};
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/detalleexpedientedesdeclienteconsulta.js" type="text/javascript"></script>