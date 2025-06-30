<%@page import="com.ejie.aa79b.model.enums.ClasificacionDocumentosEnum" %>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<script>
	var horasObligatorias = '${horasObligatorias}';
	var claseDocuEnum = {
			'traduccion' : <%=ClasificacionDocumentosEnum.TRADUCCION.getValue()%>,
			'revision' : <%=ClasificacionDocumentosEnum.REVISION.getValue()%>,
			'trabajo' : <%=ClasificacionDocumentosEnum.TRABAJO.getValue()%>
	};
	var txtNivel = '<spring:message code="label.nivel"/>';
	var txtNivelDeCalidad = '<spring:message code="label.nivelDeCalidad"/>';
	var txtPorPenalizacion = '<spring:message code="label.porcentajePenalizacion"/>';
</script>
<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/tareaRevisionDatosPagoProveedor.js" type="text/javascript"></script>