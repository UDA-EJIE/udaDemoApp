<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>
<script type="text/javascript">

var labelEncrip='<spring:message code="label.documento.encrip"/>';
var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';
var tipoSubida = {
		'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
}
var horasObligatorias = '${horasObligatorias}';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareatrabajo.js" type="text/javascript"></script>