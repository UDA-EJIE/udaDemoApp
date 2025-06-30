<%@include file="/WEB-INF/includeTemplate.inc"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.EstadoExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.FaseExpedienteEnum" %>
<%@page import="com.ejie.aa79b.model.enums.MotivosAnulacionEnum" %>
<%@page import="com.ejie.aa79b.common.Constants" %>

<script>
	var idExpediente = '';
	var anyoExpediente = '';
	var tipoExp = '';
	var estadoExp = '';
	
	var datosExp = {
		'tipoExp' : {
			'interpretacion' : '<%=TipoExpedienteEnum.INTERPRETACION.getValue()%>',
			'traduccion' : '<%=TipoExpedienteEnum.TRADUCCION.getValue()%>',
			'revision' : '<%=TipoExpedienteEnum.REVISION.getValue()%>'
		},
		'estadoExp' : {
			'enCurso' : '<%=EstadoExpedienteEnum.EN_CURSO.getValue()%>',
			'cerrado' : '<%=EstadoExpedienteEnum.CERRADO.getValue()%>',
			'anulado' : '<%=EstadoExpedienteEnum.ANULADO.getValue()%>',
			'rechazado' : '<%=EstadoExpedienteEnum.RECHAZADO.getValue()%>'
		},
		'faseExp' : {
			'pdteRevFacturacion' : '<%=FaseExpedienteEnum.PDTE_REV_FACTURACION.getValue()%>',
			'anulado' : '<%=FaseExpedienteEnum.ANULADO.getValue()%>',
			'rechazado' : '<%=FaseExpedienteEnum.RECHAZADO.getValue()%>'
		}
	};
	
	var datosAnulacionExp = {
		'motivosAnulacion' : {
			'otros' : '<%=MotivosAnulacionEnum.OTROS.getValue()%>'
		}
	};
	
	var datosRechazoExp = {
		'motivosRechazo' : {
			'otros' : '<%=Constants.MOTIVO_RECHAZO_OTROS%>'
		}
	};
	
</script>

<script src="${staticsUrl}/aa79b/scripts/aa79bItzulnet/cambioestadoexpediente.js" type="text/javascript"></script>