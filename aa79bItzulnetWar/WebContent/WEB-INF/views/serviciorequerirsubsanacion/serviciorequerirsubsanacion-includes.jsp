<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.FaseExpedienteEnum"%>
<script type="text/javascript">
var fasePdteTramCliente = '<%=FaseExpedienteEnum.PDTE_TRAM_CLIENTE.getValue()%>';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/serviciorequerirsubsanacion.js" type="text/javascript"></script>