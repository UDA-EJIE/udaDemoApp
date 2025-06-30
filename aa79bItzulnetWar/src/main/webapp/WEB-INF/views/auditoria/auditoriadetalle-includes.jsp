<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.AuditoriaPorNivelCalidadEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoAuditoriaEnum" %>
<%@page import="com.ejie.aa79b.model.enums.AuditoriaTipoSeccionEnum" %>
<%@page import="com.ejie.aa79b.model.enums.ActivoEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoEnvioEmailEnum" %>
<%@page import="com.ejie.aa79b.model.enums.TipoSubidaEnum" %>

<script type="text/javascript">
	var auditoriaViewData = {
		'porNivelCalidadCampo' : {
			'cero' : '<%=AuditoriaPorNivelCalidadEnum.NIVEL_CERO.getValue()%>',
			'uno' : '<%=AuditoriaPorNivelCalidadEnum.NIVEL_UNO.getValue()%>',
			'tres' : '<%=AuditoriaPorNivelCalidadEnum.NIVEL_TRES.getValue()%>',
			'cinco' : '<%=AuditoriaPorNivelCalidadEnum.NIVEL_CINCO.getValue()%>'
		},
		'estadoAuditoria' : {
			'sinEnviar' : '<%=EstadoAuditoriaEnum.SIN_ENVIAR.getValue()%>',
			'enviada' : '<%=EstadoAuditoriaEnum.ENVIADA.getValue()%>',
			'confirmada' : '<%=EstadoAuditoriaEnum.CONFIRMADA.getValue()%>'
		},
		'tipoSeccion' : {
			'valoracion' : '<%=AuditoriaTipoSeccionEnum.VALORACION.getValue()%>',
			'condicion' : '<%=AuditoriaTipoSeccionEnum.CONDICION.getValue()%>',
			'libre' : '<%=AuditoriaTipoSeccionEnum.LIBRE.getValue()%>'
		},
		'activo' : {
			'si' : '<%=ActivoEnum.SI.getValue()%>',
			'no' : '<%=ActivoEnum.NO.getValue()%>'
		},
		'estadoEnvioEmail' : {
			'ok' : '<%=EstadoEnvioEmailEnum.OK.getValue()%>',
			'sinDestinatarios' : '<%=EstadoEnvioEmailEnum.SIN_DESTINATARIOS.getValue()%>',
			'error' : '<%=EstadoEnvioEmailEnum.ERROR.getValue()%>'
		},
		'tipoSubida' : {
				'docExpediente' : '<%=TipoSubidaEnum.DOC_EXPEDIENTE.getValue()%>',
				'docTareas' : '<%=TipoSubidaEnum.DOC_TAREAS.getValue()%>'
		},
		'estadoActualAuditoria' : '${auditoriaDatos.estado}'
	};
	var labelEncrip='<spring:message code="label.documento.encrip"/>';
	var labelNoEncrip='<spring:message code="label.documento.noencrip"/>';

</script>


<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/auditoriadetalle.js" type="text/javascript"></script>