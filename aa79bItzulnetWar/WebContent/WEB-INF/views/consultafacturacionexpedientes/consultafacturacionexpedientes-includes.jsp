<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<script type="text/javascript">
var tipoExpTradRev = '<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getValue()%>';
</script>


<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/consultafacturacionexpedientes.js" type="text/javascript"></script>