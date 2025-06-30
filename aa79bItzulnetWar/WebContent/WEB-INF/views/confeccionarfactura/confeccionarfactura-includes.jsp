<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<script type="text/javascript">

	
	var tiposExpediente =  {
				'interpretacion' : '<%=TipoExpedienteGrupoEnum.INTERPRETACION.getValue()%>',
				'tradRev' : '<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue()%>',
			};
	
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/confeccionarfactura.js" type="text/javascript"></script>