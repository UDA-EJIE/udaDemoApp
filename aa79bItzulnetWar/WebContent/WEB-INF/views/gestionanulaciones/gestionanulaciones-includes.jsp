<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.MotivosAnulacionEnum" %>

<script>

	var datosExp = {
		'tipoExp' : {
			'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>',
			'traduccion' : '<%=TipoExpedienteEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=TipoExpedienteEnum.REVISION.getValue()%>'
		}
	};
	var datosAnulacionExp = {
		'motivosAnulacion' : {
			'otros' : '<%=MotivosAnulacionEnum.OTROS.getValue()%>'
		}
	};
	
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/gestionanulaciones.js" type="text/javascript"></script>