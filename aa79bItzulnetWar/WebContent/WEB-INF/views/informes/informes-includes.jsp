<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
var tiposExpediente =  {
			'tradRev' : '<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode()%>',
		};
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/informes.js" type="text/javascript"></script>