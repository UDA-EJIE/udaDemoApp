<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
var labelES='<spring:message code="label.es"/>';
var labelEU='<spring:message code="label.eu"/>';
var tipoTareaIntrepretar= '<%=TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue()%>';
var tipoExpedienteInter= '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>';

var txtRevisarNumPalabras = '<spring:message code="label.revisarNumPalabras"/>';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/crearconftareas.js" type="text/javascript"></script>
