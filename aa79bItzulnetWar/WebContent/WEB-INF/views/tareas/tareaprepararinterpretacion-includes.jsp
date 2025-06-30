<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoTareaGestionAsociadaEnum"%>
<script>
	var horasObligatorias = '${horasObligatorias}';
	var tipoTarea = ${tipoTarea};
	var tituloObservaciones ='<spring:message code="label.observaciones"/>';
	var tiposTarea = {
			"interpretacion": '<%=TipoTareaGestionAsociadaEnum.INTERPRETAR.getValue()%>'
	}
</script>
<script	src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/prepararinterpretacion.js" type="text/javascript"></script>