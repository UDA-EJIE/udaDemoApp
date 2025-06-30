<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.EstadoAuditoriaEnum" %>
<script type="text/javascript">

var usuarioConectado='${usuarioConectado}';

var auditoriaData = {
		'estadoAuditoria' : {
			'sinEnviar' : '<%=EstadoAuditoriaEnum.SIN_ENVIAR.getValue()%>',
			'enviada' : '<%=EstadoAuditoriaEnum.ENVIADA.getValue()%>',
			'confirmada' : '<%=EstadoAuditoriaEnum.CONFIRMADA.getValue()%>'
		}
};

</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/auditoria.js" type="text/javascript"></script>